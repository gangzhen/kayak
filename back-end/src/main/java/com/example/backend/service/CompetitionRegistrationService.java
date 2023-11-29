package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.common.enums.ReviewEnum;
import com.example.backend.controller.request.RegistrationCondition;
import com.example.backend.controller.response.AthleteDetailResponse;
import com.example.backend.controller.response.YearCompetitionItemResponse;
import com.example.backend.entity.Registration;
import com.example.backend.exception.ServiceException;
import com.example.backend.mapper.RegistrationMapper;
import com.example.backend.utils.CustomDateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompetitionRegistrationService extends ServiceImpl<RegistrationMapper, Registration> {

    @Resource
    RegionalCompetitionService regionalCompetitionService;
    @Resource
    UserService userService;

    public void registration(Registration registration) {
        checkRegistration(registration);
        //根据报名人员ID查询详情
        if (registration.getAthleteId() == null || registration.getAthleteId() <= 0) {
            AthleteDetailResponse athleteDetail = searchAthleteDetail(registration.getAthleteIdNumber(), registration.getYear());
            registration.setAthleteId(athleteDetail.getId());
        }
        registration.setProgress(ReviewEnum.PENDING.getCode());
        registration.setProgressDes(ReviewEnum.PENDING.getDes());

        // 避免重复报名
        QueryWrapper<Registration> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("athlete_id", registration.getAthleteId())
                .eq("competition_id", registration.getCompetitionId())
                .in("progress", ReviewEnum.PASS.getCode(), ReviewEnum.PENDING.getCode());

        List<Registration> list = list(queryWrapper);
        if (!list.isEmpty()) {
            throw new ServiceException("请勿重复报名");
        }
        //入库
        save(registration);
    }

    public void cancel(Integer id) {
        Registration queryRegistration = getById(id);
        if (queryRegistration.getId() == null) {
            throw new ServiceException("非法删除");
        }
        if (ReviewEnum.PENDING.getCode().equals(queryRegistration.getProgress())) {
            removeById(id);
        } else {
            throw new ServiceException("非法删除");
        }
    }

    public Page<Registration> searchCompetitionRegistration(RegistrationCondition registrationCondition) {
        QueryWrapper<Registration> queryWrapper = new QueryWrapper<>();
        if (registrationCondition.getYear() > 0) {
            queryWrapper.eq("year", registrationCondition.getYear());
        } else {
            queryWrapper.eq("year", CustomDateUtils.getCurrentYear());
        }
        if (!StrUtil.isBlank(registrationCondition.getRegion())) {
            queryWrapper.eq("region", registrationCondition.getRegion());
        }
        if (!StrUtil.isBlank(registrationCondition.getReview())) {
            queryWrapper.eq("progress", registrationCondition.getReview());
        }
        return this.page(new Page<>(registrationCondition.getPage(), registrationCondition.getPageSize()), queryWrapper);
    }

    public void review(Registration registration) {
        checkReviewRegistration(registration);
        Registration queryRegistration = getById(registration.getId());
        if (queryRegistration.getId() == null) {
            throw new ServiceException("审核信息为非法值");
        }
        ReviewEnum status = ReviewEnum.getStatus(registration.getProgress());
        if (status == null) {
            throw new ServiceException("审核码出错");
        }
        if (ReviewEnum.FAIL.getCode().equals(status.getCode())) {
            queryRegistration.setReason(registration.getReason());
        } else if (ReviewEnum.PASS.getCode().equals(status.getCode())) {
            queryRegistration.setReason("审核通过");
        }
        queryRegistration.setProgress(status.getCode());
        queryRegistration.setProgressDes(status.getDes());
        updateById(queryRegistration);
    }

    public List<YearCompetitionItemResponse> searchYearCompetitions(Integer year) {
        return regionalCompetitionService.searchYearCompetitions(year);
    }

    public AthleteDetailResponse searchAthleteDetail(Integer id) {
        return userService.searchAthleteDetail(id);
    }

    public AthleteDetailResponse searchAthleteDetail(String idNumber, Integer year) {
        return userService.searchAthleteDetail(idNumber, year);
    }

    private void checkRegistration(Registration registration) {
        if (StrUtil.isBlank(registration.getAthleteIdNumber()) || StrUtil.isBlank(registration.getAthleteName()) || StrUtil.isBlank(registration.getCompetition()) || StrUtil.isBlank(registration.getLevel()) || StrUtil.isBlank(registration.getRegion()) || registration.getYear() == null || registration.getYear() <= 0) {
            throw new ServiceException("填报信息有空值");
        }
    }

    private void checkReviewRegistration(Registration registration) {
        if (registration.getId() == null || registration.getYear() <= 0) {
            throw new ServiceException("审核信息为非法值");
        }
    }
}
