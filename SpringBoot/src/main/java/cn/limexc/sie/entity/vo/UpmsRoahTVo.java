package cn.limexc.sie.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/2 15:45
 */
@Data
public class UpmsRoahTVo {
    boolean isSelectd=false;
    String roleId;
    List<String> authIds;

    @Override
    public String toString() {
        return "UpmsRoahTVo{" +
                "isSelectd=" + isSelectd +
                ", roleId='" + roleId + '\'' +
                ", AuthIds=" + authIds +
                '}';
    }
}
