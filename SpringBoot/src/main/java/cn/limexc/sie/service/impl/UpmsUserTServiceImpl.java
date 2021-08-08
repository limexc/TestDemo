package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsUserTQuery;
import cn.limexc.sie.mapper.UpmsUserTMapper;
import cn.limexc.sie.service.UpmsUserTService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * UPMS权限管理系统用户信息表 服务实现类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Service
@Slf4j
public class UpmsUserTServiceImpl extends ServiceImpl<UpmsUserTMapper, UpmsUserT> implements UpmsUserTService {
    /**
     * 通过username查询用户信息
     * @param userAlias
     * @return
     */
    @Override
    public UpmsUserT selectByUserAlias(String userAlias) {
        QueryWrapper<UpmsUserT> wrapper = new QueryWrapper<UpmsUserT>();
        wrapper.eq("USER_ALIAS",userAlias);
        log.info("使用用户名从数据库获得用户信息,名称:"+userAlias);
        UpmsUserT userT = baseMapper.selectOne(wrapper);
        return userT;
    }
}
