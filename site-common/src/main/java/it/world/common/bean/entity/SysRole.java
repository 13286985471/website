package it.world.common.bean.entity;


import lombok.Data;

import java.util.List;

@Data
public class SysRole  extends BaseEntity {

  private Integer id;
  private String roleName;
  private String description;
  private List<SysPermission> sysPermissions;

}
