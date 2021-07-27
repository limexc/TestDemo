package cn.limexc.sie.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * UPMS权限管理 角色信息表
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Data
@Accessors(chain = true)
@TableName("UPMS_ROLE_T")
public class UpmsRoleT extends Model<UpmsRoleT> {

    private static final long serialVersionUID = 1L;

    /**
     * 表序列 ID，该字段使用序列
     */
    @TableId(value = "ROLE_ID", type = IdType.ID_WORKER)
    private Double roleId;
    /**
     * 角色ID
     */
    @TableField("ROLE_NO")
    private Double roleNo;
    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    private String roleName;
    /**
     * 生效时间
     */
    @TableField("ROLE_STARTTIME")
    private Date roleStarttime;
    /**
     * 失效时间
     */
    @TableField("ROLE_STOPTIME")
    private Date roleStoptime;
    /**
     * 角色状态
     */
    @TableField("ROLE_STATUS")
    private Long roleStatus;
    /**
     * 备注
     */
    @TableField("ROLE_NOTE")
    private String roleNote;
    @TableField("ROLE_BACK1")
    private String roleBack1;
    @TableField("ROLE_BACK2")
    private String roleBack2;
    @TableField("ROLE_BACK3")
    private String roleBack3;
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
        return this.roleId;
    }

}
