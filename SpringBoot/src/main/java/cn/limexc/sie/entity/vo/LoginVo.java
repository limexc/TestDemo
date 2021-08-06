package cn.limexc.sie.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 用于登录数据封装
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/6 8:42
 */
@Data
@ApiModel(value = "登录对象",description = "登录对象")
public class LoginVo {
    @ApiModelProperty(value = "用户名,非用户姓名")
    private String userAlias;
    @ApiModelProperty(value = "密码")
    private String userPasswd;
}
