import java.awt.*;
import java.util.ArrayList;

public class CarShop<T extends Car> {
    private final int capacity;
    private ArrayList<Car> storage;

    public CarShop(int capacity){
        this.capacity = capacity;
        this.storage = new ArrayList<Car>();
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
