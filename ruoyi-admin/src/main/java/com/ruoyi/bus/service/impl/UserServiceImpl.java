package com.ruoyi.bus.service.impl;

import com.ruoyi.bus.service.IUserService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author 赵传昊
 * @Date 2019/5/10 21:09
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public SysUser getUser() {
        SysUser user = ShiroUtils.getSysUser();
        if (user != null){
            SysUser sysUser = sysUserService.selectUserById(user.getUserId());
            user.setStuId(sysUser.getStuId());
            user.setStudentNumber(sysUser.getStudentNumber());
            user.setName(sysUser.getName());
            user.setOrganizationName(sysUser.getOrganizationName());
        }
        return user;
    }
}
