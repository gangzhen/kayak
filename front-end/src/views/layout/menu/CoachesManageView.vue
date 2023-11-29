<script>
export default {
  name: "CoachesManageView",
  data() {
    return {
      searchForm: {},
      tableData: [],
      dialogTitle: '',
      dialogVisible: false,
      dialogData: {},
      coachRules: {},
      page: 1,
      pageSize: 10,
      totalNum: 10,
    }
  },
  methods: {

    onAddDialog() {
      console.log(1)
    },

    handleCancel() {
      console.log(1)
    },

    handleAddOrEdit() {
      console.log(1)
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
              width="300"
              align="center">
          </el-table-column>
          <el-table-column
              prop="level"
              label="级别"
              width="100"
              align="center">
          </el-table-column>
          <el-table-column
              prop="totalLevel"
              label="参赛人数"
              width="200"
              align="center">
          </el-table-column>
          <el-table-column
              prop="rankingLevel"
              label="名次"
              width="200"
              align="center">
          </el-table-column>
          <el-table-column
              prop="points"
              label="积分"
              width="200"
              align="center">
          </el-table-column>
          <el-table-column
              label="操作"
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

      <el-form label-width="90px" :model="dialogData" :rules="coachRules" ref="dialogData">
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