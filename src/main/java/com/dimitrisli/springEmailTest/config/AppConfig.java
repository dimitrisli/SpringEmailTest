package com.dimitrisli.springEmailTest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ImportResource(value = "classpath:/spring/appXMLContext.xml")
@PropertySource(value = "classpath:/properties/application.properties")
public class AppConfig {

    @Bean
    public JavaMailSenderImpl emailSender(@Value("${email.host}") String emailHost,
                                          @Value("${email.port}") Integer emailPort,
                                          @Value("${email.username}") String username,
                                          @Value("${email.pass}") String password){
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
            emailSender.setHost(emailHost);
            emailSender.setPort(emailPort);
            emailSender.setUsername(username);
            emailSender.setPassword(password);
            //emailSender.setDefaultEncoding("UTF_8");
            Properties mailProps = new Properties();
                mailProps.setProperty("mail.transport.protocol","smtp");
                mailProps.setProperty("mail.smtp.auth","true");
                mailProps.setProperty("mail.smtp.starttls.enable","true");
                mailProps.setProperty("mail.debug","false");
                emailSender.setJavaMailProperties(mailProps);
        return emailSender;
    }
}
