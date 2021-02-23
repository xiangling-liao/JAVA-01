package soundsystem2.javabased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import soundsystem2.javabased.entity.CDPlayer;


public class SoundSystemApplicationJavaBased {
    public static void main(String[] args) {
        System.out.println("--->2. java-based configuration...");
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext("soundsystem2.javabased.config", "soundsystem2.javabased.entity");
        CDPlayer cdPlayer = (CDPlayer) annotationContext.getBean("cdplayer-java-based");
        cdPlayer.play();
    }
}
