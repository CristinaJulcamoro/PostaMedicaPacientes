/**
 * @file: AplicationConfig.java
 * @author: (c)2024 jvale_uznxw
 * @created: 1 mar. 2024 09:47:38
 */
package edu.unc.PostaMedicaPacientes.config;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 */
@Configuration
public class AplicationConfig {
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
