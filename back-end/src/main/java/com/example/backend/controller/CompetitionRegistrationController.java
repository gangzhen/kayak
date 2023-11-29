package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.common.encapsulation.Result;
import com.example.backend.controller.request.RegistrationCondition;
import com.example.backend.controller.response.AthleteDetailResponse;
import com.example.backend.controller.response.YearCompetitionItemResponse;
import com.example.backend.entity.Registration;
import com.example.backend.service.CompetitionRegistrationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/competition-registration")
public class CompetitionRegistrationController {

    @Resource
    CompetitionRegistrationService competitionRegistrationService;

    @PostMapping("/registration")
    public Result registration(@RequestBody Registration registration) {
        competitionRegistrationService.registration(registration);
        return Result.success("报名成功");
    }

    @DeleteMapping("/cancel/{id}")
    public Result cancel(@PathVariable Integer id) {
        competitionRegistrationService.cancel(id);
        return Result.success("取消报名成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody RegistrationCondition registrationCondition) {
        Page<Registration> pageList = competitionRegistrationService.searchCompetitionRegistration(registrationCondition);
        return Result.success(pageList);
    }

    @PostMapping("/review")
    public Result review(@RequestBody Registration registration) {
        competitionRegistrationService.review(registration);
        return Result.success("审核成功");
    }

    @GetMapping("/search-competitions/{year}")
    public Result competitions(@PathVariable(name = "year") Integer year) {
        List<YearCompetitionItemResponse> competitions = competitionRegistrationService.searchYearCompetitions(year);
        return Result.success(competitions);
    }

    @GetMapping("/search-athlete-detail/{id}")
    public Result athleteDetail(@PathVariable(name = "id") Integer id) {
        AthleteDetailResponse athleteDetail = competitionRegistrationService.searchAthleteDetail(id);
        return Result.success(athleteDetail);
    }
}
