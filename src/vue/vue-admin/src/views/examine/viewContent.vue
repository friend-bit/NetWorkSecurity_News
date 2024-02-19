<template>
  <div>
    <div style="width: 80%;margin-left:10%;">
      <div style="display:flex;justify-content:center"><h1>{{contentData.title}}</h1></div>
      <div style="display:flex;justify-content: center">
        <i class="el-icon-view" ></i><span style="margin-left: 15px">{{contentData.viewCount}}</span>
        <i class="el-icon-date" style="margin-left: 100px"></i><span style="margin-left: 15px">{{contentData.createdataTime}}</span>
        <i class="el-icon-user" style="margin-left: 100px"></i><span style="margin-left: 15px">{{contentData.author}}</span>
      </div>
      <div style="font-size: 25px;margin-top: 20px" v-html="contentData.content"></div>
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";
export default {
  data(){
    return{
      contentData:[]
    };
  },
  created() {
    const id = this.$route.query.id;
    request.get("/content/article/" + id,{
      headers:{
        'token': localStorage.getItem('token')
      }
    }).then(res => {
      this.contentData = res.data;
    });
  },
  methods:{
  }
}
</script>