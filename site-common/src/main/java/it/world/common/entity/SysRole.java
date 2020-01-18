package it.world.common.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRole implements Serializable {

  private Long id;
  private String roleName;
  private List<SysPermission> sysPermissions;

}
