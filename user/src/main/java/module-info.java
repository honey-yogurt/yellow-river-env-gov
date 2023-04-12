module cn.gov.yrcc.user {
	exports cn.gov.yrcc.user.dto;
	exports cn.gov.yrcc.user.service;

	requires spring.context;
	requires spring.beans;
	requires spring.tx;
	requires spring.core;
	requires spring.security.core;
	requires spring.security.crypto;

	requires org.mybatis;
	requires org.mybatis.spring;
	requires org.slf4j;

	requires static lombok;
	requires java.validation;
	requires java.annotation;
	requires swagger.annotations;
	requires pagehelper;
	requires hutool.all;

	requires cn.gov.yrcc.infrastructure;
	requires cn.gov.yrcc.mbg;
}
