package cn.limexc.sie.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

/**
 * <p>
 * UPMS权限管理 用户角色表
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Data
@Accessors(chain = true)
@TableName("UPMS_ROFU_T")
public class UpmsRofuT extends Model<UpmsRofuT> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色表ID
     */
    @TableId(value = "ROFU_ID", type = IdType.ID_WORKER)
    private Double rofuId;
    /**
     * 用户ID
     */
    @TableField("ROFU_USERID")
    private Double rofuUserid;
    /**
     * 权限表ID
     */
    @TableField("ROFU_AUTHID")
    private Double rofuAuthid;
    /**
     * 备注
     */
    @TableField("ROFU_NOTE")
    private String rofuNote;
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
        return this.rofuId;
    }

}
