<script>

export default {
  name: "ChartsStayView",
  data() {
    return {
      searchForm: {},
      chartXData: ['2013', '2014', '2015', '2016', '2017', '2018', '2019'],
      chartYData: [10, 52, 200, 334, 390, 330, 220],
    }
  },
  created() {
    this.onSearch();
  },
  methods: {

    onSearch() {
      //TODO 根据条件查询图表数据
      this.$http.get("/points/stay-chart").then(res => {
        this.chartXData = res.data.xdata;
        this.chartYData = res.data.ydata;
        this.drawChart();
      })
    },

    drawChart() {
      var chartDom = document.getElementById('chartsStay');
      var myChart = this.$echarts.init(chartDom);
      var option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: this.chartXData,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: 'Direct',
            type: 'bar',
            barWidth: '60%',
            itemStyle: {
              normal: {
                color: '#7279ee'
              }
            },
            data: this.chartYData
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

<template>
  <div>
    <div class="p-charts">
      <div class="p-charts-chart" id="chartsStay"></div>
    </div>
  </div>
</template>

<style scoped>

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