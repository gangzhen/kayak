<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "UserInformationView",
  components: {ValidCode},
  data() {

    const usernameRule = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入姓名"));
      }
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
      if (!value) {
        return callback(new Error("请输入身份证号"));
      }

      const regIDNumber = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(([0-2][1-9])|10|20|30|31)\d{3}(\d|X|x)$/;
      if (regIDNumber.test(value)) {
        return callback();
      }
      callback(new Error("身份证号格式有误"));
    }

    const phoneNumberRule = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入手机号"));
      }

      const regPhoneNumber = /^(0|86|17951)?(13[0-9]|15[012356789]|17[3678]|18[0-9]|14[57])[0-9]{8}$/;
      if (regPhoneNumber.test(value)) {
        return callback();
      }
      callback(new Error("手机号格式有误"));
    };

    const validateCodeRule = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入验证码"));
      }

      if (value !== this.validCode) {
        callback(new Error('验证码有误'))
      } else {
        callback();
      }
    }

    return {
      userId: JSON.parse(localStorage.getItem("userInfo") || '{}').id,
      userInfo: {},
      validCode: '',
      dialogVisible: false,
      modifyRule: {
        username: [
          {validator: usernameRule, trigger: 'submit'},
        ],
        idNumber: [
          {validator: idNumberRule, trigger: 'submit'},
        ],
        phoneNumber: [
          {validator: phoneNumberRule, trigger: 'submit'},
        ],
        role: [
          {required: true, message: '请选择角色', trigger: 'submit'},
        ],
        validCode: [
          {validator: validateCodeRule, trigger: 'submit'},
        ],
      },
    }
  },
  created() {
    this.getUserInfo();
  },
  methods: {

    // 回调子组件验证码
    rcValidCode(code) {
      this.validCode = code;
    },

    // 调用子组件的刷新方法
    refreshValidCode() {
      this.$refs.validCodeRef.refreshIdentifyCode();
    },

    // 信息修改校验规则
    ruleModifiedInfo() {
      let flag = true;
      this.$refs['userInfo'].validate((valid) => {
        if (!valid) {
          console.log('规则校验失败');
          flag = flag && false
        }
      });
      return flag;
    },

    // 获取用户信息
    getUserInfo() {
      // 调用后端接口获取用户信息
      this.$http.get(`/user/search/${this.userId}`).then(res => {
        if (res.code === '200') {
          this.userInfo = res.data
        }
      })
    },

    // 取消修改信息，返回首页
    handleCancel() {
      // 跳转回根目录
      this.$router.push("/");
    },

    // 打开弹框修改个人信息，校验输入是否有问题
    handleModifyInfo() {
      if (this.ruleModifiedInfo()) {
        this.dialogVisible = true;
        this.refreshValidCode()
      } else {
        this.dialogVisible = false;
      }
    },

    // 关闭弹框
    handleDialogCancel() {
      this.dialogVisible = false;
      this.userInfo.validCode = '';
      this.userInfo.password = '';
    },

    // 确认修改信息
    handleConfirm() {
      // 调用后端接口修改信息
      this.$http.put('/user/update', this.userInfo).then(res => {
        if (res.code === '200') {
          localStorage.setItem('userInfo', JSON.stringify(res.data))
          this.dialogVisible = false
          this.userInfo.validCode = '';
          this.userInfo.password = '';
          this.$message.success('修改成功')
        } else {
          this.$message.error(res.msg)
        }
      })
    },

  },
}
</script>

<template>
  <div class="p-main-area">
    <div class="p-info">
      <el-form class="p-info-form" :model="userInfo" ref="userInfo" :rules="modifyRule">
        <div class="p-info-form-title">个人信息</div>
        <el-form-item prop="username">
          <el-input v-model="userInfo.username" placeholder="请输入姓名" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="idNumber">
          <el-input v-model="userInfo.idNumber" placeholder="请输入身份证号"
                    prefix-icon="el-icon-postcard"></el-input>
        </el-form-item>
        <el-form-item prop="phoneNumber">
          <el-input v-model="userInfo.phoneNumber" placeholder="请输入手机号">
            <template slot="prepend">
              <div style="color: #c1c4cc">
                <i class="el-icon-mobile-phone"></i> <span>+86</span>
              </div>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="role">
          <el-select v-model="userInfo.role" v-if="userInfo.role !== 'admin'" placeholder="请选择角色" style="width: 100%">
            <template #prefix>
                <span style="padding-left: 5px;">
                  <i class="el-icon-user-solid"></i>
                </span>
            </template>
            <el-option label="运动员" value="athlete"></el-option>
            <el-option label="教练员" value="coach"></el-option>
          </el-select>
          <el-select v-model="userInfo.role" v-if="userInfo.role === 'admin'" placeholder="请选择角色" style="width: 100%" :disabled="true">
            <template #prefix>
                <span style="padding-left: 5px;">
                  <i class="el-icon-user-solid"></i>
                </span>
            </template>
            <el-option label="管理员" value="admin"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="validCode">
          <div style="display: flex">
            <el-input v-model="userInfo.validCode" style="flex: 1;" placeholder="请输入验证码"
                      prefix-icon="el-icon-circle-check"></el-input>
            <div class="p-lr-area-input-valid">
              <ValidCode  ref="validCodeRef" @update:value="rcValidCode"/>
            </div>
          </div>
        </el-form-item>
        <el-form-item>
          <div class="p-info-form-btn">
            <el-button style="width: 45%" @click.native="handleCancel">取 消</el-button>
            <el-button type="primary" style="width: 45%" @click.native="handleModifyInfo">修 改</el-button>
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

      <el-form label-width="90px" :model="userInfo">
        <el-form-item label="密码" prop="idNumber">
          <el-input v-model="userInfo.password" placeholder="请输入密码确认修改"
                    prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click.native="handleDialogCancel">取 消</el-button>
        <el-button type="primary" @click.native="handleConfirm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped>

.p-info {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  width: 60%;
  height: 80%;
}

.p-info-form {
  width: 60%;
}

.p-info-form-title {
  font-size: 24px;
  font-weight: bolder;
  text-align: center;
  margin-top: 10px;
  margin-bottom: 20px;
}

.p-info-form-btn {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

</style>