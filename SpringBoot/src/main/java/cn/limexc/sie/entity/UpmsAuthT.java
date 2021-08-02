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
 * 在该实体类中通过用户id与菜单、目录、功能id的绑定
 * @author 贤致源
 * @since 2021-07-27
 */
@Data
@KeySequence(value = "UPMS_AUTH_S")
@Accessors(chain = true)
@TableName("UPMS_AUTH_T")
public class UpmsAuthT extends BaseEntity<UpmsAuthT> {//Model<UpmsAuthT>

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

    @Override
    protected Serializable pkVal() {
        return this.authId;
    }

}
