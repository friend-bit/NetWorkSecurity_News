<template>
  <div>
    <div style="margin: 30px;font-size: 30px">编辑用户</div>
    <el-form :inline="true" :model="userInfo" style="width:80%" label-width="100px">
      <el-form-item label="ID">
        <el-input v-model="userInfo.id"  :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="userInfo.nickName" placeholder="输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="userInfo.phonenumber" placeholder="输入手机号"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="userInfo.sex" >
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save">保存</el-button>
      <el-button type="danger" @click="cancel">取消</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      userInfo: {},
      options: [
        { value: 0, label: '男' },
        { value: 1, label: '女' }
      ]
    };
  },
  created() {
    const id = this.$route.query.id;
    request.get("/user/searchUser/" + id)
        .then(res => {
          console.log("Raw response data:", res.data); // Log the entire response for inspection
          this.userInfo = res.data[0]; // Check if the data is at the expected location
          console.log("User data fetched:", this.userInfo);
        })
        .catch(error => {
          console.error("Error fetching user data:", error);
          // Handle the error appropriately, e.g., show an error message to the user.
        });
  },
  methods: {
    save() {
      request.put('/user/update',this.userInfo).then(res =>{
        if(res.code == '200'){
          this.$notify.success('更新成功')
          this.$router.push('/User')
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    cancel() {
      this.$router.push('/User')
      console.log("Cancel button clicked");
      // You might want to navigate back or perform other actions
    }
  }
};
</script>
