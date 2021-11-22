package com.um.grupo2.instalador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
public class InstaladorApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		InstaladorApplication.context = SpringApplication.run(InstaladorApplication.class);
		Application.launch(JavaFXApplication.class, args);
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

}
