package cn.limexc.sie.entity.vo;

import lombok.Data;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/28 14:02
 */
@Data
public class UpmsMenuTQuery {

    private String menuName;
    private String menuType;
    private String menuStatus;
    private int menuId;

    @Override
    public String toString() {
        return "UpmsMenuTQuery{" +
                "menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuStatus='" + menuStatus + '\'' +
                ", menuId=" + menuId +
                '}';
    }
}
