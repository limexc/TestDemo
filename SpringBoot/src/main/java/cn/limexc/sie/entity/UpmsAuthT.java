package cn.limexc.sie.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.sql.JDBCType;
import java.util.Date;

/**
 * <p>
 * UPMS权限管理需要 权限表
 * </p>
 *
 * 权限就是用户在登录后能看到系统中哪些菜单、目录、功能
 * 在该实体类中通过用户id与菜单、目录、功能id的绑定，在通过查询数据库后返回该用户正确的权限
 * @author testjava
 * @since 2021-07-27
 */
@Data
@KeySequence(value = "UPMS_AUTH_S")
@Accessors(chain = true)
@TableName("UPMS_AUTH_T")
public class UpmsAuthT extends BaseEntity {//Model<UpmsAuthT>

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @ApiModelProperty(value = "表ID",example = "1")
    @TableId(value = "AUTH_ID", type = IdType.INPUT)
    private Integer authId;
    /**
     * 菜单id
     */
    @TableField(value = "AUTH_MENUID",jdbcType = JdbcType.INTEGER)
    private Integer authMenuid;
    /**
     * 权限名
     */
    @TableField(value = "AUTH_NAME",jdbcType = JdbcType.VARCHAR)
    private String authName;
    /*
    @ApiModelProperty(value = "创建者id",example = "1")
    @TableField(value = "CREATED_BY",jdbcType = JdbcType.INTEGER)
    private Integer createdBy;

    //@TableField("CREATION_DATE")
    @TableField(fill = FieldFill.INSERT, jdbcType= JdbcType.TIMESTAMP)
    private java.util.Date creationDate;

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
        return this.authId;
    }

}
