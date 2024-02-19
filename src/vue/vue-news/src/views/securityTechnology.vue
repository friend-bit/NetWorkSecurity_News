<template>
<!--  <div style="width: 100%;height: 100%;background-color: white;">-->
<!--    <div style="display: flex;flex-direction: row;margin-left: 60px;line-height: 40px" >-->
<!--      <div style="margin-right: 80px">-->
<!--        <div style="font-size: 40px;"><b>Security Technology</b></div>-->
<!--      </div>-->
<!--    </div>-->
<!--&lt;!&ndash;    <div style="display: flex;flex-direction: row">&ndash;&gt;-->
<!--&lt;!&ndash;      <div class="archives">&ndash;&gt;-->
<!--&lt;!&ndash;        <img src="https://watermelonetwork.oss-cn-zhangjiakou.aliyuncs.com/NetWorkSecurity/archives.png" style="width: 100%;height: 100%">&ndash;&gt;-->
<!--&lt;!&ndash;        <div>信安软考资料</div>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->
<!--&lt;!&ndash;      <div class="archives">&ndash;&gt;-->
<!--&lt;!&ndash;        <img src="https://watermelonetwork.oss-cn-zhangjiakou.aliyuncs.com/NetWorkSecurity/archives.png" style="width: 100%;height: 100%">&ndash;&gt;-->
<!--&lt;!&ndash;        <div>CISSP资料</div>&ndash;&gt;-->
<!--&lt;!&ndash;      </div>&ndash;&gt;-->
<!--&lt;!&ndash;    </div>&ndash;&gt;-->
<!--    <el-collapse v-model="activeName" accordion style="margin-left: 60px;">-->
<!--      <el-collapse-item title="机密性 Confidentiality" name="1">-->
<!--        <div>网络信息不泄露给非授权的用户、实体或程序，能够防止非授权者获取信息</div>-->
<!--        <div style="display: flex;flex-direction: row;flex-wrap: wrap;">-->
<!--          <div class="down-content"  v-for="item in selData" :key="item.id">-->
<!--            <div style="width:100%;height:100%;display: flex; flex-direction:row;align-items: center" @click="goToArticleContent(item.id)">-->
<!--              <div style="height: 200px;width: 250px;margin-left: 20px">-->
<!--                <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </el-collapse-item>-->
<!--      <el-collapse-item title="完整性 Integrity" name="2">-->
<!--        <div>网络信息或系统未经授权不能进行更改的特性</div>-->
<!--        <div style="display: flex;flex-direction: row;flex-wrap: wrap;">-->
<!--          <div class="down-content"  v-for="item in searchData" :key="item.id">-->
<!--            <div style="width:100%;height:100%;display: flex; flex-direction:row;align-items: center" @click="goToArticleContent(item.id)">-->
<!--              <div style="height: 200px;width: 250px;margin-left: 20px">-->
<!--                <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </el-collapse-item>-->
<!--      <el-collapse-item title="可用性 Applicability" name="3">-->
<!--        <div>合法许可的用户能够及时获取网络信息或服务的特性</div>-->
<!--        <div style="display: flex;flex-direction: row;flex-wrap: wrap;">-->
<!--          <div class="down-content"  v-for="item in selData" :key="item.id">-->
<!--            <div style="width:100%;height:100%;display: flex; flex-direction:row;align-items: center" @click="goToArticleContent(item.id)">-->
<!--              <div style="height: 200px;width: 250px;margin-left: 20px">-->
<!--                <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </el-collapse-item>-->
<!--      <el-collapse-item title="抗抵赖性 Non-repudiation" name="4">-->
<!--        <div>防止网络信息系统相关用户否认其活动行为的特性</div>-->
<!--        <div style="display: flex;flex-direction: row;flex-wrap: wrap;">-->
<!--          <div class="down-content"  v-for="item in selData" :key="item.id">-->
<!--            <div style="width:100%;height:100%;display: flex; flex-direction:row;align-items: center" @click="goToArticleContent(item.id)">-->
<!--              <div style="height: 200px;width: 250px;margin-left: 20px">-->
<!--                <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </el-collapse-item>-->
<!--      <el-collapse-item title="可控性 Controllability" name="5">-->
<!--        <div>网络信息系统责任主体对其具有管理、支配能的属性，能够根据授权规则对系统进行有效掌握和控制，使得管理者有效控制系统的行为和信息的使用，符合系统运行目标。</div>-->
<!--        <div style="display: flex;flex-direction: row;flex-wrap: wrap;">-->
<!--          <div class="down-content"  v-for="item in selData" :key="item.id">-->
<!--            <div style="width:100%;height:100%;display: flex; flex-direction:row;align-items: center" @click="goToArticleContent(item.id)">-->
<!--              <div style="height: 200px;width: 250px;margin-left: 20px">-->
<!--                <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </el-collapse-item>-->
<!--    </el-collapse>-->
<!--  </div>-->
  <div>
    <div style="margin: 30px">
      <div style="color: white;font-size: 40px">安全科技</div>
      <div style="display: flex;flex-direction: row">
        <div style="width: 50%">
          <el-carousel height="400px" direction="vertical" >
            <el-carousel-item v-for="item in CarouselTechnologyData" :key="item.id">
              <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;" @click="goToArticleContent(item.id)">
              <span style="text-align: center; margin:10px 0px; position: absolute; bottom: 0; left: 0; width: 100%; background-color: rgba(0, 0, 0, 0.5); color: white; ">{{ item.title }}</span>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div style="height: 400px;width: 600px;margin:0px 20px;padding-left: 30px;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);background-color: white">
          <div style="margin-top: 8px" v-for="(item,index) in TechnologyData" :key="index">
            <span @click="goToArticleContent(item.id)">{{[index+1]}}—{{item.title}}</span>
          </div>
        </div>
      </div>
    </div>
    <div style="display: flex; flex-direction: row; flex-wrap: wrap; width: 100%; height: 100%;">
      <div v-for="item in TechnologyData2" :key="item.id">
        <div style="width: 280px; height: 100%; display: flex; flex-direction: column; align-items: center; background-color: white;margin-left: 10%;margin-bottom: 10px">
          <div style="height: 150px; width: 250px; margin: 10px; margin-top: 20px;" @click="goToArticleContent(item.id)">
            <img :src="item.thumbnail" :title="item.title" style="width: 100%; height: 100%;">
          </div>
          <div style="height: 70px; width: 75%; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical;">
            {{item.summary}}
          </div>
        </div>
      </div>
    </div>
    <div style="width: 80%;margin:30px;background-color: white">
      <div style="display: flex;flex-direction: row;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5)" v-for="item in TechnologyData2">
        <img :src="item.thumbnail" style="width: 300px;margin: 20px">
        <div style="display: flex;flex-direction: column;margin: 20px;">
          <span style="font-size: 20px">{{item.title}}</span>
          <span style="font-size: 10px;color: #007bff;margin-top: 10px">{{item.author}}</span>
          <span style="font-size: 25px;margin-top: 10px;overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;">{{item.summary}}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      activeNames: 1,
      CarouselTechnologyData:[],
      TechnologyData:[],
      TechnologyData2:[],
      searchData:[],
    };
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/article/TechnologyArticle?pageNum=1&pageSize=3").then(res =>{
        if (res.code == '200') {
          this.CarouselTechnologyData = res.data;
          console.log(this.CarouselTechnologyData)
        }
      })
      request.get("/article/TechnologyArticle?pageNum=1&pageSize=10").then(res =>{
        if (res.code == '200') {
          this.TechnologyData = res.data;
        }
      })
      request.get("/article/TechnologyArticle?pageNum=2&pageSize=10").then(res =>{
        if (res.code == '200') {
          this.TechnologyData2 = res.data;
        }
      })
    },
    goToArticleContent(id){
      this.$router.push('/articleContent/'+id);
    },
  }
}
</script>
<style>
  .archives{
    margin-top: 50px;
    margin-left: 5%;
    width: 10%;
    height: 10%;
    display: flex;
    align-items:center;
    flex-direction: column;
  }
  .archives:hover{
    cursor: pointer;
  }
  .down-content{
    margin-left: 20px;
    margin-top: 15px;
    height: 250px;
    width: 300px;
    box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);
  }
</style>