<template>
  <div style="height:100vh;overflow: hidden">
    <div style="width: 500px; height: 400px; background-color: white; border-radius: 10px; margin: 150px auto; padding: 50px;">
      <div style="text-align: center; font-size: 30px; font-weight: bold; color: dodgerblue">注册</div>
      <el-form ref="loginForm" :model="user">
        <el-form-item prop="userName">
          <el-input placeholder="请输入用户名" prefix-icon="el-icon-user" size="medium" v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item prop="nickName">
          <el-input placeholder="请输入昵称" prefix-icon="el-icon-user" size="medium" v-model="user.nickName"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="请输入密码" prefix-icon="el-icon-user" size="medium" v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="checkpassword">
          <el-input type="password" placeholder="再次输入密码" prefix-icon="el-icon-user" size="medium" v-model="checkpassword"></el-input>
        </el-form-item>
        <el-form-item>
          <div style="display: flex">
            <el-input prefix-icon="el-icon-circle-check" placeholder="请输入验证码" size="medium" style="flex: 1" v-model="yzm"></el-input>
            <div style="flex: 1">
              <valid-code></valid-code>
            </div>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%;" size="medium" type="primary" @click="regedit">注册</el-button>
        </el-form-item>
        <span style="color: dimgrey;cursor: pointer;" @click="$router.push('/login');">返回登录</span>
      </el-form>
    </div>
  </div>
</template>
<script>

import ValidCode from "@/views/conponents/ValidCode";
import request from "@/utils/request";

export default {
  components:{
    ValidCode
  },
  data(){
    return{
      user:{
        avatar:'1',
      },
      yzm:'',
      checkpassword:'',
    }
  },
  created() {
  },
  methods:{
    regedit(){
      request.post('/user/regedit',this.user).then(res =>{
        console.log(this.user);
        if(res.code=='200'){
          this.$notify.success("注册成功")
          this.$router.push('/login')
        }else{
          this.$notify.success(res.data)
        }
      })
    }
  }
}
</script>