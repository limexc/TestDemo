package cn.limexc.sie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.limexc.sie.entity.BaseEntity;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.LoginInfoVo;
import cn.limexc.sie.entity.vo.LoginVo;
import cn.limexc.sie.exception.UserException;
import cn.limexc.sie.mapper.UpmsUserTMapper;
import cn.limexc.sie.service.MemberService;
import cn.limexc.sie.util.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/6 8:50
 */
@Service
public class MemberServiceImpl extends ServiceImpl<UpmsUserTMapper, UpmsUserT> implements MemberService {

    @Override
    public String login(LoginVo loginVo){
        String userAlias = loginVo.getUserAlias();
        String userPasswd = loginVo.getUserPasswd();

        //校验参数
        if(StringUtils.isEmpty(userAlias) ||
                StringUtils.isEmpty(userPasswd)) {
           throw new UserException("请输入用户名或密码");
        }

        //获取用户信息
        UpmsUserT userT = baseMapper.selectOne(
                new QueryWrapper<UpmsUserT>().eq("USER_ALIAS", userAlias)
        );
        if(null == userT) {
            throw new UserException("用户名错误!");
        }

        //校验密码
        if(SecureUtil.md5(userT.getUserPasswd())==SecureUtil.md5(userPasswd)) {
            throw new UserException("密码错误!");
        }

        //校验是否被禁用
        if(!"Act".equals(userT.getUserStatus())) {
            throw new UserException("该账户已被禁用,请联系管理员进行处理.");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(
                String.valueOf(userT.getUserId()),
                userT.getUserAlias()
        );
        return token;
    }

    @Override
    public LoginInfoVo getLoginInfo(String userId) {
        UpmsUserT userT = baseMapper.selectById(userId);
        System.out.println(userT.toString());
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtil.copyProperties(userT,loginInfoVo);
        return loginInfoVo;
    }



}
