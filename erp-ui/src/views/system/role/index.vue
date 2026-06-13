<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :inline="true" :model="queryParams" class="search-form">
      <el-form-item label="角色名称">
        <el-input v-model="queryParams.roleName" placeholder="请输入角色名称" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="roleList" border>
      <el-table-column label="角色ID" prop="id" width="80" />
      <el-table-column label="角色名称" prop="roleName" width="150" />
      <el-table-column label="角色编码" prop="roleCode" width="150" />
      <el-table-column label="排序" prop="sort" width="80" />
      <el-table-column label="状态" width="80">
        <template #default="scope">
          <el-tag :type="scope.row.status === 0 ? 'success' : 'danger'">
            {{ scope.row.status === 0 ? '正常' : '停用' }}
          </el-tag>
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

    <!-- 分页 -->
    <el-pagination
      v-show="total > 0"
      v-model:current-page="queryParams.pageNum"
      v-model:page-size="queryParams.pageSize"
      :total="total"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="getList"
      @current-change="getList"
    />

    <!-- 新增/修改对话框 -->
    <el-dialog v-model="dialog.visible" :title="dialog.title" width="500px" append-to-body>
      <el-form ref="roleFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="form.roleCode" placeholder="请输入角色编码" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" :min="0" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio :value="0">正常</el-radio>
            <el-radio :value="1">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { listRole, addRole, updateRole, deleteRole } from '@/api/system/role'

const roleList = ref([])
const loading = ref(false)
const total = ref(0)
const roleFormRef = ref()

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  roleName: ''
})

const dialog = reactive({
  visible: false,
  title: ''
})

const form = reactive({
  id: undefined,
  roleName: '',
  roleCode: '',
  sort: 0,
  status: 0,
  remark: ''
})

const rules = {
  roleName: [{ required: true, message: '请输入角色名称', trigger: 'blur' }],
  roleCode: [{ required: true, message: '请输入角色编码', trigger: 'blur' }]
}

onMounted(() => {
  getList()
})

const getList = async () => {
  loading.value = true
  try {
    const res = await listRole(queryParams)
    roleList.value = res.data.records
    total.value = res.data.total
  } finally {
    loading.value = false
  }
}

const handleQuery = () => {
  queryParams.pageNum = 1
  getList()
}

const resetQuery = () => {
  queryParams.roleName = ''
  handleQuery()
}

const handleAdd = () => {
  reset()
  dialog.visible = true
  dialog.title = '新增角色'
}

const handleUpdate = (row) => {
  reset()
  Object.assign(form, row)
  dialog.visible = true
  dialog.title = '修改角色'
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确认删除角色"' + row.roleName + '"？', '提示', { type: 'warning' })
    await deleteRole(row.id)
    ElMessage.success('删除成功')
    getList()
  } catch (error) {
    // 取消操作
  }
}

const submitForm = async () => {
  try {
    await roleFormRef.value.validate()
    if (form.id) {
      await updateRole(form.id, form)
      ElMessage.success('修改成功')
    } else {
      await addRole(form)
      ElMessage.success('新增成功')
    }
    dialog.visible = false
    getList()
  } catch (error) {
    // 验证失败
  }
}

const cancel = () => {
  dialog.visible = false
  reset()
}

const reset = () => {
  form.id = undefined
  form.roleName = ''
  form.roleCode = ''
  form.sort = 0
  form.status = 0
  form.remark = ''
}
</script>

<style scoped>
.search-form {
  margin-bottom: 20px;
}
.mb8 {
  margin-bottom: 8px;
}
</style>
