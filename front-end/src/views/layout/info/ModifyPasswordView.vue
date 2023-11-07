<script>
export default {
  name: "ModifyPasswordView",
  data() {

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
      if (value !== this.userPwd.newPwd) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback();
      }
    }

    return {
      userInfo: JSON.parse(localStorage.getItem("userInfo") || '{}'),
      userPwd: {
        id: '',
        oldPwd: '',
        newPwd: '',
        confirmPwd: ''
      },
      dialogVisible: false,
      pwdRules: {
        oldPwd: [
          {required: true, message: '请输入旧密码', trigger: 'blur'},
        ],
        newPwd: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {validator: passwordRule, trigger: 'submit'},
        ],
        confirmPwd: [
          {required: true, message: '请确认密码', trigger: 'blur'},
          {validator: confirmPasswordRule, trigger: 'submit'},
        ],
      },
    }
  },
  methods: {
    handleCancel() {
      // 跳转回根目录
      this.$router.push("/");
    },

    ruleModifiedPwd() {
      let flag = true;
      this.$refs.userPwdRef.validate((valid) => {
        if (!valid) {
          console.log('规则校验失败');
          flag = flag && false
        }
      })
      return flag;
    },

    handleModifyPassword() {
      // 校验密码格式
      this.dialogVisible = this.ruleModifiedPwd();
    },

    handleDialogCancel() {
      this.dialogVisible = false;
    },

    handleConfirm() {
      this.userPwd.id = this.userInfo.id;
      this.$http.put('/user/update-pwd', this.userPwd).then(res => {
        if (res.code === '200') {
          localStorage.removeItem('userInfo')
          this.$message.success('修改成功，请重新登录')
          this.$router.push('/login')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  }
}
</script>

<template>
  <div class="p-main-area">
    <div class="p-pwd">
      <el-form class="p-pwd-form" :model="userPwd" :rules="pwdRules" ref="userPwdRef">
        <div class="p-pwd-form-title">修改密码</div>
        <el-form-item prop="oldPwd">
          <el-input v-model="userPwd.oldPwd" placeholder="请输入旧密码" prefix-icon="el-icon-unlock"
                    show-password></el-input>
        </el-form-item>
        <el-form-item prop="newPwd">
          <el-input v-model="userPwd.newPwd" placeholder="请输入新密码"
                    prefix-icon="el-icon-lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirmPwd">
          <el-input v-model="userPwd.confirmPwd" placeholder="请确认新密码"
                    prefix-icon="el-icon-lock" show-password></el-input>
        </el-form-item>

        <el-form-item>
          <div class="p-pwd-form-btn">
            <el-button style="width: 45%" @click.native="handleCancel">取 消</el-button>
            <el-button type="primary" style="width: 45%" @click.native="handleModifyPassword">修 改</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>

    <el-dialog
        title="确认修改"
        :visible.sync="dialogVisible"
        width="25%"
        :close-on-click-modal="false"
        :show-close="false"
        center>

      <p>
        确认修改密码？
      </p>

      <span slot="footer" class="dialog-footer">
        <el-button @click.native="handleDialogCancel">取 消</el-button>
        <el-button type="primary" @click.native="handleConfirm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped>

.p-pwd {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  width: 60%;
  height: 80%;
}

.p-pwd-form {
  width: 60%;
}

.p-pwd-form-title {
  font-size: 24px;
  font-weight: bolder;
  text-align: center;
  margin-top: 10px;
  margin-bottom: 20px;
}

.p-pwd-form-btn {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

</style>