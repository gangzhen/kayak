package com.example.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum RankingLevelPointsEnum {

    NULL("zero", 0, null, 0, null, new int[]{}, 0),

    A2000_1_1(LevelEnum.A2000.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 2000),
    A2000_1_2(LevelEnum.A2000.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 1500),
    A2000_1_3(LevelEnum.A2000.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 1100),
    A2000_1_4(LevelEnum.A2000.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 800),
    A2000_1_5(LevelEnum.A2000.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 500),
    A2000_1_6(LevelEnum.A2000.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 300),

    A2000_2_1(LevelEnum.A2000.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 2000),
    A2000_2_2(LevelEnum.A2000.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 1500),
    A2000_2_3(LevelEnum.A2000.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 1100),
    A2000_2_4(LevelEnum.A2000.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 800),
    A2000_2_5(LevelEnum.A2000.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 500),


    A1600_1_1(LevelEnum.A1600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 1600),
    A1600_1_2(LevelEnum.A1600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 1400),
    A1600_1_3(LevelEnum.A1600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 1000),
    A1600_1_4(LevelEnum.A1600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 750),
    A1600_1_5(LevelEnum.A1600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 350),
    A1600_1_6(LevelEnum.A1600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 150),

    A1600_2_1(LevelEnum.A1600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 1600),
    A1600_2_2(LevelEnum.A1600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 1200),
    A1600_2_3(LevelEnum.A1600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 800),
    A1600_2_4(LevelEnum.A1600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 500),
    A1600_2_5(LevelEnum.A1600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 300),


    A1200_1_1(LevelEnum.A1200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 1200),
    A1200_1_2(LevelEnum.A1200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 1000),
    A1200_1_3(LevelEnum.A1200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 700),
    A1200_1_4(LevelEnum.A1200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 450),
    A1200_1_5(LevelEnum.A1200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 290),
    A1200_1_6(LevelEnum.A1200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 100),

    A1200_2_1(LevelEnum.A1200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 1200),
    A1200_2_2(LevelEnum.A1200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 900),
    A1200_2_3(LevelEnum.A1200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 600),
    A1200_2_4(LevelEnum.A1200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 400),
    A1200_2_5(LevelEnum.A1200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 200),


    A900_1_1(LevelEnum.A900.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 900),
    A900_1_2(LevelEnum.A900.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 630),
    A900_1_3(LevelEnum.A900.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 450),
    A900_1_4(LevelEnum.A900.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 270),
    A900_1_5(LevelEnum.A900.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 180),
    A900_1_6(LevelEnum.A900.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 90),

    A900_2_1(LevelEnum.A900.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 900),
    A900_2_2(LevelEnum.A900.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 630),
    A900_2_3(LevelEnum.A900.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 450),
    A900_2_4(LevelEnum.A900.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 225),
    A900_2_5(LevelEnum.A900.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 90),


    B800_1_1(LevelEnum.B800.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 800),
    B800_1_2(LevelEnum.B800.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 560),
    B800_1_3(LevelEnum.B800.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 400),
    B800_1_4(LevelEnum.B800.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 240),
    B800_1_5(LevelEnum.B800.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 160),
    B800_1_6(LevelEnum.B800.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 80),

    B800_2_1(LevelEnum.B800.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 800),
    B800_2_2(LevelEnum.B800.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 560),
    B800_2_3(LevelEnum.B800.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 400),
    B800_2_4(LevelEnum.B800.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 200),
    B800_2_5(LevelEnum.B800.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 80),


    B600_1_1(LevelEnum.B600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 600),
    B600_1_2(LevelEnum.B600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 420),
    B600_1_3(LevelEnum.B600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 300),
    B600_1_4(LevelEnum.B600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 180),
    B600_1_5(LevelEnum.B600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 120),
    B600_1_6(LevelEnum.B600.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 60),

    B600_2_1(LevelEnum.B600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 600),
    B600_2_2(LevelEnum.B600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 420),
    B600_2_3(LevelEnum.B600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 300),
    B600_2_4(LevelEnum.B600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 150),
    B600_2_5(LevelEnum.B600.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 60),


    B500_1_1(LevelEnum.B500.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 500),
    B500_1_2(LevelEnum.B500.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 350),
    B500_1_3(LevelEnum.B500.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 250),
    B500_1_4(LevelEnum.B500.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 150),
    B500_1_5(LevelEnum.B500.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 100),
    B500_1_6(LevelEnum.B500.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 50),


    B500_2_1(LevelEnum.B500.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 500),
    B500_2_2(LevelEnum.B500.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 250),
    B500_2_3(LevelEnum.B500.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 250),
    B500_2_4(LevelEnum.B500.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 125),
    B500_2_5(LevelEnum.B500.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 50),


    C400_1_1(LevelEnum.C400.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 400),
    C400_1_2(LevelEnum.C400.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 280),
    C400_1_3(LevelEnum.C400.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 200),
    C400_1_4(LevelEnum.C400.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 120),
    C400_1_5(LevelEnum.C400.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 80),
    C400_1_6(LevelEnum.C400.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 40),

    C400_2_1(LevelEnum.C400.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 400),
    C400_2_2(LevelEnum.C400.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 280),
    C400_2_3(LevelEnum.C400.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 200),
    C400_2_4(LevelEnum.C400.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 100),
    C400_2_5(LevelEnum.C400.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 40),


    C200_1_1(LevelEnum.C200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 200),
    C200_1_2(LevelEnum.C200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 140),
    C200_1_3(LevelEnum.C200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 100),
    C200_1_4(LevelEnum.C200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 60),
    C200_1_5(LevelEnum.C200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 40),
    C200_1_6(LevelEnum.C200.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 20),

    C200_2_1(LevelEnum.C200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 200),
    C200_2_2(LevelEnum.C200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 140),
    C200_2_3(LevelEnum.C200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 100),
    C200_2_4(LevelEnum.C200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 50),
    C200_2_5(LevelEnum.C200.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 20),


    C100_1_1(LevelEnum.C100.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 100),
    C100_1_2(LevelEnum.C100.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 70),
    C100_1_3(LevelEnum.C100.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 50),
    C100_1_4(LevelEnum.C100.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 30),
    C100_1_5(LevelEnum.C100.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 20),
    C100_1_6(LevelEnum.C100.getCode(), TotalCodeEnum.ONE.getCode(), TotalCodeEnum.ONE.getDes(), RankingCodeEnum.SIX.getCode(), RankingCodeEnum.SIX.getDes(), RankingCodeEnum.SIX.getRankingRange(), 10),

    C100_2_1(LevelEnum.C100.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.ONE.getCode(), RankingCodeEnum.ONE.getDes(), RankingCodeEnum.ONE.getRankingRange(), 100),
    C100_2_2(LevelEnum.C100.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getCode(), RankingCodeEnum.TWO.getDes(), RankingCodeEnum.TWO.getRankingRange(), 70),
    C100_2_3(LevelEnum.C100.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.THREE.getCode(), RankingCodeEnum.THREE.getDes(), RankingCodeEnum.THREE.getRankingRange(), 50),
    C100_2_4(LevelEnum.C100.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FOUR.getCode(), RankingCodeEnum.FOUR.getDes(), RankingCodeEnum.FOUR.getRankingRange(), 25),
    C100_2_5(LevelEnum.C100.getCode(), TotalCodeEnum.TWO.getCode(), TotalCodeEnum.TWO.getDes(), RankingCodeEnum.FIVE.getCode(), RankingCodeEnum.FIVE.getDes(), RankingCodeEnum.FIVE.getRankingRange(), 10),


    ;

    /**
     * 级别code
     */
    private final String code;
    /**
     * 总参赛人数code
     */
    private final int totalCode;
    /**
     * 总参赛人数级别
     */
    private final String totalLevel;
    /**
     * 名次code
     */
    private final int rankingCode;
    /**
     * 名次级别
     */
    private final String rankingLevel;
    /**
     * 名次范围
     */
    private final int[] rankingRange;
    /**
     * 对应积分
     */
    private final int points;

    public static RankingLevelPointsEnum getRankingPoints(String code, int totalCode, int rankingCode) {

        for (RankingLevelPointsEnum item : RankingLevelPointsEnum.values()) {
            if (item.code.equals(code)) {
                // 这里使用code分为两个个得分等级，如果后期需要根据填写名词进行比较可以更改为totalRanking[]的数组进行比较
                if (totalCode == item.getTotalCode()) {
                    // 这里使用code分为六个得分等级，如果后期需要根据填写名词进行比较可以更改为ranking[]的数组进行比较
                    if (rankingCode == item.getRankingCode()) {
                        return item;
                    }
                }
            }
        }
        return NULL;
    }

}
