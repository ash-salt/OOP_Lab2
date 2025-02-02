import java.awt.*;

public abstract class Truck extends Car {

    private boolean rampUp;
    public Truck(int nrDoors, double enginePower, Color color, String modelName){
        super(nrDoors, enginePower, color, modelName);
        this.rampUp = false;

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

    @Override
    public void gas(double amount) {

        // "[...] lastbilen ska inte kunna köra om flaket är uppfällt."
        if (!getRampUp()) {

            super.gas(amount);

        }

        else {

            throw new IllegalStateException("Fordonets flak är tippat! Flaket måste sänkas till 0° innan färd.");

        }

    }
}
