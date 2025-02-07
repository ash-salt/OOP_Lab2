import java.awt.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    public void gasBrakeTest() {
        Vehicle c = new Saab95();
        Vehicle v = new Volvo240();

        c.startEngine();
        v.startEngine();

        c.gas(1);
        v.gas(1);
        assertEquals(1.35, v.getCurrentSpeed(), 0.0001);
        assertEquals(1.35, c.getCurrentSpeed(), 0.0001);

        v.brake(1);
        c.brake(1);
        assertEquals(0.1, v.getCurrentSpeed(), 0.0001);
        assertEquals(0.1, c.getCurrentSpeed(), 0.0001);
    }

    @Test
    public void maxSpeedTest() {
        Vehicle c = new Saab95();
        for (int i=0; i<500; i++) {
            c.gas(1);
        }
        assertEquals(125, c.getCurrentSpeed(), 0.0);
    }

    @Test
    public void illegalArgumentTest() {
        Vehicle c = new Volvo240();
        c.startEngine();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> c.gas(1000));
        assertEquals("amount not allowed", exception.getMessage());

    }

    @Test
    public void moveTurnTest() {

        Volvo240 car = new Volvo240();

        car.startEngine();

        car.move();

        car.turnLeft();

        car.move();

        double[] pos = car.getPos();

        assertEquals(-0.1, pos[0], 0);

        assertEquals(0.1, pos[1], 0);
    }

    @Test
    public void colorTest() {

        Saab95 car = new Saab95();

        car.startEngine();

        assertEquals(Color.red, car.getColor());

        car.setColor(Color.black);

        assertEquals(Color.black, car.getColor());

    }

    @Test
    public void turboTest() {


        Saab95 car = new Saab95();
        car.startEngine();

        assertEquals(1.25, car.speedFactor(), 0.0001);

        car.setTurboOn();

        assertEquals(1.625, car.speedFactor(), 0.0001);
    }

    // Just nu är detta testet broken
    @Test
    public void scaniaFlatBedTest() {

        Scania truck = new Scania();

        truck.adjustFlatbed(5);

        Exception exception = assertThrows(IllegalStateException.class, truck::move);

        assertEquals("Fordonets flak är tippat! Flaket måste sänkas till 0° innan färd.", exception.getMessage());

    }

    @Test
    public void carStoredTest() {
        Vehicle c = new Volvo240();
        VehicleTransport ct = new VehicleTransport();

        ct.adjustFlatbed();

        ct.loadCar(c);

        Exception exception = assertThrows(IllegalStateException.class, c::startEngine);

        assertEquals("This action cannot be performed as this vehicle is currently stored", exception.getMessage());
    }

    @Test
    public void storeTruckErrorTest() {
        Scania scania = new Scania();
        VehicleTransport ct = new VehicleTransport();

        ct.adjustFlatbed();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> ct.loadCar(scania));

        assertEquals("Vehicle is too large", exception.getMessage());
    }

    @Test
    public void CarShopTest() {
        CarShop<Volvo240> cs = new CarShop<Volvo240>(10, new double[]{0,0});
        Volvo240 v = new Volvo240();
        cs.loadCar(v);
        assertTrue(v.getStored());
        assertEquals(v, cs.getStorage().getFirst());

    }

    @Test
    public void storeStoredTest() {
        CarShop<Volvo240> cs = new CarShop<Volvo240>(10, new double[]{0,0});
        Volvo240 v = new Volvo240();
        VehicleTransport ct = new VehicleTransport();
        ct.adjustFlatbed();
        ct.loadCar(v);
        Exception exception = assertThrows(IllegalStateException.class,
                () -> cs.loadCar(v));
        assertEquals("A car cannot be stored if it is already stored", exception.getMessage());
    }
}