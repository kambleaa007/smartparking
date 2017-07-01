package com.techmahindra.smartparking.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.techmahindra.smartparking.job.CancelBlockedParkingJob;
import com.techmahindra.smartparking.job.CancelBookedParkingJob;
import com.techmahindra.smartparking.job.ExpiredBookedParkingJob;
import com.techmahindra.smartparking.job.ExpiredOccupiedParkingJob;
import com.techmahindra.smartparking.job.ExtendedAgainExpiredOccupiedParkingJob;
import com.techmahindra.smartparking.job.ExtendedExpiredOccupiedParkingJob;
import com.techmahindra.smartparking.job.ToBeExpiredOccupiedParkingJob;

@Configuration
@PropertySource({ "classpath:quartz.properties" })
@ComponentScan(basePackages = { "com.techmahindra.smartparking.*" })
public class QuartzConfig {
	
	private static final String EVERY_5_MINUTE = "0 0/5 * 1/1 * ? *";
	private static final String EVERY_15_MINUTE = "0 0/15 * 1/1 * ? *";

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public JobDetailFactoryBean jobDetailCancelBlockedParkingBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(CancelBlockedParkingJob.class);
		factory.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "cancelBlockedParking");
		map.put(CancelBlockedParkingJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("cancelBlockedParkingJobGroup");
		factory.setName("cancelBlockedParkingJob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean cancelBlockedParking() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailCancelBlockedParkingBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("cancelBlockedParkingTrigger");
		stFactory.setGroup("cancelBlockedParkingTriggerGroup");
		stFactory.setCronExpression(EVERY_5_MINUTE);
		return stFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetailCancelBookedParkingBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(CancelBookedParkingJob.class);
		factory.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "cancelBookedParking");
		map.put(CancelBookedParkingJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("cancelBookedParkingJobGroup");
		factory.setName("cancelBookedParkingJob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean cancelBookedParking() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailCancelBookedParkingBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("cancelBookedParkingTrigger");
		stFactory.setGroup("cancelBookedParkingTriggerGroup");
		stFactory.setCronExpression(EVERY_15_MINUTE);
		return stFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetailExpiredBookedParkingBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(ExpiredBookedParkingJob.class);
		factory.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "expiredBookedParking");
		map.put(ExpiredBookedParkingJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("expiredBookedParkingJobGroup");
		factory.setName("expiredBookedParkingJob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean expiredBookedParking() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailExpiredBookedParkingBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("expiredBookedParkingTrigger");
		stFactory.setGroup("expiredBookedParkingTriggerGroup");
		stFactory.setCronExpression(EVERY_15_MINUTE);
		return stFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetailToBeExpiredOccupiedParkingBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(ToBeExpiredOccupiedParkingJob.class);
		factory.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "toBeExpiredOccupiedParking");
		map.put(ToBeExpiredOccupiedParkingJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("toBeExpiredOccupiedParkingJobGroup");
		factory.setName("toBeExpiredOccupiedParkingJob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean toBeExpiredOccupiedParking() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailToBeExpiredOccupiedParkingBean()
				.getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("toBeExpiredOccupiedParkingTrigger");
		stFactory.setGroup("toBeExpiredOccupiedParkingTriggerGroup");
		stFactory.setCronExpression(EVERY_15_MINUTE);
		return stFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetailExpiredOccupiedParkingBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(ExpiredOccupiedParkingJob.class);
		factory.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "expiredOccupiedParking");
		map.put(ExpiredOccupiedParkingJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("expiredOccupiedParkingJobGroup");
		factory.setName("expiredOccupiedParkingJob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean expiredOccupiedParking() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailExpiredOccupiedParkingBean()
				.getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("expiredOccupiedParkingTrigger");
		stFactory.setGroup("expiredOccupiedParkingTriggerGroup");
		stFactory.setCronExpression(EVERY_15_MINUTE);
		return stFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetailExtendedExpiredOccupiedParkingBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(ExtendedExpiredOccupiedParkingJob.class);
		factory.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "extendedExpiredOccupiedParking");
		map.put(ExtendedExpiredOccupiedParkingJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("extendedExpiredOccupiedParkingJobGroup");
		factory.setName("extendedExpiredOccupiedParkingJob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean extendedExpiredOccupiedParking() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailExtendedExpiredOccupiedParkingBean()
				.getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("extendedExpiredOccupiedParkingBeanTrigger");
		stFactory.setGroup("extendedExpiredOccupiedParkingBeanTriggerGroup");
		stFactory.setCronExpression(EVERY_15_MINUTE);
		return stFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetailExtendedAgainExpiredOccupiedParkingBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(ExtendedAgainExpiredOccupiedParkingJob.class);
		factory.setDurability(true);
		Map<String, Object> map = new HashMap<>();
		map.put("name", "extendedAgainExpiredOccupiedParking");
		map.put(ExtendedAgainExpiredOccupiedParkingJob.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("extendedAgainExpiredOccupiedParkingJobGroup");
		factory.setName("extendedAgainExpiredOccupiedParkingJob");
		return factory;
	}

	// Job is scheduled after every 1 minute
	@Bean
	public CronTriggerFactoryBean extendedAgainExpiredOccupiedParking() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory
				.setJobDetail(jobDetailExtendedAgainExpiredOccupiedParkingBean()
						.getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("extendedAgainExpiredOccupiedParkingBeanTrigger");
		stFactory
				.setGroup("extendedAgainExpiredOccupiedParkingBeanTriggerGroup");
		stFactory.setCronExpression(EVERY_15_MINUTE);
		return stFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext context) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

		schedulerFactory.setDataSource(dataSource);
		schedulerFactory.setTransactionManager(transactionManager);
		schedulerFactory.setOverwriteExistingJobs(true);
		schedulerFactory.setSchedulerName("smartparking-quartz-scheduler");

		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		schedulerFactory.setJobFactory(jobFactory);

		schedulerFactory.setConfigLocation(new ClassPathResource(
				"quartz.properties"));

		schedulerFactory.setTriggers(cancelBlockedParking().getObject(),
				cancelBookedParking().getObject(), expiredBookedParking()
						.getObject(), toBeExpiredOccupiedParking().getObject(),
				expiredOccupiedParking().getObject(),
				extendedExpiredOccupiedParking().getObject(),
				extendedAgainExpiredOccupiedParking().getObject());

		return schedulerFactory;
	}

}
