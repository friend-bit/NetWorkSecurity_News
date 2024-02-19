<template>
  <div>
    <div style="margin: 20px 20px;">
      <el-input  v-model="params.userId" style="width: 240px" placeholder="请输入ID" ></el-input>
      <el-input  v-model="params.nickName" style="width: 240px; margin-left: 20px" placeholder="请输入用户名" ></el-input>
      <el-button @click="load"  style="margin-left: 20px" type="primary" ><i class="el-icon-search"></i> 搜索 </el-button>
      <el-button @click="reset" style="margin-left: 20px" type="primary" ><i class="el-icon-refresh"></i> 重置 </el-button>
    </div>
    <el-table :data="UserData" stripe>
      <el-table-column prop="id" label="ID" width="50px"></el-table-column>
      <el-table-column prop="avatar" label="头像">
        <template v-slot="scope">
          <el-image :src="scope.row.avatar" ></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="nickName" label="用户名"></el-table-column>
      <el-table-column prop="phonenumber" label="手机号"></el-table-column>
      <el-table-column prop="sex" label="性别">
        <template slot-scope="scope">
          <span v-if="scope.row.sex == 0">男</span>
          <span v-else-if="scope.row.sex == 1">女</span>
        </template>
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editeUser?id='+scope.row.id)">编辑</el-button>
          <el-popconfirm style="margin-left: 5px" title="确认删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px;">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev,pager,next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>


<script>
import request  from "@/utils/request";
export default {
  data(){
    return {
      UserData:[],
      total:0,
      params:{
        pageNum:1,
        pageSize:8,
        userId: '',
        nickName: '',
      },
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get('/user/userList',{
        params: this.params}).then(res =>{
          if(res.code == 200){
            this.UserData=res.data.rows;
            this.total=Number(res.data.total);
          }
      });
    },
    del(id){
      request.delete('/user/'+ id).then(res=>{
        if(res.code == '200'){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    reset() {
      this.params={
        pageNum: 1,
        pageSize: 8,
        userId:'',
        nickName: '',
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum
      this.load()
    },
  },
}
</script>