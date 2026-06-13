package com.erp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.system.entity.SysUser;

/**
 * 用户 Service
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户
     */
    SysUser getByUsername(String username);
}
