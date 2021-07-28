package cn.limexc.sie.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @Description 自动更新字段  填充器
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/27 21:43
 */
@Slf4j
@Component
public class AutoUpdateHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 起始版本 3.3.0(推荐使用)  有问题，还未改  属性名非字段名
        this.strictInsertFill(metaObject, "creationDate", Date.class,new Date());
        //插入时填充最后更新时间
        this.strictUpdateFill(metaObject, "lastUpdatedDate", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 起始版本 3.3.0(推荐)LocalDateTime.class, LocalDateTime.now()
        this.strictUpdateFill(metaObject, "lastUpdatedDate", Date.class, new Date());
    }

    /**
    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<Object> fieldVal) {
        // 每次填充，直接使用填充值
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }
    */

}