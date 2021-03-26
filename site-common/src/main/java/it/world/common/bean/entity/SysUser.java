package it.world.common.bean.entity;


import lombok.Data;

import java.util.List;
@Data
public class SysUser  extends BaseEntity {

  private Integer id;
  private String username;
  private String password;
  private String headImgUrl;
  private List<SysRole> roles;      //用户角色
}
