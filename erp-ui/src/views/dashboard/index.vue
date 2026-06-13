<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>今日销售额</span>
              <el-tag type="success">+12%</el-tag>
            </div>
          </template>
          <div class="card-body">
            <span class="amount">¥ 12,345</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>今日订单数</span>
              <el-tag type="primary">+8%</el-tag>
            </div>
          </template>
          <div class="card-body">
            <span class="amount">56</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>待处理采购单</span>
              <el-tag type="warning">12</el-tag>
            </div>
          </template>
          <div class="card-body">
            <span class="amount">12</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>库存预警商品</span>
              <el-tag type="danger">5</el-tag>
            </div>
          </template>
          <div class="card-body">
            <span class="amount">5</span>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>销售趋势</span>
          </template>
          <div ref="salesChartRef" style="height: 300px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>商品分类占比</span>
          </template>
          <div ref="categoryChartRef" style="height: 300px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts'

const salesChartRef = ref()
const categoryChartRef = ref()

onMounted(() => {
  initSalesChart()
  initCategoryChart()
})

const initSalesChart = () => {
  const chart = echarts.init(salesChartRef.value)
  const option = {
    tooltip: { trigger: 'axis' },
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月']
    },
    yAxis: { type: 'value' },
    series: [{
      data: [150, 230, 224, 218, 135, 147],
      type: 'line',
      smooth: true
    }]
  }
  chart.setOption(option)
}

const initCategoryChart = () => {
  const chart = echarts.init(categoryChartRef.value)
  const option = {
    tooltip: { trigger: 'item' },
    legend: { orient: 'vertical', left: 'left' },
    series: [{
      name: '商品分类',
      type: 'pie',
      radius: '50%',
      data: [
        { value: 1048, name: '电子产品' },
        { value: 735, name: '服装鞋帽' },
        { value: 580, name: '食品饮料' },
        { value: 484, name: '办公用品' },
        { value: 300, name: '其他' }
      ]
    }]
  }
  chart.setOption(option)
}
</script>

<style scoped>
.dashboard-container {
  padding: 0;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-body {
  text-align: center;
}

.amount {
  font-size: 24px;
  font-weight: bold;
  color: #409EFF;
}
</style>
