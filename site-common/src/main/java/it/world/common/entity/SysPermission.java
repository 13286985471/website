package it.world.common.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SysPermission implements Serializable {


  private long id;
  private String pmName;
  private String description;
  private String url;
  private long pid;


}
