package cn.limexc.sie;

import cn.limexc.sie.entity.vo.UpmsRoahTVo;
import cn.limexc.sie.mapper.UpmsRofuTMapper;
import cn.limexc.sie.service.UpmsRoahTService;
import cn.limexc.sie.service.UpmsRofuTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/30 13:47
 */
@SpringBootTest
public class RoahTest {
    @Autowired
    private UpmsRofuTMapper rofuTMapper;
    @Autowired
    private UpmsRofuTService rofuTService;
    @Autowired
    private UpmsRoahTService roahTService;

    @Test
    public void listRoahTest(){
        UpmsRoahTVo roahTVO = new UpmsRoahTVo();
        List<String> as = new ArrayList<>();
        as.add("2");
        as.add("3");
        roahTVO.setRoleId("2");
        roahTVO.setAuthIds(as);
        roahTService.editRoleAuth(roahTVO);
    }

}
