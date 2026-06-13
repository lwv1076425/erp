<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" class="search-form">
      <el-form-item label="订单编号">
        <el-input v-model="queryParams.orderNo" placeholder="请输入订单编号" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="订单编号" prop="orderNo" width="150" />
      <el-table-column label="供应商ID" prop="supplierId" width="100" />
      <el-table-column label="订单日期" prop="orderDate" width="120" />
      <el-table-column label="订单金额" prop="totalAmount" width="120" />
      <el-table-column label="状态" width="100">
        <template #default="scope">
          <el-tag :type="getStatusType(scope.row.status)">{{ getStatusText(scope.row.status) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" width="160" />
      <el-table-column label="操作" width="180" fixed="right">
        <template #default="scope">
          <el-button type="primary" link icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="danger" link icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination v-show="total > 0" v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize" :total="total" :page-sizes="[10,20,50,100]" layout="total, sizes, prev, pager, next, jumper" @size-change="getList" @current-change="getList" />

    <el-dialog v-model="dialog.visible" :title="dialog.title" width="600px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="供应商ID" prop="supplierId"><el-input-number v-model="form.supplierId" :min="1" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="订单日期" prop="orderDate"><el-date-picker v-model="form.orderDate" type="date" value-format="YYYY-MM-DD" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="订单金额" prop="totalAmount"><el-input-number v-model="form.totalAmount" :min="0" :precision="2" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="状态" prop="status">
            <el-select v-model="form.status">
              <el-option label="草稿" :value="0" />
              <el-option label="已审核" :value="1" />
              <el-option label="已入库" :value="2" />
              <el-option label="已完成" :value="3" />
              <el-option label="已取消" :value="4" />
            </el-select>
          </el-form-item></el-col>
          <el-col :span="24"><el-form-item label="备注"><el-input v-model="form.remark" type="textarea" /></el-form-item></el-col>
        </el-row>
      </el-form>
      <template #footer><el-button type="primary" @click="submitForm">确定</el-button><el-button @click="cancel">取消</el-button></template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { listPurOrder, addPurOrder, updatePurOrder, deletePurOrder } from '@/api/purchase/order'

const list = ref([])
const loading = ref(false)
const total = ref(0)
const formRef = ref()
const queryParams = reactive({ pageNum: 1, pageSize: 10, orderNo: '' })
const dialog = reactive({ visible: false, title: '' })
const form = reactive({ id: undefined, supplierId: null, orderDate: '', totalAmount: 0, status: 0, remark: '' })
const rules = { supplierId: [{ required: true, message: '请输入供应商ID', trigger: 'blur' }] }

onMounted(() => { getList() })

const getList = async () => {
  loading.value = true
  try { const res = await listPurOrder(queryParams); list.value = res.data.records; total.value = res.data.total } finally { loading.value = false }
}
const handleQuery = () => { queryParams.pageNum = 1; getList() }
const resetQuery = () => { queryParams.orderNo = ''; handleQuery() }
const handleAdd = () => { reset(); dialog.visible = true; dialog.title = '新增采购订单' }
const handleUpdate = (row) => { reset(); Object.assign(form, row); dialog.visible = true; dialog.title = '修改采购订单' }
const handleDelete = async (row) => { try { await ElMessageBox.confirm('确认删除？', '提示', { type: 'warning' }); await deletePurOrder(row.id); ElMessage.success('删除成功'); getList() } catch {} }
const submitForm = async () => { try { await formRef.value.validate(); form.id ? await updatePurOrder(form.id, form) : await addPurOrder(form); ElMessage.success(form.id ? '修改成功' : '新增成功'); dialog.visible = false; getList() } catch {} }
const cancel = () => { dialog.visible = false; reset() }
const reset = () => { form.id = undefined; form.supplierId = null; form.orderDate = ''; form.totalAmount = 0; form.status = 0; form.remark = '' }
const getStatusType = (status) => ['info', 'primary', 'success', 'success', 'danger'][status] || 'info'
const getStatusText = (status) => ['草稿', '已审核', '已入库', '已完成', '已取消'][status] || '未知'
</script>

<style scoped>.search-form{margin-bottom:20px}.mb8{margin-bottom:8px}</style>
