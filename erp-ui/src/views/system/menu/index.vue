<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :inline="true" :model="queryParams" class="search-form">
      <el-form-item label="菜单名称">
        <el-input v-model="queryParams.menuName" placeholder="请输入菜单名称" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd()">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Sort" @click="toggleExpandAll">展开/折叠</el-button>
      </el-col>
    </el-row>

    <!-- 数据表格 -->
    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="menuList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      border
    >
      <el-table-column label="菜单名称" prop="menuName" width="200" />
      <el-table-column label="图标" prop="icon" width="80">
        <template #default="scope">
          <el-icon v-if="scope.row.icon"><component :is="scope.row.icon" /></el-icon>
        </template>
      </el-table-column>
      <el-table-column label="排序" prop="sort" width="80" />
      <el-table-column label="权限标识" prop="perms" width="180" />
      <el-table-column label="组件路径" prop="component" width="180" />
      <el-table-column label="状态" width="80">
        <template #default="scope">
          <el-tag :type="scope.row.status === 0 ? 'success' : 'danger'">
            {{ scope.row.status === 0 ? '正常' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" width="160" />
      <el-table-column label="操作" width="200" fixed="right">
        <template #default="scope">
          <el-button type="primary" link icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="primary" link icon="Plus" @click="handleAdd(scope.row)">新增</el-button>
          <el-button type="danger" link icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增/修改对话框 -->
    <el-dialog v-model="dialog.visible" :title="dialog.title" width="600px" append-to-body>
      <el-form ref="menuFormRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="上级菜单" prop="parentId">
              <el-tree-select
                v-model="form.parentId"
                :data="menuOptions"
                :props="{ value: 'id', label: 'menuName', children: 'children' }"
                placeholder="选择上级菜单"
                check-strictly
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="菜单类型" prop="menuType">
              <el-radio-group v-model="form.menuType">
                <el-radio value="M">目录</el-radio>
                <el-radio value="C">菜单</el-radio>
                <el-radio value="F">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="menuName">
              <el-input v-model="form.menuName" placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="sort">
              <el-input-number v-model="form.sort" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType !== 'F'">
            <el-form-item label="路由地址" prop="path">
              <el-input v-model="form.path" placeholder="请输入路由地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType === 'C'">
            <el-form-item label="组件路径" prop="component">
              <el-input v-model="form.component" placeholder="请输入组件路径" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType !== 'M'">
            <el-form-item label="权限标识" prop="perms">
              <el-input v-model="form.perms" placeholder="请输入权限标识" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示状态" prop="visible">
              <el-radio-group v-model="form.visible">
                <el-radio :value="0">显示</el-radio>
                <el-radio :value="1">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio :value="0">正常</el-radio>
                <el-radio :value="1">停用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, nextTick } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { listMenu, addMenu, updateMenu, deleteMenu } from '@/api/system/menu'

const menuList = ref([])
const menuOptions = ref([])
const loading = ref(false)
const isExpandAll = ref(true)
const refreshTable = ref(true)
const menuFormRef = ref()

const queryParams = reactive({
  menuName: ''
})

const dialog = reactive({
  visible: false,
  title: ''
})

const form = reactive({
  id: undefined,
  parentId: 0,
  menuName: '',
  menuType: 'M',
  sort: 0,
  path: '',
  component: '',
  perms: '',
  icon: '',
  visible: 0,
  status: 0,
  remark: ''
})

const rules = {
  menuName: [{ required: true, message: '请输入菜单名称', trigger: 'blur' }],
  sort: [{ required: true, message: '请输入排序', trigger: 'blur' }]
}

onMounted(() => {
  getList()
})

const getList = async () => {
  loading.value = true
  try {
    const res = await listMenu(queryParams)
    menuList.value = handleTree(res.data, 'id')
    menuOptions.value = [{ id: 0, menuName: '顶级菜单', children: menuList.value }]
  } finally {
    loading.value = false
  }
}

const handleTree = (data, idField) => {
  const map = {}
  const tree = []
  data.forEach(item => { map[item[idField]] = { ...item, children: [] } })
  data.forEach(item => {
    const parent = map[item.parentId]
    if (parent) {
      parent.children.push(map[item[idField]])
    } else {
      tree.push(map[item[idField]])
    }
  })
  return tree
}

const toggleExpandAll = () => {
  refreshTable.value = false
  isExpandAll.value = !isExpandAll.value
  nextTick(() => { refreshTable.value = true })
}

const handleQuery = () => {
  getList()
}

const resetQuery = () => {
  queryParams.menuName = ''
  handleQuery()
}

const handleAdd = (row) => {
  reset()
  if (row) {
    form.parentId = row.id
  }
  dialog.visible = true
  dialog.title = '新增菜单'
}

const handleUpdate = (row) => {
  reset()
  Object.assign(form, row)
  dialog.visible = true
  dialog.title = '修改菜单'
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确认删除菜单"' + row.menuName + '"？', '提示', { type: 'warning' })
    await deleteMenu(row.id)
    ElMessage.success('删除成功')
    getList()
  } catch (error) {
    // 取消操作
  }
}

const submitForm = async () => {
  try {
    await menuFormRef.value.validate()
    if (form.id) {
      await updateMenu(form.id, form)
      ElMessage.success('修改成功')
    } else {
      await addMenu(form)
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
  form.parentId = 0
  form.menuName = ''
  form.menuType = 'M'
  form.sort = 0
  form.path = ''
  form.component = ''
  form.perms = ''
  form.icon = ''
  form.visible = 0
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
