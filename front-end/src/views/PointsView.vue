<template>
  <div>

    <el-form :inline="true" :model="searchForm">
      <el-form-item label="性别:">
        <el-select v-model="searchForm.gender" placeholder="请选择性别" @change="handleGenderChange">
          <el-option label="男" value="male"></el-option>
          <el-option label="女" value="female"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="排序:">
        <el-select v-model="searchForm.sort" placeholder="请选择排序" @change="handleSortChange">
          <el-option label="按积分由高到低" value="htl"></el-option>
          <el-option label="按积分由低到高" value="lth"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="姓名:">
        <el-input v-model="searchForm.name" placeholder="请输入姓名" clearable></el-input>
      </el-form-item>
<!--      <el-form-item label="年龄:">-->
<!--        <el-input v-model="searchForm.age" placeholder="请输入年龄" clearable></el-input>-->
<!--      </el-form-item>-->
      <el-form-item label="地区:">
        <el-input v-model="searchForm.region" placeholder="请输入地区" clearable></el-input>
      </el-form-item>
      <el-form-item label="日期:">
        <el-date-picker
            v-model="searchForm.year"
            type="year"
            value-format="yyyy"
            placeholder="选择日期"
            clearable
            @change="handleYearChange">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="onSearch">查询</el-button>
      </el-form-item>
    </el-form>

    <div class="p-table">
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            prop="rank"
            label="排名"
            width="250"
            align="center">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            align="center">
        </el-table-column>
        <el-table-column
            prop="region"
            label="地区"
            align="center">
        </el-table-column>
        <el-table-column
            prop="points"
            label="积分"
            align="center">
        </el-table-column>
      </el-table>

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="page"
          :page-size="pageSize"
          :total="totalNum"
          background
          layout="prev, pager, next">
      </el-pagination>
    </div>


  </div>
</template>
<script>
export default {
  data() {
    return {
      searchForm: {
        gender: 'male',
        sort: 'htl',
        name: '',
        age: '',
        region: '',
        year: new Date().getFullYear().toString()
      },
      totalNum: 30,
      page: 1,
      pageSize: 10,
      tableData: [],
      // tableData: [{
      //   rank: '1',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '2',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '3',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '4',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '5',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '6',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '7',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '8',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '9',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }, {
      //   rank: '10',
      //   name: '王阳',
      //   region: '桦林桦钢',
      //   points: '100000',
      // }],
    }
  },
  created() {
    this.onSearch();
  },
  methods: {

    onSearch() {
      // 使用page和pageSize分页查询
      this.searchForm["page"] = this.page;
      this.searchForm["pageSize"] = this.pageSize;
      this.$http.post("/points/search-rank", this.searchForm).then(res => {
        this.tableData = res.data.records
        this.totalNum = res.data.total
      })
    },

    handleGenderChange() {
      console.log(this.searchForm.item)
      this.onSearch();
    },

    handleSortChange() {
      console.log(this.searchForm.sort)
      this.onSearch();
    },

    handleYearChange() {
      console.log(this.searchForm.year)
      this.onSearch();
    },

    handleSizeChange(val) {
      this.pageSize = val;
      console.log(`每页 ${val} 条`);
      this.onSearch();
    },

    handleCurrentChange(val) {
      this.page = val;
      console.log(`当前页: ${val}`);
      this.onSearch();
    },

  }
}

</script>

<style>

.el-form {
  margin: 1vh 10px;
}

.el-form-item {
  font-weight: bolder;
}

.el-input__inner {
  width: 180px;
}

.el-date-editor.el-input, .el-date-editor.el-input__inner {
  width: 180px;
}

.el-table {
  height: 78vh;
  font-size: 14px;
  padding-top: 1vh;
  padding-left: 3vh;
  padding-right: 3vh;
}

.el-table__header-wrapper {
  font-size: 16px;
}

.el-table__row {
  height: 7.4vh;
}

.el-pagination {
  margin-top: 1vh;
  display: flex;
  justify-content: center;
}

.el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: #6E77F2;
  color: #fff;
}

.p-table {
  height: 84vh;
  margin-top: 1vh;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
  border-radius: 10px;
}

</style>