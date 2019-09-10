package it.world.auth.entity;


public class SysPermissionRole {

  private long id;
  private long sysRoleId;
  private long sysPermissionId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSysRoleId() {
    return sysRoleId;
  }

  public void setSysRoleId(long sysRoleId) {
    this.sysRoleId = sysRoleId;
  }


  public long getSysPermissionId() {
    return sysPermissionId;
  }

  public void setSysPermissionId(long sysPermissionId) {
    this.sysPermissionId = sysPermissionId;
  }

  @Override
  public String toString() {
    return "SysPermissionRole{" +
            "id=" + id +
            ", sysRoleId=" + sysRoleId +
            ", sysPermissionId=" + sysPermissionId +
            '}';
  }
}
