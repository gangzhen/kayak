<script>
export default {
  name: 'Header',
  props: {
    breadcrumb: Array,
  },
  data() {
    return {
      isCollapse: false,
      collapseIcon: 'el-icon-s-fold',
      userInfo: JSON.parse(localStorage.getItem("userInfo") || "{}")
    }
  },
  methods: {

    handleCollapse() {
      this.isCollapse = !this.isCollapse;
      this.collapseIcon = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold';
      this.cbCollapse();
    },

    // 父子组件回调父组件方法
    cbCollapse() {
      this.$emit('cbCollapse:value', this.isCollapse);
    },

    handlePersonalInfo() {
      this.$router.push('/information')
    },

    handleModifyPassword() {
      this.$router.push('/modify-password')
    },

    handleLogout() {
      let userId = this.userInfo.id;
      this.$http.post(`/user/logout/${userId}`).then(res => {
        if (res.code === '200') {
          localStorage.removeItem("userInfo")
          localStorage.removeItem("token")
          this.$router.push('/login')
          this.$message.success("已退出登录")
        } else {
          this.$message.error(res.msg)
        }
      })

    }
  }

}
</script>

<template>
  <div class="p-header-area">

    <el-row>
      <el-col style="width: 2%">
        <i :class="collapseIcon" style="font-size: 25px; height: 44px; line-height: 44px" @click="handleCollapse"></i>
      </el-col>
      <el-col  style="width: 10%">
        <div style="width: 300px;">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item v-for="item in breadcrumb" :to="item.to" :key="item.text">{{ item.text }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
      </el-col>
      <el-col style="width: 86%">
        <div style="flex: 1; display: flex; align-items: center; justify-content: flex-end">

          <el-dropdown placement="bottom">
            <!--              <img src="@/assets/logo.png" alt="" style="width: 40px; height: 40px">-->
            <span style="font-size: 16px; font-weight: bolder; height: 44px; line-height: 44px">{{ userInfo.username }}</span>

            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handlePersonalInfo">个人信息</el-dropdown-item>
              <el-dropdown-item @click.native="handleModifyPassword">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="handleLogout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<style scoped>

.p-header-area {
  width: 100%;
}

</style>