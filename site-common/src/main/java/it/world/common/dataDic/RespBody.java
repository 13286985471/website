package it.world.common.dataDic;

import lombok.Data;

/**
 * 响应报文封装
 */
@Data
public class RespBody {
    private boolean success=true;
    //响应码
    private String code="200";
    //响应消息
    private String msg="success";
    //响应内容
    private Object data;
    public RespBody(boolean success,String code, String msg) {
        this.success=success;
        this.code = code;
        this.msg = msg;
    }

    public RespBody(boolean success,String code) {
        this.success=success;
        this.code = code;
    }
    public RespBody(boolean success,String code, String msg, Object data) {
        this.success=success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RespBody(Object data) {
        this.data = data;
    }

    public RespBody() {
        super();
    }

}
