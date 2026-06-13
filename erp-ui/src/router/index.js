import { createRouter, createWebHistory } from 'vue-router'
import { getToken } from '@/utils/auth'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    name: 'Layout',
    component: () => import('@/layout/index.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index.vue'),
        meta: { title: '首页', icon: 'HomeFilled' }
      },
      // 系统管理
      {
        path: 'system/user',
        name: 'SystemUser',
        component: () => import('@/views/system/user/index.vue'),
        meta: { title: '用户管理' }
      },
      {
        path: 'system/role',
        name: 'SystemRole',
        component: () => import('@/views/system/role/index.vue'),
        meta: { title: '角色管理' }
      },
      {
        path: 'system/menu',
        name: 'SystemMenu',
        component: () => import('@/views/system/menu/index.vue'),
        meta: { title: '菜单管理' }
      },
      {
        path: 'system/dict',
        name: 'SystemDict',
        component: () => import('@/views/system/dict/index.vue'),
        meta: { title: '字典管理' }
      },
      {
        path: 'system/config',
        name: 'SystemConfig',
        component: () => import('@/views/system/config/index.vue'),
        meta: { title: '系统配置' }
      },
      // 基础数据
      {
        path: 'base/supplier',
        name: 'BaseSupplier',
        component: () => import('@/views/base/supplier/index.vue'),
        meta: { title: '供应商管理' }
      },
      {
        path: 'base/customer',
        name: 'BaseCustomer',
        component: () => import('@/views/base/customer/index.vue'),
        meta: { title: '客户管理' }
      },
      {
        path: 'base/product',
        name: 'BaseProduct',
        component: () => import('@/views/base/product/index.vue'),
        meta: { title: '商品管理' }
      },
      {
        path: 'base/unit',
        name: 'BaseUnit',
        component: () => import('@/views/base/unit/index.vue'),
        meta: { title: '计量单位' }
      },
      {
        path: 'base/warehouse',
        name: 'BaseWarehouse',
        component: () => import('@/views/base/warehouse/index.vue'),
        meta: { title: '仓库管理' }
      },
      // 采购管理
      {
        path: 'purchase/order',
        name: 'PurchaseOrder',
        component: () => import('@/views/purchase/order/index.vue'),
        meta: { title: '采购订单' }
      },
      {
        path: 'purchase/receipt',
        name: 'PurchaseReceipt',
        component: () => import('@/views/purchase/receipt/index.vue'),
        meta: { title: '采购入库' }
      },
      {
        path: 'purchase/return',
        name: 'PurchaseReturn',
        component: () => import('@/views/purchase/return/index.vue'),
        meta: { title: '采购退货' }
      },
      // 销售管理
      {
        path: 'sales/order',
        name: 'SalesOrder',
        component: () => import('@/views/sales/order/index.vue'),
        meta: { title: '销售订单' }
      },
      {
        path: 'sales/delivery',
        name: 'SalesDelivery',
        component: () => import('@/views/sales/delivery/index.vue'),
        meta: { title: '销售出库' }
      },
      {
        path: 'sales/return',
        name: 'SalesReturn',
        component: () => import('@/views/sales/return/index.vue'),
        meta: { title: '销售退货' }
      },
      // 库存管理
      {
        path: 'inventory/stock',
        name: 'InventoryStock',
        component: () => import('@/views/inventory/stock/index.vue'),
        meta: { title: '库存查询' }
      },
      {
        path: 'inventory/check',
        name: 'InventoryCheck',
        component: () => import('@/views/inventory/check/index.vue'),
        meta: { title: '库存盘点' }
      },
      {
        path: 'inventory/transfer',
        name: 'InventoryTransfer',
        component: () => import('@/views/inventory/transfer/index.vue'),
        meta: { title: '库存调拨' }
      },
      // 财务核算
      {
        path: 'finance/payment',
        name: 'FinancePayment',
        component: () => import('@/views/finance/payment/index.vue'),
        meta: { title: '收付款管理' }
      },
      {
        path: 'finance/invoice',
        name: 'FinanceInvoice',
        component: () => import('@/views/finance/invoice/index.vue'),
        meta: { title: '发票管理' }
      },
      {
        path: 'finance/journal',
        name: 'FinanceJournal',
        component: () => import('@/views/finance/journal/index.vue'),
        meta: { title: '日记账' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = getToken()
  if (to.path === '/login') {
    next()
  } else if (!token) {
    next('/login')
  } else {
    next()
  }
})

export default router
