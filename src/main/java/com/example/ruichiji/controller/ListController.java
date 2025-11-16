package com.example.ruichiji.controller;

import com.example.ruichiji.service.DataService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListController {
    @FXML private TableView<ObjectNode> table;
    @FXML private TableColumn<ObjectNode, String> colId;
    @FXML private TableColumn<ObjectNode, String> colTitle;
    @FXML private TableColumn<ObjectNode, String> colDate;
    @FXML private Button btnNew;
    @FXML private Button btnRefresh;

    private DataService dataService;
    private String kind;
    private ObservableList<ObjectNode> items = FXCollections.observableArrayList();

    public void setDataService(DataService ds) {
        this.dataService = ds;
        initializeTable();
    }

    public void setKind(String kind) {
        this.kind = kind;
        // load data for this kind
        loadData();
    }

    private void initializeTable() {
        colId.setCellValueFactory(cellData -> {
            var node = cellData.getValue().get("id");
            String s = (node != null && !node.isNull()) ? node.asText("") : "";
            return new SimpleStringProperty(s);
        });
        colTitle.setCellValueFactory(cellData -> {
            var node = cellData.getValue().get("title");
            String s = (node != null && !node.isNull()) ? node.asText("") : "";
            return new SimpleStringProperty(s);
        });
        colDate.setCellValueFactory(cellData -> {
            var node = cellData.getValue().get("date");
            String s = (node != null && !node.isNull()) ? node.asText("") : "";
            return new SimpleStringProperty(s);
        });

        table.setRowFactory(tv -> {
            TableRow<ObjectNode> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && !row.isEmpty()) {
                    editItem(row.getItem());
                }
            });
            return row;
        });

        ContextMenu menu = new ContextMenu();
        MenuItem edit = new MenuItem("編集");
        edit.setOnAction(e -> {
            ObjectNode sel = table.getSelectionModel().getSelectedItem();
            if (sel != null) editItem(sel);
        });
        MenuItem del = new MenuItem("削除");
        del.setOnAction(e -> {
            ObjectNode sel = table.getSelectionModel().getSelectedItem();
            if (sel != null) deleteItem(sel);
        });
        menu.getItems().addAll(edit, del);
        table.setContextMenu(menu);
    }

    @FXML public void onNew() {
        openEditor(null, true);
    }

    @FXML public void onRefresh() {
        loadData();
    }

    private void loadData() {
        try {
            List<ObjectNode> list = dataService.readList(kind);
            items.setAll(list);
            table.setItems(items);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("読み込みエラー", e.getMessage());
        }
    }

    private void editItem(ObjectNode node) {
        openEditor(node, false);
    }

    private void deleteItem(ObjectNode node) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "削除してよいですか？", ButtonType.YES, ButtonType.NO);
        a.setTitle("確認");
        a.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.YES) {
                items.remove(node);
                try {
                    dataService.writeList(kind, List.copyOf(items));
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert("保存エラー", e.getMessage());
                }
            }
        });
    }

    private void openEditor(ObjectNode node, boolean isNew) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditorView.fxml"));
            Node view = loader.load();
            var ctrl = loader.getController();
            if (ctrl instanceof com.example.ruichiji.controller.EditorController) {
                com.example.ruichiji.controller.EditorController ec = (com.example.ruichiji.controller.EditorController) ctrl;
                ec.setDataService(this.dataService);
                ec.setKind(this.kind);
                ec.setItem(node, isNew);
            }

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle((isNew ? "New " : "Edit ") + kind);
            stage.setScene(new Scene((javafx.scene.Parent) view));
            stage.setWidth(900);
            stage.setHeight(640);
            stage.showAndWait();

            // refresh after editor closed
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("エラー", e.getMessage());
        }
    }

    private void showAlert(String title, String body) {
        Alert a = new Alert(Alert.AlertType.ERROR, body, ButtonType.OK);
        a.setTitle(title);
        a.showAndWait();
    }
}