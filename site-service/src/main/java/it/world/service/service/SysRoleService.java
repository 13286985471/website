package it.world.service.service;


import it.world.common.bean.entity.SysRole;

import java.util.List;


public interface SysRoleService {
    List<SysRole> findRoleById(Integer id);
}
