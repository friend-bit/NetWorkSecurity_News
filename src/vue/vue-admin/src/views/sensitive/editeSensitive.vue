<template>
  <div>
    <div style="display: flex;flex-direction: row;justify-content: space-between;margin-top: 20px">
      <div style="margin-left: 50px">
        <span>敏感词 </span>
        <el-input style="width: 200px" v-model="SensitiveData.content" placeholder="请输入敏感词"></el-input>
      </div>
      <div>
        <span>类别 </span>
        <template>
          <el-select v-model="SensitiveData.categoryName" placeholder="请选择">
            <el-option
                v-for="item in levelif"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </template>
      </div>
      <div class="block">
        <span class="demonstration">创建时间 </span>
        <el-date-picker
            v-model="SensitiveData.createdataTime"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
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
  data() {
    return{
      SensitiveData:{},
    }
  },
  created() {
    const id = this.$route.query.id;
    request.get("/sensitive/" + id).then(res => {
      console.log("Raw response data:", res.data); // Log the entire response for inspection
      this.SensitiveData = res.data[0]; // Check if the data is at the expected location
      console.log("User data fetched:", this.SensitiveData);
    }).catch(error => {console.error("Error fetching user data:", error);});
  },
  methods:{

  }
}

</script>