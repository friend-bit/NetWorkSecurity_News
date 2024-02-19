<template>
  <div>
    <div style="margin: 20px 20px;">
      <el-input  v-model="params.newsId" style="width: 240px" placeholder="请输入ID" ></el-input>
      <el-input  v-model="params.newsTitle" style="width: 240px; margin-left: 20px" placeholder="请输入标题" ></el-input>
      <el-button style="margin-left: 20px" type="primary" ><i class="el-icon-search"></i> 搜索 </el-button>
      <el-button style="margin-left: 20px" type="primary" ><i class="el-icon-refresh"></i> 重置 </el-button>
    </div>
    <el-table :data="commentData" stripe>
      <el-table-column prop="id" label="ID" width="50px"></el-table-column>
      <el-table-column prop="content" label="内容"></el-table-column>
      <el-table-column prop="articleId" label="文章ID" width="150px"></el-table-column>
      <el-table-column prop="rootId" label="根ID" width="150px"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-popconfirm style="margin-left: 5px" title="确认通过吗？" @confirm="adopt(scope.row.id)">
            <el-button type="danger" slot="reference">通过</el-button>
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
      commentData:[],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 8,
        commentId: '',
        commentTitle: '',
      },
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/comment/commenttExamine",{params: this.params}).then(res =>{
        if(res.code == 200){
          this.commentData=res.data
          this.total=Number(res.data.total);
        }
        console.log(this.commentData)
      })
    },
    reset() {
      this.params={
        pageNum: 1,
        pageSize: 8,
        commentId: '',
        commentTitle: '',
      }
      this.load()
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum
      this.load()
    },
    adopt(id){
      request.put("/comment/examineContent/"+id).then(res=>{
        if(res.code == '200'){
          this.$notify.success('通过')
          this.load()
        }
      })
    },
  }
}
</script>