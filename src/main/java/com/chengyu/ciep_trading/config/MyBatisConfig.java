package com.chengyu.ciep_trading.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author CL
 */
@Configuration
@MapperScan({"com.chengyu.ciep_trading.mapper"})
public class MyBatisConfig {

}
