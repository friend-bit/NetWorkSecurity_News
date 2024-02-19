<template>
  <div class="forgot-password">
    <h2>找回密码</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="email">邮箱：</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
    };
  },
  methods: {
    submitForm() {
      // 在这里处理提交逻辑，例如发送重置密码链接到用户的邮箱
      console.log('邮箱：', this.email);
      // 重置表单
      this.email = '';
    },
  },
};
</script>

<style scoped>
.forgot-password {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 10px;
}

label {
  display: block;
}

input[type="email"] {
  width: 100%;
  padding: 5px;
}

button[type="submit"] {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}
</style>
