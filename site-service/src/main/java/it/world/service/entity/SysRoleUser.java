package it.world.service.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleUser implements Serializable {
  private Long id;
  private Long sysUserId;
  private Long sysRoleId;
}
