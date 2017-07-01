package com.techmahindra.smartparking.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages="com.techmahindra.smartparking.dao.jpa.sensor")
public class CassandraConfig extends AbstractCassandraConfiguration {
	@Autowired
	private Environment environment;
	
	@Override
	protected List<String> getStartupScripts() {
	List<String> startupScripts = new ArrayList<>();
	startupScripts.add(getSensorDataCreateTableQuery());
	startupScripts.add(getSensorTransactionErrorCreateTableQuery()); 
	return startupScripts;
	}
	
	private String getSensorDataCreateTableQuery() {
		String query ="CREATE TABLE IF NOT EXISTS smartparking.sensor_data ("
				+"device_id text,"
				+"timestamp timestamp,"
				+"resource_path text,"
				+"subscription_id text,"
				+"value text,"
				+"PRIMARY KEY (device_id, timestamp)"
				+") WITH CLUSTERING ORDER BY ( timestamp DESC )";
		 
		return query;
	}
	
	private String getSensorTransactionErrorCreateTableQuery() {
		String query ="CREATE TABLE IF NOT EXISTS smartparking.sensor_transaction_error ("
				+"device_id text,"
				+"timestamp timestamp,"
				+"code int,"
				+"reason text,"
				+"request_id text,"
				+"resource_path text,"
				+"sub_code text,"
				+"value text,"
				+"PRIMARY KEY (device_id, timestamp)"
				+") WITH CLUSTERING ORDER BY ( timestamp DESC )";
				
		return query;
	}


	@Bean
	@Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(environment.getProperty("cassandra.contactpoints"));
        cluster.setPort(Integer.parseInt(environment.getProperty("cassandra.port")));
        return cluster;
    }
 
    @Bean
    @Override
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
    
	@Override
	protected String getKeyspaceName() {
		return environment.getProperty("cassandra.keyspace");
	}
	
	/**
	 * only required if you want to use CassandraTemplate
	 * @return
	 * @throws Exception
	 */
	@Bean
	public CassandraOperations cassandraOperationsTemplate() throws Exception {
		return new CassandraTemplate(session().getObject());
	}

}