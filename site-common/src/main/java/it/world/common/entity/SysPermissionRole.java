package it.world.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermissionRole implements Serializable {
  private Integer id;
  private Integer sysRoleId;
  private Integer sysPermissionId;
}
