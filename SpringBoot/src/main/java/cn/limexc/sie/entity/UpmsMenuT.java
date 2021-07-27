package cn.limexc.sie.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

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
@TableName("UPMS_MENU_T")
public class UpmsMenuT extends Model<UpmsMenuT> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单表ID
     */
    @TableId(value = "MENU_ID", type = IdType.ID_WORKER)
    private Double menuId;
    /**
     * 菜单名
     */
    @TableField("MENU_NAME")
    private String menuName;
    /**
     * 菜单类型|目录 菜单 功能
     */
    @TableField("MENU_TYPE")
    private String menuType;
    /**
     * 上级目录
     */
    @TableField("MENU_UPPER")
    private Double menuUpper;
    /**
     * 菜单标识
     */
    @TableField("MENU_FLAGE")
    private String menuFlage;
    /**
     * 菜单地址
     */
    @TableField("MENU_URL")
    private String menuUrl;
    /**
     * 菜单图标
     */
    @TableField("MENU_ICO")
    private String menuIco;
    /**
     * 排序号
     */
    @TableField("MENU_SERIAL")
    private Double menuSerial;
    /**
     * 状态|有效 无效
     */
    @TableField("MENU_STATUS")
    private Long menuStatus;
    /**
     * 备注
     */
    @TableField("MENU_NOTE")
    private String menuNote;
    @TableField("MENU_BACK1")
    private String menuBack1;
    @TableField("MENU_BACK2")
    private String menuBack2;
    @TableField("MENU_BACK3")
    private String menuBack3;
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
        return this.menuId;
    }

}
