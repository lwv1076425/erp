<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8"><el-col :span="1.5"><el-button type="primary" plain icon="Plus" @click="handleAdd">新增发票</el-button></el-col></el-row>
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="发票编号" prop="invoiceNo" width="150" />
      <el-table-column label="类型" width="100"><template #default="scope"><el-tag :type="scope.row.invoiceType === 0 ? 'success' : 'primary'">{{ scope.row.invoiceType === 0 ? '销售发票' : '采购发票' }}</el-tag></template></el-table-column>
      <el-table-column label="金额" prop="amount" width="120" />
      <el-table-column label="税额" prop="taxAmount" width="120" />
      <el-table-column label="开票日期" prop="invoiceDate" width="120" />
      <el-table-column label="状态" width="80"><template #default="scope"><el-tag :type="scope.row.status === 0 ? 'info' : 'success'">{{ scope.row.status === 0 ? '草稿' : '已确认' }}</el-tag></template></el-table-column>
      <el-table-column label="操作" width="120" fixed="right"><template #default="scope"><el-button type="danger" link icon="Delete" @click="handleDelete(scope.row)">删除</el-button></template></el-table-column>
    </el-table>
    <el-pagination v-show="total > 0" v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize" :total="total" :page-sizes="[10,20,50,100]" layout="total, sizes, prev, pager, next, jumper" @size-change="getList" @current-change="getList" />
    <el-dialog v-model="dialog.visible" title="新增发票" width="500px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="发票类型" prop="invoiceType"><el-radio-group v-model="form.invoiceType"><el-radio :value="0">销售发票</el-radio><el-radio :value="1">采购发票</el-radio></el-radio-group></el-form-item>
        <el-form-item label="金额" prop="amount"><el-input-number v-model="form.amount" :min="0" :precision="2" /></el-form-item>
        <el-form-item label="税额"><el-input-number v-model="form.taxAmount" :min="0" :precision="2" /></el-form-item>
        <el-form-item label="开票日期"><el-date-picker v-model="form.invoiceDate" type="date" value-format="YYYY-MM-DD" /></el-form-item>
        <el-form-item label="备注"><el-input v-model="form.remark" type="textarea" /></el-form-item>
      </el-form>
      <template #footer><el-button type="primary" @click="submitForm">确定</el-button><el-button @click="cancel">取消</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { listFinInvoice, addFinInvoice, deleteFinInvoice } from '@/api/finance/invoice'
const list = ref([]); const loading = ref(false); const total = ref(0); const formRef = ref()
const queryParams = reactive({ pageNum: 1, pageSize: 10 }); const dialog = reactive({ visible: false })
const form = reactive({ invoiceType: 0, amount: 0, taxAmount: 0, invoiceDate: '', remark: '' })
const rules = { amount: [{ required: true, message: '请输入金额', trigger: 'blur' }] }
onMounted(() => { getList() })
const getList = async () => { loading.value = true; try { const res = await listFinInvoice(queryParams); list.value = res.data.records; total.value = res.data.total } finally { loading.value = false } }
const handleAdd = () => { form.invoiceType = 0; form.amount = 0; form.taxAmount = 0; form.invoiceDate = ''; form.remark = ''; dialog.visible = true }
const handleDelete = async (row) => { try { await ElMessageBox.confirm('确认删除？', '提示', { type: 'warning' }); await deleteFinInvoice(row.id); ElMessage.success('删除成功'); getList() } catch {} }
const submitForm = async () => { try { await formRef.value.validate(); await addFinInvoice(form); ElMessage.success('新增成功'); dialog.visible = false; getList() } catch {} }
const cancel = () => { dialog.visible = false }
</script>
<style scoped>.mb8{margin-bottom:8px}</style>
