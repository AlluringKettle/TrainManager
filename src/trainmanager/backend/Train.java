package trainmanager.backend;

import java.util.ArrayList;

public class Train extends ArrayList<Carriage> {

    public Train(Locomotive loco) {
        this.add(loco);
    }

    public Locomotive getLoco() {
        return (Locomotive) this.get(0);
    }

    public int getCarsAmount() {
        return this.size();
    }

    public void addCar(Carriage car) {
        if (this.getCarsAmount() > this.getLoco().getMaxLoad()) {
            throw new LocomotiveLoadExceeded();
        }
        this.add(car);
    }

    public static class LocomotiveLoadExceeded extends RuntimeException {}
}

