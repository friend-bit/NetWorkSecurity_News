<template>
  <div>
    <div style="display: flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px">
        <span>文章标题 </span>
        <el-input style="width: 500px"  placeholder="请输入内容" v-model="NewsInfo.title"></el-input>
      </div>
      <div>
        <span>分类 </span>
        <template>
          <el-select v-model="NewsInfo.categoryId" placeholder="请选择">
            <el-option
                v-for="item in classif"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </template>
      </div>
      <div style="margin-right: 50px">
        <span>作者 </span>
        <el-input style="width: 200px" placeholder="请输入内容" v-model="NewsInfo.author"></el-input>
      </div>
    </div>
    <div style="display:flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px;display: flex;justify-content: center">
        <span style="margin-top: 15px;margin-right: 10px">文章摘要 </span>
        <textarea style="width: 500px;height: 35px;line-height: 30px" placeholder="请输入内容" v-model="NewsInfo.summary"></textarea>
      </div>
    </div>
    <div style="display: flex;flex-direction: row;margin-left: 50px;margin-top: 20px">
      <span style="margin-right: 20px">缩略图</span>
      <el-upload
          class="upload-demo"
          drag
          action="https://jsonplaceholder.typicode.com/posts/"
          multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
      <div>上传图片：</div>
      <img :src="NewsInfo.thumbnail" style="height: 200px;width: 300px;">
      <div style="display: flex;flex-direction: column;">
        <el-button type="primary" style="width: 100px;margin: 10px" @click="save">保存</el-button>
        <el-button type="danger" style="width: 100px" @click="cancel">取消</el-button>
      </div>

    </div>
    <div style="display: flex;flex-direction: row;margin-left: 5%;margin-top: 30px">
      <textarea style="width: 45%;height: 300px;margin-right: 50px" v-model="NewsInfo.content" placeholder="在这里输入..." ></textarea>
      <div style="width: 45%;border-left: 1px solid #eee;overflow-y: auto;background-color: #cccccc;" v-html="NewsInfo.content"></div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      NewsInfo: {},
      classif:{},
    };
  },
  created() {
    const id = this.$route.query.id;
    request.get("/content/article/searchArticle/" + id).then(res => {
          console.log("Raw response data:", res.data); // Log the entire response for inspection
          this.NewsInfo = res.data[0]; // Check if the data is at the expected location
          console.log("User data fetched:", this.NewsInfo);
        })
        .catch(error => {
          console.error("Error fetching user data:", error);
        });
    request.get('/content/category/listAllCategory').then(res =>{
      if(res.code ==200){
        this.classif=res.data
      }
      console.log(this.classif)
    })
  },
  methods: {
    save() {
      request.put('/content/article/update',this.NewsInfo).then(res =>{
        if(res.code == '200'){
          this.$notify.success('更新成功')
          this.$router.push('/News')
        }else{
          this.$notify.error(res.msg)
        }
      })
      // https://watermelonetwork.oss-cn-zhangjiakou.aliyuncs.com/NetWorkSecurity/08060ef070bb394083ac81b076c101115e.jpg
    },
    cancel() {
      this.$router.push('/News')
      console.log("Cancel button clicked");
    }
  }
};
</script>