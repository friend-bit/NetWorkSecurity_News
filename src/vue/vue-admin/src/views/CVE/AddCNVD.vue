<template>
  <div>
    <div style="margin-bottom: 30px">添加CNVD</div>
    <div style="display: flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px">
        <span>编号 </span>
        <el-input style="width: 200px" v-model="cnvd.serialNumber" placeholder="请输入CNVD编号"></el-input>
      </div>
      <div style="margin-right: 50px">
        <span>漏洞标题 </span>
        <el-input v-model="cnvd.title" style="width: 200px" placeholder="请输入漏洞标题"></el-input>
      </div>
      <div>
        <span>级别 </span>
        <template>
          <el-select v-model="cnvd.level" placeholder="请选择">
            <el-option
                v-for="item in levelif"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </template>
      </div>
      <div style="margin-right: 50px">
        <span>参考链接 </span>
        <el-input v-model="cnvd.link" style="width: 200px" placeholder="请输入参考链接"></el-input>
      </div>
    </div>
    <div style="display:flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px;display: flex;justify-content: center">
        <span style="margin-top: 15px;margin-right: 10px">cnvd描述 </span>
        <textarea v-model="cnvd.summary" style="width: 400px;height: 35px;line-height: 30px" placeholder="请输入内容"></textarea>
      </div>
      <div class="block">
        <span class="demonstration">公开时间 </span>
        <el-date-picker
            v-model="cnvd.releaseTime"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
      </div>
      <div style="margin-right: 50px">
        <span>补丁链接 </span>
        <el-input v-model="cnvd.patch" style="width: 200px" placeholder="请输入补丁链接"></el-input>
      </div>
    </div>
    <div style="text-align: center;margin-top: 30px">
      <el-button type="primary" @click="save" >提交</el-button>
      <el-button type="danger" @click="cancel">取消</el-button>
    </div>
  </div>
</template>
<script>
  import request from "@/utils/request";

  export default {
    data(){
      return{
        levelif:[{
          value:'低',
          label:'低',
        },{
          value:'中',
          label:'中',
        },{
          value:'高',
          label:'高',
        }],
        cnvd: {
          serialNumber:'',
          title:'',
          level:'',
          link:'',
          path:'',
          releaseTime:'',
          view_count: '0',
        },
      }
    },
    created() {
      this.load();
    },
    methods: {
      save() {
        request.post('/cnvd/addCNVD', this.cnvd).then(res => {
          if (res.code == '200') {
            this.$notify.success('添加成功');
            this.$router.push('/CNVDManage');
          } else {
            this.$notify.error(res.msg);
          }
        });
      },
      cancel() {
        this.$router.push('/CNVDManage');
      }
    }
  }
</script>