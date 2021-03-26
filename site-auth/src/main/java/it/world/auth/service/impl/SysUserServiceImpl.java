package it.world.auth.service.impl;


import it.world.auth.mapper.SysUserMapper;
import it.world.auth.service.SysUserService;
import it.world.common.bean.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.findByUsername(username);
    }

}
