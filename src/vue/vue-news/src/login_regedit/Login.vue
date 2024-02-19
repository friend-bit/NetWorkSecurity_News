<template>
  <div>
    <div style="width: 500px; height: 400px; background-color: white; border-radius: 10px; margin: 150px auto; padding: 50px;">
      <div style="margin:30px; text-align: center; font-size: 30px; font-weight: bold; color: dodgerblue">登录</div>
      <el-form :model="user"  ref="loginForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" prefix-icon="el-icon-user" size="medium" v-model="user.userName"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input show-password placeholder="请输入密码" show-password prefix-icon="el-icon-lock" size="medium" v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item>
          <div style="display: flex" >
            <el-input prefix-icon="el-icon-circle-check" placeholder="请输入验证码" size="medium" style="flex: 1" v-model="yzm"></el-input>
            <div style="flex: 1">
              <valid-code></valid-code>
            </div>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" size="medium" type="primary" @click="login">登录</el-button>
        </el-form-item>
        <div style="display: flex;justify-content: center">
          <span style="color: dimgrey;cursor: pointer;" @click="$router.push('/regedit');">前往注册</span>
          <span style="margin: 0px 10px">|</span>
          <span style="color: dimgrey;cursor: pointer;" @click="$router.push('/ForgotPassword')">忘记密码</span>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie';
import ValidCode from "@/views/conponents/ValidCode";
export default {
  components:{
    ValidCode
  },
  data(){
    return{
      user:{},
      yzm:'',
    }
  },
  methods:{
    login(){
      request.post('/login',this.user).then(res =>{
        if(res.code == '200'){
          this.$notify.success("登录成功")
          this.$router.push('/')
          if(res.data!=null){
            Cookies.set('token',res.data.token)
            localStorage.setItem('token',res.data.token)
          }
        }else {
          this.$notify.error(res.data)
        }
      })
    }
  }
}
</script>