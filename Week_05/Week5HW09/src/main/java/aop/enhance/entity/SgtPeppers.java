package aop.enhance.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// spring-context[@Component]
@Slf4j
@Component("lonelyHeartsClub")
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        log.info("Playing " + title + " by " + artist);
    }
}
