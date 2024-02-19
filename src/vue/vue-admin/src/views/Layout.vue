<template>
  <div>
    <!--head area-->
    <div style="display: flex;flex-direction: row;width: 100%;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">
      <div style="height: 90px;line-height: 60px;background-color: white;margin-bottom: 2px;width: 50%;display: flex;align-items: center">
        <img src="@/assets/logo.png" alt="" style="width:100px; position: relative;top: 10px;left: 20px">
        <span style="margin-left: 25px;font-size: 40px">管理系统</span>
      </div>
      <div style="height: 90px;width: 50%;background-color:white;padding-right: 30px">
        <div style="display: flex;flex-direction:row;justify-content: flex-end;margin-top: 20px" v-if="!isLoggedIn">
          <div style="padding-right: 20px" @click="$router.push('/login')">登录</div>
          <div @click="$router.push('/regedit')">注册</div>
        </div>
        <el-dropdown style="display: flex;justify-content: flex-end" v-else>
          <div>
            <img :src="userInfolData.avatar" style="margin-top: 20px;margin-right:20px;border-radius: 50%;height: 50px;width: 50px;">
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item><div @click="logout">注销</div></el-dropdown-item>
            </el-dropdown-menu>
          </div>
        </el-dropdown>
      </div>
    </div>

    <!--side border and body-->
    <div style="display: flex">
      <!--side border navigation-->
      <div style="width: 200px; min-height:calc(100vh - 62px);overflow:  hidden;margin-right: 2px;background-color: white">
        <el-menu :default-active="$route.path === '/' ? $route.path : $route.path.substring(1)" :default-openeds="[1]" router class="el-menu-demo">
          <el-menu-item index="Home">
            <i class="el-icon-house"></i>
            <span>首页</span>
          </el-menu-item>
          <el-menu-item index="writeNews">
            <i class="el-icon-edit-outline"></i>
            <span>写新闻</span>
          </el-menu-item>
          <el-submenu index="admin">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span>系统管理</span>
            </template>
            <el-menu-item index="User">用户管理</el-menu-item>
            <el-menu-item index="News">新闻管理</el-menu-item>
          </el-submenu>
          <el-submenu index="Room">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>内容审核</span>
            </template>
            <el-menu-item index="Content">投稿内容审核</el-menu-item>
            <el-menu-item index="Comment">评论内容审核</el-menu-item>
          </el-submenu>
          <el-submenu index="CNVD">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>CNVD管理</span>
            </template>
            <el-menu-item index="CNVDManage">CNVD管理列表</el-menu-item>
          </el-submenu>
          <el-submenu index="Sensitive">
            <template slot="title">
              <i class="el-icon-house"></i>
              <span>敏感词管理</span>
            </template>
            <el-menu-item index="Sensitive">敏感词管理列表</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
      <!--body-->
      <div style="flex: 1;background-color: white">
        <router-view />
      </div>
    </div>
  </div>
</template>
<script>
import request  from "@/utils/request";
import Cookies from "js-cookie";

export default {
  data(){
    return {

      isLoggedIn:Cookies.get('token')?true:false,
      userinfo:[],
      userInfolData:[],
      fileList:[],
      url:'',
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
      request.get('/getInfo',{
        headers:{
          'token': localStorage.getItem('token')
        }
      }).then(res =>{
        if(res.code == '200'){
          this.userinfo=res.data;
          this.userInfolData=res.data.users;
        }
      })
    },
    logout(){
      request.post('/user/logout').then(res =>{
        Cookies.remove('token')
        this.$router.push('/login')
      })
    },
  }
}
</script>
