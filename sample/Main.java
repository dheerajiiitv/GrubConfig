package sample;

import backend.bootConfig;
import backend.power;
import backend.updateGrub;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    Stage window;
    Scene scene1, scene2, scene3, powerOption;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        window = primaryStage;

        //Button 1
        Label label1 = new Label("Edit your grub configuration here");
        Button button1 = new Button("Edit grub order");
        Button button3 = new Button("Modify time out period");
        Button powerButton = new Button("Change power setting");
        button1.setOnAction(e -> window.setScene(scene2));
        button3.setOnAction(e -> window.setScene(scene3));
        powerButton.setOnAction(e -> window.setScene(powerOption));
        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1, button3, powerButton);
        scene1 = new Scene(layout1, 200, 200);
        //Button 2
        Button button2 = new Button("Ok");
        button2.setOnAction(e -> {
            System.out.println("Grub Updated with value ");
        });

        //Layout 2
        TextField grubInput = new TextField();

        Button button = new Button("Enter grub value.");

        bootConfig bootconfig = new bootConfig("/home/dheeraj/Desktop/Grub/src/sample/grub");
        button.setOnAction(e -> {
            if (isInt(grubInput, grubInput.getText())) {
//                Edit.
                int order = Integer.parseInt(grubInput.getText());
//                Home screen.
                if (inRange(order, 5)) {
                    // Edit
                    String arr = grubInput.getText();
                    try {
                        bootconfig.changeGrubOrder(arr);

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    // Alert Box.
                }
                window.setScene(scene1);
                window.show();
            } else {

            }
        });
//        StackPane layout2 = new StackPane();
//        layout2.getChildren().addAll(button2, button, ageInput);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(grubInput, button);
        scene2 = new Scene(layout, 600, 300);
        TextField timeInput = new TextField();
        VBox layout3 = new VBox(10);
        Label timeOrder = new Label("Enter time (in seconds)");
        layout3.setPadding(new Insets(20, 20, 20, 20));
        Button changeTime = new Button("Ok");
        changeTime.setOnAction(e -> {
            if (isInt(timeInput, timeInput.getText())) {
//                Edit.
                try {
                    bootconfig.changeTimeOrder(timeInput.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                window.setScene(scene1);
                window.show();
            } else {

            }
        });
        layout3.getChildren().addAll(timeOrder, timeInput, changeTime);

        scene3 = new Scene(layout3, 600, 300);

        // Power option
        VBox powerLayout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
//        Button shutdown = new Button("Reboot");
//        Button reboot = new Button("Shutdown");
        Button scheduleShutdown = new Button("Schedule Shutdown");
        Button schduleReboot = new Button("Schedule Reboot");
//        shutdown.setOnAction(e -> {
//
//        });
//
//        reboot.setOnAction(e -> {
//
//        });
power powerObject  = new power();
        schduleReboot.setOnAction(e -> {
            int time = PopUpBox.display("Schedule Reboot");
            System.out.println(time);
            if (time != -1) {
                try {
                    powerObject.reboot(Integer.toString(time));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            //           Execute schedule reboot.
        });

        scheduleShutdown.setOnAction(e -> {
            int time = PopUpBox.display("Schedule Reboot");
            if (time != -1) {
                try {
                    powerObject.shutdownLaptop(Integer.toString(time));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
//            Excute schedule shutdown
        });
        powerLayout.getChildren().addAll( scheduleShutdown, schduleReboot);
        powerOption = new Scene(powerLayout, 600, 300);


        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Grub Configuration");
        window.show();


    }


    private boolean inRange(int value, int range) {
        if (value < range)
            return true;
        else
            return false;
    }

    private boolean isInt(TextField input, String message) {
        try {
            Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + message + " is not a number");
            return false;
        }

    }
}
