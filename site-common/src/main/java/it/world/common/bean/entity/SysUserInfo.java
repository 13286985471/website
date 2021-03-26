package it.world.common.bean.entity;

import lombok.Data;

/**
 * 用户拓展信息表
 */
@Data
public class SysUserInfo  extends BaseEntity {
  private Integer id;
  private String provinceCode;
  private String cityCode;
  private String districtCode;
  private String address;
  private String phoneNum;
  private String email;
}
