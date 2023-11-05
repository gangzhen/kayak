package com.example.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.common.encapsulation.YearStamp;
import com.example.backend.common.encapsulation.YearUser;
import com.example.backend.controller.response.ChartResponse;
import com.example.backend.entity.UserPoints;
import com.example.backend.mapper.UserPointsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartsService extends ServiceImpl<UserPointsMapper, UserPoints> {

    @Resource
    UserPointsMapper userPointsMapper;

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

    public BigDecimal divide(int a, int b) {
        if (b == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.divide(b1, 2, RoundingMode.HALF_UP);
    }
}
