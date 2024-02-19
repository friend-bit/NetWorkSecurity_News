<template>
  <div>
    <div style="margin-top: 5%;margin-left: 2%;margin-right: 2%;padding:20px 40px;background-color: white;">
      <div style="display: flex;justify-content: space-between;line-height: 60px;">
        <div style="display:flex;flex-direction: row;align-items: center;">
          <img src="@/assets/logo.png" style="width: 120px;">
          <div style="display: flex;flex-direction: row">
            <div style="margin-left: 30px;cursor: pointer;">
              <span  @click="$router.push('/homeView')">首页</span>
            </div>
            <div style="margin-left: 30px;cursor: pointer;">
              <span  @click="$router.push('/policyLaw')">政策法律</span>
            </div>
            <div style="margin-left: 30px;cursor: pointer;">
              <span  @click="$router.push('/hackEvent')">hack事件</span>
            </div>
            <div style="margin-left: 30px;cursor: pointer;">
              <span  @click="$router.push('/securityTechnology')">Security Technology</span>
            </div>
            <div style="margin-left: 30px;cursor: pointer;">
              <span  @click="$router.push('/immediately')">即时新闻</span>
            </div>
          </div>
        </div>
        <div style="display:flex;flex-direction: row;align-items: center;">
          <form style="display: flex;align-items: center;flex: 1;" @submit.prevent="searchAndRedirect">
            <input type="text" style="width: 400px;padding:10px;border-radius: 15px 0 0 15px;border: 1px solid #ccc;outline: none;flex: 1;" v-model="SearchNews" placeholder="请输入搜索内容" >
            <button type="submit" style="padding: 10px 30px;border-radius: 0 15px 15px 0;border: none;cursor: pointer;">搜索</button>
          </form>
        </div>
        <div style="display:flex;flex-direction: row;align-items: center;">
          <div v-if="!isLoggedIn" style="display: flex;flex-direction: row">
            <div style="margin-left: 30px;cursor: pointer;" @click="$router.push('/login')" >登录</div>
            <div style="margin-left: 30px;cursor: pointer;" @click="$router.push('/regedit')" >注册</div>
          </div>
          <el-dropdown v-else>
            <div>
              <img :src="userInfolData.avatar" style="margin-top: 30px;margin-right:10px;border-radius: 50%;height: 40px;width: 40px;">
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><div @click="$router.push('/personal')">个人信息</div></el-dropdown-item>
                <el-dropdown-item><div @click="logout">注销</div></el-dropdown-item>
              </el-dropdown-menu>
            </div>
          </el-dropdown>
        </div>
      </div>
      <hr>
      <div style="display: flex;flex-direction: column;">
        <div style="display: flex;flex-direction: row;justify-content: center"><h1 style="color: #b3009e;cursor: pointer;" @click="goToArticleContent(TopArticle[0].id)">{{TopArticle[0].title}}</h1></div>
        <div style="display: flex;flex-direction: row;justify-content: center;margin-bottom: 10px;color: #007bff;">
          <div style="margin-right: 50px;cursor: pointer;" @click="goToArticleContent(TopArticle[1].id)">{{TopArticle[1].title}}</div>
          <div style="cursor: pointer;" @click="goToArticleContent(TopArticle[2].id)">{{TopArticle[2].title}}</div>
        </div>
      </div>
      <div style="width: 80%;margin-left: 10%">
        <template>
          <el-carousel :interval="4000" type="card" height="300px">
            <el-carousel-item v-for="item in CarouselChart" :key="item" >
              <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;" @click="goToArticleContent(item.id)">
              <span style="text-align: center; margin-top: 10px; position: absolute; bottom: 0; left: 0; width: 100%; background-color: rgba(0, 0, 0, 0.5); color: white; padding: 10px;">{{ item.title }}</span>
            </el-carousel-item>
          </el-carousel>
        </template>
      </div>
      <div style="display: flex;flex-direction: row;align-items: center">
        <div style="text-align: left;font-size: 20px;font-weight:bold;margin-left: 20px"><h3>黑客事件</h3></div>
        <div style="background-color: #cccccc;height: 10px;width:90%;margin-left: 5px;border-radius: 5px;"></div>
      </div>
      <div style="display: flex; flex-wrap: wrap; margin-left: 40px;">
        <div v-for="item in hackNews" :key="item.id" style="flex: 0 0 24%; margin-left: 10px; margin-bottom: 10px; position: relative;">
          <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;" @click="goToArticleContent(item.id)">
          <div style="position: absolute; bottom: 0; left: 0; width: 92.5%; background-color: rgba(0, 0, 0, 0.5); color: white; padding: 10px;">
            {{ item.title }}
          </div>
        </div>
      </div>
      <hr>
      <div style="display: flex;flex-direction: row;align-items: center">
        <div style="text-align: left;font-size: 20px;font-weight:bold;margin-left: 20px"><h3>热榜事件</h3></div>
        <div style="background-color: #cccccc;height: 10px;width:90%;margin-left: 5px;border-radius: 5px;"></div>
      </div>
      <div style="display: flex; flex-direction: row; flex-wrap: wrap; width: 100%; height: 100%;">
        <div v-for="item in hotArticleData" :key="item.id" style="margin: 10px">
          <div style="width: 280px; height: 100%; display: flex; flex-direction: column; align-items: center; background-color: white;margin-left: 20%;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);" @click="goToArticleContent(item.id)">
            <div style="height: 150px; width: 250px; margin: 10px; margin-top: 20px;">
              <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">
            </div>
            <div style="height: 70px; width: 75%; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical;margin-bottom: 10px">
              {{item.summary}}
            </div>
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
      userInfolData:[],
      index:0,
      SearchNews:'',
      CarouselChart: [],
      CarouselData:[],
      selData:[],
      searchData:[],
      hackNews:[],
      hotArticleData: [],
      TopArticle:[],
    };
  },
  async created() {
    await this.load();
  },
  methods:{
    load(){
      request.put('/user/userInfo',{},{
        headers:{
          'token': localStorage.getItem('token')
        }
      }).then(res =>{
        if(res.code == '200'){
          this.userInfolData=res.data;
        }
      }),
      request.get('/article/TopArticle').then(res =>{
        if(res.code == '200'){
          this.TopArticle=res.data;
          console.log(this.TopArticle)
        }
      })
      request.get('/article/CarouselChart').then(res =>{
        if(res.code == '200'){
          this.CarouselChart=res.data;
        }
      })
      request.get('/article/hackArticle?pageNum=1&pageSize=8').then(res =>{
        if(res.code == '200'){
          this.hackNews=res.data;
        }
      })
      request.get('/article/hotArticleList').then(res =>{
        this.hotArticleData=res.data;
      })
    },
    logout(){
      request.post('/logout').then(res =>{
        Cookies.remove('token')
        this.$router.push('/login')
      })
    },
    searchAndRedirect() {
      if (this.SearchNews.trim() !== '') {
        // If SearchNews is not empty, navigate to SearchNews with the search parameter
        this.$router.push('/SearchNews?SearchNews=' + this.SearchNews);
      } else {
        // If SearchNews is empty, navigate to SearchNewsIndex
        this.$router.push('/SearchNewsIndex');
      }
    },
    goToArticleContent(id){
      this.$router.push('/articleContent/'+id);
    },
  }
}
</script>