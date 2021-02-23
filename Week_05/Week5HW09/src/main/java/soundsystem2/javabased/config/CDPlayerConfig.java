package soundsystem2.javabased.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soundsystem2.javabased.entity.CDPlayer;
import soundsystem2.javabased.entity.CompactDisc;
import soundsystem2.javabased.entity.SgtPeppers;

//ComponentScan can be also enabled in XML
@Configuration
public class CDPlayerConfig {
    @Bean(name="cdplayer-java-based")
    public CDPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }

    // share the same sgtPeppers, cuz by default they are singleton instance
    @Bean
    public CDPlayer anotherCDPlayer() {
        return new CDPlayer(sgtPeppers());
    }

    @Bean(name="lonelyHeartsClubBand")
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }
}
