import java.util.ArrayList;

public class CarShop<T extends Vehicle> {
    private final int capacity;
    private ArrayList<T> storage;
    private final double[] position;

    public CarShop(int capacity, double[] position){
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can not be negative or 0");
        }
        this.capacity = capacity;
        this.storage = new ArrayList<T>();
        this.position = position;
    }

    public double[] getPos() {
        return position;
    }

    public ArrayList<T> getStorage() {
        return storage;
    }

    public void loadCar(T car) {
        if (storage.size() == capacity) {
            throw new IndexOutOfBoundsException("Shop is already full, please release a car");
        }
        car.setStored(true);
        car.setPos(getPos());
        storage.add(car);
    }

    public void releaseCar(T car) {
        car.setStored(false);
        storage.remove(car);
        double[] newPos = new double[] {getPos()[0] - 0.25, getPos()[1] - 0.25};
        car.setPos(newPos);
    }


}
