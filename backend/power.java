package backend;

import java.io.File;
import java.io.IOException;

public class power {
    public  static  void main(String args[]) throws IOException, InterruptedException {


//        Shutdown
        String time="50";
        shutdownLaptop(time);
//        reboot(time);
        



    }

    public static void shutdownLaptop(String time) throws IOException, InterruptedException {
        ProcessBuilder pb = null;
        try {
             pb = new ProcessBuilder("shutdown", "-P", time);
        }
        catch (Exception e){
            System.out.println("Shutdown command not working error - "+e);
        }
        pb.redirectError(new File("a.txt"));
        Process p = pb.start();
        p.waitFor();
        System.out.println("Reached");


    }

    public static void reboot(String time) throws IOException, InterruptedException {
        ProcessBuilder pb = null;
        try {
            pb = new ProcessBuilder("shutdown", "-r", time);
        }
        catch (Exception e){
            System.out.println("Shutdown command not working error - "+e);
        }
        pb.redirectInput(new File("a.txt"));
        Process p = pb.start();
        p.waitFor();
        System.out.println("Reached");

    }


}
