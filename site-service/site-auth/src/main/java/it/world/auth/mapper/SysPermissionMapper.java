package it.world.auth.mapper;

import it.world.auth.entity.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionMapper {
     List<SysPermission> findAll();
     List<SysPermission> findByAdminUserId(int userId);

}
