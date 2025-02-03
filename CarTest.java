import java.awt.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    @Test
    public void gasBrakeTest() {
        Car c = new Saab95();
        Car v = new Volvo240();

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
        Car c = new Saab95();
        for (int i=0; i<500; i++) {
            c.gas(1);
        }
        assertEquals(125, c.getCurrentSpeed(), 0.0);
    }

    @Test
    public void illegalArgumentTest() {
        Car c = new Volvo240();
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

}