package soundsystem4.mixandmatch.entity;

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
