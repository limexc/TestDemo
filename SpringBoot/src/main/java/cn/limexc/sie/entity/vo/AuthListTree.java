package cn.limexc.sie.entity.vo;

import cn.limexc.sie.entity.subject.IndexSub;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/5 15:48
 */
@Data
public class AuthListTree {
    private List<IndexSub> treeDate= new ArrayList<>();
    private List<Integer> selectRid = new ArrayList<>();
}
