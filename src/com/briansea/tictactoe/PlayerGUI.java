package com.briansea.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import java.io.*;
import javafx.stage.Stage;


import java.nio.channels.FileChannel;

/**
 * @author Brian Sea (stupid)
 * @date 8/22/2022
 *
 * The graphical component of each player
 */
public class PlayerGUI extends BorderPane {

    private String name;

    private String url;
    private ImageView picture;

    // Allow editing of the name
    private TextField nameField;

    // Allow editing of the picture used by the player
    private Button imageButton;

    /**
     * Create a default player GUI.  The name is blank.
     */
    public PlayerGUI(){
        url = "None";

        Label nameLabel = new Label("Name:");
        nameLabel.setPadding(new Insets(5,5,5,5));

        nameField = new TextField();

        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.CENTER_LEFT);
        nameBox.getChildren().addAll(nameLabel, nameField);

        imageButton = new Button("Add Picture");
        imageButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        imageButton.setContentDisplay(ContentDisplay.TOP);

        picture = new ImageView(getClass().getResource("/camera.png").toString());
        picture.fitWidthProperty().bind(this.widthProperty().divide(2));
        picture.fitHeightProperty().bind(picture.fitWidthProperty());
        imageButton.setGraphic(picture);

        setTop(nameBox);
        setCenter(imageButton);

        this.setPadding(new Insets(5,5,5,5));

        imageButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                FileChooser chooser = new FileChooser();
                File imageFile = chooser.showOpenDialog(((Stage)getScene().getWindow()));
                url = imageFile.toURI().toString();
                Image image = new Image(url);
                picture.setImage(image);
                imageButton.setGraphic(picture);
            }
        });
    }

    /**
     * Get the name of the current player
     * @return the name of the player
     */
    public String getName(){
        return nameField.getText();
    }

    public String getUrl() { return url; }

    /**
     * Change the name of the player
     * @param n the new name; cannot be empty or null
     * @return true of the name is changed, false otherwise
     */
    public boolean setName(String n){
        if( n != null && !n.isEmpty()){
            name = n;
            nameField.setText(n);
            return true;
        }
        return false;
    }
}
