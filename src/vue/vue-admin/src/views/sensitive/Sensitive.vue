<template>
  <div>
    <div style="display:flex;justify-content: space-between; margin: 20px 20px;">
      <div>
        <el-input v-model="params.SensitiveContent" style="width: 400px" placeholder="请输入敏感词内容" ></el-input>
        <el-button @click="load" style="margin-left: 20px" type="primary" ><i class="el-icon-search"></i> 搜索 </el-button>
        <el-button @click="reset" style="margin-left: 20px" type="primary" ><i class="el-icon-refresh"></i> 重置 </el-button>
      </div>
      <el-button style="margin-left: 400px" type="danger" @click="dialogFormVisible = true"><i class="el-icon-plus"></i> 添加 </el-button>
    </div>
    <el-table :data="SensitiveData" stripe>
      <el-table-column prop="id" label="ID" width="50px"></el-table-column>
      <el-table-column prop="content" label="敏感词" width="150px"></el-table-column>
      <el-table-column prop="categoryName" label="类别" width="150px"></el-table-column>
      <el-table-column prop="createdataTime" label="创建时间" width="300px"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="$router.push('/editeSensitive?id='+scope.row.id)">编辑</el-button>
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

    <el-dialog title="添加敏感词" :visible.sync="dialogFormVisible">
      <el-form :model="Sensitiveform">
        <el-form-item label="敏感词内容" :label-width="formLabelWidth">
          <el-input v-model="Sensitiveform.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="敏感词类型" :label-width="formLabelWidth">
          <el-select v-model="Sensitiveform.categoryName" placeholder="请选择敏感词类型">
            <el-option v-for="item in form" :key="item.id" :label="item.name" :value="item.name"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addSensitiveWord">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request  from "@/utils/request";
export default{
  data(){
    return{
      SensitiveData:[],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 8,
        SensitiveContent: '',
      },
      dialogFormVisible: false,
      Sensitiveform:{},
      form: {},
      formLabelWidth: '120px'
    }
  },
  created() {
    this.load()
  },
  methods:{
    load() {
      request.get("/sensitive/SensitiveList",{params: this.params}).then(res =>{
        if(res.code == 200){
          this.SensitiveData=res.data.rows;
          this.total=Number(res.data.total);
        }
      }),
      request.get("/SensitiveCategory/sensitiveCategoryList",{params: this.params}).then(res =>{
        if(res.code == 200){
          this.form=res.data.rows;
          console.log("User data fetched:", this.form);
        }
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum
      this.load()
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 8,
        SensitiveContent: '',
      };
      this.load();
    },
    closest() {
      this.showDialog = false;
    },
    addSensitiveWord() {
      if (!this.Sensitiveform.content || !this.Sensitiveform.categoryName) {
        this.$notify.error('请填写完整的敏感词信息');
        return;
      }
      request.post("/sensitive/addSensitive", this.Sensitiveform).then(res => {
        if (res.code == 200) {
          this.$notify.success('添加成功');
          this.dialogFormVisible = false;
          this.load();
        } else {
          this.$notify.error(res.msg);
        }
      })
    },
    del(id){
      request.delete('/sensitive/'+ id).then(res=>{
        if(res.code == '200'){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
  },
}
</script>