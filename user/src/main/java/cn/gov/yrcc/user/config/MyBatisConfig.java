package cn.gov.yrcc.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * Created by yogurt on 2023/3/27.
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"cn.gov.yrcc.mapper","cn.gov.yrcc.dao"})
public class MyBatisConfig {
}
