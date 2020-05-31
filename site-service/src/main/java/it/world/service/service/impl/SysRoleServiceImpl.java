package it.world.service.service.impl;


import it.world.common.entity.SysRole;
import it.world.service.mapper.SysRoleMapper;
import it.world.service.service.SysRoleService;
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
