<script>

export default {
  name: "ChartsEntryView",
  data() {
    return {
      searchForm: {
        item: 'item1'
      },
      chartXData: ['2013', '2014', '2015', '2016', '2017', '2018', '2019'],
      chartYData: [100, 200, 300, 150, 270, 400, 80],
    }
  },
  created() {
    this.onSearch();
  },
  methods: {

    onSearch() {
      //TODO 根据条件查询图表数据
      this.$http.get('/charts/entry').then(res => {
        this.chartXData = res.data.xdata;
        this.chartYData = res.data.ydata;
        this.drawChart();
      })
    },

    drawChart() {
      var chartDom = document.getElementById('chartsEntry');
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
  <div class="p-main-charts">
    <div class="p-main-charts-search" v-show="false">
    </div>
    <div class="p-main-charts-display" id="chartsEntry"></div>
  </div>
</template>

<style scoped>

</style>