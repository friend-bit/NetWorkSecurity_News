<template>
  <div>
    <div style="display: flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px">
        <span>文章标题 </span>
        <el-input style="width: 500px"  placeholder="请输入内容"></el-input>
      </div>
      <div>
        <span>分类 </span>
        <el-input style="width: 200px"  placeholder="请输入内容"></el-input>
      </div>
      <div style="margin-right: 50px">
        <span>标签 </span>
        <el-input style="width: 200px" placeholder="请输入内容"></el-input>
      </div>
    </div>
    <div style="display:flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px;display: flex;justify-content: center">
        <span style="margin-top: 15px;margin-right: 10px">文章摘要 </span>
        <textarea style="width: 500px;height: 35px;line-height: 30px" placeholder="请输入内容"></textarea>
      </div>
      <div>
        <span>允许评论 </span>
        <el-radio v-model="is_comment" label="1">正常</el-radio>
        <el-radio v-model="is_comment" label="0">停用</el-radio>
      </div>
      <div style="margin-right: 80px">
        <span>是否置顶 </span>
        <el-radio v-model="istop" label="1">是</el-radio>
        <el-radio v-model="istop" label="0">否</el-radio>
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
      <div style="display: flex;flex-direction: column;margin-left:20%;">
        <el-button type="primary" style="width: 100px;margin: 10px">发布</el-button>
        <el-button type="info" style="width: 100px">保存草稿</el-button>
      </div>
    </div>
    <div style="display: flex;flex-direction: row;margin-left: 5%;margin-top: 30px">
      <textarea style="width: 45%;height: 300px;margin-right: 50px" v-model="text" placeholder="在这里输入..."></textarea>
      <div style="width: 45%;border-left: 1px solid #eee;overflow-y: auto;background-color: #cccccc;" v-html="text"></div>
    </div>
  </div>
</template>