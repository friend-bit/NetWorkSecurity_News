<template>
  <div>
    <div style="display: flex;margin:10px 0">
      <el-card style="width: 100%;margin-right: 10px">
        <div slot="header" class="clearfix">
          <span>文件上传下载</span>
        </div>
        <div>
          <el-table>
            <el-table-column label="ID" prop="id"></el-table-column>
            <el-table-column label="用户名" prop="username"></el-table-column>
            <el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="地址" prop="address"></el-table-column>
            <el-table-column label="文件上传">
              <template v-slot="scope">
                <el-upload
                    action="http://localhost:8989/file/upload"
                    :headers="{token:user.token}"
                    :show-file-list="false"
                    :on-success="(row,res,file,fileList) => handleTableFileUpload(scope.row,res,file,fileList)"
                >
                  <el-button size="mini" type="primary">点击上传</el-button>
                </el-upload>
              </template>
            </el-table-column>
            <el-table-column label="文件上传">
              <template v-slot="scope">
                <el-image v-if="scope.row.avatar" :src="scope.row.avatar" style="width: 50px;height: 50px"></el-image>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-card>
    </div>

<!--    <div style="display: flex;margin: 10px 0">-->
<!--      <el-card style="width: 50%;margin-right: 10px">-->
<!--        <div slot="header" class="clearfix">-->
<!--          <span>文件下载</span>-->
<!--        </div>-->
<!--        <div>-->
<!--          <el-upload-->
<!--            action="http://localhost:8989/file/upload"-->
<!--            :headers="{token:user.token}"-->
<!--            list-type="picture"-->
<!--            :on-success="handleFileUpload"-->
<!--          >-->
<!--            <el-button size="mini" type="primary">单文件上传</el-button>-->
<!--          </el-upload>-->
<!--        </div>-->

<!--        <div style="margin:10px 0">-->
<!--          <el-upload-->
<!--              action="http://localhost:8989/file/upload"-->
<!--              :headers="{token:user.token}"-->
<!--              :on-success="handleFileUpload"-->
<!--          >-->
<!--            <el-button size="mini" type="primary">单文件上传</el-button>-->
<!--          </el-upload>-->
<!--        </div>-->
<!--      </el-card>-->
<!--    </div>-->
  </div>
</template>
<script>
export default {
  name:'upload',
  data(){
    return{
      fileList:[],
      user:JSON.parse(localStorage.getItem('honey-user')||'{}')
    }
  },
  methods:{
    handleTableFileUpload(row,res,file,fileList){
      console.log(row,res,file,fileList)
      row.avatar=res.data
    },
    handleFileUpload(response,file,fileList) {
      this.fileList=fileList
      console.log(response,file,fileList)
      console.log(this.fileList)
    },
  }
}
</script>