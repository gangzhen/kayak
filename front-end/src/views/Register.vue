<script>

import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "Register",
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
      registerForm: {
        username: '',
        idNumber: '',
        password: '',
        confirmPassword: '',
        phoneNumber: '',
        validCode: '',
      },
      validCode: '',
      registerRules: {
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

    handleRegister() {
      if (this.ruleRegisterForm()) {
        //TODO 修改注册接口
        this.$http.post('/login/login', this.registerForm).then(res => {
          if (res.code === '200') {
            localStorage.setItem('userInfo', JSON.stringify(res.data))
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

    ruleRegisterForm() {
      let flag = true;
      this.$refs['registerForm'].validate((valid) => {
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

  <div class="p-register">
    <div class="p-register-area">
      <div class="p-register-area-img">
        <img src="../assets/loginAndRegister.png" alt="" style="width: 100%;">
      </div>
      <div class="p-register-area-input">
        <el-form class="p-register-area-input-form" :model="registerForm" :rules="registerRules" ref="registerForm">
          <div class="p-register-area-input-title">中国网球协会赛事</div>
          <el-form-item prop="idNumber">
            <el-input v-model="registerForm.username" placeholder="请输入姓名" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <el-form-item prop="idNumber">
            <el-input v-model="registerForm.idNumber" placeholder="请输入身份证号" prefix-icon="el-icon-postcard"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="registerForm.password" placeholder="请输入密码" prefix-icon="el-icon-lock"
                      show-password></el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword">
            <el-input v-model="registerForm.confirmPassword" placeholder="请确认密码" prefix-icon="el-icon-lock"
                      show-password></el-input>
          </el-form-item>
          <el-form-item prop="phoneNumber">
            <el-input v-model="registerForm.phoneNumber" placeholder="请输入手机号" prefix-icon="el-icon-mobile-phone"></el-input>
          </el-form-item>
          <el-form-item prop="validCode">
            <div style="display: flex">
              <el-input v-model="registerForm.validCode" style="flex: 1;" placeholder="请输入验证码"
                        prefix-icon="el-icon-postcard"></el-input>
              <div class="p-register-area-input-valid">
                <ValidCode @update:value="rcValidCode"/>
              </div>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 100%" @click.native="handleRegister">注 册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

  </div>

</template>

<style scoped>

.p-register {
  height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #d90718;
}

.p-register-area {
  display: flex;
  background-color: white;
  width: 40%;
  height: 40vh;
  border-radius: 10px;
  overflow: hidden;
}

.p-register-area-img {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.p-register-area-input {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.p-register-area-input-form {
  width: 80%
}

.p-register-area-input-title {
  font-size: 20px;
  font-weight: bolder;
  text-align: center;
  margin-top: 10px;
  margin-bottom: 20px;
}

.p-register-area-input-valid {
  flex: 1;
  height: 32px;
  display: flex;
  justify-content: center;
}

</style>