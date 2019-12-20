package com.lovelive.common.base;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDHexGenerator;

import java.io.Serializable;

/**
 * 自定义主键策略
 *
 * @author dHe
 * @date 2019-4-26
 */
public class CustomUUIDGenerator extends UUIDHexGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try {
            Object id = FieldUtils.readField(object, "id", true);
            if (id != null) {
                return (Serializable) id;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return super.generate(session, object);
    }

}
