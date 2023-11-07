<script>
export default {
  name: "RegionalCompetitionView",
  data() {
    return {
      searchForm: {
        region: '',
      },
      tableData: [
        {
          competition: 'A1000',
          level: 'A1000',
          region: 'region1',
          year: '2023',
        }
      ],
      dialogTitle: '',
      dialogVisible: false,
      dialogData: {},
      competitionRules: {},
      page: 1,
      pageSize: 10,
      totalNum: 10,
    }
  },
  methods: {

    onSearch() {
      console.log(1)
      //TODO 后端接口调用
    },

    handleRegionChange() {
      console.log(this.searchForm.region)
      this.onSearch();
    },

    onAddDialog() {
      this.dialogTitle = '新增'
      this.dialogVisible = true
    },

    onEditDialog(index, row) {
      this.dialogData = {
        competition: row.competition,
        level: row.level,
        region: row.region,
        year: row.year,
      }
      this.dialogTitle = '编辑'
      this.dialogVisible = true
    },

    handleAddOrEdit() {
      if (this.ruleFormData()) {
        if (this.dialogTitle === '新 增') {
          this.handleAdd();
        } else if (this.dialogTitle === '编 辑') {
          this.handleEdit();
        }
      }
    },

    handleAdd() {
      //TODO 调用后端接口
      console.log(1)
      this.handleCancel();
    },

    handleEdit() {
      //TODO 调用后端接口
      console.log(1)
      this.handleCancel();
    },

    handleCancel() {
      this.dialogVisible = false;
      this.dialogTitle = '';
      this.dialogData = {};
      this.$refs.competitionDialogDataRef.resetFields();
      this.onSearch();
    },

    onDelete(index, row) {
      console.log(1)
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

<template>
  <div class="p-main-area">

    <div class="p-main-table-search">
      <el-form :inline="true" :model="searchForm">
        <el-form-item label="地区:">
          <el-select v-model="searchForm.region" placeholder="请选择赛事地区" @change="handleRegionChange">
            <el-option v-for="option in $regionOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native="onAddDialog">新增</el-button>
        </el-form-item>
      </el-form>
    </div>


    <div class="p-main-table">
      <div class="p-main-table-data">
        <el-table
            :data="tableData"
            style="width: 100%">
          <el-table-column
              type="index"
              label="序号"
              width="100"
              align="center">
          </el-table-column>
          <el-table-column
              prop="competition"
              label="赛事"
              align="center">
          </el-table-column>
          <el-table-column
              prop="level"
              label="赛事级别"
              align="center">
          </el-table-column>
          <el-table-column
              prop="region"
              label="地区"
              align="center">
          </el-table-column>
          <el-table-column
              prop="year"
              label="年份"
              align="center">
          </el-table-column>
          <el-table-column
              label="操作"
              width="250"
              align="center">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="primary"
                  @click.native="onEditDialog(scope.$index, scope.row)">编辑
              </el-button>

              <el-popconfirm
                  title="确定删除比赛记录吗？"
                  @confirm="onDelete(scope.$index, scope.row)"
              >
                <el-button style="margin-left: 5px" size="mini" type="danger" slot="reference">删除</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <div class="p-main-table-page">
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


    <el-dialog
        :title="dialogTitle"
        :visible.sync="dialogVisible"
        width="25%"
        :close-on-click-modal="false"
        :show-close="false"
        center>

      <el-form label-width="90px" :model="dialogData" :rules="competitionRules" ref="competitionDialogDataRef">
        <el-form-item label="赛事:" prop="name">
          <el-input v-model="dialogData.competition"></el-input>
        </el-form-item>
        <el-form-item label="赛事级别:" prop="level">
          <el-select v-model="dialogData.level" placeholder="请选择比赛项目">
            <el-option v-for="option in $levelOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地区:">
          <el-select v-model="dialogData.region" placeholder="请选择赛事地区" @change="handleRegionChange">
            <el-option v-for="option in $regionOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年份:">
          <el-date-picker
              v-model="dialogData.year"
              type="year"
              value-format="yyyy"
              placeholder="选择年份">
          </el-date-picker>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click.native="handleCancel()">取 消</el-button>
        <el-button type="primary" @click.native="handleAddOrEdit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>