package cn.limexc.sie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/28 22:06
 */
@Data
public class BaseEntity<T extends Model> extends Model{
    @ApiModelProperty(value = "创建者id",example = "1")
    @TableField(value = "CREATED_BY",jdbcType = JdbcType.INTEGER)
    private Integer createdBy;

    //@TableField("CREATION_DATE")
    @TableField(fill = FieldFill.INSERT, jdbcType=JdbcType.TIMESTAMP)
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
}
