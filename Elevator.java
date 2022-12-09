
public class Elevator extends Thread{
    private final double Max_Weight = 1000;
    private final int Max_People = 15;
    private int curr_people = 0;
    private int curr_weight = 0;
    private int curr_floor = 0;
    public Elevator() {
    }

  
    public void run() {

        while(!Company.closed()){
            System.out.println("Elevator is currently on floor: "+curr_floor);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            curr_floor++;
            if(curr_floor >= 10)
                curr_floor = 0;
        }
    }

    public boolean getOn(int floor, User user){
        if(curr_floor == floor){
            if(user.getWeight() + curr_weight > Max_Weight)
                return false;
            if(curr_people + 1 > Max_People)
                return false;
            return true;
        }
        return false;
    }

    public boolean getOff(int nextFloor, User user) {
        if(curr_floor == nextFloor){
            curr_weight-=user.getWeight();
            curr_people--;
            return true;
        }
        return false;
    }
}
