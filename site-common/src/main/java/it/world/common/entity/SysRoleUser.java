package it.world.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleUser implements Serializable {
  private Integer id;
  private Integer sysUserId;
  private Integer sysRoleId;
}
