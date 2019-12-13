package it.world.service.entity;


public class SysPermission {

  private long id;
  private String pmName;
  private String description;
  private String url;
  private long pid;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPmName() {
    return pmName;
  }

  public void setPmName(String pmName) {
    this.pmName = pmName;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }

}
