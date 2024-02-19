<template>
  <div>
    <div style="display: flex;justify-content: space-between;height: 80px;line-height: 60px;">
      <div style="display:flex;flex-direction: row;align-items: center;margin-left: 30px;color: white">
        <img src="@/assets/logo.png" style="width: 120px;">
        <div class="title-font">
          <span class="font-style" @click="$router.push('/homeView')">首页</span>
        </div>
        <div class="title-font">
          <span class="font-style" @click="$router.push('/policyLaw')">政策法律</span>
        </div>
        <div class="title-font">
          <span class="font-style" @click="$router.push('/hackEvent')">hack事件</span>
        </div>
        <div class="title-font">
          <span class="font-style" @click="$router.push('/securityTechnology')">Security Technology</span>
        </div>
        <div class="title-font">
          <span class="font-style" @click="$router.push('/immediately')">即时新闻</span>
        </div>
      </div>
      <div style="display:flex;flex-direction: row;align-items: center;">
        <form class="search-form" @submit.prevent="searchAndRedirect">
          <input  type="text" placeholder="请输入搜索关键词" class="search-input" v-model="SearchNews">
          <button type="submit" style="padding: 10px 30px;border-radius: 0 15px 15px 0;border: none;cursor: pointer;">搜索</button>
        </form>
      </div>
      <div style="display:flex;flex-direction: row;align-items: center;margin-right: 20px;">
        <div v-if="!isLoggedIn">
          <div class="title-font" @click="$router.push('/login')">登录</div>
          <div class="title-font" @click="$router.push('/regedit')">注册</div>
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
    <hr />
    <router-view />
  </div>
</template>

<script>

import request from "@/utils/request";
import Cookies from 'js-cookie'
export default {
  name:"Layout.vue",
  data(){
    return{
      isLoggedIn:Cookies.get('token')?true:false,
      userInfolData:[],
      SearchNews: '',
    }
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
      request.put('/user/userInfo',{
          headers:{
            'token': localStorage.getItem('token')
          }
      }).then(res =>{
        if(res.code == '200'){
          this.userInfolData=res.data;
        }
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
    logout(){
      request.post('/logout').then(res =>{
        Cookies.remove('token')
        this.$router.push('/login')
      })
    }
  }
}
</script>
<style>

.title-font{
  display: inline-block;
  margin-left: 30px;
  cursor: pointer;
}

.search-form {
  display: flex;
  align-items: center;
  flex: 1;
}
.search-input {
  width: 400px;
  padding:10px;
  border-radius: 15px 0 0 15px;
  border: 1px solid #ccc;
  outline: none;
  flex: 1;
}

</style>