package cn.limexc.sie.util;

/**
 * @Description code码封装枚举类
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/26 8:40
 */
public enum ResponseCode {
    /** 成功 */
    SUCCESS("200", "成功"),

    /** 操作失败 */
    ERROR("500", "操作失败");

    private ResponseCode(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
