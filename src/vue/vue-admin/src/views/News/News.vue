<template>
  <div>
    <div style="margin: 20px 20px;">
      <el-input  v-model="params.id" style="width: 240px" placeholder="请输入ID" ></el-input>
      <el-input  v-model="params.serial_number" style="width: 240px; margin-left: 20px" placeholder="请输入标题" ></el-input>
      <el-button @click="load"  style="margin-left: 20px" type="primary" ><i class="el-icon-search"></i> 搜索 </el-button>
      <el-button @click="reset" style="margin-left: 20px" type="primary" ><i class="el-icon-refresh"></i> 重置 </el-button>
    </div>
    <el-table :data="NewsData" stripe>
      <el-table-column prop="id" label="ID" width="50px"></el-table-column>
      <el-table-column prop="title" label="标题">
        <template v-slot="scope">
          <div style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
            {{ scope.row.title }}
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="thumbnail" label="缩略图">
        <template v-slot="scope">
          <el-image :src="scope.row.thumbnail" ></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="summary" label="文章摘要">
        <template v-slot="scope">
          <div style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
            {{ scope.row.summary }}
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="createdataTime" label="创建时间"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editeNews?id='+scope.row.id)">编辑</el-button>
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
  data() {
    return {
      NewsData:[],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 8,
        id: '',
        serial_number: '',
      },
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/content/article/articleList",{params: this.params}).then(res =>{
        if(res.code == 200){
          this.NewsData=res.data.rows;
          this.total=Number(res.data.total);
        }
      })
    },
    reset() {
      this.params={
        pageNum: 1,
        pageSize: 8,
        newsId:'',
        newsTitle: '',
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum
      this.load()
    },
    del(id){
      request.delete('/content/article/'+ id).then(res=>{
        if(res.code == '200'){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
  }
}
</script>