import java.util.ArrayList;
import java.util.Scanner;

public class Company {

    private static Elevator elevator;
    private static ArrayList<User> users;

    public static double getDouble(Scanner sc, String message){
        double val = -1;
        while (val < 0){
            System.out.print(message);
            try{
                val = Double.parseDouble(sc.nextLine());
            }catch (Exception e){
                //System.out.println("");
            }
        }
        return val;
    }

    public static int getInt(Scanner sc, String message){
        int val = -1;
        while (val < 0){
            System.out.print(message);
            try{
                val = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                //System.out.println("");
            }
        }
        return val;
    }

    public static String getString(Scanner sc, String message){
        System.out.print(message);
        return sc.nextLine();
    }

    public static boolean getOn(int floor, User user){
        return elevator.getOn(floor, user);
    }

    public static boolean getOff(int floor, User user){
        return elevator.getOff(floor, user);
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int numWorkers = getInt(sc, "Enter the number of workers in the company: ");
        users = new ArrayList<>();
        elevator = new Elevator();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < numWorkers; i++){
            User u = new User(getString(sc,"Enter the name of the user: "), getDouble(sc, "Enter the weight of the user: "));
            users.add(u);
            Thread t = new Thread(u);
            threads.add(t);
        }
        Thread thread = new Thread(elevator);
        thread.start();

        for(Thread t: threads){
            t.start();
        }

        for(User u: users){
            u.join();
        }

    }

    public static boolean closed() {
        if (users == null)
            return false;
        boolean closed = true;
        for(User u: users)
            if(u.isAtWork())
                closed = false;
        return closed;
    }
}
