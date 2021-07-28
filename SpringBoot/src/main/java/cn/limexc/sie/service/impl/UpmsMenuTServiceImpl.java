package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.mapper.UpmsMenuTMapper;


import cn.limexc.sie.service.UpmsMenuTService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * UPMS权限管理 菜单表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Service
public class UpmsMenuTServiceImpl extends ServiceImpl<UpmsMenuTMapper, UpmsMenuT> implements UpmsMenuTService {
    /**
     *
     */
     @Autowired
     private UpmsMenuTMapper menuTMapper;

     @Override
     public boolean addMenu(UpmsMenuT upmsMenuT) {

     menuTMapper.insert(upmsMenuT);

     return false;
     }



}
