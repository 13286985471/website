package it.world.common.bean.entity;

import lombok.Data;

@Data
public class SysRoleUser  extends BaseEntity {
  private Integer id;
  private Integer sysUserId;
  private Integer sysRoleId;
}
