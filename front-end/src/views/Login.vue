<script>

import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "Login",
  components: {
    ValidCode
  },
  data() {

    const validateCodeRule = (rule, value, callback) => {
      if (value !== this.validCode) {
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
          {required: true, message: '请输入验证码', trigger: 'blur'},
          {validator: validateCodeRule, trigger: 'submit'},
        ],
      },
    }
  },
  methods: {

    handleLogin() {
      if (this.ruleLoginForm()) {
        this.$http.post('/user/login', this.loginForm).then(res => {
          if (res.code === '200') {
            localStorage.setItem('userInfo', JSON.stringify(res.data))
            this.$router.push('/points')
            this.$message.success('登录成功')
          } else {
            this.$message.error(res.msg)
          }
        })
      }
    },

    handleRegister() {
      this.$router.push('/register')
    },

    rcValidCode(code) {
      this.validCode = code;
    },

    ruleLoginForm() {
      let flag = true;
      this.$refs['loginForm'].validate((valid) => {
        if (!valid) {
          console.log('规则校验失败');
          flag = flag && false
        }
      });
      return flag;
    },
  }
}
</script>

<template>

  <div class="p-lr">
    <div class="p-lr-area">
      <div class="p-lr-area-img">
        <img src="../assets/loginAndRegister.png" alt="" style="width: 100%;">
      </div>
      <div class="p-lr-area-input">
        <el-form class="p-lr-area-input-form" :model="loginForm" :rules="loginRules" ref="loginForm">
          <div class="p-lr-area-input-title">中国网球协会赛事</div>
          <el-form-item prop="idNumber">
            <el-input v-model="loginForm.idNumber" placeholder="请输入身份证号"
                      prefix-icon="el-icon-postcard"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="loginForm.password" placeholder="请输入密码" prefix-icon="el-icon-lock"
                      show-password></el-input>
          </el-form-item>
          <el-form-item prop="validCode">
            <div style="display: flex">
              <el-input v-model="loginForm.validCode" style="flex: 1;" placeholder="请输入验证码"
                        prefix-icon="el-icon-circle-check"></el-input>
              <div class="p-lr-area-input-valid">
                <ValidCode @update:value="rcValidCode"/>
              </div>
            </div>
          </el-form-item>
          <div style="display: flex; margin-bottom: 10px">
            <div style="flex: 1; text-align: left">首次登录，请先<span class="p-lr-area-input-jump"
                                                                      @click="handleRegister">注册</span>
            </div>
            <div style="flex: 1; text-align: right"><span class="p-lr-area-input-jump">忘记密码？</span></div>
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

</style>