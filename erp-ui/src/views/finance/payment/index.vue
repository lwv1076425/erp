<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8"><el-col :span="1.5"><el-button type="primary" plain icon="Plus" @click="handleAdd">新增收付款</el-button></el-col></el-row>
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="收付款单号" prop="paymentNo" width="150" />
      <el-table-column label="类型" width="80"><template #default="scope"><el-tag :type="scope.row.paymentType === 0 ? 'success' : 'warning'">{{ scope.row.paymentType === 0 ? '收款' : '付款' }}</el-tag></template></el-table-column>
      <el-table-column label="账户ID" prop="accountId" width="100" />
      <el-table-column label="金额" prop="amount" width="120" />
      <el-table-column label="收付款日期" prop="paymentDate" width="120" />
      <el-table-column label="状态" width="80"><template #default="scope"><el-tag :type="scope.row.status === 0 ? 'info' : 'success'">{{ scope.row.status === 0 ? '草稿' : '已确认' }}</el-tag></template></el-table-column>
      <el-table-column label="操作" width="120" fixed="right"><template #default="scope"><el-button type="danger" link icon="Delete" @click="handleDelete(scope.row)">删除</el-button></template></el-table-column>
    </el-table>
    <el-pagination v-show="total > 0" v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize" :total="total" :page-sizes="[10,20,50,100]" layout="total, sizes, prev, pager, next, jumper" @size-change="getList" @current-change="getList" />
    <el-dialog v-model="dialog.visible" title="新增收付款" width="500px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="收付款类型" prop="paymentType"><el-radio-group v-model="form.paymentType"><el-radio :value="0">收款</el-radio><el-radio :value="1">付款</el-radio></el-radio-group></el-form-item>
        <el-form-item label="账户ID" prop="accountId"><el-input-number v-model="form.accountId" :min="1" /></el-form-item>
        <el-form-item label="金额" prop="amount"><el-input-number v-model="form.amount" :min="0" :precision="2" /></el-form-item>
        <el-form-item label="收付款日期"><el-date-picker v-model="form.paymentDate" type="date" value-format="YYYY-MM-DD" /></el-form-item>
        <el-form-item label="备注"><el-input v-model="form.remark" type="textarea" /></el-form-item>
      </el-form>
      <template #footer><el-button type="primary" @click="submitForm">确定</el-button><el-button @click="cancel">取消</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { listFinPayment, addFinPayment, deleteFinPayment } from '@/api/finance/payment'
const list = ref([]); const loading = ref(false); const total = ref(0); const formRef = ref()
const queryParams = reactive({ pageNum: 1, pageSize: 10 }); const dialog = reactive({ visible: false })
const form = reactive({ paymentType: 0, accountId: null, amount: 0, paymentDate: '', remark: '' })
const rules = { accountId: [{ required: true, message: '请输入账户ID', trigger: 'blur' }], amount: [{ required: true, message: '请输入金额', trigger: 'blur' }] }
onMounted(() => { getList() })
const getList = async () => { loading.value = true; try { const res = await listFinPayment(queryParams); list.value = res.data.records; total.value = res.data.total } finally { loading.value = false } }
const handleAdd = () => { form.paymentType = 0; form.accountId = null; form.amount = 0; form.paymentDate = ''; form.remark = ''; dialog.visible = true }
const handleDelete = async (row) => { try { await ElMessageBox.confirm('确认删除？', '提示', { type: 'warning' }); await deleteFinPayment(row.id); ElMessage.success('删除成功'); getList() } catch {} }
const submitForm = async () => { try { await formRef.value.validate(); await addFinPayment(form); ElMessage.success('新增成功'); dialog.visible = false; getList() } catch {} }
const cancel = () => { dialog.visible = false }
</script>
<style scoped>.mb8{margin-bottom:8px}</style>
