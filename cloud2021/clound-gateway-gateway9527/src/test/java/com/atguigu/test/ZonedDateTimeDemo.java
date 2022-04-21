package com.atguigu.test;

import java.time.ZonedDateTime;

/**
 * java8 关于时间的新特性
 */
public class ZonedDateTimeDemo {

    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();// 默认时区
        System.out.println(zonedDateTime);
        System.out.println("学习git时测试分支情况，我是第一版本");
        System.out.println("学习git时测试分支情况，我是第2版本");
        System.out.println("学习git时测试分支情况，我是第three版本");
    }

}
