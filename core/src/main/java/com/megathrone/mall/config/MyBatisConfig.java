package com.megathrone.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.megathrone.mall.mbg.mapper")
public class MyBatisConfig {

}
