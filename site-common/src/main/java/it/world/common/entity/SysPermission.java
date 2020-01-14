package it.world.common.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {


  private long id;
  private String pmName;
  private String description;
  private String url;
  private long pid;


}
