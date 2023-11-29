package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.controller.request.RegionCondition;
import com.example.backend.controller.response.YearCompetitionItemResponse;
import com.example.backend.entity.Competition;
import com.example.backend.exception.ServiceException;
import com.example.backend.mapper.CompetitionMapper;
import com.example.backend.utils.CustomDateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionalCompetitionService extends ServiceImpl<CompetitionMapper, Competition> {
    public void add(Competition competition) {
        save(competition);
    }

    public void delete(Integer id) {
        removeById(id);
    }

    public void update(Competition competition) {
        updateById(competition);
    }


    public Page<Competition> searchRegionalCompetition(RegionCondition regionCondition) {
        QueryWrapper<Competition> queryWrapper = new QueryWrapper<>();
        if (regionCondition.getYear() > 0) {
            queryWrapper.eq("year", regionCondition.getYear());
        } else {
            queryWrapper.eq("year", CustomDateUtils.getCurrentYear());
        }
        if (!StrUtil.isBlank(regionCondition.getRegion())) {
            queryWrapper.eq("region", regionCondition.getRegion());
        }
        return this.page(new Page<>(regionCondition.getPage(), regionCondition.getPageSize()), queryWrapper);
    }


    public List<YearCompetitionItemResponse> searchYearCompetitions(Integer year) {
        QueryWrapper<Competition> queryWrapper = new QueryWrapper<>();
        if (year > 0) {
            queryWrapper.eq("year", year);
        } else {
            throw new ServiceException("年份为空");
        }
        List<Competition> competitions = this.list(queryWrapper);
        return competitions.stream().map(item -> new YearCompetitionItemResponse(item.getId(), item.getCompetition(), item.getLevel(), item.getRegion(), item.getYear())).collect(Collectors.toList());
    }
}
