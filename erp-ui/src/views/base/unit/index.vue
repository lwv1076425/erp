<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="单位编码" prop="unitCode" width="120" />
      <el-table-column label="单位名称" prop="unitName" width="150" />
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

    <el-dialog v-model="dialog.visible" :title="dialog.title" width="400px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="单位编码" prop="unitCode"><el-input v-model="form.unitCode" /></el-form-item>
        <el-form-item label="单位名称" prop="unitName"><el-input v-model="form.unitName" /></el-form-item>
        <el-form-item label="状态"><el-radio-group v-model="form.status"><el-radio :value="0">正常</el-radio><el-radio :value="1">停用</el-radio></el-radio-group></el-form-item>
      </el-form>
      <template #footer><el-button type="primary" @click="submitForm">确定</el-button><el-button @click="cancel">取消</el-button></template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { listUnit, addUnit, updateUnit, deleteUnit } from '@/api/base/unit'

const list = ref([])
const loading = ref(false)
const formRef = ref()
const dialog = reactive({ visible: false, title: '' })
const form = reactive({ id: undefined, unitCode: '', unitName: '', status: 0 })
const rules = { unitCode: [{ required: true, message: '请输入单位编码', trigger: 'blur' }], unitName: [{ required: true, message: '请输入单位名称', trigger: 'blur' }] }

onMounted(() => { getList() })

const getList = async () => {
  loading.value = true
  try { const res = await listUnit({ pageNum: 1, pageSize: 100 }); list.value = res.data.records } finally { loading.value = false }
}
const handleAdd = () => { reset(); dialog.visible = true; dialog.title = '新增计量单位' }
const handleUpdate = (row) => { reset(); Object.assign(form, row); dialog.visible = true; dialog.title = '修改计量单位' }
const handleDelete = async (row) => { try { await ElMessageBox.confirm('确认删除？', '提示', { type: 'warning' }); await deleteUnit(row.id); ElMessage.success('删除成功'); getList() } catch {} }
const submitForm = async () => { try { await formRef.value.validate(); form.id ? await updateUnit(form.id, form) : await addUnit(form); ElMessage.success(form.id ? '修改成功' : '新增成功'); dialog.visible = false; getList() } catch {} }
const cancel = () => { dialog.visible = false; reset() }
const reset = () => { form.id = undefined; form.unitCode = ''; form.unitName = ''; form.status = 0 }
</script>

<style scoped>.mb8{margin-bottom:8px}</style>
