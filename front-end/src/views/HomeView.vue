<template>
  <div>
    <el-container>
      <!--      侧边栏-->
      <el-aside class="p-aside" :width="asideWidth">
        <Aside :isCollapse="isCollapse"/>
      </el-aside>

      <el-container>
        <!--        头部区域-->
        <el-header>
          <Header v-on:cbCollapse="rcCollapse"/>
        </el-header>
        <!--        主体区域-->
        <el-main>
          <router-view/>
        </el-main>
      </el-container>

    </el-container>
  </div>
</template>
<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Aside from '@/components/Aside.vue'

export default {
  name: 'HomeView',
  components: {
    Header,
    Aside
  },
  data() {
    return {
      asideWidth: '200px',
      isCollapse: false,
    }
  },
  methods: {
    rcCollapse(flag) {
      this.isCollapse = flag;
      this.asideWidth = flag ? '64px' : '200px';
    }
  }
}
</script>

<style>

.p-aside {
  min-height: 100vh;
  background-color: #001529
}

</style>