<template>
  <el-container class="layout-container">
    <!-- 侧边栏 -->
    <el-aside :width="isCollapse ? '64px' : '200px'" class="sidebar">
      <div class="logo">
        <h1 v-if="!isCollapse">ERP</h1>
        <h1 v-else>E</h1>
      </div>
      <el-menu
        :default-active="route.path"
        :collapse="isCollapse"
        :router="true"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
      >
        <el-menu-item index="/dashboard">
          <el-icon><HomeFilled /></el-icon>
          <template #title>首页</template>
        </el-menu-item>
        <el-sub-menu index="/system">
          <template #title>
            <el-icon><Setting /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item index="/system/user">用户管理</el-menu-item>
          <el-menu-item index="/system/role">角色管理</el-menu-item>
          <el-menu-item index="/system/menu">菜单管理</el-menu-item>
          <el-menu-item index="/system/dict">字典管理</el-menu-item>
          <el-menu-item index="/system/config">系统配置</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="/base">
          <template #title>
            <el-icon><Grid /></el-icon>
            <span>基础数据</span>
          </template>
          <el-menu-item index="/base/supplier">供应商管理</el-menu-item>
          <el-menu-item index="/base/customer">客户管理</el-menu-item>
          <el-menu-item index="/base/product">商品管理</el-menu-item>
          <el-menu-item index="/base/unit">计量单位</el-menu-item>
          <el-menu-item index="/base/warehouse">仓库管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="/purchase">
          <template #title>
            <el-icon><ShoppingCart /></el-icon>
            <span>采购管理</span>
          </template>
          <el-menu-item index="/purchase/order">采购订单</el-menu-item>
          <el-menu-item index="/purchase/receipt">采购入库</el-menu-item>
          <el-menu-item index="/purchase/return">采购退货</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="/sales">
          <template #title>
            <el-icon><Money /></el-icon>
            <span>销售管理</span>
          </template>
          <el-menu-item index="/sales/order">销售订单</el-menu-item>
          <el-menu-item index="/sales/delivery">销售出库</el-menu-item>
          <el-menu-item index="/sales/return">销售退货</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="/inventory">
          <template #title>
            <el-icon><Warehouse /></el-icon>
            <span>库存管理</span>
          </template>
          <el-menu-item index="/inventory/stock">库存查询</el-menu-item>
          <el-menu-item index="/inventory/check">库存盘点</el-menu-item>
          <el-menu-item index="/inventory/transfer">库存调拨</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="/finance">
          <template #title>
            <el-icon><TrendCharts /></el-icon>
            <span>财务核算</span>
          </template>
          <el-menu-item index="/finance/payment">收付款管理</el-menu-item>
          <el-menu-item index="/finance/invoice">发票管理</el-menu-item>
          <el-menu-item index="/finance/journal">日记账</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>

    <!-- 主内容区 -->
    <el-container>
      <!-- 顶部导航栏 -->
      <el-header class="header">
        <div class="header-left">
          <el-icon class="collapse-btn" @click="isCollapse = !isCollapse">
            <Fold v-if="!isCollapse" />
            <Expand v-else />
          </el-icon>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-if="route.meta.title">{{ route.meta.title }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="header-right">
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              {{ userStore.userInfo.nickname || '管理员' }}
              <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

      <!-- 内容区 -->
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/store/modules/user'
import { ElMessageBox } from 'element-plus'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()
const isCollapse = ref(false)

const handleCommand = async (command) => {
  if (command === 'logout') {
    try {
      await ElMessageBox.confirm('确定要退出登录吗？', '提示', {
        type: 'warning'
      })
      await userStore.logoutAction()
      router.push('/login')
    } catch (error) {
      // 取消操作
    }
  }
}
</script>

<style scoped>
.layout-container {
  height: 100vh;
}

.sidebar {
  background-color: #304156;
  transition: width 0.3s;
  overflow: hidden;
}

.logo {
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #2b2f3a;
}

.logo h1 {
  color: #fff;
  font-size: 20px;
  margin: 0;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
}

.header-left {
  display: flex;
  align-items: center;
}

.collapse-btn {
  font-size: 20px;
  cursor: pointer;
  margin-right: 15px;
}

.header-right {
  display: flex;
  align-items: center;
}

.el-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
}

.main-content {
  background-color: #f0f2f5;
  padding: 20px;
}
</style>
