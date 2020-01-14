package it.world.common.entity;


import lombok.Data;

import java.util.List;

@Data
public class SysRole {

  private long id;
  private String roleName;
  private List<SysPermission> sysPermissions;

}
