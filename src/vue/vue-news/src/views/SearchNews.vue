<template>
  <div>
    <div style="margin: 5px 15px">
      <span style="font-size: 12px" @click="$router.push('/HomeView')">返回首页</span>
    </div>
    <div style="background-color: white;">
      <div style="padding-left: 20px;display: flex;flex-direction: row">
        <img src="@/assets/logo.png" style="width: 120px;">
        <div style="display:flex;flex-direction: row;align-items: center;">
          <form style="display: flex;align-items: center;flex: 1;" @submit.prevent="searchAndRedirect">
            <input type="text" style="width: 400px;padding:10px;border-radius: 15px 0 0 15px;border: 1px solid #ccc;outline: none;flex: 1;" v-model="params.newsTitle" placeholder="请输入搜索内容" >
            <button type="submit" style="padding: 10px 30px;border-radius: 0 15px 15px 0;border: none;cursor: pointer;">搜索</button>
          </form>
        </div>
      </div>
      <div style="background-color: #F5F5F5;width: 96%;margin-left: 2%;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);line-height: 30px">
        <span style="margin-left: 10px;"><b>结果：找到相关内容{{total}}</b></span>
      </div>
      <div style="margin-left: 2%;margin-top: 40px" v-for="item in SearchNews">
        <a style="font-size: 21px" @click="$router.push('/articleContent/'+item.id)"><b>{{item.title}}</b></a>
        <div style="width: 1000px;height: 40px;overflow: hidden;display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;">{{item.summary}}</div>
        <div style="display: flex;flex-direction: row">
          <div style="color: green">{{item.createdataTime}}</div>
          <div style="color: #cccccc;margin-left: 10px">-{{item.categoryName}}</div>
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
</template>
<style>
.head-middle{
  display:flex;
  flex-direction: row;
  align-items: center;
  margin-left: 50px;
}
.search-form {
  display: flex;
  align-items: center;
  flex: 1;
}
.search-input {
  width: 500px;
  padding:10px;
  border: 0.5px solid cornflowerblue;
  outline: none;
  flex: 1;
}

.search-button {
  padding: 10px 30px;
  border: none;
  cursor: pointer;
  background-color: cornflowerblue;
  color: white;
}
</style>

<script>
import request  from "@/utils/request";
export default {
  data(){
    return{
      SearchNews:[],
      total:0,
      params:{
        pageNum:1,
        pageSize:8,
        newsTitle: this.$route.query.SearchNews || '',
      },
    }
  },
  created() {
    this.params.newsTitle=this.$route.query.SearchNews;
    this.load();
  },
  methods:{
    load(){
      request.get('/article/searchNews',{params:this.params}).then(res =>{
        this.SearchNews=res.data.rows;
        this.total=Number(res.data.total);
        console.log(this.params.newsTitle);
      })
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum;
      this.load();
    },
    searchAndRedirect() {
      const newSearchTitle = this.params.newsTitle.trim();

      // Check if the search parameter has changed
      if (newSearchTitle !== this.$route.query.SearchNews) {
        if (newSearchTitle !== '') {
          // If SearchNews is not empty, navigate to SearchNews with the search parameter
          this.$router.push('/SearchNews?SearchNews=' + newSearchTitle);
        } else {
          // If SearchNews is empty, navigate to SearchNewsIndex
          this.$router.push('/SearchNewsIndex');
        }
      } else {
        // Log or handle the case where the search parameter hasn't changed
        console.log('Search parameter has not changed.');
      }
      this.load();
    },

  }
}
</script>