<script>
export default {
  name: "RegionalCompetitionView01",
  data() {
    return {
      searchForm: {
        year: new Date().getFullYear().toString(),
        region: '',
      },
      tableData: [],
      dialogTitle: '',
      dialogVisible: false,
      dialogData: {
        competition: '',
        level: '',
        region: '',
        year: ''
      },
      competitionRules: {
        competition: [
          {required: true, message: '请输入赛事', trigger: 'blur'},
          {required: true, message: '请输入赛事', trigger: 'submit'},
        ],
        level: [
          {required: true, message: '请选择赛事级别', trigger: 'change'},
          {required: true, message: '请选择赛事级别', trigger: 'submit'},
        ],
        region: [
          {required: true, message: '请选择赛事地区', trigger: 'change'},
          {required: true, message: '请选择赛事地区', trigger: 'submit'},
        ],
        year: [
          {required: true, message: '请选择赛事年份', trigger: 'change'},
          {required: true, message: '请选择赛事年份', trigger: 'submit'},
        ],
      },
      page: 1,
      pageSize: 10,
      totalNum: 10,
      userInfo: JSON.parse(localStorage.getItem("userInfo") || '{}'),
    }
  },
  created() {
    this.onSearch()
  },
  methods: {

    onSearch() {
      this.searchForm['page'] = this.page;
      this.searchForm['pageSize'] = this.pageSize;
      //后端查询接口调用
      this.$http.post('/region-competition/search', this.searchForm).then(res => {
        this.tableData = res.data.records
        this.totalNum = res.data.total
      })
    },

    onDelete(index, row) {
      this.handleDelete(row.id);
    },

    onAddDialog() {
      this.dialogData['year'] = new Date().getFullYear().toString();
      this.dialogTitle = '新 增'
      this.dialogVisible = true
    },

    onEditDialog(index, row) {
      this.dialogData = {
        competition: row.competition,
        level: row.level,
        region: row.region,
        year: row.year.toString(),
      }
      this.dialogTitle = '编 辑'
      this.dialogVisible = true
    },

    ruleFormData() {
      let flag = true;
      this.$refs.competitionDialogDataRef.validate((valid) => {
        if (!valid) {
          console.log('规则校验失败');
          flag = flag && false
        }
      });
      return flag;
    },

    handleYearChange() {
      this.onSearch();
    },

    handleRegionChange() {
      this.onSearch();
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
      //后端新增接口调用
      this.$http.post('/region-competition/add', this.dialogData).then(res => {
        if (res.code === '200') {
          this.$message.success('添加成功');
          this.handleCancel();
        }
      })
    },

    handleEdit() {
      //后端更新接口调用
      this.$http.put('/region-competition/update', this.dialogData).then(res => {
        if (res.code === '200') {
          this.$message.success('更新成功');
          this.handleCancel();
        }
      })
    },

    handleDelete(id) {
      //后端删除接口调用
      this.$http.delete(`/region-competition/delete/${id}`).then(res => {
        if (res.code === "200") {
          this.$message.success('删除成功');
          this.onSearch();
        }
      })
    },

    handleCancel() {
      // 先重置表单数据
      this.dialogData = {
        competition: '',
        level: '',
        region: '',
        year: ''
      };

      // 然后关闭对话框并重置表单校验状态
      this.$nextTick(() => {
        if (this.$refs.competitionDialogDataRef) {
          this.$refs.competitionDialogDataRef.resetFields();
        }
        this.dialogVisible = false;
        this.dialogTitle = '';
      });

      // 重新加载搜索结果
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

    handleLevelDisplay(row, column, value) {
      if (!value) return '';
      const region = this.$levelOptions.find(option => option.value === value);
      return region ? region.label : '';
    },

    handleRegionDisplay(row, column, value) {
      if (!value) return '';
      const region = this.$regionOptions.find(option => option.value === value);
      return region ? region.label : '';
    },

  }
}
</script>

<template>
  <div class="p-main-area">

    <div class="p-main-table-search">
      <el-form :inline="true" :model="searchForm">
        <el-form-item label="年份:">
          <el-date-picker
              v-model="searchForm.year"
              type="year"
              value-format="yyyy"
              placeholder="选择年份"
              :clearable="false"
              :editable="false"
              @change="handleYearChange">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="地区:">
          <el-select v-model="searchForm.region" placeholder="请选择赛事地区" @change="handleRegionChange" clearable>
            <el-option v-for="option in $regionOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-if="userInfo.role === 'admin'" @click.native="onAddDialog">新增</el-button>
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
              :formattrer="handleLevelDisplay"
              align="center">
          </el-table-column>
          <el-table-column
              prop="region"
              label="地区"
              :formatter="handleRegionDisplay"
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
              align="center"
              v-if="userInfo.role === 'admin'">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="primary"
                  @click.native="onEditDialog(scope.$index, scope.row)">编辑
              </el-button>

              <el-popconfirm
                  title="确定删除地区赛事吗？"
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
        <el-form-item label="赛事:" prop="competition">
          <el-input v-model="dialogData.competition"></el-input>
        </el-form-item>
        <el-form-item label="赛事级别:" prop="level">
          <el-select v-model="dialogData.level" placeholder="请选择赛事级别">
            <el-option v-for="option in $levelOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地区:" prop="region">
          <el-select v-model="dialogData.region" placeholder="请选择赛事地区">
            <el-option v-for="option in $regionOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年份:" prop="year">
          <el-date-picker
              v-model="dialogData.year"
              type="year"
              value-format="yyyy"
              placeholder="选择年份"
              :clearable="false"
              :editable="false">
          </el-date-picker>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click.native="handleCancel">取 消</el-button>
        <el-button type="primary" @click.native="handleAddOrEdit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>