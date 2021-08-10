package cn.limexc.sie.entity.vo;

import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.UpmsRoleT;
import lombok.Data;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/29 1:28
 */
@Data
public class UpmsRofuTQuery extends UpmsRofuT {

    boolean isSelect = false;
    UpmsRoleT upmsRoleT;


    @Override
    public String toString() {
        return "UpmsRofuTQuery{" +
                "isSelect=" + isSelect +
                ", upmsRoleT=" + upmsRoleT +
                '}';
    }
}
