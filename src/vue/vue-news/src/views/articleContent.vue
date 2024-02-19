<template>
  <div>
    <div style="width: 80%;margin-left:10%;">
      <div style="display:flex;justify-content:center"><h1>{{contentData.title}}</h1></div>
      <div style="display:flex;justify-content: center">
        <i class="el-icon-view" ></i><span style="margin-left: 15px">{{contentData.viewCount}}</span>
        <i class="el-icon-date" style="margin-left: 100px"></i><span style="margin-left: 15px">{{contentData.createdataTime}}</span>
        <i class="el-icon-user" style="margin-left: 100px"></i><span style="margin-left: 15px">{{contentData.author}}</span>
      </div>
      <div style="font-size: 25px;margin-top: 20px" v-html="contentData.content"></div>
      <div style="font-size: 30px;margin-top:30px"><b>评论</b></div><hr style="color: red;margin-top:30px">
      <div style="display:flex;flex-direction: row;justify-content: center;align-items: center">
        <img :src="userinfo.avatar" style="border-radius: 50%; height: 60px; width: 60px;margin-right: 30px">
        <textarea style="width: 80%;height: 30px;border-radius: 10px;" v-model="comment.content"></textarea>
        <el-button style="margin-left: 30px;text-align: right;" type="primary" @click="publish">发表评论</el-button>
      </div>
      <div>
        <div v-for="item in commentData" :key="item.id" style="width: 100%; margin-top: 5%; display: flex; flex-direction: column; padding: 10px; box-sizing: border-box;">
          <div style="display: flex; flex-direction: row; margin-bottom: 10px;">
            <img :src="item.avatar" style="border-radius: 50%; height: 60px; width: 60px; margin-right: 10px;">
            <div style="display: flex; flex-direction: column;">
              <div style="margin-bottom: 5px;">{{item.username}}</div>
              <div>{{item.content}}</div>
              <div style="display: flex;flex-direction: row">
                <div style="display: flex; align-items: center;">
                  <button @click="likeUp(item.id)" style="background-color: transparent; border: none;">
                    <i class="fas fa-thumbs-up" :style="{ color: isLiked ? 'gray' : 'rgba(255, 255, 255, 0.5)' }"></i>
                  </button>
                </div>
                <div style="display: flex; align-items: center;">
                  <button @click="dislikeComment(item.id)" style="background-color: transparent; border: none;">
                    <i class="fas fa-thumbs-down" style="color: rgba(255, 255, 255, 0.5);"></i>
                  </button>
                </div>
                <div style="display: flex; align-items: center;" @click="replyComment(item.id)">
                  <div style="color: rgba(255, 255, 255, 0.5);">回复</div>
                  <button style="background-color: transparent; border: none;">
                    <i class="fas fa-reply" style="color: rgba(255, 255, 255, 0.5);"></i>
                  </button>
                </div>
              </div>
              <div v-if="showReply && comment.itemId === item.id" style="display:flex;flex-direction: row;align-items: center;margin-top: 20px">
                <img :src="userinfo.avatar" style="border-radius: 50%; height: 60px; width: 60px;margin-right: 30px">
                <textarea style="width:800px;height: 30px;border-radius: 10px;" v-model="comment.content"></textarea>
                <el-button style="margin-left: 30px;text-align: right;" type="primary" @click="publish">发表评论</el-button>
              </div>
            </div>
          </div>
          <div v-if="item.children" style="margin-left:40px;margin-top:40px">
            <div v-for="child in item.children" :key="child.id" style="display: flex; flex-direction: row;">
              <img :src="child.avatar" style="border-radius: 50%; height: 60px; width: 60px; margin-right: 10px;">
              <div style="display: flex; flex-direction: column;">
                <div style="margin-bottom: 5px;">{{child.username}}</div>
                <div>{{child.content}}</div>
                <div style="display: flex;flex-direction: row">
                  <div style="display: flex; align-items: center;">
                    <button @click="likeComment(item.id)" style="background-color: transparent; border: none;">
                      <i class="fas fa-thumbs-up" style="color: rgba(255, 255, 255, 0.5);"></i>
                    </button>
                  </div>
                  <div style="display: flex; align-items: center;">
                    <button @click="dislikeComment(item.id)" style="background-color: transparent; border: none;">
                      <i class="fas fa-thumbs-down" style="color:  rgba(255, 255, 255, 0.5);"></i>
                    </button>
                  </div>
                  <div style="display: flex; align-items: center;" @click="replyComment(item.id)">
                    <div style="color: rgba(255, 255, 255, 0.5);">回复</div>
                    <button  style="background-color: transparent; border: none;">
                      <i class="fas fa-reply" style="color: rgba(255, 255, 255, 0.5);"></i>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
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
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";
import Cookies from "js-cookie";
export default {
  data(){
    return{
      showReply:false,
      userinfo:{},
      isLiked:false,
      isLoggedIn:Cookies.get('token')?true:false,
      commentData:[],
      contentData:[],
      comment:{},
      total:0,
      params:{
        pageNum:1,
        pageSize:8,
        articleId: '',
      },
    };
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      const id=this.$route.params.id
      this.comment.article_id=id
      request.get("/article/"+id).then(res=>{
        if (res.code == '200') {
          this.contentData=res.data;
        }
      })
      request.put('/user/userInfo',{
        headers:{
          'token': localStorage.getItem('token')
        }
      }).then(res =>{
        if(res.code == '200'){
          this.comment.userid=res.data.id;
          this.userinfo.avatar=res.data.avatar;
        }
      })
      request.put('/article/updateViewCount/'+id).then(res =>{
      })
      this.params.articleId=this.$route.params.id
      request.get("/comment/commentList",{params:this.params}).then(res =>{
        if(res.code == '200'){
          this.commentData=res.data.rows;
          this.total=Number(res.data.total);
          this.comment.root_id=res.data.rows.id;
        }
      })
    },
    publish(){
      this.comment.type=0;
      this.comment.rootId=-1;
      this.comment.createBy=1;
      request.post("/comment/addComment",this.comment).then(res =>{
        if(res.code == '200'){
          this.$notify.success('发布成功')
          this.comment.content = '';
        }else{
          this.$notify.error(res.msg)
        }
        this.load()
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum;
      this.load();
    },
    likeUp(commentId){
      this.isLiked = !this.isLiked;
    },
    replyComment(itemId) {
      if (this.showReply && this.comment.itemId === itemId) {
        // 如果已经显示回复框并且点击的是同一个评论，则隐藏
        this.showReply = false;
      } else {
        // 否则显示回复框，并设置回复评论的ID
        this.showReply = true;
        this.comment.itemId = itemId;
        this.comment.root_id=itemId;
      }
    },
  }
}
</script>