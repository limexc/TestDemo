package cn.limexc.sie.mapper;

import cn.limexc.sie.entity.UpmsMenuT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * UPMS权限管理 菜单表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Mapper
public interface UpmsMenuTMapper extends BaseMapper<UpmsMenuT> {

    //菜单新增 返回影响行数
    //int insertMenu(@Param("upmsMenuT") UpmsMenuT upmsMenuT);

}
