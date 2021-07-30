package cn.limexc.sie.entity.vo;

import cn.limexc.sie.entity.UpmsRofuT;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/29 1:28
 */
@Data
public class UpmsRofuTQuery extends UpmsRofuT {

    private boolean isSelect;
    private Integer roleId;
    private Integer roleNo;
    private String roleName;
    private Date roleStartTime;
    private Date roleStopTime;
    private String roleStatus;

}
