package cn.limexc.sie.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/28 0:50
 */
@Data
public class UpmsUserTQuery {

    @ApiModelProperty("用户名 模糊查询")
    private String userAlias;
    private String userName;
    private String userStatus;
    private Date creationDate;
    private Date lastUpdatedDate;

}
