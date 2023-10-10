<template>
  <div>

    <el-row>
      <el-col :span="4">
        <div style="width: 40px">
          <span :class="collapseIcon" style="font-size: 25px;" @click="handleCollapse"></span>
        </div>
      </el-col>
      <el-col :span="20">
        <div style="width: 200px;">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">管理系统</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/points' }">积分排名</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
      </el-col>
    </el-row>

  </div>
</template>
<script>
export default {
  name: 'Header',
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
      this.$emit('cbCollapse', this.isCollapse);
    }
  }

}
</script>

<style>

.el-header {
  display: flex;
  align-items: center;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
}

.el-breadcrumb {
  font-size: 16px;
  height: 26px;
  line-height: 26px;
}

</style>