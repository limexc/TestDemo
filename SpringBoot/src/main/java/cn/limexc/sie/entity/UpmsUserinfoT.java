package cn.limexc.sie.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@KeySequence(value = "UPMS_USERINFO_S")
@TableName("UPMS_USERINFO_T")
public class UpmsUserinfoT extends Model<UpmsUserinfoT> {

    private static final long serialVersionUID = 1L;

    /**
     * UPMS 用户表序列 ID
     */
    @TableId(value = "USERINFO_ID", type = IdType.INPUT)
    private Double userinfoId;
    /**
     * 用户名
     */
    @TableField("USERINFO_ALIAS")
    private String userinfoAlias;
    /**
     * 真实姓名
     */
    @TableField("USERINFO_NAME")
    private String userinfoName;
    /**
     * 账户密码
     */
    @TableField("USERINFO_PASSWD")
    private String userinfoPasswd;
    /**
     * 电话
     */
    @TableField("USERINFO_TEL")
    private String userinfoTel;
    /**
     * 用户邮箱
     */
    @TableField("USERINFO_EMAIL")
    private String userinfoEmail;
    /**
     * 性别
     */
    @TableField("USERINFO_GENDER")
    private String userinfoGender;
    /**
     * 用户状态
     */
    @TableField("USERINFO_STATUS")
    private String userinfoStatus;
    /**
     * 备注
     */
    @TableField("USERINFO_NOTE")
    private String userinfoNote;
    @TableField("USERINFO_BACK1")
    private String userinfoBack1;
    @TableField("USERINFO_BACK2")
    private String userinfoBack2;
    @TableField("USERINFO_BACK3")
    private String userinfoBack3;
    @TableField("CREATED_BY")
    private Double createdBy;

    //@TableField("CREATION_DATE")
    @TableField(fill = FieldFill.INSERT)
    private Date creationDate;
    @TableField("LAST_UPDATED_BY")
    private Double lastUpdatedBy;

    //@TableField("LAST_UPDATED_DATE")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastUpdatedDate;
    @TableField("LAST_UPDATED_LOGIN")
    private Double lastUpdatedLogin;
    @TableField("DELETE_FLAG")
    @TableLogic
    private String deleteFlag;
    @TableField("DESCRIPTION")
    private String description;


    @Override
    protected Serializable pkVal() {
        return this.userinfoId;
    }

}
