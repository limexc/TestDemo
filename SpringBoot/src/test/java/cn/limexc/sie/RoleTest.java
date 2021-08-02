package cn.limexc.sie;

import cn.limexc.sie.mapper.UpmsRofuTMapper;
import cn.limexc.sie.service.UpmsRofuTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/30 13:47
 */
@SpringBootTest
public class RoleTest {
    @Autowired
    private UpmsRofuTMapper rofuTMapper;
    @Autowired
    private UpmsRofuTService rofuTService;

    @Test
    public void listRoleTest(){
        rofuTService.listRofu("2");
    }

}
