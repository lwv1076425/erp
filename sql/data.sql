-- ============================================================
-- ERP 系统初始数据
-- ============================================================

USE erp;

-- ============================================================
-- 系统管理初始数据
-- ============================================================

-- 初始管理员用户（密码: admin123，BCrypt 加密）
INSERT INTO sys_user (username, password, nickname, status, create_by) VALUES
('admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '超级管理员', 0, 'system');

-- 初始角色
INSERT INTO sys_role (role_name, role_code, sort, status, create_by) VALUES
('超级管理员', 'admin', 1, 0, 'system'),
('普通用户', 'user', 2, 0, 'system');

-- 用户角色关联
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1);

-- 初始菜单
INSERT INTO sys_menu (menu_name, parent_id, sort, path, component, menu_type, visible, status, perms, icon) VALUES
('系统管理', 0, 1, '/system', NULL, 'M', 0, 0, NULL, 'setting'),
('用户管理', 1, 1, 'user', 'system/user/index', 'C', 0, 0, 'system:user:list', 'user'),
('角色管理', 1, 2, 'role', 'system/role/index', 'C', 0, 0, 'system:role:list', 'peoples'),
('菜单管理', 1, 3, 'menu', 'system/menu/index', 'C', 0, 0, 'system:menu:list', 'tree-table'),
('字典管理', 1, 4, 'dict', 'system/dict/index', 'C', 0, 0, 'system:dict:list', 'dict'),
('系统配置', 1, 5, 'config', 'system/config/index', 'C', 0, 0, 'system:config:list', 'edit'),
('基础数据', 0, 2, '/base', NULL, 'M', 0, 0, NULL, 'component'),
('供应商管理', 7, 1, 'supplier', 'base/supplier/index', 'C', 0, 0, 'base:supplier:list', 'peoples'),
('客户管理', 7, 2, 'customer', 'base/customer/index', 'C', 0, 0, 'base:customer:list', 'peoples'),
('商品管理', 7, 3, 'product', 'base/product/index', 'C', 0, 0, 'base:product:list', 'goods'),
('商品分类', 7, 4, 'category', 'base/category/index', 'C', 0, 0, 'base:category:list', 'tree'),
('计量单位', 7, 5, 'unit', 'base/unit/index', 'C', 0, 0, 'base:unit:list', 'edit'),
('仓库管理', 7, 6, 'warehouse', 'base/warehouse/index', 'C', 0, 0, 'base:warehouse:list', 'house'),
('采购管理', 0, 3, '/purchase', NULL, 'M', 0, 0, NULL, 'shopping'),
('采购订单', 14, 1, 'order', 'purchase/order/index', 'C', 0, 0, 'purchase:order:list', 'list'),
('采购入库', 14, 2, 'receipt', 'purchase/receipt/index', 'C', 0, 0, 'purchase:receipt:list', 'inbound'),
('采购退货', 14, 3, 'return', 'purchase/return/index', 'C', 0, 0, 'purchase:return:list', 'return'),
('销售管理', 0, 4, '/sales', NULL, 'M', 0, 0, NULL, 'money'),
('销售订单', 18, 1, 'order', 'sales/order/index', 'C', 0, 0, 'sales:order:list', 'list'),
('销售出库', 18, 2, 'delivery', 'sales/delivery/index', 'C', 0, 0, 'sales:delivery:list', 'outbound'),
('销售退货', 18, 3, 'return', 'sales/return/index', 'C', 0, 0, 'sales:return:list', 'return'),
('库存管理', 0, 5, '/inventory', NULL, 'M', 0, 0, NULL, 'warehouse'),
('库存查询', 22, 1, 'stock', 'inventory/stock/index', 'C', 0, 0, 'inventory:stock:list', 'list'),
('库存盘点', 22, 2, 'check', 'inventory/check/index', 'C', 0, 0, 'inventory:check:list', 'checked'),
('库存调拨', 22, 3, 'transfer', 'inventory/transfer/index', 'C', 0, 0, 'inventory:transfer:list', 'swap'),
('财务核算', 0, 6, '/finance', NULL, 'M', 0, 0, NULL, 'chart'),
('收付款管理', 26, 1, 'payment', 'finance/payment/index', 'C', 0, 0, 'finance:payment:list', 'money'),
('发票管理', 26, 2, 'invoice', 'finance/invoice/index', 'C', 0, 0, 'finance:invoice:list', 'invoice'),
('日记账', 26, 3, 'journal', 'finance/journal/index', 'C', 0, 0, 'finance:journal:list', 'log');

-- 用户操作按钮权限
INSERT INTO sys_menu (menu_name, parent_id, sort, menu_type, perms) VALUES
('用户查询', 2, 1, 'F', 'system:user:query'),
('用户新增', 2, 2, 'F', 'system:user:add'),
('用户修改', 2, 3, 'F', 'system:user:edit'),
('用户删除', 2, 4, 'F', 'system:user:remove'),
('重置密码', 2, 5, 'F', 'system:user:resetPwd'),
('角色查询', 3, 1, 'F', 'system:role:query'),
('角色新增', 3, 2, 'F', 'system:role:add'),
('角色修改', 3, 3, 'F', 'system:role:edit'),
('角色删除', 3, 4, 'F', 'system:role:remove');

-- 角色菜单关联（超级管理员拥有所有权限）
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, id FROM sys_menu;

-- 初始字典
INSERT INTO sys_dict (dict_name, dict_code, status, create_by) VALUES
('用户性别', 'sys_user_gender', 0, 'system'),
('系统状态', 'sys_status', 0, 'system'),
('菜单类型', 'sys_menu_type', 0, 'system'),
('订单状态', 'biz_order_status', 0, 'system'),
('收付款类型', 'fin_payment_type', 0, 'system');

-- 字典项
INSERT INTO sys_dict_item (dict_id, item_label, item_value, sort) VALUES
(1, '未知', '0', 1),
(1, '男', '1', 2),
(1, '女', '2', 3),
(2, '正常', '0', 1),
(2, '停用', '1', 2),
(3, '目录', 'M', 1),
(3, '菜单', 'C', 2),
(3, '按钮', 'F', 3),
(4, '草稿', '0', 1),
(4, '已审核', '1', 2),
(4, '已完成', '2', 3),
(4, '已取消', '3', 4),
(5, '收款', '0', 1),
(5, '付款', '1', 2);

-- 系统配置
INSERT INTO sys_config (config_name, config_key, config_value, config_type, create_by) VALUES
('主框架页-默认皮肤', 'sys.index.skinName', 'skin-blue', 'Y', 'system'),
('用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'system'),
('主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'system');

-- ============================================================
-- 基础数据初始数据
-- ============================================================

-- 计量单位
INSERT INTO base_unit (unit_name, unit_code, status, create_by) VALUES
('个', 'piece', 0, 'system'),
('件', 'item', 0, 'system'),
('箱', 'box', 0, 'system'),
('千克', 'kg', 0, 'system'),
('吨', 'ton', 0, 'system'),
('米', 'meter', 0, 'system'),
('平方米', 'sqm', 0, 'system'),
('升', 'liter', 0, 'system');

-- 仓库
INSERT INTO base_warehouse (warehouse_code, warehouse_name, status, create_by) VALUES
('WH001', '主仓库', 0, 'system'),
('WH002', '退货仓', 0, 'system');

-- ============================================================
-- 财务初始数据
-- ============================================================

-- 账户
INSERT INTO fin_account (account_name, account_code, account_type, balance, status, create_by) VALUES
('基本户', 'ACC001', 0, 0, 0, 'system'),
('现金', 'ACC002', 1, 0, 0, 'system');
