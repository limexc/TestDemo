package cn.limexc.sie.entity.vo;

import lombok.Data;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/10 10:55
 */
@Data
public class AddUserVo {

    private String userAlias;
    private String userEmail;
    private String userGender;
    private String userName;
    private String userNote;
    private String userPasswd1;
    private String userPasswd2;
    private String userStatus;
    private String userTel;

    @Override
    public String toString() {
        return "AddUserVo{" +
                "userAlias='" + userAlias + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userName='" + userName + '\'' +
                ", userNote='" + userNote + '\'' +
                ", userPasswd1='" + userPasswd1 + '\'' +
                ", userPasswd2='" + userPasswd2 + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", userTel='" + userTel + '\'' +
                '}';
    }
}
