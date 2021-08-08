package cn.limexc.sie.security;

import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResponseUtil;
import cn.limexc.sie.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 未授权的统一处理方式
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        log.warn("错误,未授权的操作!");
        ResponseUtil.out(response, ResultData.fail(ResponseCode.ERROR.val(), "错误,未授权的操作!"));
    }
}
