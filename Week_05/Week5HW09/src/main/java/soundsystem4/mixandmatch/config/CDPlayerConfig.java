package soundsystem4.mixandmatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import soundsystem4.mixandmatch.entity.CDPlayer;
import soundsystem4.mixandmatch.entity.CompactDisc;


//ComponentScan can be also enabled in XML
@Configuration
@Import(CDConfig.class)
public class CDPlayerConfig {
    @Bean(name="cdplayer4")
    public CDPlayer cdPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }
}
