<template>
  <div>

    <div style="display:flex;justify-content: space-between; margin: 20px 20px;">
      <div>
        <el-input v-model="params.serial_number" style="width: 400px" placeholder="请输入CVE编号" ></el-input>
        <el-button @click="load" style="margin-left: 20px" type="primary" ><i class="el-icon-search"></i> 搜索 </el-button>
        <el-button @click="reset" style="margin-left: 20px" type="primary" ><i class="el-icon-refresh"></i> 重置 </el-button>
      </div>
      <el-button style="margin-left: 400px" type="danger" @click="$router.push('/AddCNVD')"><i class="el-icon-plus"></i> 添加 </el-button>
    </div>
    <el-table :data="CNVDData" stripe>
      <el-table-column prop="id" label="ID" width="50px"></el-table-column>
      <el-table-column prop="serialNumber" label="编号" width="150px"></el-table-column>
      <el-table-column prop="summary" label="描述">
        <template v-slot="scope">
          <div style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
            {{ scope.row.title }}
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="level" label="级别" width="100px">
        <template v-slot="scope">
          <div style="display: flex; flex-direction: row; align-items: center;">
            <div
                :style="{
          width: '50px',
          height: '10px',
          backgroundColor: scope.row.level === '低' ? 'green' : scope.row.level === '中' ? 'orange' : 'red'
        }"
            ></div>
            <div>{{ scope.row.level }}</div>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="link" label="参考链接">
        <template v-slot="scope">
          <div style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
            <a :href="scope.row.link">
              {{ scope.row.link }}
            </a>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="patch" label="补丁链接">
        <template v-slot="scope">
          <div style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
            <a :href="scope.row.patch">
              {{ scope.row.patch }}
            </a>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="releaseTime" label="公开时间"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editeCNVD?id='+scope.row.id)">编辑</el-button>
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
export default{
  data(){
    return{
      CNVDData:[],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 8,
        serial_number: '',
      },
    }
  },
  created() {
    this.load()
  },
  methods:{
    load() {
      request.get("/cnvd/getcnvdList",{params: this.params}).then(res =>{
        if(res.code == 200){
          this.CNVDData=res.data.rows;
          this.total=Number(res.data.total);
        }
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum
      this.load()
    },
    reset() {
      this.params={
        pageNum: 1,
        pageSize: 8,
        CVEID:'',
        serial_number: '',
      }
      this.load()
    },
    del(id){
      request.delete('/cnvd/'+ id).then(res=>{
        if(res.code == '200'){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
          this.load()
        }
      })
    },
  }
}
</script>