package com.example.ruichiji;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainApp extends Application {
    private static File workspaceRoot;

    @Override
    public void start(Stage stage) throws Exception {
        // Workspace 驕ｸ謚槭ム繧､繧｢繝ｭ繧ｰ
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Ruichiji website root folder (contains assets/)");
        File selected = chooser.showDialog(stage);
        if (selected == null) {
            stage.close();
            return;
        }
        workspaceRoot = selected;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
        MainController controller = loader.getController();
        controller.setWorkspaceRoot(workspaceRoot);

        stage.setTitle("Ruichiji Editor (Local)");
        stage.setScene(scene);
        stage.setWidth(1100);
        stage.setHeight(700);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
