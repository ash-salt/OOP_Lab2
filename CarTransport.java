import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Truck{

    private final int capacity;
    private ArrayList<Car> storage;

    public CarTransport() {
        super(2, 80, Color.gray, "Car Transport");
        this.capacity = 10;
        this.storage = new ArrayList<Car>();
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
        if (c instanceof Truck) {
            throw new IllegalArgumentException("Vehicle is too large");
        }

        storage.add(c);
        c.setPos(getPos());

    }

    public void unloadLastCar() {
        if (!getRampUp()) {
            throw new IllegalStateException("Ramp needs to be extended to perform this action");
        }
        Car lastCar = storage.getLast();
        double[] newPos = new double[] {getPos()[0] - 0.25, getPos()[1] - 0.25};
        lastCar.setPos(newPos);
        storage.remove(lastCar);
    }

    public void updatePos() {
        for (Car car : storage) {
            car.setPos(getPos());
        }
    }

    @Override
    public void move() {
        super.move();
        updatePos();
    }


    @Override
    public void gas(double amount) {
        super.gas(amount);
        updatePos();
    }

    @Override
    public void brake(double amount) {
        super.brake(amount);
        updatePos();
    }


}
