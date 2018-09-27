package backend;

import java.io.File;
import java.io.IOException;

public class updateGrub {

    public static void main() throws IOException, InterruptedException {
//        This script will executed whenever user do changes in grub
        ProcessBuilder processBuilder = null;
        try {

             processBuilder = new ProcessBuilder("sudo","update-grub");
            
        }
        catch (Exception e){
            System.out.println("Not able to update grub");
        }
        processBuilder.redirectError(new File("a.txt"));
        Process p = processBuilder.start();
        p.waitFor();

/* For output in console
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));
        String s = null;
        while ((s = stdInput.readLine()) != null)
        {
            System.out.println(s);
        }
*/



    }

}