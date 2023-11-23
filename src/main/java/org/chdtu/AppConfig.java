package org.chdtu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.chdtu")
@PropertySource("classpath:myApp.properties")
public class AppConfig {

}
