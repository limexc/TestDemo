package cn.limexc.sie.util;

import lombok.Data;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/26 8:41
 */
@Data
public class ResultData {

    private String code;

    private String msg;

    private Object info;

    public static ResultData success(Object info) {
        return resultData(ResponseCode.SUCCESS.val(), ResponseCode.SUCCESS.msg(), info);
    }

    public static ResultData success(Object info, String msg) {
        return resultData(ResponseCode.SUCCESS.val(), msg, info);
    }

    public static ResultData fail(String code, String msg) {
        return resultData(code, msg, null);
    }

    public static ResultData fail(String code, String msg, Object info) {
        return resultData(code, msg, info);
    }

    private static ResultData resultData(String code, String msg, Object info) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setInfo(info);
        return resultData;
    }
}