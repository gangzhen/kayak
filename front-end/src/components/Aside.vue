<script>
export default {
  name: 'Aside',
  props: {
    isCollapse: Boolean
  },
  data() {
    return {
      breadcrumb: [],
    }
  },
  mounted() {
    this.handleMenuSelect(this.$route.path)
  },
  methods: {

    handleMenuSelect(key) {
      this.updateBreadcrumb(key);
      this.cbBreadcrumb();
      console.log(key)
    },

    updateBreadcrumb(key) {
      if (key === '/points') {
        this.breadcrumb = [
          { text: '管理系统', link: '' },
          { text: '积分排名', link: '' },
        ];
      } else if (key === '/charts/stay') {
        this.breadcrumb = [
          { text: '管理系统', link: '' },
          { text: '图表展示', link: '' },
          { text: '留榜人数', link: '' },
        ];
      } else if (key === '/charts/stay-rate') {
        this.breadcrumb = [
          { text: '管理系统', link: '' },
          { text: '图表展示', link: '' },
          { text: '留榜率', link: '' },
        ];
      } else if (key === '/charts/entry') {
        this.breadcrumb = [
          { text: '管理系统', link: '' },
          { text: '图表展示', link: '' },
          { text: '新入榜人数', link: '' },
        ];
      } else if (key === '/charts/entry-rate') {
        this.breadcrumb = [
          { text: '管理系统', link: '' },
          { text: '图表展示', link: '' },
          { text: '新入榜率', link: '' },
        ];
      } else if (key === '/manage') {
        this.breadcrumb = [
          { text: '管理系统', link: '' },
          { text: '信息管理', link: '' },
        ];
      }
    },

    cbBreadcrumb() {
      this.$emit('breadcrumb:value', this.breadcrumb)
    },
  }

}
</script>

<template>
  <div>

    <div class="logo-img">
      <img src="@/assets/logo.png" alt="" style="width: 50px; height: 50px">
    </div>
    <div class="logo-title">
      <span v-show="!isCollapse">管理系统</span>
    </div>

    <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#d90718"
             text-color="rgba(255, 255, 255, 0.8)"
             active-text-color="#fff"
             style="border: none"
             :default-active="$route.path"
             @select="handleMenuSelect">
      <el-menu-item index="/points">
        <i class="el-icon-s-flag" style="color: rgba(255, 255, 255, 0.95)"></i>
        <span slot="title">积分排名</span>
      </el-menu-item>
      <el-submenu index="/charts">
        <template slot="title">
          <i class="el-icon-data-line" style="color: rgba(255, 255, 255, 0.95)"></i>
          <span slot="title">图表展示</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/charts/stay">
            <i class="el-icon-user-solid" style="color: rgba(255, 255, 255, 0.95)"></i>
            <span slot="title">留榜人数</span>
          </el-menu-item>
          <el-menu-item index="/charts/stay-rate">
            <i class="el-icon-s-help" style="color: rgba(255, 255, 255, 0.95)"></i>
            <span slot="title">留榜率</span>
          </el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <el-menu-item index="/charts/entry">
            <i class="el-icon-user" style="color: rgba(255, 255, 255, 0.95)"></i>
            <span slot="title">新入榜人数</span>
          </el-menu-item>
          <el-menu-item index="/charts/entry-rate">
            <i class="el-icon-help" style="color: rgba(255, 255, 255, 0.95)"></i>
            <span slot="title">新入榜率</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/manage">
        <i class="el-icon-takeaway-box" style="color: rgba(255, 255, 255, 0.95)"></i>
        <span slot="title">信息管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped>

.logo-img {
  height: 150px;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-title {
  font-weight: bold;
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