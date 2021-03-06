package it.world.service.service.impl;


import it.world.common.bean.entity.SysUser;
import it.world.service.mapper.SysUserMapper;
import it.world.service.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public Integer insUser(SysUser sysUser) {
        String password = sysUser.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(bCryptPasswordEncoder.encode(password));
        sysUserMapper.insertUser(sysUser);
        return sysUser.getId();
    }
}
