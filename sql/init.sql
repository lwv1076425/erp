-- ============================================================
-- ERP 系统数据库初始化脚本
-- 数据库: MySQL 8.0
-- ============================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS erp DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE erp;

-- ============================================================
-- 系统管理模块
-- ============================================================

-- 用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) COMMENT '昵称',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    avatar VARCHAR(255) COMMENT '头像',
    gender TINYINT DEFAULT 0 COMMENT '性别（0=未知，1=男，2=女）',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
CREATE TABLE IF NOT EXISTS sys_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '角色ID',
    role_name VARCHAR(50) NOT NULL COMMENT '角色名称',
    role_code VARCHAR(50) NOT NULL COMMENT '角色编码',
    sort INT DEFAULT 0 COMMENT '排序',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_role_code (role_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 菜单表
CREATE TABLE IF NOT EXISTS sys_menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '菜单ID',
    menu_name VARCHAR(50) NOT NULL COMMENT '菜单名称',
    parent_id BIGINT DEFAULT 0 COMMENT '父菜单ID',
    sort INT DEFAULT 0 COMMENT '排序',
    path VARCHAR(200) COMMENT '路由地址',
    component VARCHAR(255) COMMENT '组件路径',
    menu_type CHAR(1) COMMENT '菜单类型（M=目录，C=菜单，F=按钮）',
    visible TINYINT DEFAULT 0 COMMENT '是否可见（0=显示，1=隐藏）',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    perms VARCHAR(100) COMMENT '权限标识',
    icon VARCHAR(100) COMMENT '图标',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS sys_user_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    UNIQUE KEY uk_user_role (user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色菜单关联表
CREATE TABLE IF NOT EXISTS sys_role_menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    role_id BIGINT NOT NULL COMMENT '角色ID',
    menu_id BIGINT NOT NULL COMMENT '菜单ID',
    UNIQUE KEY uk_role_menu (role_id, menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';

-- 字典表
CREATE TABLE IF NOT EXISTS sys_dict (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '字典ID',
    dict_name VARCHAR(100) NOT NULL COMMENT '字典名称',
    dict_code VARCHAR(100) NOT NULL COMMENT '字典编码',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_dict_code (dict_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- 字典项表
CREATE TABLE IF NOT EXISTS sys_dict_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '字典项ID',
    dict_id BIGINT NOT NULL COMMENT '字典ID',
    item_label VARCHAR(100) NOT NULL COMMENT '字典项标签',
    item_value VARCHAR(100) NOT NULL COMMENT '字典项值',
    sort INT DEFAULT 0 COMMENT '排序',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_dict_id (dict_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典项表';

-- 系统配置表
CREATE TABLE IF NOT EXISTS sys_config (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '配置ID',
    config_name VARCHAR(100) NOT NULL COMMENT '配置名称',
    config_key VARCHAR(100) NOT NULL COMMENT '配置键',
    config_value VARCHAR(500) COMMENT '配置值',
    config_type CHAR(1) DEFAULT 'N' COMMENT '配置类型（Y=系统，N=非系统）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_config_key (config_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- ============================================================
-- 基础数据模块
-- ============================================================

-- 供应商表
CREATE TABLE IF NOT EXISTS base_supplier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '供应商ID',
    supplier_code VARCHAR(50) NOT NULL COMMENT '供应商编码',
    supplier_name VARCHAR(100) NOT NULL COMMENT '供应商名称',
    contact_person VARCHAR(50) COMMENT '联系人',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    address VARCHAR(255) COMMENT '地址',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_supplier_code (supplier_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';

-- 客户表
CREATE TABLE IF NOT EXISTS base_customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '客户ID',
    customer_code VARCHAR(50) NOT NULL COMMENT '客户编码',
    customer_name VARCHAR(100) NOT NULL COMMENT '客户名称',
    contact_person VARCHAR(50) COMMENT '联系人',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    address VARCHAR(255) COMMENT '地址',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_customer_code (customer_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户表';

-- 商品分类表
CREATE TABLE IF NOT EXISTS base_product_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '分类ID',
    category_name VARCHAR(50) NOT NULL COMMENT '分类名称',
    parent_id BIGINT DEFAULT 0 COMMENT '父分类ID',
    sort INT DEFAULT 0 COMMENT '排序',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- 商品表
CREATE TABLE IF NOT EXISTS base_product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '商品ID',
    product_code VARCHAR(50) NOT NULL COMMENT '商品编码',
    product_name VARCHAR(100) NOT NULL COMMENT '商品名称',
    category_id BIGINT COMMENT '分类ID',
    unit_id BIGINT COMMENT '计量单位ID',
    spec VARCHAR(100) COMMENT '规格型号',
    barcode VARCHAR(50) COMMENT '条形码',
    purchase_price DECIMAL(12,2) DEFAULT 0 COMMENT '采购价',
    sale_price DECIMAL(12,2) DEFAULT 0 COMMENT '销售价',
    min_stock DECIMAL(12,2) DEFAULT 0 COMMENT '最低库存',
    max_stock DECIMAL(12,2) DEFAULT 0 COMMENT '最高库存',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_product_code (product_code),
    KEY idx_category_id (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 计量单位表
CREATE TABLE IF NOT EXISTS base_unit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '单位ID',
    unit_name VARCHAR(50) NOT NULL COMMENT '单位名称',
    unit_code VARCHAR(20) NOT NULL COMMENT '单位编码',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_unit_code (unit_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='计量单位表';

-- 仓库表
CREATE TABLE IF NOT EXISTS base_warehouse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '仓库ID',
    warehouse_code VARCHAR(50) NOT NULL COMMENT '仓库编码',
    warehouse_name VARCHAR(100) NOT NULL COMMENT '仓库名称',
    address VARCHAR(255) COMMENT '仓库地址',
    manager VARCHAR(50) COMMENT '负责人',
    phone VARCHAR(20) COMMENT '联系电话',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_warehouse_code (warehouse_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库表';

-- ============================================================
-- 采购管理模块
-- ============================================================

-- 采购订单表
CREATE TABLE IF NOT EXISTS pur_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
    order_no VARCHAR(50) NOT NULL COMMENT '订单编号',
    supplier_id BIGINT NOT NULL COMMENT '供应商ID',
    order_date DATE COMMENT '订单日期',
    total_amount DECIMAL(12,2) DEFAULT 0 COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已审核，2=已入库，3=已完成，4=已取消）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_order_no (order_no),
    KEY idx_supplier_id (supplier_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购订单表';

-- 采购订单明细表
CREATE TABLE IF NOT EXISTS pur_order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量',
    price DECIMAL(12,2) NOT NULL COMMENT '单价',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    received_quantity DECIMAL(12,2) DEFAULT 0 COMMENT '已入库数量',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_order_id (order_id),
    KEY idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购订单明细表';

-- 采购入库单表
CREATE TABLE IF NOT EXISTS pur_receipt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '入库单ID',
    receipt_no VARCHAR(50) NOT NULL COMMENT '入库单编号',
    order_id BIGINT COMMENT '采购订单ID',
    warehouse_id BIGINT NOT NULL COMMENT '仓库ID',
    receipt_date DATE COMMENT '入库日期',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_receipt_no (receipt_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购入库单表';

-- 采购入库明细表
CREATE TABLE IF NOT EXISTS pur_receipt_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    receipt_id BIGINT NOT NULL COMMENT '入库单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量',
    price DECIMAL(12,2) NOT NULL COMMENT '单价',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_receipt_id (receipt_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购入库明细表';

-- 采购退货单表
CREATE TABLE IF NOT EXISTS pur_return (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '退货单ID',
    return_no VARCHAR(50) NOT NULL COMMENT '退货单编号',
    order_id BIGINT COMMENT '采购订单ID',
    supplier_id BIGINT NOT NULL COMMENT '供应商ID',
    return_date DATE COMMENT '退货日期',
    total_amount DECIMAL(12,2) DEFAULT 0 COMMENT '退货总金额',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_return_no (return_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购退货单表';

-- 采购退货明细表
CREATE TABLE IF NOT EXISTS pur_return_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    return_id BIGINT NOT NULL COMMENT '退货单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量',
    price DECIMAL(12,2) NOT NULL COMMENT '单价',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_return_id (return_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购退货明细表';

-- ============================================================
-- 销售管理模块
-- ============================================================

-- 销售订单表
CREATE TABLE IF NOT EXISTS sal_order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
    order_no VARCHAR(50) NOT NULL COMMENT '订单编号',
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    order_date DATE COMMENT '订单日期',
    total_amount DECIMAL(12,2) DEFAULT 0 COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已审核，2=已出库，3=已完成，4=已取消）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_order_no (order_no),
    KEY idx_customer_id (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售订单表';

-- 销售订单明细表
CREATE TABLE IF NOT EXISTS sal_order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量',
    price DECIMAL(12,2) NOT NULL COMMENT '单价',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    delivered_quantity DECIMAL(12,2) DEFAULT 0 COMMENT '已出库数量',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_order_id (order_id),
    KEY idx_product_id (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售订单明细表';

-- 销售出库单表
CREATE TABLE IF NOT EXISTS sal_delivery (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '出库单ID',
    delivery_no VARCHAR(50) NOT NULL COMMENT '出库单编号',
    order_id BIGINT COMMENT '销售订单ID',
    warehouse_id BIGINT NOT NULL COMMENT '仓库ID',
    delivery_date DATE COMMENT '出库日期',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_delivery_no (delivery_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售出库单表';

-- 销售出库明细表
CREATE TABLE IF NOT EXISTS sal_delivery_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    delivery_id BIGINT NOT NULL COMMENT '出库单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量',
    price DECIMAL(12,2) NOT NULL COMMENT '单价',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_delivery_id (delivery_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售出库明细表';

-- 销售退货单表
CREATE TABLE IF NOT EXISTS sal_return (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '退货单ID',
    return_no VARCHAR(50) NOT NULL COMMENT '退货单编号',
    order_id BIGINT COMMENT '销售订单ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    return_date DATE COMMENT '退货日期',
    total_amount DECIMAL(12,2) DEFAULT 0 COMMENT '退货总金额',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_return_no (return_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售退货单表';

-- 销售退货明细表
CREATE TABLE IF NOT EXISTS sal_return_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    return_id BIGINT NOT NULL COMMENT '退货单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量',
    price DECIMAL(12,2) NOT NULL COMMENT '单价',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_return_id (return_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='销售退货明细表';

-- ============================================================
-- 库存管理模块
-- ============================================================

-- 库存表
CREATE TABLE IF NOT EXISTS inv_stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '库存ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    warehouse_id BIGINT NOT NULL COMMENT '仓库ID',
    quantity DECIMAL(12,2) DEFAULT 0 COMMENT '库存数量',
    frozen_quantity DECIMAL(12,2) DEFAULT 0 COMMENT '冻结数量',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_product_warehouse (product_id, warehouse_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- 库存流水表
CREATE TABLE IF NOT EXISTS inv_stock_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '流水ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    warehouse_id BIGINT NOT NULL COMMENT '仓库ID',
    business_type VARCHAR(20) NOT NULL COMMENT '业务类型（purchase_in=采购入库，purchase_return=采购退货，sale_out=销售出库，sale_return=销售退货，check_gain=盘盈，check_loss=盘亏，transfer_in=调入，transfer_out=调出）',
    business_id BIGINT COMMENT '业务单据ID',
    business_no VARCHAR(50) COMMENT '业务单据编号',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量（正数=入库，负数=出库）',
    before_quantity DECIMAL(12,2) COMMENT '变动前库存',
    after_quantity DECIMAL(12,2) COMMENT '变动后库存',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_product_id (product_id),
    KEY idx_warehouse_id (warehouse_id),
    KEY idx_business (business_type, business_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存流水表';

-- 盘点单表
CREATE TABLE IF NOT EXISTS inv_check (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '盘点单ID',
    check_no VARCHAR(50) NOT NULL COMMENT '盘点单编号',
    warehouse_id BIGINT NOT NULL COMMENT '仓库ID',
    check_date DATE COMMENT '盘点日期',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_check_no (check_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='盘点单表';

-- 盘点明细表
CREATE TABLE IF NOT EXISTS inv_check_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    check_id BIGINT NOT NULL COMMENT '盘点单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    system_quantity DECIMAL(12,2) COMMENT '系统库存',
    actual_quantity DECIMAL(12,2) COMMENT '实际库存',
    diff_quantity DECIMAL(12,2) COMMENT '差异数量',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_check_id (check_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='盘点明细表';

-- 调拨单表
CREATE TABLE IF NOT EXISTS inv_transfer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '调拨单ID',
    transfer_no VARCHAR(50) NOT NULL COMMENT '调拨单编号',
    from_warehouse_id BIGINT NOT NULL COMMENT '调出仓库ID',
    to_warehouse_id BIGINT NOT NULL COMMENT '调入仓库ID',
    transfer_date DATE COMMENT '调拨日期',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_transfer_no (transfer_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调拨单表';

-- 调拨明细表
CREATE TABLE IF NOT EXISTS inv_transfer_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '明细ID',
    transfer_id BIGINT NOT NULL COMMENT '调拨单ID',
    product_id BIGINT NOT NULL COMMENT '商品ID',
    quantity DECIMAL(12,2) NOT NULL COMMENT '数量',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    KEY idx_transfer_id (transfer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调拨明细表';

-- ============================================================
-- 财务核算模块
-- ============================================================

-- 账户表
CREATE TABLE IF NOT EXISTS fin_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '账户ID',
    account_name VARCHAR(100) NOT NULL COMMENT '账户名称',
    account_code VARCHAR(50) NOT NULL COMMENT '账户编码',
    account_type TINYINT DEFAULT 0 COMMENT '账户类型（0=银行账户，1=现金账户，2=其他）',
    balance DECIMAL(12,2) DEFAULT 0 COMMENT '账户余额',
    status TINYINT DEFAULT 0 COMMENT '状态（0=正常，1=停用）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_account_code (account_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表';

-- 收付款单表
CREATE TABLE IF NOT EXISTS fin_payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '收付款单ID',
    payment_no VARCHAR(50) NOT NULL COMMENT '收付款单编号',
    payment_type TINYINT NOT NULL COMMENT '收付款类型（0=收款，1=付款）',
    account_id BIGINT NOT NULL COMMENT '账户ID',
    business_type VARCHAR(20) COMMENT '业务类型（purchase=采购，sale=销售）',
    business_id BIGINT COMMENT '业务单据ID',
    business_no VARCHAR(50) COMMENT '业务单据编号',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    payment_date DATE COMMENT '收付款日期',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_payment_no (payment_no),
    KEY idx_business (business_type, business_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收付款单表';

-- 发票表
CREATE TABLE IF NOT EXISTS fin_invoice (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '发票ID',
    invoice_no VARCHAR(50) NOT NULL COMMENT '发票编号',
    invoice_type TINYINT NOT NULL COMMENT '发票类型（0=销售发票，1=采购发票）',
    business_type VARCHAR(20) COMMENT '业务类型',
    business_id BIGINT COMMENT '业务单据ID',
    business_no VARCHAR(50) COMMENT '业务单据编号',
    amount DECIMAL(12,2) NOT NULL COMMENT '金额',
    tax_amount DECIMAL(12,2) DEFAULT 0 COMMENT '税额',
    invoice_date DATE COMMENT '开票日期',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_invoice_no (invoice_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发票表';

-- 日记账/凭证表
CREATE TABLE IF NOT EXISTS fin_journal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '凭证ID',
    journal_no VARCHAR(50) NOT NULL COMMENT '凭证编号',
    journal_date DATE COMMENT '凭证日期',
    business_type VARCHAR(20) COMMENT '业务类型',
    business_id BIGINT COMMENT '业务单据ID',
    business_no VARCHAR(50) COMMENT '业务单据编号',
    debit_amount DECIMAL(12,2) DEFAULT 0 COMMENT '借方金额',
    credit_amount DECIMAL(12,2) DEFAULT 0 COMMENT '贷方金额',
    summary VARCHAR(255) COMMENT '摘要',
    status TINYINT DEFAULT 0 COMMENT '状态（0=草稿，1=已确认）',
    create_by VARCHAR(50) COMMENT '创建者',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '更新者',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志（0=未删除，1=已删除）',
    remark VARCHAR(500) COMMENT '备注',
    UNIQUE KEY uk_journal_no (journal_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日记账/凭证表';
