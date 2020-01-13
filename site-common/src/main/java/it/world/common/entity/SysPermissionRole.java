package it.world.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysPermissionRole {
  private long id;
  private long sysRoleId;
  private long sysPermissionId;
}
