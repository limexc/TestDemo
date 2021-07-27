<template>
  <div id="myChart" :style="{width: '800px', height: '600px'}"></div>
</template>
<script>
export default {
  name: 'Echarts',
  mounted(){
    // 基于准备好的dom，初始化echarts实例
    let myChart = this.$echarts.init(document.getElementById('myChart'))
    // 绘制图表
    myChart.setOption({
      title: {
        text: '水果销量统计',
        left: 'center',
        top: 20,
        textStyle: {
          color: '#555555'
        }
      },
      tooltip: {},
      xAxis: {
        data: [
          "苹果",
          "香蕉",
          "橘子",
          "火龙果",
          "葡萄",
          "西瓜"
        ]
      },
      yAxis: {},
      series: [{
        name: '销量',
        type: 'bar',
        data: [
          {
            value: 333,
            itemStyle: {
              color: "#3fb1e3"
            }
          },
          {
            value: 133,
            itemStyle: {
              color: "#c4ebad"
            }
          },
          {
            value: 99,
            itemStyle: {
              color: "#c4ebad"
            }
          },
          {
            value: 222,
            itemStyle: {
              color: "#6be6c1"
            }
          },
          {
            value: 399,
            itemStyle: {
              color: "#3fb1e3"
            }
          },
          {
            value: 123,
            itemStyle: {
              color: "#c4ebad"
            }
          }
        ]
      }]
    });
  }
}
</script>