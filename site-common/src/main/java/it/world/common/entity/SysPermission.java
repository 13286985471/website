package it.world.common.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {


  private Integer id;
  private String pmName;
  private String description;
  private String url;
  private Integer pid;
}
