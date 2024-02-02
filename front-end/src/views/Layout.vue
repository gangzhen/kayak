<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Aside from '@/components/Aside.vue'

export default {
  name: 'Layout',
  components: {
    Header,
    Aside
  },
  data() {
    return {
      asideWidth: '250px',
      isCollapse: false,
      breadcrumb: [],
    }
  },
  methods: {

    rcCollapse(flag) {
      this.isCollapse = flag;
      this.asideWidth = flag ? '64px' : '250px';
    },

    rcBreadcrumb(data) {
      this.breadcrumb = data
    }
  }
}
</script>

<template>
  <div>
    <el-container>
      <!--      侧边栏-->
      <el-aside class="p-aside" :width="asideWidth">
        <Aside :isCollapse="isCollapse" @breadcrumb:value="rcBreadcrumb"/>
      </el-aside>

      <el-container>
        <!--        头部区域-->
        <el-header class="p-header">
          <Header :breadcrumb="breadcrumb" @cbCollapse:value="rcCollapse"/>
        </el-header>
        <!--        主体区域-->
        <el-main class="p-main">
          <router-view/>
        </el-main>
      </el-container>

    </el-container>
  </div>
</template>

<style scoped>

</style>