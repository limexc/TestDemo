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
 * UPMS权限管理 菜单表
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Data
@Accessors(chain = true)
@KeySequence(value = "UPMS_MENU_S")
@TableName("UPMS_MENU_T")
public class UpmsMenuT extends Model<UpmsMenuT> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单表ID
     */
    @TableId(value = "MENU_ID", type = IdType.INPUT)
    private Integer menuId;
    /**
     * 菜单名
     */
    @TableField(value = "MENU_NAME",jdbcType = JdbcType.VARCHAR)
    private String menuName;
    /**
     * 菜单类型|目录 菜单 功能
     */
    @TableField(value = "MENU_TYPE",jdbcType = JdbcType.VARCHAR)
    private String menuType;
    /**
     * 上级目录
     */
    @TableField(value = "MENU_UPPER",jdbcType = JdbcType.INTEGER)
    private Integer menuUpper;
    /**
     * 菜单标识
     */
    @TableField(value = "MENU_FLAGE",jdbcType = JdbcType.VARCHAR)
    private String menuFlage;
    /**
     * 菜单地址
     */
    @TableField(value = "MENU_URL",jdbcType = JdbcType.VARCHAR)
    private String menuUrl;
    /**
     * 菜单图标
     */
    @TableField(value = "MENU_ICO",jdbcType = JdbcType.VARCHAR)
    private String menuIco;
    /**
     * 排序号
     */
    @TableField(value = "MENU_SERIAL",jdbcType = JdbcType.INTEGER)
    private Integer menuSerial;
    /**
     * 状态|有效 无效
     */
    @TableField(value = "MENU_STATUS",jdbcType = JdbcType.VARCHAR)
    private String menuStatus;
    /**
     * 备注
     */
    @TableField(value = "MENU_NOTE",jdbcType = JdbcType.VARCHAR)
    private String menuNote;
    @TableField(value = "MENU_BACK1",jdbcType = JdbcType.VARCHAR)
    private String menuBack1;
    @TableField(value = "MENU_BACK2",jdbcType = JdbcType.VARCHAR)
    private String menuBack2;
    @TableField(value = "MENU_BACK3",jdbcType = JdbcType.VARCHAR)
    private String menuBack3;
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


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
