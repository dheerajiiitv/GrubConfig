package backend;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class bootConfig {
    Properties configFile;

    public bootConfig(String fileLocation) throws FileNotFoundException {
        configFile = new Properties();
        FileInputStream in = new FileInputStream(fileLocation);
        try {
            configFile.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        String grubFileLocation = "/etc/default/grub";
        bootConfig config = new bootConfig(grubFileLocation);
        Set confiSettings;
        confiSettings = config.configFile.keySet();
        /*
        To see data in file
        Iterator itr = confiSettings.iterator();

        while (itr.hasNext()) {
            String str = (String) itr.next();
            System.out.println("The URL for " + str +
                    " is " + config.configFile.getProperty(str));
        }*/

//        To change grub order.
//        changeGrubOrder(args[0], config);


//        TO change time setting
//        changeTimeOrder(args[0], config);


    }

    public static  void changeGrubOrder(String value) throws IOException, InterruptedException {
        String grubFileLocation = "/etc/default/grub";
        bootConfig config = new bootConfig(grubFileLocation);
        config.configFile.setProperty("GRUB_DEFAULT", value);
        File file = new File("/etc/default/grub");
        FileOutputStream out = new FileOutputStream(file);
        config.configFile.store(out, null);
        out.close();
        System.out.println(file.canWrite());
        file.setReadable(false);
        System.out.println(file.canWrite());
        updateGrub grubUpdate = new updateGrub();
        grubUpdate.main();
    }

    public static  void changeTimeOrder(String value) throws IOException, InterruptedException {
        String grubFileLocation = "/etc/default/grub";
        bootConfig config = new bootConfig(grubFileLocation);
        config.configFile.setProperty("GRUB_TIMEOUT", value);
        File file = new File("/etc/default/grub");
        FileOutputStream out = new FileOutputStream(file);
        config.configFile.store(out, null);
        out.close();
        updateGrub grubUpdate = new updateGrub();
        grubUpdate.main();
    }

}

