package it.world.auth.service;


import it.world.auth.entity.SysUser;

public interface SysUserService {
    SysUser findByUsername(String username);
}