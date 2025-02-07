import java.awt.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class CarShop<T extends Car> {
    private final int capacity;
    private ArrayList<T> storage;

    public CarShop(int capacity){
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can not be negative or 0");
        }
        this.capacity = capacity;
        this.storage = new ArrayList<T>();
    }

    public ArrayList<T> getStorage() {
        return storage;
    }

    public void loadCar(T car) {
        if (storage.size() == capacity) {
            throw new IndexOutOfBoundsException("Shop is already full, please release a car");
        }
        car.setStored();
        storage.add(car);
    }

    public void releaseCar(T car) {
        car.setNotStored();
        storage.remove(car);
    }


}
