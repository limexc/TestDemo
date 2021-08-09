package cn.limexc.sie.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description 用于更新用户对象绑定 实体类
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/8 15:44
 */
@Data
public class UpmsRofuVo {
    String userId;
    List<String> roleIds;
}
