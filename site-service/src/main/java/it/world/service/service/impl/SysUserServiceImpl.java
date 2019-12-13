package it.world.service.service.impl;


import it.world.service.entity.SysUser;
import it.world.service.mapper.SysUserMapper;
import it.world.service.service.SysUserService;
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
