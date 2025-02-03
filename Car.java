import java.awt.*;

public abstract class Car implements Movable{
    //Abstrakt class, kan inte skapa instanser. Tanken är att det inte ska gå att skapa en generisk bil

    private final int nrDoors;
    private final double enginePower;
    private double currentSpeed;
    private Color color;
    private final String modelName;
    private double x;
    private double y;
    private Direction direction;
    private boolean stored;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        //Definierar instansvariabler
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;

        this.x = 0;
        this.y = 0;
        this.direction = Direction.NORTH;

        //stored meddelar att bilen inte ska kunna röra sig
        this.stored = false;


        stopEngine();

    }
    public String getModel() { return modelName;}

    protected void setStored() {
        stopEngine();
        stored = true;
    }

    protected void setNotStored() {stored = false;}

    private void checkStored() {
        if (stored) {
            throw new IllegalStateException("This action cannot be performed as this vehicle is currently stored");
        }
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    protected void setCurrentSpeed(double value) {currentSpeed = value;}

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        checkStored();
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public double speedFactor(){
        return enginePower * 0.01;
    }

    private void incrementSpeed(double amount){
        checkStored();
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower()));
    }

    private void decrementSpeed(double amount){
        checkStored();
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }

    public double[] getPos(){
        double[] pos = new double[2];
        pos[0] = x;
        pos[1] = y;
        return pos;
    }

    protected void setPos(double[] pos){
        if (pos.length != 2) {
            throw new IllegalArgumentException("incorrect argument length");
        }
        x = pos[0];
        y = pos[1];
    }

    //Rör sig enligt ett x/y grid beroende på riktning
    public void move() {
        checkStored();
        switch (direction) {
            case NORTH -> y += getCurrentSpeed();
            case SOUTH -> y -= getCurrentSpeed();
            case WEST -> x -= getCurrentSpeed();
            case EAST -> x += getCurrentSpeed();
        }
    }

    public void turnLeft() {
        checkStored();
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.NORTH;
        }
    }

    public void turnRight() {
        checkStored();
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
    }

    //Gas och brake ska bara kunna ta värden mellan 0 och 1
    public void gas(double amount){
        if (0 <= amount && amount <= 1) {incrementSpeed(amount);}
        else throw new IllegalArgumentException("amount not allowed");
    }

    public void brake(double amount){
        if (0 <= amount && amount <= 1) {decrementSpeed(amount);}
        else throw new IllegalArgumentException("amount not allowed");
    }


}
