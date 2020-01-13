package it.world.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysRoleUser {
  private long id;
  private long sysUserId;
  private long sysRoleId;
}
