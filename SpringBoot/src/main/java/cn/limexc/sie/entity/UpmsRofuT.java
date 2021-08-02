package cn.limexc.sie.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * UPMS权限管理 用户角色表
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Data
@Accessors(chain = true)
@KeySequence(value = "UPMS_ROFU_S")
@TableName("UPMS_ROFU_T")
public class UpmsRofuT extends BaseEntity{//Model<UpmsRofuT>

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色表ID
     */
    @TableId(value = "ROFU_ID", type = IdType.INPUT)
    private Integer rofuId;
    /**
     * 用户ID
     */
    @TableField("ROFU_USERID")
    private Integer rofuUserid;
    /**
     * 权限表ID
     */
    @TableField("ROFU_ROLEID")
    private Integer rofuRoleid;
    /**
     * 备注
     */
    @TableField("ROFU_NOTE")
    private String rofuNote;
    /*
    @ApiModelProperty(value = "创建者id",example = "1")
    @TableField(value = "CREATED_BY")
    private Integer createdBy;

    //@TableField("CREATION_DATE")
    @TableField(fill = FieldFill.INSERT, jdbcType= JdbcType.TIMESTAMP)
    private java.util.Date creationDate;

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
*/

    @Override
    protected Serializable pkVal() {
        return this.rofuId;
    }

}
