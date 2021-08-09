package cn.limexc.sie.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 安全认证用户详情信息
 * 相关实体类
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@Slf4j
public class SecurityUser implements UserDetails {

    //当前登录用户
    private transient UpmsUserT currentUserInfo;

    //用户状态
    private String status;

    //当前权限
    private List<String> menuValueList;

    public SecurityUser() {
    }

    public SecurityUser(UpmsUserT user) {
        if (user != null) {
            this.currentUserInfo = user;
            this.status = user.getUserStatus();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String menuValue : menuValueList) {
            if(StringUtils.isEmpty(menuValue)) {
                continue;
            }
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(menuValue);
            authorities.add(authority);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUserInfo.getUserPasswd();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUserAlias();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
