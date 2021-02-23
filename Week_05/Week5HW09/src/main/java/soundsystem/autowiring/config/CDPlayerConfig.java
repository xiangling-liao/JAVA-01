package soundsystem.autowiring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//ComponentScan can be also enabled in XML
@Configuration
@ComponentScan(basePackages= "soundsystem/autowiring")
public class CDPlayerConfig {
}
