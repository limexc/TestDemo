package cn.limexc.sie.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/28 20:44
 */
@Data
public class IndexSub {
    private int menuId;
    private String menuName;
    private List<MenuSub> children = new ArrayList<>();
}
