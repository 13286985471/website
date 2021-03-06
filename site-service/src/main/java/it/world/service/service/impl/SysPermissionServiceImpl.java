package it.world.service.service.impl;


import it.world.common.bean.entity.SysPermission;
import it.world.service.mapper.SysPermissionMapper;
import it.world.service.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findAll() {
        return sysPermissionMapper.findAll();
    }

    @Override
    public List<SysPermission> findByAdminUserId(int userId) {
        return sysPermissionMapper.findByAdminUserId(userId);
    }
}
