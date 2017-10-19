package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Processor v1.0");

        FileChooser fileChooser = new FileChooser();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 250, 150);
        scene.getStylesheets().add("./sample/main.css");
        primaryStage.setScene(scene);

        Text scenetitle = new Text("File Processor");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button openButton = new Button("Open Resource File");
        HBox hbBtn = new HBox(5);
        hbBtn.getChildren().add(openButton);
        grid.add(hbBtn, 1, 4);

        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        File file = fileChooser.showOpenDialog(primaryStage);
                        if (file != null) {
                            openFile(file);
                        }
                    }
                }
        );

        primaryStage.show();
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            Logger.getLogger(
                   Main.class.getName()).log(
                           Level.SEVERE, null, e
            );
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
