<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "CoachesManageView02",
  components: {ValidCode},
  data() {

    const usernameRule = (rule, value, callback) => {
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

    const phoneNumberRule = (rule, value, callback) => {
      const regPhoneNumber = /^(0|86|17951)?(13[0-9]|15[012356789]|17[3678]|18[0-9]|14[57])[0-9]{8}$/;
      if (regPhoneNumber.test(value)) {
        return callback();
      }
      callback(new Error("手机号格式有误"));
    };

    return {
      searchForm: {
        year: new Date().getFullYear().toString()
      },
      tableData: [],
      dialogTitle: '',
      dialogVisible: false,
      dialogData: {
        id: '',
        year: '',
        username: '',
        idNumber: '',
        phoneNumber: '',
        role: 'coach',
      },
      coachRules: {
        year: [
          {required: true, message: '请选择年份', trigger: 'submit'},
        ],
        username: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {validator: usernameRule, trigger: 'submit'},
        ],
        idNumber: [
          {required: true, message: '请输入身份证号', trigger: 'blur'},
          {validator: idNumberRule, trigger: 'submit'},
        ],
        phoneNumber: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {validator: phoneNumberRule, trigger: 'submit'},
        ],
        role: [
          {required: true, message: '请选择角色', trigger: 'submit'},
        ],
      },
      page: 1,
      pageSize: 10,
      totalNum: 10,
      userInfo: JSON.parse(localStorage.getItem("userInfo") || '{}'),
    }
  },
  created() {
    this.onSearch();
  },
  methods: {

    onSearch() {
      // 使用page和pageSize分页查询
      this.searchForm['page'] = this.page;
      this.searchForm['pageSize'] = this.pageSize;
      this.$http.post('/coach-manage/search', this.searchForm).then(res => {
        this.tableData = res.data.records
        this.totalNum = res.data.total
      })
    },

    onAddDialog() {
      this.handleInitDialogData();
      this.dialogTitle = '新 增';
      this.dialogVisible = true;
    },

    onEditDialog(index, row) {
      this.handleDataTableToDialog(row);
      this.dialogTitle = '编 辑';
      this.dialogVisible = true;
    },

    onDelete(index, row) {
      this.handleDelete(row.id);
    },

    ruleFormData() {
      let flag = true;
      this.$refs.coachDialogDataRef.validate((valid) => {
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

    handleInitDialogData() {
      this.dialogData = {
        id: '',
        year: new Date().getFullYear().toString(),
        username: '',
        idNumber: '',
        phoneNumber: '',
        role: 'coach',
      }
    },

    handleDataTableToDialog(row) {
      this.dialogData = {
        id: row.id,
        year: row.year.toString(),
        username: row.username,
        idNumber: row.idNumber,
        phoneNumber: row.phoneNumber,
        role: row.role,
      }
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
      // this.dialogData新增入库
      this.$http.post('/coach-manage/add', this.dialogData).then(res => {
        if (res.code === "200") {
          this.$message.success('添加成功');
          this.handleCancel();
        }
      })
    },

    handleEdit() {
      // this.dialogData更新入库
      this.$http.put('/coach-manage/update', this.dialogData).then(res => {
        if (res.code === "200") {
          this.$message.success('更新成功');
          this.handleCancel();
        }
      })
    },

    handleDelete(id) {
      // 根据唯一ID删除库数据
      this.$http.delete(`/coach-manage/delete/${id}`).then(res => {
        if (res.code === "200") {
          this.$message.success('删除成功');
          this.onSearch();
        }
      })
    },

    handleCancel() {
      // 先重置表单数据
      this.dialogData = {
        id: '',
        username: '',
        idNumber: '',
        phoneNumber: '',
        role: '',
      };

      // 然后关闭对话框并重置表单校验状态
      this.$nextTick(() => {
        if (this.$refs.coachDialogDataRef) {
          this.$refs.coachDialogDataRef.resetFields();
        }
        this.dialogVisible = false;
        this.dialogTitle = '';
      });

      // 重新加载搜索结果
      this.onSearch();
    },

    handleSizeChange() {
      console.log(1)
    },

    handleCurrentChange() {
      console.log(1)
    },

  }
}
</script>

<template>
  <div class="p-main-area">

    <div class="p-main-table-search">
      <el-form :inline="true" :model="searchForm">
        <!--        <el-form-item label="年份:">-->
        <!--          <el-date-picker-->
        <!--              v-model="searchForm.year"-->
        <!--              type="year"-->
        <!--              value-format="yyyy"-->
        <!--              placeholder="选择年份"-->
        <!--              :clearable="false"-->
        <!--              :editable="false"-->
        <!--              @change="handleYearChange">-->
        <!--          </el-date-picker>-->
        <!--        </el-form-item>-->
        <el-form-item label="角色:" prop="role">
          <el-select v-model="searchForm.role" placeholder="请选择角色" style="width: 100%" @change="handleYearChange">
            <el-option v-for="option in $roleOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-if="userInfo.role === 'admin'">
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
              width="200"
              align="center">
          </el-table-column>
          <el-table-column
              prop="phoneNumber"
              label="组别"
              align="center">
          </el-table-column>
          <el-table-column
              prop="username"
              label="姓名"
              align="center">
          </el-table-column>
          <el-table-column
              label="操作"
              width="200"
              align="center"
              v-if="false">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="primary"
                  @click.native="onEditDialog(scope.$index, scope.row)">编辑
              </el-button>

              <el-popconfirm
                  title="确定删除此教练员吗？"
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

      <el-form label-width="90px" :model="dialogData" :rules="coachRules" ref="coachDialogDataRef">
        <el-form-item label="年份:" prop="year">
          <el-date-picker
              v-model="dialogData.year"
              type="year"
              value-format="yyyy"
              placeholder="选择年份"
              :clearable="false"
              :editable="false"
              :disabled="true">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="教练员:" prop="username">
          <el-input v-model="dialogData.username" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="身份证号:" prop="idNumber">
          <el-input v-model="dialogData.idNumber" placeholder="请输入身份证号"></el-input>
        </el-form-item>
        <el-form-item label="手机号:" prop="phoneNumber">
          <el-input v-model="dialogData.phoneNumber" placeholder="请输入手机号">
          </el-input>
        </el-form-item>
        <el-form-item label="角色:" prop="role">
          <el-select v-model="dialogData.role" placeholder="请选择角色" style="width: 100%" :disabled="true">
            <el-option v-for="option in $roleOptions"
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