<template>
  <div>
    <div style="margin: 5px 15px">
      <span style="font-size: 12px" @click="$router.push('/HomeView')">返回首页</span>
    </div>
    <div style="padding: 10%">
      <div style="padding-left: 20px;display: flex;flex-direction: column;justify-content: center;align-items: center">
        <img src="@/assets/logo.png" style="width: 300px;">
        <div class="head-middle" >
          <form class="search-form">
            <input type="text" placeholder="请输入搜索内容" class="search-input" v-model="SearchNews">
            <button type="button" class="search-button" @click="searchAndRedirect"><b>搜 索</b></button>
          </form>
        </div>
      </div>
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
export default {
  data(){
    return{
      SearchNews:'',
    }
  },
  methods:{
    searchAndRedirect() {
      // Check if the trimmed SearchNews is not empty
      if (this.SearchNews.trim() !== '') {
        // Check if the current route is not already '/SearchNews'
        if (this.$route.path !== '/SearchNews') {
          this.$router.push('/SearchNews?SearchNews=' + this.SearchNews);
        }
      } else {
        // Check if the current route is not already '/SearchNewsIndex'
        if (this.$route.path !== '/SearchNewsIndex') {
          this.$router.push('/SearchNewsIndex');
        }
      }
    }

  }
}
</script>