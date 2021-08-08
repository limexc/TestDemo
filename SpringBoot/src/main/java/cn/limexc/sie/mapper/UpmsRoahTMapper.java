package cn.limexc.sie.mapper;

import cn.limexc.sie.entity.UpmsRoahT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用于角色权限表。
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/2 14:21
 */
@Mapper
public interface UpmsRoahTMapper extends BaseMapper<UpmsRoahT> {

    /**
     * 通过传入rid与权限列表进行插入操作
     * @param rid   角色id
     * @param aIds  权限id列表
     * @return      影响的行数，大于0即为插入成功
     */
    int insertRoahList(@Param("rid")String rid,@Param("aIds") List<String> aIds);

    /**
     * 逻辑删除list中的数据
     * @param rid   角色id
     * @param aIds  权限id列表  即菜单id列表
     * @return      影响的行数
     */
    int updateRoahList(@Param("rid")String rid,@Param("aIds") List<String> aIds);

}
