<script>

export default {
  name: "ChartsStayRateView",
  data() {
    return {
      searchForm: {
        item: 'item1'
      },
      chartXData: ['2013', '2014', '2015', '2016', '2017', '2018', '2019'],
      chartYData: [9.3998, 7.3816, 7.3786, 7.4081, 7.3325, 7.3741, 7.4517],
    }
  },
  created() {
    this.onSearch();
  },
  methods: {

    onSearch() {
      //TODO 根据条件查询图表数据
      this.$http.get("/points/stay-rate-chart").then(res => {
        this.chartXData = res.data.xdata;
        this.chartYData = res.data.ydata;
        this.drawChart();
      })
    },

    drawChart() {
      var chartDom = document.getElementById('chartsStayRate');
      var myChart = this.$echarts.init(chartDom);
      var option = {
        xAxis: {
          type: 'category',
          data: this.chartXData
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.chartYData,
            itemStyle: {
              normal: {
                label : {show: true},
                color: '#7279ee'
              }
            },
            type: 'line'
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
  <div class="p-main-charts">
    <div class="p-main-charts-search" v-if="false">
    </div>
    <div class="p-main-charts-display" id="chartsStayRate"></div>
  </div>
</template>

<style scoped>

</style>