<template>
  <div>
    <div class="head-content">
      <div class="head-left">
        <img src="@/assets/logo.png" style="width: 80px;">
        <div class="title-font">
          <span class="font-style">首页</span>
        </div>
        <div class="title-font">
          <span class="font-style">政策法律</span>
        </div>
        <div class="title-font">
          <span class="font-style">hack事件</span>
        </div>
        <div class="title-font">
          <span class="font-style">Security Technology</span>
        </div>
        <div class="title-font">
          <span class="font-style">即时新闻</span>
        </div>
      </div>
      <div class="head-middle">
        <form class="search-form">
          <input  type="text" placeholder="请输入搜索关键词" class="search-input">
          <button type="submit" class="search-button">搜索</button>
        </form>
      </div>
      <div class="head-right">
        <div class="title-font">登录</div>
        <div class="title-font">注册</div>
      </div>
    </div>
  </div>
</template>

<style>
.head-content{
  display: flex;
  justify-content: space-between;
  height: 60px;
  line-height: 60px;
}
.head-left{
  display:flex;
  flex-direction: row;
  align-items: center;
  margin-left: 30px;
}
.head-middle{
  display:flex;
  flex-direction: row;
  align-items: center;
}
.head-right{
  display:flex;
  flex-direction: row;
  align-items: center;
  margin-right: 20px;
}
.title-font{
  display: inline-block;
  margin-left: 30px;
  cursor: pointer;
}

.search-form {
  display: flex;
  align-items: center;
  flex: 1;
}
.search-input {
  width: 400px;
  padding:10px;
  border-radius: 15px 0 0 15px;
  border: 1px solid #ccc;
  outline: none;
  flex: 1;
}
.search-button {
  padding: 10px 30px;
  border-radius: 0 15px 15px 0;
  border: none;
  cursor: pointer;
}
</style>