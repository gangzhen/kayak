<script>

import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "Register",
  components: {
    ValidCode
  },
  data() {

    const usernameRule = (rule, value, callback) => {
      //1.可以是中文
      //2.可以是英文，允许输入点（英文名字中的那种点）， 允许输入空格
      //3.中文和英文不能同时出现
      //4.长度在20个字符以内
      const regName = /^([\u4e00-\u9fa5]{2,20}|[a-zA-Z.\s]{2,20})$/;
      if (regName.test(value)) {
        //正确的名字
        return callback();
      }
      callback(new Error("姓名格式有误"));
    };

    const idNumberRule = (rule, value, callback) => {
      const regIDNumber = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(([0-2][1-9])|10|20|30|31)\d{3}(\d|X|x)$/;
      if (regIDNumber.test(value)) {
        return callback();
      }
      callback(new Error("身份证号格式有误"));
    }

    const passwordRule = (rule, value, callback) => {
      // 使用正则表达式检查密码是否满足要求
      const regPassword = /^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$).{6,18}$/;
      if (regPassword.test(value)) {
        callback();
      } else {
        callback(new Error('密码必须为6-18位的字母、数字、符号任意两种组合'));
      }
    }

    const confirmPasswordRule = (rule, value, callback) => {
      if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback();
      }
    }

    const phoneNumberRule = (rule, value, callback) => {
      const regPhoneNumber = /^(0|86|17951)?(13[0-9]|15[012356789]|17[3678]|18[0-9]|14[57])[0-9]{8}$/;
      if (regPhoneNumber.test(value)) {
        return callback();
      }
      callback(new Error("手机号格式有误"));
    };

    const validateCodeRule = (rule, value, callback) => {
      if (value !== this.validCode) {
        callback(new Error('验证码有误'))
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
        role: '',
        validCode: '',
      },
      validCode: '',
      registerRules: {
        username: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {validator: usernameRule, trigger: 'submit'},
        ],
        idNumber: [
          {required: true, message: '请输入身份证号', trigger: 'blur'},
          {validator: idNumberRule, trigger: 'submit'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {validator: passwordRule, trigger: 'submit'},
        ],
        confirmPassword: [
          {required: true, message: '请确认密码', trigger: 'blur'},
          {validator: confirmPasswordRule, trigger: 'submit'},
        ],
        phoneNumber: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {validator: phoneNumberRule, trigger: 'submit'},
        ],
        role: [
          {required: true, message: '请选择角色', trigger: 'change'},
        ],
        validCode: [
          {required: true, message: '请输入验证码', trigger: 'blur'},
          {validator: validateCodeRule, trigger: 'submit'},
        ],
      },
    }
  },
  methods: {

    handleRegister() {
      if (this.ruleRegisterForm()) {
        //TODO 修改注册接口
        this.$http.post('/user/register', this.registerForm).then(res => {
          if (res.code === '200') {
            this.$router.push('/login')
            this.$message.success('注册成功')
          } else {
            this.$message.error(res.msg)
          }
        })
      }

    },

    handleLogin() {
      this.$router.push('/login')
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
          <el-form-item prop="username">
            <el-input v-model="registerForm.username" placeholder="请输入姓名" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
          <el-form-item prop="idNumber">
            <el-input v-model="registerForm.idNumber" placeholder="请输入身份证号"
                      prefix-icon="el-icon-postcard"></el-input>
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
            <el-input v-model="registerForm.phoneNumber" placeholder="请输入手机号">
              <template slot="prepend">
                <div style="color: #c1c4cc">
                  <i class="el-icon-mobile-phone"></i> <span>+86</span>
                </div>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="role">
            <el-select v-model="registerForm.role" placeholder="请选择角色" style="width: 100%">
              <template #prefix>
                <span style="padding-left: 5px;">
                  <i class="el-icon-user-solid"></i>
                </span>
              </template>
              <el-option label="运动员" value="athlete"></el-option>
              <el-option label="教练员" value="coach"></el-option>
            </el-select>
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

          <div style="display: flex; margin-bottom: 10px">
            <div style="flex: 1; text-align: left">已有账号？请<span class="p-register-area-input-jump"
                                                                    @click="handleLogin">登录</span>
            </div>
          </div>

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
  height: 50vh;
  border-radius: 10px;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
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
  font-size: 24px;
  font-weight: bolder;
  text-align: center;
  margin-top: 10px;
  margin-bottom: 20px;
}

.p-register-area-input-jump {
  color: #1890ff;
  cursor: pointer;
}

.p-register-area-input-valid {
  flex: 1;
  height: 32px;
  display: flex;
  justify-content: center;
}

</style>