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
            throw new IllegalStateException("Fordonets flak är tippat! Flaket måste sänkas innan färd.");
        }
    }

    @Override
    public boolean getRampUp() {
        return rampUp;
    }

    @Override
    public void adjustFlatbed() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalStateException("Fordonet är i rörelse, kan inte justera flaket!");
        }
        rampUp = !rampUp;
    }

    public ArrayList<Car> getStorage() {
        return storage;
    }

    public void loadCar(Car c) {
        double[] transportPos = getPos();
        double[] cPos = c.getPos();

        if (storage.size() == capacity) {
            throw new ArrayIndexOutOfBoundsException("Car Transport is already at maximum capacity");
        }
        else if (!getRampUp()) {
            throw new IllegalStateException("Ramp is not deployed");
        }
        else if(Math.abs(transportPos[0] - cPos[0]) > 0.5 || Math.abs(transportPos[1] - cPos[1]) > 0.5) {
            throw new IllegalStateException("Car is too far away");
        }
        else if (c instanceof HasFlatBed) {
            throw new IllegalArgumentException("Vehicle is too large");
        }
        c.setStored();
        storage.add(c);
        c.setPos(getPos());

    }

    public void unloadLastCar() {
        if (!getRampUp()) {
            throw new IllegalStateException("Ramp needs to be extended to perform this action");
        }
        Car lastCar = storage.getLast();
        lastCar.setNotStored();
        double[] newPos = new double[] {getPos()[0] - 0.25, getPos()[1] - 0.25};
        lastCar.setPos(newPos);
        storage.remove(lastCar);
    }


    @Override
    public void startEngine() {
        checkRampUp();
        super.startEngine();
    }

    @Override
    public void move() {
        checkRampUp();
        super.move();
        for (Car car : storage) {
            car.setPos(getPos());
        }
    }


    @Override
    public void gas(double amount) {
        checkRampUp();
        super.gas(amount);
    }




}
