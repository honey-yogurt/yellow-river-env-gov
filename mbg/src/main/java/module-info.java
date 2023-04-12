module cn.gov.yrcc.mbg {
	exports cn.gov.yrcc;
	exports cn.gov.yrcc.model;
	exports cn.gov.yrcc.mapper;

	requires org.mybatis.generator;
	requires org.mybatis;

	requires java.sql;
	requires mysql.connector.java;
	requires swagger.annotations;
}
