<script>
export default {
  name: 'Header',
  props: {
    breadcrumb: Array
  },
  data() {
    return {
      isCollapse: false,
      collapseIcon: 'el-icon-s-fold'
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
      console.log("")
    },

    handleModifyPassword() {
      console.log("")
    },

    handleLogout() {
      this.$router.push('/login')
    }
  }

}
</script>

<template>
  <div style="width: 100%;">

    <el-row>
      <el-col style="width: 40px">
        <i :class="collapseIcon" style="font-size: 25px; height: 44px; line-height: 44px" @click="handleCollapse"></i>
      </el-col>
      <el-col :span="2">
        <div style="width: 300px;">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item v-for="item in breadcrumb" :to="item.link" :key="item.text">{{ item.text }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
      </el-col>
      <el-col :span="21">
        <div style="flex: 1; display: flex; align-items: center; justify-content: flex-end">

          <el-dropdown placement="bottom">
            <!--              <img src="@/assets/logo.png" alt="" style="width: 40px; height: 40px">-->
            <span style="font-size: 16px; font-weight: bolder; height: 44px; line-height: 44px">管理员</span>

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

</style>