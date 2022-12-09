import java.util.Random;

public class User extends Thread{
    private String userName;
    private double weight;
    private int floor;
    private boolean atWork;
    private int nextFloor;

    public User(String name, double weight) {
        this.userName = name;
        this.weight = weight;
        floor = 0;
        atWork = true;
    }

    public boolean isAtWork() {
        return atWork;
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        long diff = 0;
        Random random = new Random();
        int val;
        while(diff < 10000){
            System.out.println(userName+" is currently on floor "+floor);
            val = random.nextInt(10);
            if(val > 5){
                nextFloor = random.nextInt(10);
                System.out.println(userName+" is waiting to go to floor "+nextFloor+" from floor "+floor);
                while (Company.getOn(floor, this)){
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(userName+" is waiting to get off at floor "+nextFloor);
                while (Company.getOff(nextFloor, this)){
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                floor = nextFloor;
            }

            try {
                sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            diff = System.currentTimeMillis() - time;
        }
        atWork = false;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Name: " + userName;
    }
}
