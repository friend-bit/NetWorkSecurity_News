<template>
  <div style="width: 96%;height: 100%;background-color: white;margin-left: 2.5%">
    <div style="display: flex;flex-direction: row;margin-left: 40px;line-height: 40px" >
      <div style="margin-right: 80px">
        <div style="font-size: 40px;"><b>政策法律</b></div><hr style="color: #eeeeee">
        <div style="width:900px;font-size: 15px;background-color: white;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">
          <span style="font-size: 25px">安全</span><hr style="color: #eeeeee">
          <div style="display: flex;flex-direction: row;justify-content: space-between;" v-for="item in LawSecureData">
            <span><img src="https://watermelonetwork.oss-cn-zhangjiakou.aliyuncs.com/NetWorkSecurity/pdficon.jpg" style="width: 30px;align-items: center">{{item.filename}}</span>
            <span style="margin-right: 20px">
              <a :href="item.url" style=" text-decoration: none;color: dodgerblue" download><i class="el-icon-download"></i>下载</a>
            </span>
          </div>
        </div>
        <div style="width:900px;font-size: 15px;background-color: white;margin-top: 50px;line-height: 40px;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">
          <span style="font-size: 25px">开发</span><hr style="color: #eeeeee">
          <div style="display: flex;flex-direction: row;justify-content: space-between;" v-for="item in LawDevelopData">
            <span><img src="https://watermelonetwork.oss-cn-zhangjiakou.aliyuncs.com/NetWorkSecurity/pdficon.jpg" style="width: 30px;align-items: center">{{item.filename}}</span>
            <span style="margin-right: 20px">
              <a :href="item.url" style=" text-decoration: none;color: dodgerblue" download><i class="el-icon-download"></i>下载</a>
            </span>
          </div>
        </div>
      </div>
      <div >
        <div style="width: 350px;height:320px;margin-top: 50px;background-color: white;box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.5);">
          <div style="display:flex;justify-content: center;overflow: visible;">
            <div style="width: 100px; height: 100px; border-radius: 50%; overflow: hidden;background-color: red;margin: -50px">
              <img :src="userInfolData.avatar" style="width: 100%; height: 100%;">
            </div>
          </div>
          <div style="display:flex;justify-content: center;overflow: visible;">
            <span style="margin-top: 80px;font-size: 30px">{{userInfolData.nickName}}</span>
          </div>
          <div>个性签名</div>
          <div style="height: 150px;text-indent: 2ch;line-height: 40px;overflow: hidden;display: -webkit-box; -webkit-line-clamp: 4; -webkit-box-orient: vertical;">
            {{userInfolData.signature}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "Personal",
  data() {
    return {
      LawSecureData: [],
      LawDevelopData: [],
      userInfolData:[],
      params:{
        pageNum:1,
        pageSize:8,
        userId: '',
        nickName: '',
      },
    };
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/law/lawSecure",{params: this.params}).then(res => {
        if(res.code == '200') {
          this.LawSecureData=res.data.rows;
        }
      }),
      request.get("/law/lawDevelop",{params: this.params}).then(res => {
        if(res.code == '200') {
          this.LawDevelopData=res.data.rows;
        }
      }),
      request.put("/user/userInfo",{
        headers:{
          'token': localStorage.getItem('token')
        }
      }).then(res =>{
        if(res.code == '200'){
          this.userInfolData=res.data;
        }
      })
    },
  }
}
</script>