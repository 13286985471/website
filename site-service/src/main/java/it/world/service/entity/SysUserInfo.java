package it.world.service.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户拓展信息表
 */
@Data
public class SysUserInfo implements Serializable {
  private Long id;
  private String provinceCode;
  private String cityCode;
  private String districtCode;
  private String address;
  private String phoneNum;
  private String email;
}
