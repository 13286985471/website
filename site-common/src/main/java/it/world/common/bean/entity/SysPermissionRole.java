package it.world.common.bean.entity;

import lombok.Data;

@Data
public class SysPermissionRole  extends BaseEntity {
  private Integer id;
  private Integer sysRoleId;
  private Integer sysPermissionId;
}
