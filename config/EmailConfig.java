package com.techmahindra.smartparking.config;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import com.techmahindra.smartparking.sms.SmsConfigBean;

@Configuration 
@PropertySource({ "classpath:mailconfig.properties","classpath:smsconfig.properties" })
public class EmailConfig {

    @Autowired
    Environment env;
    
    private static final Logger LOGGER = Logger.getLogger(EmailConfig.class);
    
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(env.getProperty("smtp.host"));
        javaMailSender.setPort(Integer.parseInt(env.getProperty("smtp.port")));
        javaMailSender.setUsername(env.getProperty("smtp.username"));
        javaMailSender.setPassword(env.getProperty("smtp.password"));
        javaMailSender.setJavaMailProperties(getMailProperties());

        LOGGER.info("user name ..."+javaMailSender.getUsername());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        return properties;
    }
    
    @Bean
    public SmsConfigBean smsConfigBean() {
        SmsConfigBean smsConfigBean = new SmsConfigBean();

       smsConfigBean.setUrl(env.getProperty("sms.url"));
       smsConfigBean.setNetworkId(env.getProperty("sms.network_id"));
       smsConfigBean.setMode(env.getProperty("sms.mode"));

       LOGGER.info("sms config........."+smsConfigBean.getUrl()+" "+smsConfigBean.getNetworkId()+" "+smsConfigBean.getMode());
        return smsConfigBean;
    }
}
