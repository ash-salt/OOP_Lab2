import java.awt.*;

// Scania är en lastbil med ett flak som ska kunna tippas och sänkas.
public class Scania extends Truck {

    // FlatbedAngle betecknar flakets vinkel till lastbilen.

    private double flatbedAngle;

    public Scania() {

        // Lastbilen har två dörrar, jag vet inte vad enginePower ska vara
        super(2,100,Color.white,"Scania");
        this.flatbedAngle = 0;
        stopEngine();

    }

    @Override
    public boolean getRampUp() {
        return flatbedAngle > 0;
    }

    public double getFlatbedAngle() {

        return flatbedAngle; //123

    }

    // Några regler för flaket angavs i labben, denna kod följer dessa;
    public void adjustFlatbed(double amount) {

        // "Det är bara om lastbilen står stilla som flaket får ha en annan vinkel än 0."
         if (getCurrentSpeed() != 0) {

             throw new IllegalStateException("Fordonet är i rörelse, kan inte justera flaket!");

        }

         double newAngle = flatbedAngle + amount;

         // "Vinkeln på flaket kan inte vara lägre än 0 eller högre än 70."
         if (newAngle < 0 || newAngle > 70) {

             throw new IllegalArgumentException("Angivet värde är ogiltigt, flakets min-och-maxlutningar är 0° respektive 70°.");

        }

         flatbedAngle = newAngle;

    }

}
