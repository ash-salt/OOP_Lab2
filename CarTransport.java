import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Car implements HasFlatBed{

    private final int capacity;
    private ArrayList<Car> storage;
    private boolean rampUp;

    public CarTransport() {
        super(2, 80, Color.gray, "Car Transport");
        this.capacity = 10;
        this.storage = new ArrayList<>();
        this.rampUp = false;
    }

    public void checkRampUp() {
        if (getRampUp()) {
            throw new IllegalStateException("Fordonets flak är tippat! Flaket måste sänkas till 0° innan färd.");
        }
    }

    public boolean getRampUp() {
        return rampUp;
    }

    public void adjustFlatbed() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalStateException("Fordonet är i rörelse, kan inte justera flaket!");
        }
        rampUp = !rampUp;
    }

    public void loadCar(Car c) {
        double[] transportPos = getPos();
        double[] cPos = c.getPos();

        if (storage.size() == capacity) {
            throw new ArrayIndexOutOfBoundsException("Car Transport is already at maximum capacity");
        }
        if (!getRampUp()) {
            throw new IllegalStateException("Ramp is not deployed");
        }
        if (Math.abs(transportPos[0] - cPos[0]) > 0.5 || Math.abs(transportPos[1] - cPos[1]) > 0.5) {
            throw new IllegalStateException("Car is too far away");
        }
        if (c instanceof HasFlatBed) {
            throw new IllegalArgumentException("Vehicle is too large");
        }

        storage.add(c);
        c.setPos(getPos());
        c.setStored();

    }

    public void unloadLastCar() {
        if (!getRampUp()) {
            throw new IllegalStateException("Ramp needs to be extended to perform this action");
        }
        Car lastCar = storage.getLast();
        double[] newPos = new double[] {getPos()[0] - 0.25, getPos()[1] - 0.25};
        lastCar.setPos(newPos);
        lastCar.setNotStored();
        storage.remove(lastCar);
    }

    public void updatePos() {
        for (Car car : storage) {
            car.setPos(getPos());
        }
    }

    @Override
    public void move() {
        checkRampUp();
        super.move();
        updatePos();
    }


    @Override
    public void gas(double amount) {
        checkRampUp();
        super.gas(amount);
        updatePos();
    }

    @Override
    public void brake(double amount) {
        super.brake(amount);
        updatePos();
    }




}
