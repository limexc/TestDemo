package cn.limexc.sie.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

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
    @ApiModelProperty(value = "创建者id",example = "1")
    @TableField(value = "CREATED_BY")
    private Integer createdBy;

    //@TableField("CREATION_DATE")
    @TableField(fill = FieldFill.INSERT, jdbcType= JdbcType.TIMESTAMP)
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
        return this.roleId;
    }

}
