package com.example.ruichiji;

import com.example.ruichiji.service.DataService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;

public class MainController {
    @FXML private StackPane centerPane;
    private DataService dataService;

    public void setWorkspaceRoot(File root) {
        this.dataService = new DataService(root.toPath());
        // 蛻晄悄陦ｨ遉ｺ縺ｯ Music
        showList("music");
    }

    @FXML public void onTopics() { showList("topics"); }
    @FXML public void onMusic()  { showList("music"); }
    @FXML public void onMovie()  { showList("movies"); }
    @FXML public void onDiscography() { showList("discography"); }
    @FXML public void onLive()   { showList("live"); }

    private void showList(String kind) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListView.fxml"));
            Node view = loader.load();
            var ctrl = loader.getController();
            if (ctrl instanceof com.example.ruichiji.controller.ListController) {
                com.example.ruichiji.controller.ListController lc = (com.example.ruichiji.controller.ListController) ctrl;
                lc.setDataService(this.dataService);
                lc.setKind(kind);
            }
            setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCenter(Node node) {
        centerPane.getChildren().clear();
        centerPane.getChildren().add(node);
    }
}
