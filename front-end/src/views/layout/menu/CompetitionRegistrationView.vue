<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "CompetitionRegistrationView",
  components: {ValidCode},
  data() {
    return {
      dialogVisible: false,
      userInfo: {},
      competitionRules: {},
    }
  },
  methods: {
    handleCancel() {
      console.log(1)
    },

    handleModifyInfo() {
      console.log(1)
    },

    handleDialogCancel() {
      console.log(1)
    },

    handleConfirm() {
      console.log()
    },

  }
}
</script>

<template>
  <div class="p-main-area">
    <div class="p-info">
      <el-form class="p-info-form" :model="userInfo" :rules="competitionRules" ref="competitionRef">
        <div class="p-info-form-title">单项比赛报名</div>
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
            <el-option v-for="option in $roleOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
          <el-select v-model="userInfo.role" v-if="userInfo.role === 'admin'" placeholder="请选择角色" style="width: 100%" :disabled="true">
            <template #prefix>
                <span style="padding-left: 5px;">
                  <i class="el-icon-user-solid"></i>
                </span>
            </template>
            <el-option v-for="option in $adminRoleOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
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