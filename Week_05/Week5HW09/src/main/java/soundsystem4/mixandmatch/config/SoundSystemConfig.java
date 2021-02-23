package soundsystem4.mixandmatch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({CDPlayerConfig.class, CDConfig.class})
@ImportResource("classpath:soundsystem3.xml")
public class SoundSystemConfig {
}
