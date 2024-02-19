<template>
  <div>

    <div style="margin: 100px 100px 100px 120px;width:1200px;height: 650px;background-color: white;">
      <div style="display:flex;justify-content: center;overflow: visible;">
        <div style="width: 200px; height: 200px; border-radius: 50%; overflow: hidden;background-color: red;margin: -100px">
          <img :src="userInfolData.avatar" style="width: 100%; height: 100%;">
        </div>
      </div>
      <div style="display: flex;flex-direction: row;justify-content: space-around;margin-top: 200px">
        <div>
          <span style="font-size: 25px">昵称:&nbsp</span>
          <span style="font-size: 25px">{{userInfolData.nickName}}</span>
        </div>
        <div>
          <span style="font-size: 25px">生日:&nbsp</span>
          <span style="font-size: 25px">10月18日</span>
        </div>
        <div>
          <span style="font-size: 25px">性别:&nbsp</span>
          <span style="font-size: 25px">{{ userInfolData.sex == 0 ? '男' : '女' }}</span>
        </div>
      </div>

      <div style="display: flex;flex-direction: row;justify-content: space-around;margin-top: 40px">
        <div>
          <span style="font-size: 25px">邮箱:&nbsp</span>
          <span style="font-size: 25px">{{userInfolData.email}}</span>
        </div>
        <div>
          <span style="font-size: 25px">手机:&nbsp</span>
          <span style="font-size: 25px">{{userInfolData.phonenumber}}</span>
        </div>
      </div>
      <div style="margin:50px 80px">
        <div>
          <span style="font-size: 25px">个性签名:</span>
        </div>
        <div style="background-color: #cccccc;height: 250px;text-indent: 2ch;line-height: 40px;overflow: hidden;display: -webkit-box; -webkit-line-clamp: 6; -webkit-box-orient: vertical;">
          {{userInfolData.signature}}
        </div>
      </div>
      <div style="text-align: center;margin-top: 30px">
        <el-button type="primary" @click="modifyPersonal" >修改信息</el-button>
        <el-button type="danger" @click="cancel">取消</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name:"Personal",
  data(){
    return{
      userInfolData: [],
    };
  },
  created(){
    request.interceptors.request.use(config => {
      config.headers.token=localStorage.getItem('token');
      return config;
    }),err=>{
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
    modifyPersonal(){
      this.$router.push('/modifyPersonal');
    }
  }
}
</script>
