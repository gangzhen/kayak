<template>
  <div>

    <el-form :inline="true" :model="searchForm">
      <el-form-item label="比赛项目:">
        <el-select v-model="searchForm.item" placeholder="请选择比赛项目" @change="handleItemChange">
          <el-option label="项目一" value="item1"></el-option>
          <el-option label="项目二" value="item2"></el-option>
          <el-option label="项目三" value="item3"></el-option>
          <el-option label="项目四" value="item4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="onAddDialog">新增</el-button>
      </el-form-item>
    </el-form>


    <div class="p-manage">
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            type="index"
            label="序号"
            width="200"
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
        <el-table-column
            label="操作"
            align="center">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="info"
                @click="onEditDialog(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
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


    <el-dialog
        :title="dialogTitle"
        :visible.sync="dialogVisible"
        width="20%"
        :close-on-click-modal="false"
        :show-close="false"
        center>

      <el-form class="p-dialog-form" label-width="80px" :model="dialogData">
        <el-form-item label="姓名:">
          <el-input v-model="dialogData.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄:">
          <el-input v-model="dialogData.age"></el-input>
        </el-form-item>
        <el-form-item label="地区:">
          <el-input v-model="dialogData.region"></el-input>
        </el-form-item>
        <el-form-item label="比赛项目:">
          <el-input v-model="dialogData.item"></el-input>
        </el-form-item>
        <el-form-item label="对应积分:">
          <el-input v-model="dialogData.points"></el-input>
        </el-form-item>
        <el-form-item label="总积分:">
          <el-input v-model="dialogData.points"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="info" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>

export default {
  data() {
    return {
      dialogVisible: false,
      dialogTitle: '',
      totalNum: 30,
      page: 1,
      pageSize: 10,
      dialogData: {},
      searchForm: {
        item: 'item1',
      },
      tableData: [{
        rank: '1',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '2',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '3',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '4',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '5',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '6',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '7',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '8',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '9',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }, {
        rank: '10',
        name: '王阳',
        region: '桦林桦钢',
        points: '100000',
      }],
    }
  },
  methods: {

    onSearch() {
      //TODO 使用page和pageSize分页查询
      // this.tableData = res.tableData
      // this.totalNum = res.totalNum
    },

    onAddDialog() {
      this.dialogTitle = '新 增';
      this.dialogVisible = true;
      this.handleAdd();
    },

    onEditDialog(index, row) {
      this.dialogTitle = '编 辑';
      this.dialogVisible = true;
      this.handleEdit();
    },

    handleAdd() {
      //TODO this.dialogData存储入库
    },

    handleEdit() {
      //TODO this.dialogData更新入库
    },

    handleDelete(index, row) {
      //TODO 根据唯一ID删除库数据
    },

    handleItemChange() {
      console.log(this.searchForm.item)
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

.el-dialog {
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
  border-radius: 10px;
}

.el-dialog__header {
  padding-bottom: 1vh;
  font-weight: bolder;
  background-color: #6E77F2;
}

.el-dialog__body {
  display: flex;
  justify-content: center;
  align-content: center;
}

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

.p-manage {
  height: 84vh;
  margin-top: 1vh;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
  border-radius: 10px;
}

</style>