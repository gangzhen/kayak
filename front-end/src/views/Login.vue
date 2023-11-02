<script>

import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "Login",
  components: {
    ValidCode
  },
  data() {

    const validateCodeRule = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'))
      } else if (value !== this.validCode) {
        callback(new Error('验证码错误'))
      } else {
        callback();
      }
    }

    return {
      loginForm: {
        idNumber: '',
        password: '',
        validCode: '',
      },
      validCode: '',
      loginRules: {
        idNumber: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        validCode: [
          {validator: validateCodeRule, trigger: 'submit'},
        ],
      },
    }
  },
  methods: {

    handleLogin() {
      if (this.ruleLoginForm()) {
        this.$http.post('/login/login', this.loginForm).then(res => {
          if (res.code === '200') {
            this.$router.push('/')
            this.$message.success('登陆成功')
          } else {
            this.$message.error(res.msg)
          }
        })
      }
    },

    rcValidCode(code) {
      this.validCode = code;
    },

    ruleLoginForm() {
      let flag = true;
      this.$refs['loginForm'].validate((valid) => {
        if (!valid) {
          console.log('规则校验失败');
          flag = false;
        }
      });
      return flag;
    },
  }
}
</script>

<template>

  <div class="p-login">
    <div class="p-login-area">
      <div class="p-login-area-img">
        <img src="@/assets/login.png" alt="" style="width: 100%;">
      </div>
      <div class="p-login-area-input">
        <el-form class="p-login-area-input-form" :model="loginForm" :rules="loginRules" ref="loginForm">
          <div class="p-login-area-input-title">中国网球协会赛事</div>
          <el-form-item prop="idNumber">
            <el-input v-model="loginForm.idNumber" placeholder="请输入身份证号" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="loginForm.password" placeholder="请输入密码" prefix-icon="el-icon-lock"
                      show-password></el-input>
          </el-form-item>
          <el-form-item prop="validCode">
            <div style="display: flex">
              <el-input v-model="loginForm.validCode" style="flex: 1;" placeholder="请输入验证码" prefix-icon="el-icon-postcard"></el-input>
              <div class="p-login-area-input-valid">
                <ValidCode @update:value="rcValidCode" />
              </div>
            </div>
          </el-form-item>
          <div style="display: flex; margin-bottom: 10px">
            <div style="flex: 1; text-align: left">首次登录，请先<span class="p-login-area-input-jump"> 注册 </span>
            </div>
            <div style="flex: 1; text-align: right"><span class="p-login-area-input-jump">忘记密码？</span></div>
          </div>
          <el-form-item>
            <el-button type="primary" style="width: 100%" @click.native="handleLogin">登 录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

  </div>

</template>

<style scoped>

.p-login {
  height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #d90718;
}

.p-login-area {
  display: flex;
  background-color: white;
  width: 40%;
  height: 40vh;
  border-radius: 10px;
  overflow: hidden;
}

.p-login-area-img {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.p-login-area-input {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.p-login-area-input-form {
  width: 80%
}

.p-login-area-input-title {
  font-size: 20px;
  font-weight: bolder;
  text-align: center;
  margin-top: 10px;
  margin-bottom: 20px;
}

.p-login-area-input-jump {
  color: #1890ff;
  cursor: pointer;
}

.p-login-area-input-valid {
  flex: 1;
  height: 32px;
  display: flex;
  justify-content: center;
}

</style>