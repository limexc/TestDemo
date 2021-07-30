package cn.limexc.sie.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/28 20:45
 */
@Data
public class MenuSub {
    private int menuId;
    private String menuName;
    private String menuUrl;
    private List<FunSub> children2 = new ArrayList<>();
}
