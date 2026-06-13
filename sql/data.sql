-- ============================================================
-- ERP 系统初始数据
-- 字符集: utf8mb4
-- ============================================================

SET NAMES utf8mb4;
SET CHARACTER_SET_CLIENT = utf8mb4;
SET CHARACTER_SET_CONNECTION = utf8mb4;

USE erp;

-- ============================================================
-- 系统管理初始数据
-- ============================================================

-- 初始管理员用户（密码: admin123，BCrypt 加密）
REPLACE INTO sys_user (id, username, password, nickname, status, create_by) VALUES
(1, 'admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 'Admin', 0, 'system');

-- 初始角色
REPLACE INTO sys_role (id, role_name, role_code, sort, status, create_by) VALUES
(1, 'Admin', 'admin', 1, 0, 'system'),
(2, 'User', 'user', 2, 0, 'system');

-- 用户角色关联
REPLACE INTO sys_user_role (user_id, role_id) VALUES
(1, 1);

-- 初始菜单
REPLACE INTO sys_menu (id, menu_name, parent_id, sort, path, component, menu_type, visible, status, perms, icon) VALUES
(1, 'System', 0, 1, '/system', NULL, 'M', 0, 0, NULL, 'setting'),
(2, 'User', 1, 1, 'user', 'system/user/index', 'C', 0, 0, 'system:user:list', 'user'),
(3, 'Role', 1, 2, 'role', 'system/role/index', 'C', 0, 0, 'system:role:list', 'peoples'),
(4, 'Menu', 1, 3, 'menu', 'system/menu/index', 'C', 0, 0, 'system:menu:list', 'tree-table'),
(5, 'Dict', 1, 4, 'dict', 'system/dict/index', 'C', 0, 0, 'system:dict:list', 'dict'),
(6, 'Config', 1, 5, 'config', 'system/config/index', 'C', 0, 0, 'system:config:list', 'edit'),
(7, 'Base', 0, 2, '/base', NULL, 'M', 0, 0, NULL, 'component'),
(8, 'Supplier', 7, 1, 'supplier', 'base/supplier/index', 'C', 0, 0, 'base:supplier:list', 'peoples'),
(9, 'Customer', 7, 2, 'customer', 'base/customer/index', 'C', 0, 0, 'base:customer:list', 'peoples'),
(10, 'Product', 7, 3, 'product', 'base/product/index', 'C', 0, 0, 'base:product:list', 'goods'),
(11, 'Category', 7, 4, 'category', 'base/category/index', 'C', 0, 0, 'base:category:list', 'tree'),
(12, 'Unit', 7, 5, 'unit', 'base/unit/index', 'C', 0, 0, 'base:unit:list', 'edit'),
(13, 'Warehouse', 7, 6, 'warehouse', 'base/warehouse/index', 'C', 0, 0, 'base:warehouse:list', 'house'),
(14, 'Purchase', 0, 3, '/purchase', NULL, 'M', 0, 0, NULL, 'shopping'),
(15, 'PurchaseOrder', 14, 1, 'order', 'purchase/order/index', 'C', 0, 0, 'purchase:order:list', 'list'),
(16, 'PurchaseReceipt', 14, 2, 'receipt', 'purchase/receipt/index', 'C', 0, 0, 'purchase:receipt:list', 'inbound'),
(17, 'PurchaseReturn', 14, 3, 'return', 'purchase/return/index', 'C', 0, 0, 'purchase:return:list', 'return'),
(18, 'Sales', 0, 4, '/sales', NULL, 'M', 0, 0, NULL, 'money'),
(19, 'SalesOrder', 18, 1, 'order', 'sales/order/index', 'C', 0, 0, 'sales:order:list', 'list'),
(20, 'SalesDelivery', 18, 2, 'delivery', 'sales/delivery/index', 'C', 0, 0, 'sales:delivery:list', 'outbound'),
(21, 'SalesReturn', 18, 3, 'return', 'sales/return/index', 'C', 0, 0, 'sales:return:list', 'return'),
(22, 'Inventory', 0, 5, '/inventory', NULL, 'M', 0, 0, NULL, 'warehouse'),
(23, 'Stock', 22, 1, 'stock', 'inventory/stock/index', 'C', 0, 0, 'inventory:stock:list', 'list'),
(24, 'StockCheck', 22, 2, 'check', 'inventory/check/index', 'C', 0, 0, 'inventory:check:list', 'checked'),
(25, 'StockTransfer', 22, 3, 'transfer', 'inventory/transfer/index', 'C', 0, 0, 'inventory:transfer:list', 'swap'),
(26, 'Finance', 0, 6, '/finance', NULL, 'M', 0, 0, NULL, 'chart'),
(27, 'Payment', 26, 1, 'payment', 'finance/payment/index', 'C', 0, 0, 'finance:payment:list', 'money'),
(28, 'Invoice', 26, 2, 'invoice', 'finance/invoice/index', 'C', 0, 0, 'finance:invoice:list', 'invoice'),
(29, 'Journal', 26, 3, 'journal', 'finance/journal/index', 'C', 0, 0, 'finance:journal:list', 'log');

-- 用户操作按钮权限
REPLACE INTO sys_menu (id, menu_name, parent_id, sort, menu_type, perms) VALUES
(30, 'UserQuery', 2, 1, 'F', 'system:user:query'),
(31, 'UserAdd', 2, 2, 'F', 'system:user:add'),
(32, 'UserEdit', 2, 3, 'F', 'system:user:edit'),
(33, 'UserDelete', 2, 4, 'F', 'system:user:remove'),
(34, 'UserResetPwd', 2, 5, 'F', 'system:user:resetPwd'),
(35, 'RoleQuery', 3, 1, 'F', 'system:role:query'),
(36, 'RoleAdd', 3, 2, 'F', 'system:role:add'),
(37, 'RoleEdit', 3, 3, 'F', 'system:role:edit'),
(38, 'RoleDelete', 3, 4, 'F', 'system:role:remove');

-- 角色菜单关联（超级管理员拥有所有权限）
REPLACE INTO sys_role_menu (role_id, menu_id)
SELECT 1, id FROM sys_menu;

-- 初始字典
REPLACE INTO sys_dict (id, dict_name, dict_code, status, create_by) VALUES
(1, 'Gender', 'sys_user_gender', 0, 'system'),
(2, 'Status', 'sys_status', 0, 'system'),
(3, 'MenuType', 'sys_menu_type', 0, 'system'),
(4, 'OrderStatus', 'biz_order_status', 0, 'system'),
(5, 'PaymentType', 'fin_payment_type', 0, 'system');

-- 字典项
REPLACE INTO sys_dict_item (dict_id, item_label, item_value, sort) VALUES
(1, 'Unknown', '0', 1),
(1, 'Male', '1', 2),
(1, 'Female', '2', 3),
(2, 'Normal', '0', 1),
(2, 'Disabled', '1', 2),
(3, 'Directory', 'M', 1),
(3, 'Menu', 'C', 2),
(3, 'Button', 'F', 3),
(4, 'Draft', '0', 1),
(4, 'Approved', '1', 2),
(4, 'Completed', '2', 3),
(4, 'Cancelled', '3', 4),
(5, 'Receipt', '0', 1),
(5, 'Payment', '1', 2);

-- 系统配置
REPLACE INTO sys_config (config_name, config_key, config_value, config_type, create_by) VALUES
('Skin', 'sys.index.skinName', 'skin-blue', 'Y', 'system'),
('InitPassword', 'sys.user.initPassword', '123456', 'Y', 'system'),
('SideTheme', 'sys.index.sideTheme', 'theme-dark', 'Y', 'system');

-- ============================================================
-- 基础数据初始数据
-- ============================================================

-- 计量单位
REPLACE INTO base_unit (id, unit_name, unit_code, status, create_by) VALUES
(1, 'Piece', 'piece', 0, 'system'),
(2, 'Item', 'item', 0, 'system'),
(3, 'Box', 'box', 0, 'system'),
(4, 'KG', 'kg', 0, 'system'),
(5, 'Ton', 'ton', 0, 'system'),
(6, 'Meter', 'meter', 0, 'system'),
(7, 'SQM', 'sqm', 0, 'system'),
(8, 'Liter', 'liter', 0, 'system');

-- 仓库
REPLACE INTO base_warehouse (id, warehouse_code, warehouse_name, status, create_by) VALUES
(1, 'WH001', 'Main', 0, 'system'),
(2, 'WH002', 'Return', 0, 'system');

-- ============================================================
-- 财务初始数据
-- ============================================================

-- 账户
REPLACE INTO fin_account (id, account_name, account_code, account_type, balance, status, create_by) VALUES
(1, 'Bank', 'ACC001', 0, 0, 0, 'system'),
(2, 'Cash', 'ACC002', 1, 0, 0, 'system');
