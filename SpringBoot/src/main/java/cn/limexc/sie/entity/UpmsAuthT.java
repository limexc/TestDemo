package cn.limexc.sie.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

/**
 * <p>
 * UPMS权限管理需要 权限表
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Data
@Accessors(chain = true)
@TableName("UPMS_AUTH_T")
public class UpmsAuthT extends Model<UpmsAuthT> {

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @TableId(value = "AUTH_ID", type = IdType.ID_WORKER)
    private Double authId;
    /**
     * 菜单表id
     */
    @TableField("AUTH_MENUID")
    private Double authMenuid;
    /**
     * 权限名
     */
    @TableField("AUTH_NAME")
    private String authName;
    @TableField("CREATED_BY")
    private Double createdBy;
    @TableField("CREATION_DATE")
    private Date creationDate;
    @TableField("LAST_UPDATED_BY")
    private Double lastUpdatedBy;
    @TableField("LAST_UPDATED_DATE")
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
        return this.authId;
    }

}
