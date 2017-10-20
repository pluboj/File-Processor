package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        /* Scene */
        Scene scene = new Scene(grid, 250, 150);
        scene.getStylesheets().add("sample/main.css");
        primaryStage.setScene(scene);

        /* Label */
        Text scenetitle = new Text("File Processor");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        /* Radio Buttons */
        final ToggleGroup group = new ToggleGroup();
        RadioButton toc = new RadioButton("Process ToC");
        toc.setToggleGroup(group);
        toc.setId("toc");
        grid.add(toc, 0, 1);
        RadioButton cr = new RadioButton("Process CopyRecall");
        cr.setToggleGroup(group);
        cr.setSelected(true);
        cr.setId("cr");
        cr.requestFocus();
        grid.add(cr, 0, 2);

        /* Button */
        Button openButton = new Button("Open Resource File");
        HBox hbBtn = new HBox(5);
        hbBtn.getChildren().add(openButton);
        grid.add(hbBtn, 0, 4, 2, 1);

        openButton.setOnAction(
                event -> {
                    RadioButton chk = (RadioButton)group.getSelectedToggle();
                    String selectedRadio = chk.getId();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        if (selectedRadio.equalsIgnoreCase("cr")) {
                            recall = new ProcessCopyRecall(file);
                            recall.generateCopyRecallVars();
                        } else if (selectedRadio.equalsIgnoreCase("toc")) {
                            //TODO:pl - add ProcessToC
                            System.out.println("Process ToC...");
                        }
                    }
                }
        );

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
