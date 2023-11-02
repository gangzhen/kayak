package com.example.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.common.RankingLevelPointsEnum;
import com.example.backend.common.YearStamp;
import com.example.backend.common.YearUser;
import com.example.backend.controller.request.Condition;
import com.example.backend.controller.request.ConditionItem;
import com.example.backend.controller.response.ChartResponse;
import com.example.backend.entity.UserPoints;
import com.example.backend.mapper.UserPointsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserPointsService extends ServiceImpl<UserPointsMapper, UserPoints> {

    @Resource
    UserPointsMapper userPointsMapper;

    public Page<UserPoints> searchUserPoints(ConditionItem conditionItem) {
        QueryWrapper<UserPoints> queryWrapper = new QueryWrapper<>();
        if (conditionItem.getName() != null) {
            queryWrapper.like("name", conditionItem.getName());
        }
        return this.page(new Page<>(conditionItem.getPage(), conditionItem.getPageSize()), queryWrapper);
    }

    public Page<UserPoints> searchUserSumPoints(Condition condition) {
        QueryWrapper<UserPoints> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "gender", "age", "id_number", "region", "IFNULL(sum(points),0) as 'points'")
                .groupBy("name", "gender", "age", "id_number", "region");
        if (condition.getName() != null) {
            queryWrapper.like("name", condition.getName());
        }
        if (condition.getGender() != null) {
            queryWrapper.like("gender", condition.getGender());
        }
        if (condition.getAge() != 0) {
            queryWrapper.like("age", condition.getAge());
        }
        if (condition.getYear() != 0) {
            queryWrapper.like("year", condition.getYear());
        }
        if (condition.getRegion() != null) {
            queryWrapper.like("region", condition.getRegion());
        }
        if (condition.getSort() == null || condition.getSort().equals("htl")) {
            queryWrapper.orderByDesc("points");
        } else if (condition.getSort().equals("lth")) {
            queryWrapper.orderByAsc("points");
        }
        Page<UserPoints> result = this.page(new Page<>(condition.getPage(), condition.getPageSize()), queryWrapper);
        int rank =  (condition.getPage() - 1) * condition.getPageSize() + 1;

        for (int i = 0; i < result.getRecords().size(); i++) {
            result.getRecords().get(i).setRank(rank);
            rank++;
        }
        return result;

    }

    public List<UserPoints> searchYears() {
        QueryWrapper<UserPoints> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("year").groupBy("year").orderByAsc("year");
        return this.list(queryWrapper);
    }

    public YearStamp calYearTimestamp(int year) {
        long startTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(year + "-01-01 00:00:00", new ParsePosition(0)).getTime() / 1000;
        long endTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(year + "-12-31 23:59:59", new ParsePosition(0)).getTime() / 1000;
        return new YearStamp(startTime, endTime);
    }

    public List<UserPoints> searchIdNumberGroupByYear(int year) {
        QueryWrapper<UserPoints> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id_number")
                .eq("year", year)
                .isNotNull("points")
                .groupBy("id_number");
        return this.list(queryWrapper);
    }

    public Map<Integer, YearUser> searchCompareData() {
        Map<Integer, List<UserPoints>> yearUsersMap = new HashMap<>();
        List<UserPoints> years = this.searchYears();

        Map<Integer, YearUser> twoYearMap = new HashMap<>();
        for (int i = 0; i < years.size(); i++) {

            UserPoints year = years.get(i);
            List<UserPoints> userPoints = searchIdNumberGroupByYear(year.getYear());
            yearUsersMap.put(year.getYear(), userPoints);

            YearUser yearUser = new YearUser();
            yearUser.setCurrentYearUser(yearUsersMap.get(year.getYear()));
            if (i == 0) {
                yearUser.setLastYearUser(new ArrayList<>());
            } else {
                yearUser.setLastYearUser(yearUsersMap.get(year.getYear() - 1));
            }
            twoYearMap.put(year.getYear(), yearUser);

        }
        return twoYearMap;

    }

    public BigDecimal divide(int a, int b) {
        if (b == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.divide(b1, 2, RoundingMode.HALF_UP);
    }

    public int compareStayNum(YearUser yearUser) {
        yearUser.getCurrentYearUser().retainAll(yearUser.getLastYearUser());
        return yearUser.getCurrentYearUser().size();
    }

    public double compareStayRate(YearUser yearUser) {
        int lastNum = yearUser.getLastYearUser().size();
        int sameNum = compareStayNum(yearUser);

        return divide(sameNum, lastNum).doubleValue();
    }

    public int compareEntryNum(YearUser yearUser) {
        yearUser.getLastYearUser().retainAll(yearUser.getCurrentYearUser());
        return yearUser.getCurrentYearUser().size() - yearUser.getLastYearUser().size();
    }

    public double compareEntryRate(YearUser yearUser) {
        int currentNum = yearUser.getCurrentYearUser().size();
        int newNum = compareEntryNum(yearUser);

        return divide(newNum, currentNum).doubleValue();
    }

    public ChartResponse searchStayChart() {

        Map<Integer, YearUser> twoYearMap = searchCompareData();

        List<String> xData = new ArrayList<>();
        List<String> yData = new ArrayList<>();

        twoYearMap.forEach((key, item) -> {
            xData.add(key.toString());
            yData.add(String.valueOf(compareStayNum(item)));
        });

        return new ChartResponse(xData, yData);
    }

    public ChartResponse searchStayRateChart() {
        Map<Integer, YearUser> twoYearMap = searchCompareData();

        List<String> xData = new ArrayList<>();
        List<String> yData = new ArrayList<>();

        twoYearMap.forEach((key, item) -> {
            xData.add(key.toString());
            yData.add(String.valueOf(compareStayRate(item)));
        });

        return new ChartResponse(xData, yData);
    }

    public ChartResponse searchEntryChart() {
        Map<Integer, YearUser> twoYearMap = searchCompareData();

        List<String> xData = new ArrayList<>();
        List<String> yData = new ArrayList<>();

        twoYearMap.forEach((key, item) -> {
            xData.add(key.toString());
            yData.add(String.valueOf(compareEntryNum(item)));
        });

        return new ChartResponse(xData, yData);
    }

    public ChartResponse searchEntryRateChart() {
        Map<Integer, YearUser> twoYearMap = searchCompareData();

        List<String> xData = new ArrayList<>();
        List<String> yData = new ArrayList<>();

        twoYearMap.forEach((key, item) -> {
            xData.add(key.toString());
            yData.add(String.valueOf(compareEntryRate(item)));
        });

        return new ChartResponse(xData, yData);
    }

    public UserPoints setUserPointValue(UserPoints userPoints) {
        RankingLevelPointsEnum rankingPoints = RankingLevelPointsEnum.getRankingPoints(userPoints.getLevel(), userPoints.getTotalCode(), userPoints.getRankingCode());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        userPoints.setPoints((long) rankingPoints.getPoints());
        userPoints.setTotalCode(rankingPoints.getTotalCode());
        userPoints.setTotalLevel(rankingPoints.getTotalLevel());
        userPoints.setRankingCode(rankingPoints.getRankingCode());
        userPoints.setRankingLevel(rankingPoints.getRankingLevel());
        userPoints.setYear(calendar.get(Calendar.YEAR));
        return userPoints;
    }

    public void add(UserPoints userPoints) {
        userPoints = setUserPointValue(userPoints);
        save(userPoints);
    }

    public void update(UserPoints userPoints) {
        userPoints = setUserPointValue(userPoints);
        updateById(userPoints);
    }
}
