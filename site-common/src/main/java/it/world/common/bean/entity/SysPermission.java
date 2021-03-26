package it.world.common.bean.entity;


import lombok.Data;

@Data
public class SysPermission extends BaseEntity{


  private Integer id;
  private String pmName;
  private String description;
  private String url;
  private Integer pid;
}
