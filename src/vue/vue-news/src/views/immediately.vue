<template>
  <div>
    <div style="display: flex;flex-direction: row;margin-left: 60px;line-height: 40px" >
      <div style="margin-right: 80px">
        <span style="font-size: 30px;">即时新闻</span><hr style="color: #eeeeee">
        <div style="width:850px;height: 650px;font-size: 15px">
          <div style="display: flex;flex-direction: row;justify-content: space-between">
            <div>类型</div>
            <div>标题</div>
            <div style="margin-right: 20px">时间</div>
          </div>
          <div style="display: flex;flex-direction: row;justify-content: space-between;" v-for="item in latestArticle">
            <span>[{{item.categoryName}}]</span>
            <span style="cursor: pointer;overflow: hidden;display: -webkit-box; -webkit-line-clamp: 1; -webkit-box-orient: vertical;" @click=goToArticleContent(item.id)>{{item.title}}</span>
            <span style="margin-right: 10px">{{item.createdataTime}}</span>
          </div>
        </div>
        <hr style="color: #eeeeee">

      </div>

      <div style="width: 380px;">
<!--        <div class="riqi">-->
<!--&lt;!&ndash;          <button class="last_month" @click="previousMonth"></button>&ndash;&gt;-->
<!--&lt;!&ndash;          <h2>{{ currentMonth }}</h2>&ndash;&gt;-->
<!--&lt;!&ndash;          <button class="next_month" @click="nextMonth"></button>&ndash;&gt;-->
<!--        </div>-->

<!--        <table>-->
<!--          <thead>-->
<!--          <tr><th>星期日</th><th>星期一</th><th>星期二</th><th>星期三</th><th>星期四</th><th>星期五</th><th>星期六</th></tr>-->
<!--          </thead>-->
<!--          <tbody>-->
<!--          <tr v-for="week in calendar" :key="week" style="cursor: pointer;">-->
<!--            <td v-for="day in week" :key="day">{{ day }}</td>-->
<!--          </tr>-->
<!--          </tbody>-->
<!--        </table>-->
        <div style="display: flex;flex-direction: row;align-items:center;margin-top: 20px">
          <div style="text-align: left;font-size: 20px;font-weight:bold">推荐阅读</div>
          <div style="background-color: #cccccc;height: 10px;width:70%;margin-left: 5px;border-radius: 5px;"></div>
        </div>
        <div style="height: 110px; display:flex;background-color: white;border-radius: 5px;margin-bottom: 10px;" v-for="(item,index) in selData">
          <img :src="item.thumbnail" :title="item.title" style="width: 100px; height: 100px;margin:5px 10px">
          <span style="line-height: 30px;overflow: hidden;display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">{{item.title}}</span>
        </div>
        <div style="display: flex;flex-direction: row;align-items:center;margin-top: 20px">
          <div style="text-align: left;font-size: 20px;font-weight:bold;">热榜事件</div>
          <div style="background-color: #cccccc;height: 10px;width:70%;margin-left: 5px;border-radius: 5px;"></div>
        </div>
        <div style="display: flex;flex-direction: column;margin-top: 10px">
          <div style="display: flex;flex-direction: row;align-items:center;margin-top: 10px;" v-for="(item,index) in hotArticleData" :key="index">
            <div style="display: flex;align-items:center;justify-content:center;width: 35px;height: 30px;background-color: red;border-radius: 5px;">
              <span style="color: white;">{{index+1}}</span>
            </div>
            <div style="margin:0px 10px;width: 400px;height: 30px;background-color: orange;">
              <span style="overflow: hidden;display: -webkit-box; -webkit-line-clamp: 1; -webkit-box-orient: vertical;">{{item.title}}</span>
            </div>
          </div>
        </div>
<!--        <div style="display: flex;flex-direction: column;margin-top: 10px">-->
<!--          <div style="display: flex;flex-direction: row;align-items:center;">-->
<!--            <div style="display: flex;align-items:center;justify-content:center;width: 35px;height: 30px;background-color: orange;border-radius: 5px;">-->
<!--              <span style="color: white;">2</span>-->
<!--            </div>-->
<!--            <div style="margin:0px 10px;width: 380px;height: 30px;background-color: orange;"></div>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div style="display: flex;flex-direction: column;margin-top: 10px">-->
<!--          <div style="display: flex;flex-direction: row;align-items:center;">-->
<!--            <div style="display: flex;align-items:center;justify-content:center;width: 35px;height: 30px;background-color: #ffdd00;border-radius: 5px;">-->
<!--              <span style="color: white;">3</span>-->
<!--            </div>-->
<!--            <div style="margin:0px 10px;width: 380px;height: 30px;background-color: #ffdd00;"></div>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div style="display: flex;flex-direction: column;margin-top: 10px">-->
<!--          <div style="display: flex;flex-direction: row;align-items:center;">-->
<!--            <div style="display: flex;align-items:center;justify-content:center;width: 35px;height: 30px;background-color:#cccccc;border-radius: 5px;">-->
<!--              <span style="color: dimgrey;">4</span>-->
<!--            </div>-->
<!--            <div style="margin:0px 10px;width: 380px;height: 30px;background-color: #cccccc;"></div>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div style="display: flex;flex-direction: column;margin-top: 10px">-->
<!--          <div style="display: flex;flex-direction: row;align-items:center;">-->
<!--            <div style="display: flex;align-items:center;justify-content:center;width: 35px;height: 30px;background-color:#cccccc;border-radius: 5px;">-->
<!--              <span style="color: dimgrey;">5</span>-->
<!--            </div>-->
<!--            <div style="margin:0px 10px;width: 380px;height: 30px;background-color: #cccccc;"></div>-->
<!--          </div>-->
<!--        </div>-->
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      currentDate: new Date(),
      selData:[],
      carouselData: [],
      hotArticleData: [],
      latestArticle:[],
    };
  },
  // computed: {
  //   currentMonth() {
  //     const year = this.currentDate.getFullYear();
  //     const month = this.currentDate.getMonth() + 1;
  //     return `${year}年${month}月`;
  //   },
  //   calendar() {
  //     const year = this.currentDate.getFullYear();
  //     const month = this.currentDate.getMonth();
  //     const firstDay = new Date(year, month, 1);
  //     const lastDay = new Date(year, month + 1, 0);
  //     const startOfWeek = new Date(year, month, 1 - firstDay.getDay());
  //     const endOfWeek = new Date(year, month + 1, 6 - lastDay.getDay());
  //     const calendar = [];
  //     let week = [];
  //     let day = new Date(startOfWeek);
  //     while (day <= endOfWeek) {
  //       if (day < firstDay || day > lastDay) {
  //         week.push('');
  //       } else {
  //         week.push(day.getDate());
  //       }
  //       if (day.getDay() === 6) {
  //         calendar.push(week);
  //         week = [];
  //       }
  //       day.setDate(day.getDate() + 1);
  //     }
  //     return calendar;
  //   },
  // },
  created() {
    this.load();
  },
  methods: {
    // previousMonth() {
    //   this.currentDate.setMonth(this.currentDate.getMonth() - 1);
    //   this.currentDate = new Date(this.currentDate); // 触发视图更新
    // },
    // nextMonth() {
    //   this.currentDate.setMonth(this.currentDate.getMonth() + 1);
    //   this.currentDate = new Date(this.currentDate); // 触发视图更新
    // },
    load(){
      request.get("/article/articleList?pageNum=1&pageSize=4").then(res =>{
        if (res.code == '200') {
          this.selData = res.data.rows;
        }
      }),
      request.get("/article/articleList?pageNum=1&pageSize=15").then(res =>{
        if (res.code == '200') {
          this.carouselData = res.data.rows;
        }
      }),
      request.get("/article/hotArticleList?pageNum=1&pageSize=10").then(res =>{
        if (res.code == '200') {
          this.hotArticleData=res.data;
        }
      }),
      request.get('/article/latestArticle').then(res=>{
        if(res.code == '200'){
          this.latestArticle=res.data;
        }
      })
    },
    goToArticleContent(id){
      this.$router.push('/articleContent/'+id);
    }
  },
};
</script>
<style>
.riqi{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.last_month {
  background-color: transparent;
  border: none;
  cursor: pointer;
  padding: 0;
}
.last_month::before {
  content: '\2039';
  font-size: 40px;
}
.next_month{
  background-color: transparent;
  border: none;
  cursor: pointer;
  padding: 0;
}
.next_month::before {
  content: '\203A';
  font-size: 40px;
}


</style>