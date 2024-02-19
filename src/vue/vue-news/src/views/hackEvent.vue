<template>
  <div>
    <div style="margin-bottom: 10px">
      <div style="display: flex;flex-direction: row;margin-left: 40px">
        <div style="width: 600px;">
          <el-carousel :interval="4000" style="width: 100%; height: 100%; ">
            <el-carousel-item v-for="item in CarouselhackNews" :key="item.id" style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: center;">
              <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;" @click="goToArticleContent(item.id)">
              <span style="text-align: center; margin:10px 0px; position: absolute; bottom: 0; left: 0; width: 100%; background-color: rgba(0, 0, 0, 0.5); color: white; ">{{ item.title }}</span>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div style="height: 300px;width: 600px;margin:0px 20px;padding-left: 30px;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">
          <div style="margin-top: 10px" v-for="(item,index) in hackNews" :key="index">
            <span @click="goToArticleContent(item.id)">{{[index+1]}}—{{item.title}}</span>
          </div>
        </div>
      </div>
    </div>
<!--    <div style="display: flex;flex-direction: row;">-->
<!--      <div style="display:flex;flex-direction:column;width: 30%;margin: 0 1.5%;justify-content: center;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">-->
<!--        <span style="margin-bottom: 10px;color: black"  v-for="item in hotArticleData" :key="item.id">{{item.title}}</span>-->
<!--      </div>-->
<!--      <div style="display:flex;flex-direction:column;width: 30%;margin: 0 1.5%;background-color: red;justify-content: center;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">-->
<!--        <span style="margin-bottom: 10px;color: black"  v-for="item in carouselData" :key="item.id">{{item.title}}</span>-->
<!--      </div>-->
<!--      <div style="display:flex;flex-direction:column;width: 30%;margin: 0 1.5%;background-color: orange;justify-content: center;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">-->
<!--        <span style="margin-bottom: 10px;color: black"  v-for="item in carouselData" :key="item.id">{{item.title}}</span>-->
<!--      </div>-->
<!--    </div>-->
    <div style="display: flex;flex-direction: row;align-items: center">
      <div style="text-align: left;font-size: 20px;font-weight:bold;margin-left: 20px"><h3>黑客热榜</h3></div>
      <div style="background-color: white;height: 10px;width:90%;margin-left: 5px;border-radius: 5px;"></div>
    </div>
    <div style="display: flex; flex-direction: row; flex-wrap: wrap; width: 100%; height: 100%;">
      <div v-for="item in hackNews2" :key="item.id">
        <div style="width: 280px; height: 100%; display: flex; flex-direction: column; align-items: center; background-color: white;margin-left: 10%;">
          <div style="height: 150px; width: 250px; margin: 10px; margin-top: 20px;">
            <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">
          </div>
          <div style="height: 70px; width: 75%; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical;">
            {{item.summary}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";
export default {
  data(){
    return{
      isLoggedIn:Cookies.get('token')?true:false,
      hackNews:[],
      hackNews2:[],
      CarouselhackNews:[],
    };
  },
  created() {
    request.interceptors.request.use(config => {
      config.headers.token = localStorage.getItem('token');
      return config;
    }), err => {
      return Promise.reject(err);
    }
    this.load();
  },
  methods:{
    load(){
      request.get("/article/hackArticle?pageNum=1&pageSize=4").then(res =>{
        if (res.code == '200') {
          this.CarouselhackNews = res.data;
          console.log(this.CarouselhackNews)
        }
      }),
      request.get("/article/hackArticle?pageNum=1&pageSize=10").then(res =>{
        if (res.code == '200') {
          this.hackNews = res.data;
        }
      }),
      request.get("/article/hackArticle?pageNum=2&pageSize=10").then(res =>{
        if (res.code == '200') {
          this.hackNews2 = res.data;
        }
      })
    },
    goToArticleContent(id){
      this.$router.push('/articleContent/'+id);
    },
  }
}
</script>