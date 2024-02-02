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
      <span v-if="!isCollapse">赛艇皮划艇赛事管理系统</span>
    </div>

    <div>
      <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#d90718"
               text-color="#ffffff"
               active-text-color="#fff"
               style="border: none"
               :default-active="$route.path"
               @select="handleMenuSelect">

        <el-submenu index="/coaches-management" v-if="true">
          <template slot="title">
            <i class="el-icon-info" style="color: #ffffff"></i>
            <span slot="title">人员信息</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/coaches-management/1">
              <i class="el-icon-user-solid" style="color: #ffffff"></i>
              <span slot="title">赛艇</span>
            </el-menu-item>
            <el-menu-item index="/coaches-management/2">
              <i class="el-icon-user" style="color: #ffffff"></i>
              <span slot="title">皮划艇</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="/regional-competition" v-if="true">
          <template slot="title">
            <i class="el-icon-date" style="color: #ffffff"></i>
            <span slot="title">赛事日历</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/regional-competition/1">
              <i class="el-icon-message-solid" style="color: #ffffff"></i>
              <span slot="title">赛艇</span>
            </el-menu-item>
            <el-menu-item index="/regional-competition/2">
              <i class="el-icon-bell" style="color: #ffffff"></i>
              <span slot="title">皮划艇</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="/competition-registration" v-if="true">
          <template slot="title">
            <i class="el-icon-document" style="color: #ffffff"></i>
            <span slot="title">赛事报名</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/competition-registration/1">
              <i class="el-icon-s-tools" style="color: #ffffff"></i>
              <span slot="title">赛艇</span>
            </el-menu-item>
            <el-menu-item index="/competition-registration/2">
              <i class="el-icon-setting" style="color: #ffffff"></i>
              <span slot="title">皮划艇</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="/athletes-management" v-if="true">
          <template slot="title">
            <i class="el-icon-data-board" style="color: #ffffff"></i>
            <span slot="title">非积分赛成绩公告</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/athletes-management/1">
              <i class="el-icon-s-help" style="color: #ffffff"></i>
              <span slot="title">赛艇</span>
            </el-menu-item>
            <el-menu-item index="/athletes-management/2">
              <i class="el-icon-help" style="color: #ffffff"></i>
              <span slot="title">皮划艇</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="/points" v-if="true">
          <template slot="title">
            <i class="el-icon-data-line" style="color: #ffffff"></i>
            <span slot="title">积分赛成绩公告</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/points/1">
              <i class="el-icon-s-help" style="color: #ffffff"></i>
              <span slot="title">赛艇</span>
            </el-menu-item>
            <el-menu-item index="/points/2">
              <i class="el-icon-help" style="color: #ffffff"></i>
              <span slot="title">皮划艇</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>


<!--        <el-menu-item index="/points">-->
<!--          <i class="el-icon-s-flag" style="color: #ffffff"></i>-->
<!--          <span slot="title">积分排名</span>-->
<!--        </el-menu-item>-->
<!--        <el-submenu index="/charts" v-if="false">-->
<!--          <template slot="title">-->
<!--            <i class="el-icon-data-line" style="color: #ffffff"></i>-->
<!--            <span slot="title">图表展示</span>-->
<!--          </template>-->
<!--          <el-menu-item-group>-->
<!--            <el-menu-item index="/charts/stay">-->
<!--              <i class="el-icon-user-solid" style="color: #ffffff"></i>-->
<!--              <span slot="title">留榜人数</span>-->
<!--            </el-menu-item>-->
<!--            <el-menu-item index="/charts/stay-rate">-->
<!--              <i class="el-icon-s-help" style="color: #ffffff"></i>-->
<!--              <span slot="title">留榜率</span>-->
<!--            </el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--          <el-menu-item-group>-->
<!--            <el-menu-item index="/charts/entry">-->
<!--              <i class="el-icon-user" style="color: #ffffff"></i>-->
<!--              <span slot="title">新入榜人数</span>-->
<!--            </el-menu-item>-->
<!--            <el-menu-item index="/charts/entry-rate">-->
<!--              <i class="el-icon-help" style="color: #ffffff"></i>-->
<!--              <span slot="title">新入榜率</span>-->
<!--            </el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--        </el-submenu>-->
<!--        <el-menu-item index="/regional-competition">-->
<!--          <i class="el-icon-trophy" style="color: #ffffff"></i>-->
<!--          <span slot="title">赛事信息</span>-->
<!--        </el-menu-item>-->
<!--        <el-menu-item index="/competition-registration" v-if="this.userInfo.role !== 'coach'">-->
<!--          <i class="el-icon-basketball" style="color: #ffffff"></i>-->
<!--          <span slot="title">单项比赛报名</span>-->
<!--        </el-menu-item>-->
<!--        <el-menu-item index="/coaches-management" v-if="false">-->
<!--          <i class="el-icon-medal-1" style="color: #ffffff"></i>-->
<!--          <span slot="title">教练员列表</span>-->
<!--        </el-menu-item>-->
<!--        <el-menu-item index="/athletes-management" v-if="userInfo.role === 'admin'">-->
<!--          <i class="el-icon-takeaway-box" style="color: #ffffff"></i>-->
<!--          &lt;!&ndash;          <span slot="title">比赛积分列表</span>&ndash;&gt;-->
<!--          <span slot="title">单项比赛排名</span>-->
<!--        </el-menu-item>-->
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