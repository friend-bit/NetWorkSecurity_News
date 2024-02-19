<template>
  <div>
    <div style="margin: 20px 0">
      <el-select class="input" v-model="timeRange" placeholder="请选择" @change="load">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
    </div>
    <el-card>
      <div id="line" style="width: 80%;height: 400px"></div>
    </el-card>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import request  from "@/utils/request";
import * as echarts from 'echarts'
const option = {
  title: {
    text: '发布次数'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['发布量']
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  toolbox: {
    feature: {
      saveAsImage: {}
    }
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '发布量',
      type: 'line',
      stack: 'Total',
      smooth:true,
      data: []
    },
    {
      name: '浏览多增次数',
      type: 'line',
      stack: 'Total',
      smooth:true,
      data: []
    },
  ]
};
export default {
  data(){
    return{
      lineBox:null,
      timeRange:'week',
      options:[
        {label:'最近一周',value:'week'},
        {label:'最近一个月',value:'month'},
        {label:'最近两个月',value:'month2'},
      ]
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      if(!this.lineBox){
        this.lineBox=echarts.init(document.getElementById('line'))
      }
      const apiUrl = `/content/article/lineCharts/${this.timeRange}`;
      request.get(apiUrl).then((res) => {
        this.lineBox.clear();
        option.xAxis.data = res.data.date;
        option.series[0].data = res.data.release;
        this.lineBox.setOption(option);
      });
    }
  }
}
</script>

<style>
.input{
  width: 300px;
}
</style>
