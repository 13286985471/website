package it.world.auth.service;





import it.world.auth.entity.SysRole;

import java.util.List;


public interface SysRoleService {
    List<SysRole> findRoleById(Integer id);
}
