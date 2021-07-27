package cn.limexc.sie;

import cn.limexc.sie.entity.UpmsUserinfoT;
import cn.limexc.sie.mapper.UpmsUserinfoTMapper;
import cn.limexc.sie.service.UpmsUserinfoTService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SieApplicationTests {
	@Autowired
	UpmsUserinfoTMapper upmsUserinfoTMapper;
	@Autowired
	UpmsUserinfoTService upmsUserinfoTService;

	@Test
	void contextLoads() {
	}

	@Test
	public void delTest(){
		int affectRows = upmsUserinfoTMapper.deleteById(2);
		if(affectRows>0){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		System.out.println(affectRows);
	}

	@Test
	public void userlisttest(){
		List<UpmsUserinfoT> userinfoTS= upmsUserinfoTMapper.selectList(null);
		for (UpmsUserinfoT user: userinfoTS) {
			System.out.println(user.toString());
		}
		System.out.println("-----------------");
		List<UpmsUserinfoT> users=upmsUserinfoTService.list();
		for (UpmsUserinfoT us: users){
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
		List<UpmsUserinfoT> users = upmsUserinfoTMapper.selectList(null); //mybatisplus的查询方法
		PageInfo<UpmsUserinfoT> list = new PageInfo<>(users); //转换为PageInfo的对象
		System.out.println(list);
	}

}
