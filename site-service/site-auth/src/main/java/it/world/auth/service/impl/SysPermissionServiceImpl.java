package it.world.auth.service.impl;



import it.world.auth.entity.SysPermission;
import it.world.auth.mapper.SysPermissionMapper;
import it.world.auth.service.SysPermissionService;
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
