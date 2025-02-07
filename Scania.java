import java.awt.*;

// Scania är en lastbil med ett flak som ska kunna tippas och sänkas.
public class Scania extends Vehicle implements HasFlatbed {

    // FlatbedAngle betecknar flakets vinkel till lastbilen.

    private double flatbedAngle;

    public Scania() {

        // Lastbilen har två dörrar, jag vet inte vad enginePower ska vara
        super(2, 100, Color.white, "Scania");
        this.flatbedAngle = 0;
        stopEngine();

    }

    public void checkRampUp() throws IllegalStateException {
        if (getRampUp()) {
            throw new IllegalStateException("Fordonets flak är tippat! Flaket måste sänkas till 0° innan färd.");
        }
    }

    @Override
    public boolean getRampUp() {
        return getFlatbedAngle() > 0;
    }

    public double getFlatbedAngle() {

        return flatbedAngle; //123

    }

    @Override
    public void adjustFlatbed() {
        adjustFlatbed(35);
    }


    // Några regler för flaket angavs i labben, denna kod följer dessa;
    public void adjustFlatbed(double angle) {

        // "Det är bara om lastbilen står stilla som flaket får ha en annan vinkel än 0."
         if (getCurrentSpeed() != 0) {
             throw new IllegalStateException("Fordonet är i rörelse, kan inte justera flaket!");
        }

         // "Vinkeln på flaket kan inte vara lägre än 0 eller högre än 70."
         if (angle < 0 || angle > 70) {
             throw new IllegalArgumentException("Angivet värde är ogiltigt, flakets min-och-maxlutningar är 0° respektive 70°.");
        }
         flatbedAngle = angle;

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
    }

    @Override
    public void gas(double amount) {
        // "[...] lastbilen ska inte kunna köra om flaket är uppfällt."
        checkRampUp();
        super.gas(amount);

    }

}
