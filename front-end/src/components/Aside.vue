<script>
export default {
  name: 'Aside',
  props: {
    isCollapse: Boolean
  },
  data() {
    return {
      breadcrumb: [],
      userInfo: JSON.parse(localStorage.getItem("userInfo") || '{}'),
    }
  },
  watch: {
    $route(to, from) {
      this.handleMenuSelect()
    },
  },
  mounted() {
    this.handleMenuSelect()
  },
  methods: {

    handleMenuSelect() {
      this.updateBreadcrumb();
      this.cbBreadcrumb();
    },

    updateBreadcrumb() {
      const matchedRoutes = this.$route.matched;
      const breadcrumb = [];
      for (const route of matchedRoutes) {
        if (route.meta && route.meta.breadcrumb) {
          breadcrumb.push({
            text: route.meta.breadcrumb,
            to: !route.path && route.name === 'home' ? '/' : route.path,
          });
        }
      }

      this.breadcrumb = breadcrumb;
    },

    cbBreadcrumb() {
      this.$emit('breadcrumb:value', this.breadcrumb)
    },
  }

}
</script>

<template>
  <div class="p-aside-area">

    <div class="logo-img">
      <img src="@/assets/logo.png" alt="" style="width: 50px; height: 50px">
    </div>
    <div class="logo-title">
      <span v-show="!isCollapse">管理系统</span>
    </div>

    <div>
      <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#d90718"
               text-color="#ffffff"
               active-text-color="#fff"
               style="border: none"
               :default-active="$route.path"
               @select="handleMenuSelect">
        <el-menu-item index="/points">
          <i class="el-icon-s-flag" style="color: #ffffff"></i>
          <span slot="title">积分排名</span>
        </el-menu-item>
        <el-submenu index="/charts">
          <template slot="title">
            <i class="el-icon-data-line" style="color: #ffffff"></i>
            <span slot="title">图表展示</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/charts/stay">
              <i class="el-icon-user-solid" style="color: #ffffff"></i>
              <span slot="title">留榜人数</span>
            </el-menu-item>
            <el-menu-item index="/charts/stay-rate">
              <i class="el-icon-s-help" style="color: #ffffff"></i>
              <span slot="title">留榜率</span>
            </el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="/charts/entry">
              <i class="el-icon-user" style="color: #ffffff"></i>
              <span slot="title">新入榜人数</span>
            </el-menu-item>
            <el-menu-item index="/charts/entry-rate">
              <i class="el-icon-help" style="color: #ffffff"></i>
              <span slot="title">新入榜率</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="/regional-competition">
          <i class="el-icon-trophy" style="color: #ffffff"></i>
          <span slot="title">地区赛事</span>
        </el-menu-item>
        <el-menu-item index="/competition-registration" v-if="this.userInfo.role !== 'coach'">
          <i class="el-icon-basketball" style="color: #ffffff"></i>
          <span slot="title">单项比赛报名</span>
        </el-menu-item>
        <el-menu-item index="/coaches-management">
          <i class="el-icon-medal-1" style="color: #ffffff"></i>
          <span slot="title">教练员列表</span>
        </el-menu-item>
        <el-menu-item index="/athletes-management" v-if="this.userInfo.role === 'admin'">
          <i class="el-icon-takeaway-box" style="color: #ffffff"></i>
          <span slot="title">运动员管理</span>
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<style scoped>

.p-aside-area {
  background-color: #d90718;
}

.logo-img {
  height: 150px;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-title {
  height: 40px;
  line-height: 40px;
  font-size: 18px;
  color: white;
  text-align: center;
  margin-top: -20px;
  margin-bottom: 10px;
  transition: all .3s;
}

</style>