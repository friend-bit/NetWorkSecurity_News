<template>
  <div>
    <div style="display: flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px">
        <span>文章标题 </span>
        <el-input style="width: 500px"  placeholder="请输入标题" v-model="article.title"></el-input>
      </div>
      <div>
        <span>分类 </span>
        <template>
          <el-select v-model="article.type" placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </template>
      </div>
      <div style="margin-right: 50px" >
        <span>标签</span>
        <el-input style="width: 200px" placeholder="请输入内容" v-model="article.tags"></el-input>
      </div>
    </div>
    <div style="display:flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px;display: flex;justify-content: center">
        <span style="margin-top: 15px;margin-right: 10px">文章摘要 </span>
        <textarea style="width: 500px; height: 35px; line-height: 30px" placeholder="请输入内容" v-model="article.summary"></textarea>
      </div>
      <div>
        <span>允许评论 </span>
        <el-radio v-model="article.is_comment" :label="1">正常</el-radio>
        <el-radio v-model="article.is_comment" :label="0">停用</el-radio>
      </div>
      <div style="margin-right: 80px">
        <span>是否置顶 </span>
        <el-radio v-model="article.istop" :label="1">是</el-radio>
        <el-radio v-model="article.istop" :label="0">否</el-radio>
      </div>
    </div>
    <div style="display: flex;flex-direction: row;margin-left: 50px;margin-top: 20px">
      <span style="margin-right: 20px">缩略图</span>
      <el-upload
          action="http://localhost:8989/upload"
          class="upload-demo"
          name="multipartFile"
          :headers="{token: token}"
          :show-file-list="false"
          :on-success="(row,file,fileList) => handleTableFileUpload(article.row,file,fileList)"
          drag
          multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
      <div>上传图片：</div>
      <img :src="article.thumbnail" style="height: 200px;width: 300px;">
      <div style="display: flex;flex-direction: column;">
        <el-button type="primary" style="width: 100px;margin: 10px" @click="release">发布</el-button>
        <el-button type="info" style="width: 100px" @click="draft">保存草稿</el-button>
      </div>
    </div>
    <div style="display: flex;flex-direction: row;margin-left: 5%;margin-top: 30px">
      <textarea style="width: 45%;height: 300px;margin-right: 50px" v-model="article.content" placeholder="在这里输入..."></textarea>
      <div style="width: 45%;border-left: 1px solid #eee;overflow-y: auto;background-color: #cccccc;" v-html="article.content"></div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      article:{
        title:'',
        content:'',
        summary:'',
        thumbnail:'',
        type:'',
        tags:'',
        istop:'',
        is_comment:'',
        view_count:0,
        author:'',
      },
      options:'',
      text: '',
      tag_id:'',
      params:{
        pageNum:1,
        pageSize:8,
        newsId: '',
        newsTitle: '',
      },
      fileList:[],
      token:localStorage.getItem('token')
    };
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      request.get('/content/tag/searchTag',{
        params: this.params}).then(res =>{
        if(res.code == 200){
          this.options=res.data.rows;
        }
      });
      request.get('/getInfo',{
        headers:{
          'token': localStorage.getItem('token')
        }
      }).then(res =>{
        if(res.code == '200'){
          this.article.author=res.data.users.nickName;
          console.log(this.article.author)
        }
      });
    },
    handleTableFileUpload(row,file,fileList){
      // console.log(row,res,file,fileList)
      this.article.thumbnail=file.response.data
      console.log(this.article.thumbnail)
    },
    release(){
      this.article.status=0
      this.article.category_id=1
      request.post('/content/article/add',this.article).then(res =>{
        if(res.code == '200'){
          this.$notify.success('添加成功')
          this.$router.push('/News')
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    draft(){
      this.article.status=1
      this.article.category_id=1
      request.post('/content/article/add',this.article).then(res =>{
        if(res.code == '200'){
          this.$notify.success('添加成功')
          this.$router.push('/News')
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>


