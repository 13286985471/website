package it.world.gateway.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUser implements Serializable {

  private static final long serialVersionUID = -4392052022219864593L;
  private Integer id;
  private String username;
  private String password;
  private String headImgUrl;
  private List<SysRole> roles;      //用户角色
}
