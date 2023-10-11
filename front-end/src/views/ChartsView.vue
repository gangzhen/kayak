<template>
  <div>

    <el-form :inline="true" :model="searchForm">
      <el-form-item label="比赛项目:">
        <el-select v-model="searchForm.item" placeholder="请选择比赛项目" @change="handleItemChange">
          <el-option label="项目一" value="item1"></el-option>
          <el-option label="项目二" value="item2"></el-option>
          <el-option label="项目三" value="item3"></el-option>
          <el-option label="项目四" value="item4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期:">
        <el-date-picker
            v-model="searchForm.date"
            type="date"
            placeholder="选择日期" clearable value-format="timestamp" @change="handleDateChange">
        </el-date-picker>
      </el-form-item>
    </el-form>

    <div class="p-charts">
      <div class="p-charts-chart" id="lineChart"></div>
    </div>
  </div>
</template>

<script>

export default {
  name: "ChartsView",
  data() {
    return {
      searchForm: {
        item: 'item1'
      },
      chartXData: ['2013', '2014', '2015', '2016', '2017', '2018', '2019'],
      chartYData1: [7.3998, 7.3816, 7.3786, 7.4081, 7.3325, 7.3741, 7.4517],
      chartYData2: [0.8, 0.7, 3, 0.8, 0.8, 0.4, 0.5],
    }
  },
  mounted() {
    this.drawLineChart();
  },
  methods: {

    onSearch() {
      //TODO 根据条件查询图表数据
    },

    drawLineChart() {
      const colors = ['#5470c6', '#91cc75'];
      var chartDom = document.getElementById('lineChart');
      var myChart = this.$echarts.init(chartDom);
      var option = {
        color: colors,
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        title: {
          text: '男大学生50米跑平均值、不及格率各年份变化趋势',
          left: 'center',
          y: 'bottom',
          textStyle: {
            //文字颜色
            color: '#000',
            //字体风格,'normal','italic','oblique'
            fontStyle: 'normal',
            //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
            fontWeight: 'bold',
            //字体系列
            fontFamily: 'sans-serif',
            //字体大小
            fontSize: 18
          }
        },
        grid: {
          right: '8%',
        },
        legend: {
          top: '0%',
          data: ['平均成绩', '不及格率']
        },
        xAxis: [
          {
            type: 'category',
            axisTick: {
              alignWithLabel: true
            },
            // prettier-ignore
            data: this.chartXData
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '平均成绩(s)',
            position: 'left',
            alignTicks: true,
            min: 7.26, // y轴的最小值为0
            max: 7.48, // y轴的最大值为500
            axisLine: {
              show: true,
              lineStyle: {
                color: colors[1]
              }
            },
            axisLabel: {
              formatter: function (value, index) {
                return value.toFixed(2);
              }
            }
          },
          {
            type: 'value',
            name: '不及格率(%)',
            position: 'right',
            alignTicks: true,
            min: 0, // y轴的最小值为0
            max: 3.5, // y轴的最大值为500
            axisLine: {
              show: true,
              lineStyle: {
                color: colors[0]
              }
            },
            axisLabel: {
              formatter: function (value, index) {
                return value.toFixed(2);
              }
            }
          }
        ],
        series: [
          {
            name: '平均成绩',
            type: 'bar',
            yAxisIndex: 0,
            barWidth: 30,
            data: this.chartYData1
          },
          {
            name: '不及格率',
            type: 'line',
            yAxisIndex: 1,
            barWidth: 30,
            data: this.chartYData2
          }
        ]
      };
      myChart.setOption(option);
    },

    handleItemChange() {
      console.log(this.searchForm.item)
      this.onSearch();
    },

    handleDateChange() {
      console.log(this.searchForm.date)
      this.onSearch();
    },

  },
}
</script>

<style>

.el-form {
  margin: 1vh 10px;
}

.el-form-item {
  font-weight: bolder;
}

.el-input__inner {
  width: 180px;
}

.el-date-editor.el-input, .el-date-editor.el-input__inner {
  width: 180px;
}

.p-charts {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 84vh;
  margin-top: 1vh;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
  border-radius: 10px;
}

.p-charts-chart{
  width: 80%;
  height: 80vh;
}
</style>