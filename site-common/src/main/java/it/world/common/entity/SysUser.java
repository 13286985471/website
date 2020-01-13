package it.world.common.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class SysUser implements Serializable {

  private static final long serialVersionUID = -4392052022219864593L;
  private long id;
  private String username;
  private String password;
  private List<SysRole> roles;

}
