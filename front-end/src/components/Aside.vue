<template>
  <div>

    <div class="logo-img">
      <img src="@/assets/logo.png" alt="" style="width: 50px; height: 50px">
    </div>
    <div class="logo-title">
      <span v-show="!isCollapse">管理系统</span>
    </div>


    <el-menu :collapse="isCollapse" :collapse-transition="false" router background-color="#d90718"
             text-color="rgba(255, 255, 255, 0.95)"
             active-text-color="#fff"
             style="border: none"
             :default-active="$route.path">
      <el-menu-item index="/points">
        <i class="el-icon-s-flag" style="color: rgba(255, 255, 255, 0.95)"></i>
        <span slot="title">积分排名</span>
      </el-menu-item>
      <el-submenu index="/charts">
        <template slot="title"><i class="el-icon-data-line" style="color: rgba(255, 255, 255, 0.95)"></i>图表展示</template>
        <el-menu-item-group>
          <el-menu-item index="/charts/stay">
            <template slot="title"><i class="el-icon-user-solid" style="color: rgba(255, 255, 255, 0.95)"></i>留榜人数</template>
          </el-menu-item>
          <el-menu-item index="/charts/stay-rate">
            <template slot="title"><i class="el-icon-s-help" style="color: rgba(255, 255, 255, 0.95)"></i>留榜率</template>
          </el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <el-menu-item index="/charts/entry">
            <template slot="title"><i class="el-icon-user" style="color: rgba(255, 255, 255, 0.95)"></i>新入榜人数</template>
          </el-menu-item>
          <el-menu-item index="/charts/entry-rate">
            <template slot="title"><i class="el-icon-help" style="color: rgba(255, 255, 255, 0.95)"></i>新入榜率</template>
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
<script>
export default {
  name: 'Aside',
  props: {
    isCollapse: Boolean
  },
  data() {
    return {}
  }

}
</script>

<style>

.el-menu-item {
  height: 40px !important;
  line-height: 40px !important;
}

.el-menu-item:hover {
  color: #fff !important;
}

.el-submenu .el-menu-item {
  min-width: 192px;
}

.el-menu-item.is-active {
  background-color: #1890ff !important;
  border-radius: 6px;
  margin: 4px;
}

.el-menu-item.is-active i {
  margin-left: -4px;
}

.el-menu-item.is-active .el-tooltip i {
  margin-left: -4px;
}

.el-aside {
  transition: width .3s;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
}

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