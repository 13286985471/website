package it.world.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermissionRole implements Serializable {
  private Long id;
  private Long sysRoleId;
  private Long sysPermissionId;
}
