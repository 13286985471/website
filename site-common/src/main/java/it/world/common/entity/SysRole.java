package it.world.common.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SysRole {

  private long id;
  private String roleName;
  private List<SysPermission> sysPermissions;

}
