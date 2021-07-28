package cn.limexc.sie.entity;

import java.util.Date;

import java.io.Serializable;

import cn.limexc.sie.handler.AutoUpdateHandler;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import oracle.sql.DATE;
import oracle.sql.NUMBER;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 * UPMS权限管理系统用户信息表
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Data
@Accessors(chain = true)
@KeySequence(value = "UPMS_USER_S")
@TableName("UPMS_USER_T")
public class UpmsUserT extends Model<UpmsUserT> {

    private static final long serialVersionUID = 1L;

    /**
     * UPMS 用户表序列 ID
     */
    @ApiModelProperty(value = "用户id",example = "1")
    @TableId(value = "user_ID", type = IdType.INPUT)
    private Integer userId;
    /**
     * 用户名
     */
    @TableField("user_ALIAS")
    private String userAlias;
    /**
     * 真实姓名
     */
    @TableField("user_NAME")
    private String userName;
    /**
     * 账户密码
     */
    @TableField("user_PASSWD")
    private String userPasswd;
    /**
     * 电话
     */
    @TableField(value = "user_TEL",jdbcType = JdbcType.VARCHAR)
    private String userTel;
    /**
     * 用户邮箱
     */
    @TableField(value = "user_EMAIL",jdbcType = JdbcType.VARCHAR)
    private String userEmail;
    /**
     * 性别
     */
    @TableField(value = "user_GENDER",jdbcType = JdbcType.VARCHAR)
    private String userGender;
    /**
     * 用户状态
     */
    @TableField("user_STATUS")
    private String userStatus;
    /**
     * 备注
     */
    @TableField(value = "user_NOTE",jdbcType = JdbcType.VARCHAR)
    private String userNote;
    @TableField(value = "user_BACK1",jdbcType = JdbcType.VARCHAR)
    private String userBack1;
    @TableField(value = "user_BACK2",jdbcType = JdbcType.VARCHAR)
    private String userBack2;
    @TableField(value = "user_BACK3",jdbcType = JdbcType.VARCHAR)
    private String userBack3;
    @ApiModelProperty(value = "创建者id",example = "1")
    @TableField(value = "CREATED_BY")
    private Integer createdBy;

    //@TableField("CREATION_DATE")
    @TableField(fill = FieldFill.INSERT, jdbcType=JdbcType.TIMESTAMP)
    private Date creationDate;

    @ApiModelProperty(value = "最后更新者id",example = "1")
    @TableField(value = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;

    //@TableField("LAST_UPDATED_DATE")
    @TableField(fill = FieldFill.INSERT_UPDATE,jdbcType = JdbcType.TIMESTAMP)
    private Date lastUpdatedDate;

    @ApiModelProperty(value = "最后登录id",example = "1")
    @TableField("LAST_UPDATED_LOGIN")
    private Integer lastUpdatedLogin;

    @TableLogic
    @TableField(value = "DELETE_FLAG",jdbcType = JdbcType.VARCHAR)
    private String deleteFlag;
    @TableField(value = "DESCRIPTION",jdbcType = JdbcType.VARCHAR)
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
