package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class Main extends Application {

    private ProcessCopyRecall recall;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Processor v1.0");

        File desktop = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(desktop);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 250, 150);
        scene.getStylesheets().add("sample/main.css");
        primaryStage.setScene(scene);

        Text scenetitle = new Text("File Processor");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button openButton = new Button("Open Resource File");
        HBox hbBtn = new HBox(5);
        hbBtn.getChildren().add(openButton);
        grid.add(hbBtn, 1, 4);

        openButton.setOnAction(
                event -> {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        recall = new ProcessCopyRecall(file);
                        recall.generateCopyRecallVars();
                    }
                }
        );

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
