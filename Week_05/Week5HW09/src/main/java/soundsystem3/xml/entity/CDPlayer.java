package soundsystem3.xml.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class CDPlayer {
    private CompactDisc cd;

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    public void setCompactDisc(CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
