package it.world.service.service.impl;


import it.world.common.entity.SysRoleUser;
import it.world.common.entity.SysUser;
import it.world.service.mapper.SysRoleUserMapper;
import it.world.service.service.SysRoleUserService;
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
