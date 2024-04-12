package com.briansea.tictactoe;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Leaderboard extends BorderPane {

    // attributes
    private Label status;

    private String loc;

    private Leaderboard leaderboard;

    public Leaderboard(Leaderboard leaderboard, String loc){

        this.loc = loc;
        this.leaderboard = leaderboard;


        VBox centerScreen = new VBox();
        GridPane board = createBoard();
        setCenter(board);
        setBottom(createOptions());

        this.layout();
    }

    public void updateLeaderboard(){
        LeaderBoardLogic logic = new LeaderBoardLogic();
    }

     private HBox createOptions(){

        Font font = new Font(14);
        HBox rtn = new HBox();

        Button back = new Button("Back");

        back.setFont(font);

        // back button functionality
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(loc.equals("Main")){
                    TTTOptionsGUI player = new TTTOptionsGUI();
                ((Stage)getScene().getWindow()).setScene(new Scene(player, 500,500));
                }
                if(loc.equals("Game")){

                    TTTOptionsGUI player = new TTTOptionsGUI();

                ((Stage)getScene().getWindow()).setScene(new Scene(player, 500,500));
                }


            }
        });

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        rtn.getChildren().addAll(back, leftSpacer, rightSpacer);

        Insets insets = new Insets(5,5,5,5);
        rtn.setPadding(insets);

        return rtn;
    }
    private GridPane createBoard(){
        GridPane board = new GridPane();

        final int ROW_GAP = 10;

        board.setHgap(ROW_GAP);
        board.setVgap(ROW_GAP);
        board.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        int LEADERBOARD_DIM = 4;
        int dim = LEADERBOARD_DIM;

        // Add the rows and columns
        for( int i = 0; i < dim; i++ ){
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0/dim);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(80.0/dim);

            board.getColumnConstraints().add(col);
            board.getRowConstraints().add(row);
            board.setBackground(Background.fill(Paint.valueOf("ADD8E6")));
            board.setAlignment(Pos.CENTER);
        }

        // Create and add the buttons to the board
        for( int row = 1; row < dim; row++ ){
            for( int col = 0; col < dim; col++ ){
                int spotNum = row * dim + col;

                //Label space = new Label(""+spotNum);
                Label space = new Label(""+spotNum);
                space.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


                // Resize the font when the window is resized
                space.heightProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                        space.setStyle("-fx-font-size:"+(int)(0.45*newVal.doubleValue())+";");
                    }
                });

                GridPane.setVgrow(space, Priority.ALWAYS);
                GridPane.setHgrow(space, Priority.ALWAYS);

                board.add(space, col, row);
            }
        }
        return board;
    }


}
