package cn.limexc.sie.mapper;

import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.vo.UpmsRofuTQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * UPMS权限管理 用户角色表 Mapper 接口
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Mapper
public interface UpmsRofuTMapper extends BaseMapper<UpmsRofuT> {

    /**
     * 使用用户id查询全部角色，包括未与此用户进行绑定的
     * @param uid  用户id
     * @return     用户角色列表
     */
    List<UpmsRofuTQuery> selectAllRlById(@Param("uid")String uid);

}
