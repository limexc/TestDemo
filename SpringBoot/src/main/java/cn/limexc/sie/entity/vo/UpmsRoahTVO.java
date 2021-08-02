package cn.limexc.sie.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/8/2 15:45
 */
@Data
public class UpmsRoahTVO {
    boolean isSelectd=false;
    String roleId;
    List<String> AuthIds;
}
