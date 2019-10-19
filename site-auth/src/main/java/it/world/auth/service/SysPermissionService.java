package it.world.auth.service;



import it.world.auth.entity.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionService {
     List<SysPermission> findAll();
     List<SysPermission> findByAdminUserId(int userId);

}
