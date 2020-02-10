package it.world.service.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TestCsv {
    private Integer id;
    private String name;
    private String tel;
    private String address;
    private Date time;
    private Integer num;
}
