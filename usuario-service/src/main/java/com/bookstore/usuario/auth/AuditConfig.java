package com.bookstore.usuario.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.bookstore.usuario.model.EntityAuditorAware;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
	@Bean
	public AuditorAware<String> auditorAware() {
		return new EntityAuditorAware();
	}
}
