package it.world.service.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRole implements Serializable {

  private Long id;
  private String roleName;
  private String description;
  private List<SysPermission> sysPermissions;

}
