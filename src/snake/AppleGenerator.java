package snake;

public class AppleGenerator {
    private Integer applesCount;

    public AppleGenerator(Integer applesCount) {
        this.applesCount = applesCount;
    }

    public boolean isHaveApple() {
        return applesCount > 0;
    }

    public void generate(){

    }
}
