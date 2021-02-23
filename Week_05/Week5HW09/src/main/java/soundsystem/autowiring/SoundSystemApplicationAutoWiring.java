package soundsystem.autowiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import soundsystem.autowiring.entity.CDPlayer;

public class SoundSystemApplicationAutoWiring {
    public static void main(String[] args) {
        System.out.println("--->1. config by implicit bean discovery and automatic wiring...");
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext("soundsystem.autowiring.config", "soundsystem.autowiring.entity");
        CDPlayer cdPlayer = (CDPlayer) annotationContext.getBean("cdplayer");
        cdPlayer.play();
    }
}
