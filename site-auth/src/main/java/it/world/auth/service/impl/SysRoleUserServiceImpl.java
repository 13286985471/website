package it.world.auth.service.impl;


import it.world.auth.mapper.SysRoleUserMapper;
import it.world.auth.service.SysRoleUserService;
import it.world.common.bean.entity.SysRoleUser;
import it.world.common.bean.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    @Override
    public SysRoleUser findRoleIdsByUserId(SysUser user) {
        return sysRoleUserMapper.findRoleIdsByUserId(user);
    }
}
