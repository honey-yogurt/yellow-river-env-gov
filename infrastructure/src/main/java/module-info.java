module cn.gov.yrcc.infrastructure {
	// 以下是导出一方包模块
	exports cn.gov.yrcc.infrastructure.util;
	exports cn.gov.yrcc.infrastructure.config;
	exports cn.gov.yrcc.infrastructure.exception;
	exports cn.gov.yrcc.infrastructure.security.util;
	exports cn.gov.yrcc.infrastructure.security.config;

	// allow reflection
	opens cn.gov.yrcc.infrastructure.component to spring.core;
	opens cn.gov.yrcc.infrastructure.config to spring.core;
	opens cn.gov.yrcc.infrastructure.security.config to spring.core;
	opens cn.gov.yrcc.infrastructure.security.component to spring.core;

    // 以下是导入三方包模块
	requires spring.boot;
	requires spring.web;
	requires spring.context;
	requires spring.beans;
	requires spring.jdbc;
	requires spring.core;
	requires spring.security.config;
	requires spring.security.web;
	requires spring.security.core;
	requires spring.boot.autoconfigure;
	requires spring.security.crypto;

	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires org.mybatis;

	requires static lombok;
	requires commons.lang3;
	requires org.slf4j;
	requires java.sql;
	requires druid.spring.boot.starter;
	requires mybatis.plus.extension;
	requires mybatis.plus.core;
	requires javax.servlet.api;
	requires java.annotation;
	requires hutool.all;
	requires jjwt.api;

	requires cn.gov.yrcc.shared;
}
