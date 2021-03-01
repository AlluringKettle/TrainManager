package trainmanager.backend;

public class Locomotive extends Carriage {

    private int maxLoad;

    public Locomotive(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getMaxLoad() {
        return this.maxLoad;
    }

    public String getText() {
        return "L (" + String.valueOf(this.maxLoad) + ")";
    }
}
