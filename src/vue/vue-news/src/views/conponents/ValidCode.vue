<template>
  <div class="ValiCode disabled-select" style="width: 100%;height: 100%" @click="refreshCode">
    <span v-for="(item,index) in codeList" :key="index" :style="getStyle(item)">{{item.code}}</span>
  </div>
</template>

<script>
export default {
  data(){
    return{
      length:4,
      codeList:[]
    }
  },
  mounted() {
    this.createdCode()
  },
  methods:{
    refreshCode(){
      this.createdCode()
    },
    createdCode(){
      let len=this.length,
          codeList=[],
          chars='ABCDEFGHIJKLMNOPQRSTURWXYZabcdefghijklmnopqrstuvwxyz1234567890',
          charsLen=chars.length
      for (let i=0;i<len;i++){
        let rgb=[Math.round(Math.round()*220),Math.round(Math.round()*240),Math.round(Math.round()*200)]
        codeList.push({
          code:chars.charAt(Math.floor(Math.random()*charsLen)),
          color:`rgb(${rgb})`,
          // padding:`${[Math.floor(Math.random()*10)]}px`,
          transform:`rotate(${Math.floor(Math.random()*90)-Math.floor(Math.random()*90)}deg)`
        })
      }
      this.codeList=codeList
      this.$emit('update:value',codeList.map(item=>item.code).join(''))
    },
    getStyle(data){
      return `color:${data.color};font-size:${data.fontSize};padding:${data.padding};transform:${data.transform}`
    }
  }
}
</script>
<style>
.ValiCode{
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
.ValiCode span{
  display: inline-block;
  font-size: 18px;
}
</style>