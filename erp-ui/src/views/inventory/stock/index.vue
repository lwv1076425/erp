<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" class="search-form">
      <el-form-item label="商品ID"><el-input-number v-model="queryParams.productId" :min="1" clearable /></el-form-item>
      <el-form-item label="仓库ID"><el-input-number v-model="queryParams.warehouseId" :min="1" clearable /></el-form-item>
      <el-form-item><el-button type="primary" @click="handleQuery">搜索</el-button><el-button @click="resetQuery">重置</el-button></el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="商品ID" prop="productId" width="100" />
      <el-table-column label="仓库ID" prop="warehouseId" width="100" />
      <el-table-column label="库存数量" prop="quantity" width="120" />
      <el-table-column label="冻结数量" prop="frozenQuantity" width="120" />
      <el-table-column label="更新时间" prop="updateTime" width="160" />
    </el-table>
    <el-pagination v-show="total > 0" v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize" :total="total" :page-sizes="[10,20,50,100]" layout="total, sizes, prev, pager, next, jumper" @size-change="getList" @current-change="getList" />
  </div>
</template>
<script setup>
import { ref, reactive, onMounted } from 'vue'
import { listInvStock } from '@/api/inventory/stock'
const list = ref([]); const loading = ref(false); const total = ref(0)
const queryParams = reactive({ pageNum: 1, pageSize: 10, productId: null, warehouseId: null })
onMounted(() => { getList() })
const getList = async () => { loading.value = true; try { const res = await listInvStock(queryParams); list.value = res.data.records; total.value = res.data.total } finally { loading.value = false } }
const handleQuery = () => { queryParams.pageNum = 1; getList() }
const resetQuery = () => { queryParams.productId = null; queryParams.warehouseId = null; handleQuery() }
</script>
<style scoped>.search-form{margin-bottom:20px}</style>
