package it.world.service.entity;


import java.io.Serializable;
import java.util.List;

public class SysUser implements Serializable {

  private static final long serialVersionUID = -4392052022219864593L;
  private long id;
  private String username;
  private String password;
  private List<SysRole> roles;



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "SysUser{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  public List<SysRole> getRoles() {
    return roles;
  }

  public void setRoles(List<SysRole> roles) {
    this.roles = roles;
  }
}
