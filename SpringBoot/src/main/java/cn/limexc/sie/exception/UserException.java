package cn.limexc.sie.exception;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/6 9:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends RuntimeException{

        //@ApiModelProperty(value = "状态码")
        //private Integer code;

        private String msg;


}
