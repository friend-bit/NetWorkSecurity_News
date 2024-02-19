<template>
  <div style="background-color: white;margin-left: 5%;">
    <div style="font-size: 30px">昵称
      <input style="border: none;border-bottom: 1px solid #000;outline: none;width: 500px;font-size: 30px" v-model="userinfo.nickName">
    </div>
    <div style="display: flex;flex-direction: row;font-size: 30px;margin-top: 20px;align-items: center">头像
      <div class="block" style="width: 200px; margin-left: 15px">
        <el-image :src="userinfo.avatar" :key="userinfo.avatar"></el-image>
      </div>
      <el-upload
          class="upload-demo"
          action="http://localhost:8989/upload"
          name="multipartFile"
          :show-file-list="false"
          :on-success="handleTableFileUpload"
          list-type="picture"
      >
        <div style="margin-left: 20px">
          <el-button size="small" type="primary">更换头像</el-button>
        </div>
      </el-upload>
    </div>
    <div style="font-size: 30px;padding-bottom: 20px">邮箱
      <input style="border: none;border-bottom: 1px solid #000;outline: none;width: 500px;font-size: 30px" v-model="userinfo.email">
    </div>
    <div style="font-size: 30px;padding-bottom: 20px">联系方式
      <input style="border: none;border-bottom: 1px solid #000;outline: none;width: 500px;font-size: 30px" v-model="userinfo.phonenumber">
    </div>
    <div style="font-size: 30px;padding-bottom: 20px;display:flex;align-items: flex-start">
      <span>个性签名</span>
      <textarea style="border: none;border-bottom: 1px solid #000;outline: none;width: 1200px;font-size: 30px;height: 300px" v-model="userinfo.signature"></textarea>
    </div>
    <div style="display: flex;justify-content: center;padding-bottom: 30px">
      <el-button type="success" @click="save">保存信息</el-button>
    </div>

  </div>
</template>
<script>

import request from "@/utils/request";

export default {
  data(){
    return{
      userinfo:{
        userinfo:'',
      },
      fileList:[],
      token:localStorage.getItem('token')
    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      request.put('/user/userInfo').then(res =>{
        if(res.code == '200'){
          this.userinfo=res.data;
        }
      })
    },
    handleTableFileUpload(row,file,fileList){
      // console.log(row,res,file,fileList)
      this.userinfo.avatar=file.response.data
      console.log(this.userinfo.avatar)
    },
    save(){
      request.post('/user/updateUserInfo',this.userinfo).then(res =>{
        if(res.code == '200'){
          this.$notify.success('更新成功')
          this.$router.push('/Personal')
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>