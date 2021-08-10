package cn.limexc.sie.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * <p>
 * UPMS权限管理系统用户信息表
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Data
@Accessors(chain = true)
@KeySequence(value = "UPMS_USER_S")
@TableName("UPMS_USER_T")
public class UpmsUserT extends BaseEntity {//Model<UpmsUserT>

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
    @TableField(value = "user_ALIAS",jdbcType = JdbcType.VARCHAR)
    private String userAlias;
    /**
     * 真实姓名
     */
    @TableField(value = "user_NAME",jdbcType = JdbcType.VARCHAR)
    private String userName;
    /**
     * 账户密码
     */
    @TableField(value = "user_PASSWD",jdbcType = JdbcType.VARCHAR)
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
    @TableField(value = "user_STATUS",jdbcType = JdbcType.VARCHAR)
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

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "UpmsUserT{" +
                "userId=" + userId +
                ", userAlias='" + userAlias + '\'' +
                ", userName='" + userName + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", userNote='" + userNote + '\'' +
                ", userBack1='" + userBack1 + '\'' +
                ", userBack2='" + userBack2 + '\'' +
                ", userBack3='" + userBack3 + '\'' +
                '}';
    }
}
