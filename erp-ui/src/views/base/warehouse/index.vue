<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" class="search-form">
      <el-form-item label="仓库名称">
        <el-input v-model="queryParams.warehouseName" placeholder="请输入仓库名称" clearable />
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
      <el-table-column label="仓库编码" prop="warehouseCode" width="120" />
      <el-table-column label="仓库名称" prop="warehouseName" width="180" />
      <el-table-column label="地址" prop="address" width="200" />
      <el-table-column label="负责人" prop="manager" width="100" />
      <el-table-column label="联系电话" prop="phone" width="120" />
      <el-table-column label="状态" width="80">
        <template #default="scope">
          <el-tag :type="scope.row.status === 0 ? 'success' : 'danger'">{{ scope.row.status === 0 ? '正常' : '停用' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template #default="scope">
          <el-button type="primary" link icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="danger" link icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination v-show="total > 0" v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize" :total="total" :page-sizes="[10,20,50,100]" layout="total, sizes, prev, pager, next, jumper" @size-change="getList" @current-change="getList" />

    <el-dialog v-model="dialog.visible" :title="dialog.title" width="500px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="仓库编码" prop="warehouseCode"><el-input v-model="form.warehouseCode" /></el-form-item>
        <el-form-item label="仓库名称" prop="warehouseName"><el-input v-model="form.warehouseName" /></el-form-item>
        <el-form-item label="地址"><el-input v-model="form.address" /></el-form-item>
        <el-form-item label="负责人"><el-input v-model="form.manager" /></el-form-item>
        <el-form-item label="联系电话"><el-input v-model="form.phone" /></el-form-item>
        <el-form-item label="状态"><el-radio-group v-model="form.status"><el-radio :value="0">正常</el-radio><el-radio :value="1">停用</el-radio></el-radio-group></el-form-item>
        <el-form-item label="备注"><el-input v-model="form.remark" type="textarea" /></el-form-item>
      </el-form>
      <template #footer><el-button type="primary" @click="submitForm">确定</el-button><el-button @click="cancel">取消</el-button></template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { listWarehouse, addWarehouse, updateWarehouse, deleteWarehouse } from '@/api/base/warehouse'

const list = ref([])
const loading = ref(false)
const total = ref(0)
const formRef = ref()
const queryParams = reactive({ pageNum: 1, pageSize: 10, warehouseName: '' })
const dialog = reactive({ visible: false, title: '' })
const form = reactive({ id: undefined, warehouseCode: '', warehouseName: '', address: '', manager: '', phone: '', status: 0, remark: '' })
const rules = { warehouseCode: [{ required: true, message: '请输入仓库编码', trigger: 'blur' }], warehouseName: [{ required: true, message: '请输入仓库名称', trigger: 'blur' }] }

onMounted(() => { getList() })

const getList = async () => {
  loading.value = true
  try { const res = await listWarehouse(queryParams); list.value = res.data.records; total.value = res.data.total } finally { loading.value = false }
}
const handleQuery = () => { queryParams.pageNum = 1; getList() }
const resetQuery = () => { queryParams.warehouseName = ''; handleQuery() }
const handleAdd = () => { reset(); dialog.visible = true; dialog.title = '新增仓库' }
const handleUpdate = (row) => { reset(); Object.assign(form, row); dialog.visible = true; dialog.title = '修改仓库' }
const handleDelete = async (row) => { try { await ElMessageBox.confirm('确认删除？', '提示', { type: 'warning' }); await deleteWarehouse(row.id); ElMessage.success('删除成功'); getList() } catch {} }
const submitForm = async () => { try { await formRef.value.validate(); form.id ? await updateWarehouse(form.id, form) : await addWarehouse(form); ElMessage.success(form.id ? '修改成功' : '新增成功'); dialog.visible = false; getList() } catch {} }
const cancel = () => { dialog.visible = false; reset() }
const reset = () => { form.id = undefined; form.warehouseCode = ''; form.warehouseName = ''; form.address = ''; form.manager = ''; form.phone = ''; form.status = 0; form.remark = '' }
</script>

<style scoped>.search-form{margin-bottom:20px}.mb8{margin-bottom:8px}</style>
