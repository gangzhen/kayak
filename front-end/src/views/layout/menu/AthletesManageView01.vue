<script>

export default {
  name: "AthletesManageView01",
  data() {

    const nameRule = (rule, value, callback) => {
      //1.可以是中文
      //2.可以是英文，允许输入点（英文名字中的那种点）， 允许输入空格
      //3.中文和英文不能同时出现
      //4.长度在20个字符以内
      const regName = /^([\u4e00-\u9fa5]{2,20}|[a-zA-Z.\s]{2,20})$/;
      if (regName.test(value)) {
        //正确的名字
        return callback();
      }
      callback(new Error("姓名格式有误"));
    };

    const idNumberRule = (rule, value, callback) => {
      const regIDNumber = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(([0-2][1-9])|10|20|30|31)\d{3}(\d|X|x)$/;
      if (regIDNumber.test(value)) {
        return callback();
      }
      callback(new Error("身份证号格式有误"));
    }

    return {
      dialogVisible: false,
      deleteVisible: false,
      dialogTitle: '',
      totalNum: 30,
      page: 1,
      pageSize: 10,
      dialogData: {
        name: '',
        age: '',
        gender: '',
        idNumber: '',
        region: '',
        year: '',
        level: '',
        rankingCode: '',
        totalCode: '',
      },
      searchForm: {},
      tableData: [],
      athleteRules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {validator: nameRule, trigger: 'submit'},
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'}
        ],
        gender: [
          {required: true, message: '请选择性别', trigger: 'change'}
        ],
        idNumber: [
          {required: true, message: '请输入身份证号', trigger: 'blur'},
          {validator: idNumberRule, trigger: 'submit'},
        ],
        region: [
          {required: true, message: '请输入地区', trigger: 'blur'},
        ],
        level: [
          {required: true, message: '请选择级别', trigger: 'change'},
        ],
        totalCode: [
          {required: true, message: '请选择参赛人数', trigger: 'change'},
        ],
        rankingCode: [
          {required: true, message: '请选择名次', trigger: 'change'},
        ]
      },
    }
  },
  created() {
    this.onSearch();
  },
  methods: {

    ruleFormData() {
      let flag = true;
      this.$refs.athleteDialogDataRef.validate((valid) => {
        if (!valid) {
          console.log('规则校验失败');
          flag = flag && false
        }
      });
      return flag;
    },

    onSearch() {
      // 使用page和pageSize分页查询
      this.searchForm['page'] = this.page;
      this.searchForm['pageSize'] = this.pageSize;
      this.$http.post('/athlete-manage/search', this.searchForm).then(res => {
        this.tableData = res.data.records
        this.totalNum = res.data.total
      })
    },

    onAddDialog() {
      this.dialogData = {
        year: new Date().getFullYear().toString(),
      }
      this.dialogTitle = '新 增';
      this.dialogVisible = true;
    },

    onEditDialog(index, row) {
      this.dialogData = {
        id: row.id,
        name: row.name,
        age: row.age,
        gender: row.gender,
        idNumber: row.idNumber,
        region: row.region,
        year: row.year.toString(),
        level: row.level,
        rankingCode: row.rankingCode,
        totalCode: row.totalCode,
      }
      this.dialogTitle = '编 辑';
      this.dialogVisible = true;
    },

    onDelete(index, row) {
      this.handleDelete(row.id);
    },

    handleCancel() {
      // 先重置表单数据
      this.dialogData = {
        name: '',
        age: '',
        gender: '',
        idNumber: '',
        region: '',
        year: '',
        level: '',
        rankingCode: '',
        totalCode: '',
      };

      // 然后关闭对话框并重置表单校验状态
      this.$nextTick(() => {
        if (this.$refs.athleteDialogDataRef) {
          this.$refs.athleteDialogDataRef.resetFields();
        }
        this.dialogVisible = false;
        this.dialogTitle = '';
      });

      // 重新加载搜索结果
      this.onSearch();
    },

    handleAddOrEdit() {
      //TODO 校验数据字段
      if (this.ruleFormData()) {
        if (this.dialogTitle === '新 增') {
          this.handleAdd();
        } else if (this.dialogTitle === '编 辑') {
          this.handleEdit();
        }
      }
    },

    handleAdd() {
      // this.dialogData新增入库
      this.$http.post('/athlete-manage/add', this.dialogData).then(res => {
        if (res.code === "200") {
          this.$message.success('添加成功');
          this.handleCancel();
        }
      })
    },

    handleEdit() {
      // this.dialogData更新入库
      this.$http.put('/athlete-manage/update', this.dialogData).then(res => {
        if (res.code === "200") {
          this.$message.success('更新成功');
          this.handleCancel();
        }
      })
    },

    handleDelete(id) {
      // 根据唯一ID删除库数据
      this.$http.delete(`/athlete-manage/delete/${id}`).then(res => {
        if (res.code === "200") {
          this.$message.success('删除成功');
          this.onSearch();
        }
      })
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

    handleTotalDisplay(row, column, value) {
      if (!value) return '';
      const region = this.$totalCodeOptions.find(option => option.value === value);
      return region ? region.label : ''
    },

    handleRankingDisplay(row, column, value) {
      if (!value) return '';
      const region = this.$rankingCodeOptions.find(option => option.value === value);
      return region ? region.label : '';
    },

  }
}
</script>

<template>
  <div class="p-main-area">

    <div class="p-main-table-search">
      <el-form :inline="true" :model="searchForm">
        <!--      <el-form-item label="比赛项目:">-->
        <!--        <el-select v-model="searchForm.item" placeholder="请选择比赛项目" @change="handleItemChange">-->
        <!--          <el-option label="项目一" value="item1"></el-option>-->
        <!--          <el-option label="项目二" value="item2"></el-option>-->
        <!--          <el-option label="项目三" value="item3"></el-option>-->
        <!--          <el-option label="项目四" value="item4"></el-option>-->
        <!--        </el-select>-->
        <!--      </el-form-item>-->
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
              prop="name"
              label="姓名"
              width="200"
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
              prop="level"
              label="比赛名称"
              align="center">
          </el-table-column>
          <el-table-column
              prop="totalCode"
              label="参赛人数"
              width="150"
              :formatter="handleTotalDisplay"
              v-if="false"
              align="center">
          </el-table-column>
          <el-table-column
              prop="rankingLevel"
              label="名次"
              align="center">
          </el-table-column>
          <el-table-column
              prop="points"
              label="积分"
              align="center"
              v-if="false">
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

      <el-form label-width="90px" :model="dialogData" :rules="athleteRules" ref="athleteDialogDataRef">
        <el-form-item label="姓名:" prop="name">
          <el-input v-model="dialogData.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄:" prop="age">
          <el-input v-model="dialogData.age"></el-input>
        </el-form-item>
        <el-form-item label="性别:" prop="gender">
          <el-select v-model="dialogData.gender" placeholder="请选择性别">
            <el-option v-for="option in $genderOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="身份证号:" prop="idNumber">
          <el-input v-model="dialogData.idNumber"></el-input>
        </el-form-item>
        <el-form-item label="地区:" prop="region">
          <el-input v-model="dialogData.region"></el-input>
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
        <el-form-item label="级别:" prop="level">
          <el-select v-model="dialogData.level" placeholder="请选择比赛项目">
            <el-option v-for="option in $levelOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="参赛人数:" prop="totalCode">
          <el-select v-model="dialogData.totalCode" placeholder="请选择参赛人数">
            <el-option v-for="option in $totalCodeOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名次:" prop="rankingCode">
          <el-select v-model="dialogData.rankingCode" placeholder="请选择名次">
            <el-option v-for="option in $rankingCodeOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
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