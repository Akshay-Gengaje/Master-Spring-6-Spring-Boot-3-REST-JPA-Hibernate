package com.darklord.fieldInjection.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.darklord.fieldInjection.beans")
public class ProjectConfiguration {

}
