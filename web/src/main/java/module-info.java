open module cn.gov.yrcc.web {
	// 以下是导出一方包模块

	// 以下是导入三方包模块
	//requires cn.gov.yrcc.infrastructure;

	requires spring.boot;
	requires spring.web;
	requires spring.context;
	requires spring.core;
	requires spring.beans;
	requires spring.boot.autoconfigure;

	requires org.mybatis.spring;

	requires java.annotation;
	requires java.sql; // 解决启动报错的问题
	requires swagger.annotations;
	requires io.swagger.v3.oas.annotations;

	requires cn.gov.yrcc.file;
	requires cn.gov.yrcc.mbg;
	requires cn.gov.yrcc.shared;
	requires cn.gov.yrcc.user;
}
