package org.juqkai.jkjson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定方法来手动将对象转换成JSON
 * @author juqkai(juqkai@gmail.com) 2010-10-26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToJson {

}
