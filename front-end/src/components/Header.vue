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
    }
  }

}
</script>

<template>
  <div>

    <el-row>
      <el-col :span="3">
        <div style="width: 25px">
          <span :class="collapseIcon" style="font-size: 25px;" @click="handleCollapse"></span>
        </div>
      </el-col>
      <el-col :span="20">
        <div style="width: 300px;">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item v-for="item in breadcrumb" :to="item.link" :key="item.text">{{ item.text }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<style scoped>

</style>