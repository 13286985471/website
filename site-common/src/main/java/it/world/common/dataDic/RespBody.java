package it.world.common.dataDic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 响应报文封装
 */
@ToString
@Setter
@Getter
public class RespBody {
    //响应码
    String code="200";
    //响应消息
    String msg="success";
    //响应内容
    Object data;

    public RespBody(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RespBody(Object data) {
        this.data = data;
    }

}
