package it.world.service.service;


import it.world.service.entity.SysRole;

import java.util.List;


public interface SysRoleService {
    List<SysRole> findRoleById(Integer id);
}
