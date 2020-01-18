package it.world.service.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {


  private Long id;
  private String pmName;
  private String description;
  private String url;
  private Long pid;


}
