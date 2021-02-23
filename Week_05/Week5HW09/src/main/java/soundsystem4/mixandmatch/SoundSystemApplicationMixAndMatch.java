package soundsystem4.mixandmatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import soundsystem4.mixandmatch.entity.CDPlayer;

public class SoundSystemApplicationMixAndMatch {
    public static void main(String[] args) {

        System.out.println("--->4. mix and match configuration--import xml configuration in java config...");
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext("soundsystem4.mixandmatch.config", "soundsystem4.mixandmatch.entity");
        CDPlayer cdPlayer = (CDPlayer) annotationContext.getBean("cdplayer4");
        cdPlayer.play();
    }
}
