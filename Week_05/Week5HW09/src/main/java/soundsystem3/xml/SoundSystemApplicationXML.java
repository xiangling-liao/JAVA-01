package soundsystem3.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import soundsystem3.xml.entity.CDPlayer;

public class SoundSystemApplicationXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("soundsystem3.xml");
        CDPlayer cdPlayer = (CDPlayer) context.getBean("cdPlayer-xml");
        cdPlayer.play();
    }
}
