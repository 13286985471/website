package it.world.auth.common;

/**
 * 存放各种状态码与返回信息
 */
public enum RetrunCode {

    success("0","success"),//成功
    faile("-1","success");//失败

    private String rcd;
    private String msg;

    RetrunCode(String rcd,String msg){
        this.msg=msg;
        this.rcd=rcd;
    }

    public String getRcd() {
        return rcd;
    }

    public String getMsg() {
        return msg;
    }
}
