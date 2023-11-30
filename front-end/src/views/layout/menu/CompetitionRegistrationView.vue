<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "CompetitionRegistrationView",
  components: {ValidCode},
  data() {
    return {
      searchForm: {
        year: new Date().getFullYear().toString(),
        region: '',
        review: '',
      },
      adminBtnVisible: false,
      athleteBtnVisible: false,
      dialogVisible: false,
      dialogYearDisable: false,
      dialogNameDisable: false,
      dialogIdNumDisable: false,
      dialogCompDisable: false,
      dialogReasonDisable: false,
      dialogReasonVisible: false,
      dialogCancelBtnVisible: false,
      dialogCloseBtnVisible: false,
      dialogRegBtnVisible: false,
      dialogFailBtnVisible: false,
      dialogPassBtnVisible: false,
      dialogTitle: '',
      dialogData: {
        id: '',
        year: '',
        athleteName: '',
        athleteIdNumber: '',
        competitionId: '',
        competition: '',
        competitionOptions: [],
        level: '',
        region: '',
        reason: '',
      },
      registrationRules: {
        year: [
          {required: true, message: '请选择年份', trigger: 'blur'},
          {required: true, message: '请选择年份', trigger: 'submit'},
        ],
        athleteName: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {required: true, message: '请输入姓名', trigger: 'submit'},
        ],
        athleteIdNumber: [
          {required: true, message: '请输入身份证号', trigger: 'blur'},
          {required: true, message: '请输入身份证号', trigger: 'submit'},
        ],
        competitionId: [
          {required: true, message: '请选择赛事', trigger: 'change'},
          {required: true, message: '请选择赛事', trigger: 'submit'},
        ],
        level: [
          {required: true, message: '请选择赛事级别', trigger: 'change'},
          {required: true, message: '请选择赛事级别', trigger: 'submit'},
        ],
        region: [
          {required: true, message: '请选择赛事地区', trigger: 'change'},
          {required: true, message: '请选择赛事地区', trigger: 'submit'},
        ],
      },
      tableData: [],
      page: 1,
      pageSize: 10,
      totalNum: 10,
      userInfo: JSON.parse(localStorage.getItem("userInfo") || '{}'),
    }
  },
  created() {
    this.onButtonDisplay();
    this.onSearch();
  },
  methods: {

    onButtonDisplay() {
      this.adminBtnVisible = this.userInfo.role === 'admin';
      this.athleteBtnVisible = this.userInfo.role === 'athlete';
    },

    onDialogMetaStatus(btn) {
      const stateConfig = {
        'registration-athlete': { year: true, name: true, idNum: true, comp: false, reason: true, reasonVisible: false, cancelBtn: true, closeBtn: false, regBtn: true, failBtn: false, passBtn: false },
        'registration-admin': { year: false, name: false, idNum: false, comp: false, reason: true, reasonVisible: false, cancelBtn: true, closeBtn: false, regBtn: true, failBtn: false, passBtn: false },
        'review': { year: true, name: true, idNum: true, comp: true, reason: false, reasonVisible: true, cancelBtn: true, closeBtn: false, regBtn: false, failBtn: true, passBtn: true },
        'detail': { year: true, name: true, idNum: true, comp: true, reason: true, reasonVisible: true, cancelBtn: false, closeBtn: true, regBtn: false, failBtn: false, passBtn: false }
      };

      const selectedState = stateConfig[btn];
      if (selectedState) {
        this.dialogYearDisable = selectedState.year;
        this.dialogNameDisable = selectedState.name;
        this.dialogIdNumDisable = selectedState.idNum;
        this.dialogCompDisable = selectedState.comp;
        this.dialogReasonDisable = selectedState.reason;
        this.dialogReasonVisible = selectedState.reasonVisible;
        this.dialogCancelBtnVisible = selectedState.cancelBtn;
        this.dialogCloseBtnVisible = selectedState.closeBtn;
        this.dialogRegBtnVisible = selectedState.regBtn;
        this.dialogFailBtnVisible = selectedState.failBtn;
        this.dialogPassBtnVisible = selectedState.passBtn;
      }
    },

    onSearch() {
      this.searchForm['page'] = this.page;
      this.searchForm['pageSize'] = this.pageSize;
      this.$http.post('/competition-registration/search', this.searchForm).then(res => {
        this.tableData = res.data.records
        this.totalNum = res.data.total
      })
    },

    onCancel(index, row) {
      this.handleCancelRegistration(row.id);
    },

    async onRegistrationDialog() {
      await this.handleYearCompetitions(new Date().getFullYear().toString());
      if (this.userInfo.role === 'admin') {
        this.onDialogMetaStatus('registration-admin');
      } else if (this.userInfo.role === 'athlete') {
        await this.handleAthleteDetail(this.userInfo.id);
        this.onDialogMetaStatus('registration-athlete');
      }
      this.dialogTitle = '报 名';
      this.dialogVisible = true;
    },

    async onReviewDialog(index, row) {
      await this.handleYearCompetitions(row.year.toString());
      this.handleDataTableToDialog(row);
      this.onDialogMetaStatus('review');
      this.dialogTitle = '审 核';
      this.dialogVisible = true;
    },

    async onDetailDialog(index, row) {
      await this.handleYearCompetitions(row.year.toString());
      this.handleDataTableToDialog(row);
      this.onDialogMetaStatus('detail');
      this.dialogTitle = '详 情';
      this.dialogVisible = true;
    },

    async handleAthleteDetail(id) {
      try {
        const res = await this.$http.get(`/competition-registration/search-athlete-detail/${id}`);
        if (res.code === '200') {
          this.dialogData.athleteName = res.data.name;
          this.dialogData.athleteIdNumber = res.data.idNumber;
        }
      } catch (error) {
        console.error("用户详情接口调用出错", error);
      }
    },

    ruleFormData() {
      let flag = true;
      this.$refs.registrationDialogDataRef.validate((valid) => {
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

    handleReviewChange() {
      this.onSearch();
    },

    handleCancelRegistration(id) {
      //后端删除接口调用
      this.$http.delete(`/competition-registration/cancel/${id}`).then(res => {
        if (res.code === "200") {
          this.$message.success('取消成功');
          this.onSearch();
        }
      })
    },

    handleDataTableToDialog(row) {
      this.dialogData.id = row.id
      this.dialogData.year = row.year.toString()
      this.dialogData.athleteName = row.athleteName
      this.dialogData.athleteIdNumber = row.athleteIdNumber
      this.dialogData.competitionId = row.competitionId
      this.dialogData.competition = row.competition
      this.dialogData.level = row.level
      this.dialogData.region = row.region
      this.dialogData.reason = row.reason
    },

    handleInitDialogData() {
      this.dialogData = {
        id: '',
        year: '',
        athleteName: '',
        athleteIdNumber: '',
        competitionId: '',
        competition: '',
        competitionOptions: [],
        level: '',
        region: '',
        reason: '',
      }
    },

    async handleYearCompetitions(year) {
      this.handleInitDialogData();
      this.dialogData.year = year;
      try {
        const res = await this.$http.get(`/competition-registration/search-competitions/${year}`);
        if (res.code === '200') {
          this.dialogData.competitionOptions = res.data;
        }
      } catch (error) {
        console.error("字典项接口调用出错", error);
      }
    },

    handleCompetitionInfo() {
      let value = this.dialogData.competitionId;
      const competitionItem = this.dialogData.competitionOptions.find(option => option.id === value);
      if (competitionItem) {
        this.dialogData.competition = competitionItem.competition;
        this.dialogData.level = competitionItem.level;
        this.dialogData.region = competitionItem.region;
      }
    },

    handleRegistration() {
      if (this.ruleFormData()) {
        //后端报名接口调用
        this.$http.post('/competition-registration/registration', this.dialogData).then(res => {
          if (res.code === '200') {
            this.$message.success('报名信息已提交');
            this.handleCancel();
          } else if (res.code === '500') {
            this.$message.error(res.msg);
          }
        })
      }
    },

    handleFailReview() {
      if (this.dialogData.reason !== null && this.dialogData.reason.length > 0) {
        this.dialogData['progress'] = 'fail';
        //后端报名接口调用
        this.$http.post('/competition-registration/review', this.dialogData).then(res => {
          if (res.code === '200') {
            this.$message.success('审核成功');
            this.handleCancel();
          }
        })
      } else {
        this.$message.error('请输入不通过意见');
      }

    },

    handlePassReview() {
      this.dialogData['progress'] = 'pass';
      //后端报名接口调用
      this.$http.post('/competition-registration/review', this.dialogData).then(res => {
        if (res.code === '200') {
          this.$message.success('审核成功');
          this.handleCancel();
        }
      })
    },

    handleCancel() {
      // 先重置表单数据
      this.dialogData = {
        id: '',
        year: '',
        athleteName: '',
        athleteIdNumber: '',
        competitionId: '',
        competition: '',
        competitionOptions: [],
        level: '',
        region: '',
        reason: '',
      };

      // 然后关闭对话框并重置表单校验状态
      this.$nextTick(() => {
        if (this.$refs.registrationDialogDataRef) {
          this.$refs.registrationDialogDataRef.resetFields();
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

    handleProgressColor(progress) {
      switch (progress) {
        case 'fail':
          return 'red';
        case 'pass':
          return 'green';
        case 'pending':
          return 'orange';
        default:
          return ''; // 默认颜色
      }
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

    handleReviewDisplay(row, column, value) {
      if (!value) return '';
      const region = this.$reviewOptions.find(option => option.value === value);
      return region ? region.label : '';
    },

  }
}
</script>

<template>
  <div class="p-main-area">
    <div class="p-main-table-search">
      <el-form :inline="true" :model="searchForm">
        <el-form-item label="年份:" v-if="adminBtnVisible">
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
          <el-select v-model="searchForm.region"
                     placeholder="请选择赛事地区"
                     @change="handleRegionChange"
                     clearable>
            <el-option v-for="option in $regionOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核进度:">
          <el-select v-model="searchForm.review"
                     placeholder="请选择审核进度"
                     @change="handleReviewChange"
                     clearable>
            <el-option v-for="option in $reviewOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-if="adminBtnVisible || athleteBtnVisible"
                     @click.native="onRegistrationDialog">报名
          </el-button>
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
              label="报名赛事"
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
              prop="progress"
              label="审核进度"
              :formatter="handleReviewDisplay"
              align="center">
            <template slot-scope="scope">
              <span :style="{ color: handleProgressColor(scope.row.progress) }">{{ handleReviewDisplay(scope.row, scope.column, scope.row.progress) }}</span>
            </template>
          </el-table-column>
          <el-table-column
              label="操作"
              width="250"
              align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" v-if="adminBtnVisible && scope.row.progress === 'pending'"
                         @click.native="onReviewDialog(scope.$index, scope.row)">审核
              </el-button>
              <el-button size="mini" type="primary"
                         v-if="(adminBtnVisible || athleteBtnVisible) && scope.row.progress !== 'pending'"
                         @click.native="onDetailDialog(scope.$index, scope.row)">详情
              </el-button>
              <el-popconfirm title="确定取消报名吗？"
                             v-if="(adminBtnVisible || athleteBtnVisible) && scope.row.progress === 'pending'"
                             @confirm="onCancel(scope.$index, scope.row)">
                <el-button style="margin-left: 5px" size="mini" type="danger" slot="reference">取消报名</el-button>
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

      <el-form label-width="90px" :model="dialogData" :rules="registrationRules" ref="registrationDialogDataRef">
        <el-form-item label="年份:" prop="year">
          <el-date-picker
              v-model="dialogData.year"
              type="year"
              value-format="yyyy"
              placeholder="选择年份"
              :clearable="false"
              :editable="false"
              :disabled="dialogYearDisable"
              @change="handleYearCompetitions">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="运动员:" prop="athleteName">
          <!--     不是管理员且没有提交报名     -->
          <el-input v-model="dialogData.athleteName" :disabled="dialogNameDisable ">
          </el-input>
        </el-form-item>
        <el-form-item label="身份证号:" prop="athleteIdNumber">
          <el-input v-model="dialogData.athleteIdNumber" :disabled="dialogIdNumDisable">
          </el-input>
        </el-form-item>
        <el-form-item label="赛事:" prop="competitionId">
          <el-select v-model="dialogData.competitionId" placeholder="请选择赛事" @change="handleCompetitionInfo"
                     :disabled="dialogCompDisable">
            <el-option v-for="option in dialogData.competitionOptions"
                       :key="option.id"
                       :label="option.competition"
                       :value="option.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="赛事级别:" prop="level">
          <el-select v-model="dialogData.level" placeholder="请选择赛事级别" :disabled="true">
            <el-option v-for="option in $levelOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地区:" prop="region">
          <el-select v-model="dialogData.region" placeholder="请选择赛事地区" :disabled="true">
            <el-option v-for="option in $regionOptions"
                       :key="option.value"
                       :label="option.label"
                       :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核意见:" prop="reason" v-if="dialogReasonVisible">
          <el-input type="textarea" v-model="dialogData.reason" :disabled="dialogReasonDisable"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button v-if="dialogCancelBtnVisible"
                   @click.native="handleCancel">取 消</el-button>
        <el-button v-if="dialogCloseBtnVisible"
                   @click.native="handleCancel">关 闭</el-button>
        <el-button type="primary" v-if="dialogRegBtnVisible"
                   @click.native="handleRegistration">报 名</el-button>
        <el-button type="danger" v-if="dialogFailBtnVisible"
                   @click.native="handleFailReview">不 通 过</el-button>
        <el-button type="primary" v-if="dialogPassBtnVisible"
                   @click.native="handlePassReview">通 过</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>