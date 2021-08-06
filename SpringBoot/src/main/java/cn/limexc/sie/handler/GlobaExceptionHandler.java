package cn.limexc.sie.handler;

import cn.limexc.sie.exception.UserException;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 统一异常处理
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/28 10:43
 */
@ControllerAdvice
public class GlobaExceptionHandler {

    /**
     * 全局异常捕获处理
     * ExceptionHandler(Exception.class) 指定出现了什么异常执行这个方法
     * ResponseBody        返回数据
     * @param e  异常
     * @return   格式化返回数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData error(Exception e){
        e.printStackTrace();
        return ResultData.fail(ResponseCode.ERROR.val(), "执行了全局异常处理");
    }

    /**
     * 特定异常捕获处理
     * ExceptionHandler() 指定出现了什么异常执行这个方法
     * ResponseBody        返回数据
     * @param
     * @return   格式化返回数据
     */
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    @ResponseBody
    public ResultData error(org.springframework.dao.DataIntegrityViolationException e){
        e.printStackTrace();
        return ResultData.fail(ResponseCode.ERROR.val(), "数据格式错误");
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResultData error(UserException e){
        e.printStackTrace();
        return ResultData.fail(ResponseCode.ERROR.val(),e.getMsg());
    }

}
