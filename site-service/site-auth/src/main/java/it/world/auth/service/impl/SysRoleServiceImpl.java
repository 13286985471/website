package it.world.auth.service.impl;

import it.world.auth.entity.SysRole;
import it.world.auth.mapper.SysRoleMapper;
import it.world.auth.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;


    @Override
    public List<SysRole> findRoleById(Integer id) {
        return sysRoleMapper.findRoleById(id);
    }
}
