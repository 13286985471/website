package it.world.common.unified;

import lombok.Data;

/**
 * 全局响应报文母体
 */
@Data
public class RespBody {
    private boolean state=true;
    //响应码
    private Integer code=200;
    //响应消息
    private String msg="success";
    //响应内容
    private Object[] data;

    public RespBody(boolean state,Integer code) {
        this.state=state;
        this.code = code;
    }

    public RespBody(boolean state,Integer code, String msg) {
        this.state=state;
        this.code = code;
        this.msg = msg;
    }


    public RespBody(boolean state,Integer code, String msg, Object... data) {
        this.state=state;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RespBody(Object data) {
        this.data[0] = data;
    }

    public RespBody() {

    }

    public void setData(Object... data) {
        this.data = data;
    }
}
