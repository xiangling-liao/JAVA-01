package soundsystem4.mixandmatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soundsystem4.mixandmatch.entity.CompactDisc;
import soundsystem4.mixandmatch.entity.SgtPeppers;

@Configuration
public class CDConfig {
    @Bean
    public CompactDisc compactDisc() {
        return new SgtPeppers();
    }
}
