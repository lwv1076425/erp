<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" class="search-form">
      <el-form-item label="商品名称">
        <el-input v-model="queryParams.productName" placeholder="请输入商品名称" clearable />
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
      <el-table-column label="商品编码" prop="productCode" width="120" />
      <el-table-column label="商品名称" prop="productName" width="180" />
      <el-table-column label="规格型号" prop="spec" width="120" />
      <el-table-column label="计量单位" prop="unitId" width="80" />
      <el-table-column label="采购价" prop="purchasePrice" width="100" />
      <el-table-column label="销售价" prop="salePrice" width="100" />
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

    <el-dialog v-model="dialog.visible" :title="dialog.title" width="600px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12"><el-form-item label="商品编码" prop="productCode"><el-input v-model="form.productCode" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="商品名称" prop="productName"><el-input v-model="form.productName" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="规格型号"><el-input v-model="form.spec" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="条形码"><el-input v-model="form.barcode" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="采购价"><el-input-number v-model="form.purchasePrice" :min="0" :precision="2" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="销售价"><el-input-number v-model="form.salePrice" :min="0" :precision="2" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="最低库存"><el-input-number v-model="form.minStock" :min="0" :precision="2" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="最高库存"><el-input-number v-model="form.maxStock" :min="0" :precision="2" /></el-form-item></el-col>
          <el-col :span="12"><el-form-item label="状态"><el-radio-group v-model="form.status"><el-radio :value="0">正常</el-radio><el-radio :value="1">停用</el-radio></el-radio-group></el-form-item></el-col>
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
import { listProduct, addProduct, updateProduct, deleteProduct } from '@/api/base/product'

const list = ref([])
const loading = ref(false)
const total = ref(0)
const formRef = ref()
const queryParams = reactive({ pageNum: 1, pageSize: 10, productName: '' })
const dialog = reactive({ visible: false, title: '' })
const form = reactive({ id: undefined, productCode: '', productName: '', categoryId: null, unitId: null, spec: '', barcode: '', purchasePrice: 0, salePrice: 0, minStock: 0, maxStock: 0, status: 0, remark: '' })
const rules = { productCode: [{ required: true, message: '请输入商品编码', trigger: 'blur' }], productName: [{ required: true, message: '请输入商品名称', trigger: 'blur' }] }

onMounted(() => { getList() })

const getList = async () => {
  loading.value = true
  try { const res = await listProduct(queryParams); list.value = res.data.records; total.value = res.data.total } finally { loading.value = false }
}
const handleQuery = () => { queryParams.pageNum = 1; getList() }
const resetQuery = () => { queryParams.productName = ''; handleQuery() }
const handleAdd = () => { reset(); dialog.visible = true; dialog.title = '新增商品' }
const handleUpdate = (row) => { reset(); Object.assign(form, row); dialog.visible = true; dialog.title = '修改商品' }
const handleDelete = async (row) => { try { await ElMessageBox.confirm('确认删除？', '提示', { type: 'warning' }); await deleteProduct(row.id); ElMessage.success('删除成功'); getList() } catch {} }
const submitForm = async () => { try { await formRef.value.validate(); form.id ? await updateProduct(form.id, form) : await addProduct(form); ElMessage.success(form.id ? '修改成功' : '新增成功'); dialog.visible = false; getList() } catch {} }
const cancel = () => { dialog.visible = false; reset() }
const reset = () => { Object.assign(form, { id: undefined, productCode: '', productName: '', categoryId: null, unitId: null, spec: '', barcode: '', purchasePrice: 0, salePrice: 0, minStock: 0, maxStock: 0, status: 0, remark: '' }) }
</script>

<style scoped>.search-form{margin-bottom:20px}.mb8{margin-bottom:8px}</style>
