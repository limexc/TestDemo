package cn.limexc.sie.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@KeySequence(value = "UPMS_ROAH_S")
@TableName("UPMS_ROAH_T")
public class UpmsRoahT extends BaseEntity{//Model<UpmsRofuT>

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限ID
     */
    @TableId(value = "ROAH_ID", type = IdType.INPUT)
    private Integer roahId;
    /**
     * 权限ID
     */
    @TableField("ROAH_MENUID")
    private Integer roahMenuid;
    /**
     * 角色表ID
     */
    @TableField("ROAH_ROLEID")
    private Integer roahRoleid;

    @Override
    protected Serializable pkVal() {
        return this.roahId;
    }

}
