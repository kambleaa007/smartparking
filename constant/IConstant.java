package com.techmahindra.smartparking.constant;

/**
 * IConstant.java
 *
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public interface IConstant {
    // BrainTree configuration properties
   

	static String DATASOURCE_JNDI = "database.datasource";

    // Database configuration properties
	static String HIBERNATE_GENERATE_DDL = "hibernate.generate_ddl";
	static String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
	static String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	static String HIBERNATE_DATABASE = "hibernate.database";
	
	
   //
	static String VERIFICATION_NONCE="R";
	static String APPLICATION_NONCE="A";
	static String RESET_PASSWORD_NONCE="F";

	String DEVICE_LOGGER="device.";
}
