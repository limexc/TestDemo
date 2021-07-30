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
@KeySequence(value = "UPMS_ROLE_S")
@TableName("UPMS_ROLE_T")
public class UpmsRoleT extends BaseEntity {//Model<UpmsRoleT>

    private static final long serialVersionUID = 1L;

    /**
     * 表序列 ID，该字段使用序列
     */
    @ApiModelProperty(value = "表序列id",example = "1")
    @TableId(value = "ROLE_ID", type = IdType.INPUT)
    private Integer roleId;
    /**
     * 角色ID
     */
    @TableField(value = "ROLE_NO",jdbcType = JdbcType.INTEGER)
    private Integer roleNo;
    /**
     * 角色名称
     */
    @TableField(value = "ROLE_NAME",jdbcType = JdbcType.VARCHAR)
    private String roleName;
    /**
     * 生效时间
     */
    @TableField(value = "ROLE_STARTTIME",jdbcType = JdbcType.DATE)
    private Date roleStartTime;
    /**
     * 失效时间
     */
    @TableField(value = "ROLE_STOPTIME",jdbcType = JdbcType.DATE)
    private Date roleStopTime;
    /**
     * 角色状态
     */
    @TableField(value = "ROLE_STATUS",jdbcType = JdbcType.VARCHAR)
    private String roleStatus;
    /**
     * 备注
     */
    @TableField(value = "ROLE_NOTE",jdbcType = JdbcType.VARCHAR)
    private String roleNote;
    @TableField(value = "ROLE_BACK1",jdbcType = JdbcType.VARCHAR)
    private String roleBack1;
    @TableField(value = "ROLE_BACK2",jdbcType = JdbcType.VARCHAR)
    private String roleBack2;
    @TableField(value = "ROLE_BACK3",jdbcType = JdbcType.VARCHAR)
    private String roleBack3;

    /*
    @ApiModelProperty(value = "创建者id",example = "1")
    @TableField(value = "CREATED_BY",jdbcType = JdbcType.INTEGER)
    private Integer createdBy;

    //@TableField("CREATION_DATE")
    @TableField(fill = FieldFill.INSERT, jdbcType= JdbcType.TIMESTAMP)
    private Date creationDate;

    @ApiModelProperty(value = "最后更新者id",example = "1")
    @TableField(value = "LAST_UPDATED_BY",jdbcType = JdbcType.INTEGER)
    private Integer lastUpdatedBy;

    //@TableField("LAST_UPDATED_DATE")
    @TableField(fill = FieldFill.INSERT_UPDATE,jdbcType = JdbcType.TIMESTAMP)
    private Date lastUpdatedDate;

    @ApiModelProperty(value = "最后登录id",example = "1")
    @TableField(value = "LAST_UPDATED_LOGIN",jdbcType = JdbcType.INTEGER)
    private Integer lastUpdatedLogin;

    @TableLogic
    @TableField(value = "DELETE_FLAG",jdbcType = JdbcType.VARCHAR)
    private String deleteFlag;
    @TableField(value = "DESCRIPTION",jdbcType = JdbcType.VARCHAR)
    private String description;
*/

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
