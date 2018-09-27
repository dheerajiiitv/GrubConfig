package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class PopUpBox {
    static int value;
    public static int display(String title) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

//        Label label = new Label();
//        label.setText(message);
        Button closeButton = new Button("Close this window");
        Button confirm = new Button("Confirm");


        VBox layout = new VBox(10);
        Label label1 = new Label("Enter time (in minutes)");
        TextField timeInput = new TextField();
//        String value = timeInput.getText();

        confirm.setOnAction(e -> {

            if (timeInput.getText() != "") {
                if (isInt(timeInput, timeInput.getText())) {
                    value = Integer.parseInt(timeInput.getText());
                    window.close();
                }
                else{
//                    Call alert Box.
                    value = -1;

                }

            }
            else {
//                Show error


            }
        });
        closeButton.setOnAction(e -> {
            value = -1;
            window.close();


        });
        layout.getChildren().addAll(label1, timeInput, confirm, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return value;

    }
    private static boolean isInt(TextField input, String message) {
        try {
            int grubOrder = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + message + " is not a number");
            return false;
        }

    }
}

