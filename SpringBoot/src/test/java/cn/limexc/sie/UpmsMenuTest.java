package cn.limexc.sie;

import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.service.UpmsMenuTService;
import cn.limexc.sie.util.RemoveTreeNodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/28 17:37
 */
@SpringBootTest
public class UpmsMenuTest {

    @Autowired
    UpmsMenuTService menuTService;

    @Test
    public void renodeTest(){
        //获取表全部数据
        List<UpmsMenuT> menuTList = menuTService.list();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (UpmsMenuT menuT: menuTList) {
            if (menuT.getMenuUpper()==null){
                menuT.setMenuUpper(0);
            }
            map.put(menuT.getMenuId(),menuT.getMenuUpper());
        }
        List<String> renode = RemoveTreeNodeUtil.RemoveList(map,"14");
        for (String str:renode) {
            System.out.println(str);
        }

    }

}
