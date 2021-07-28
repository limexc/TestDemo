package cn.limexc.sie;

import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.mapper.UpmsUserTMapper;
import cn.limexc.sie.service.UpmsUserTService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SieApplicationTests {
	@Autowired
	UpmsUserTMapper UpmsUserTMapper;
	@Autowired
	UpmsUserTService UpmsUserTService;

	@Test
	void contextLoads() {
	}

	@Test
	public void delTest(){
		int affectRows = UpmsUserTMapper.deleteById(2);
		if(affectRows>0){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		System.out.println(affectRows);
	}

	@Test
	public void userlisttest(){
		List<UpmsUserT> userTS= UpmsUserTMapper.selectList(null);
		for (UpmsUserT user: userTS) {
			System.out.println(user.toString());
		}
		System.out.println("-----------------");
		List<UpmsUserT> users=UpmsUserTService.list();
		for (UpmsUserT us: users){
			System.out.println(us.toString());
		}

	}

	@Test
	public void pagehelpTest(){
		int page = 1;
		int size = 3;
		if (page > 0 && size > 0) {
			PageHelper.startPage(page, size);  //在查询之前添加这块代码
		}
		List<UpmsUserT> users = UpmsUserTMapper.selectList(null); //mybatisplus的查询方法
		PageInfo<UpmsUserT> list = new PageInfo<>(users); //转换为PageInfo的对象
		System.out.println(list);
	}

	@Test
	public void insertUserTest(){
		UpmsUserT user = new UpmsUserT();
		user.setUserAlias("uuxxz");
		user.setUserName("xiaoxianzi");
		user.setUserPasswd("123123");
		user.setUserStatus("Act");
		int a= UpmsUserTMapper.insert(user);
		System.out.println(a);

	}

}
