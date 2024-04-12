package com.briansea.tictactoe;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.*;


/**
 * @author Brian Sea
 * @date 8/22/2022
 *
 * The GUI used to represent the Logic
 */

public class TicTacToeGUI extends BorderPane {

    // the game itself
    private TicTacToeLogic logic;

    // Top status bar
    private Label status;

    // Graphical buttons on the board for easy update
    private Button[] board;
    private PlayerGUI[] players;
    public TicTacToeGUI( int dimension, PlayerGUI[] players){
        this.players = players;

        logic = new TicTacToeLogic(dimension);

        // Reset the dimension in case we were given an invalid one
        dimension = logic.getDimension();
        board = new Button[dimension*dimension];
        HBox optionBar = createOptions();
        GridPane board = createBoard();

        this.setTop(optionBar);
        this.setCenter(board);

        updateBoard();
    }

    /**
     * Creates the graphical component of Tic Tac Toe
     * dimension number of rows/columns in the board
     */


    // Update the graphical spaces, status bar, and winner
private void updateBoard(){

    // Update buttons
    for( int spot = 0; spot < board.length; spot++ ){
        String player = logic.getOwner(spot);
        if( player.isEmpty() ){
            player = " ";
            board[spot].setGraphic(null);
        }
        if (player.equals("X") && !players[0].getUrl().equals("None")) {
            ImageView picture = new ImageView();
            Image image = new Image(players[0].getUrl());
            picture.setImage(image);
            picture.fitWidthProperty().bind(this.widthProperty().divide(4 * logic.getDimension()));
            picture.fitHeightProperty().bind(this.heightProperty().divide(4 * logic.getDimension()));
            board[spot].setGraphic(picture);
        }
        else if (player.equals("O") && !players[1].getUrl().equals("None")) {
            ImageView picture = new ImageView();
            Image image = new Image(players[1].getUrl());
            picture.setImage(image);
            picture.fitWidthProperty().bind(this.widthProperty().divide(4 * logic.getDimension()));
            picture.fitHeightProperty().bind(this.heightProperty().divide(4 * logic.getDimension()));
            board[spot].setGraphic(picture);
        }
        else {
            board[spot].setText(player);
        }
    }

    // Update the status bar (turn or winner)
    String statusText = logic.whoseTurn();

    if( this.players.length > 0  ) {
        if (statusText.equals("X")) {
            statusText = players[0].getName();
        } else if (statusText.equals("O")) {
            statusText = players[1].getName();
        }
    }
    statusText += "\'s Turn";



    String winner = "";
    String loser = "";
    if(logic.checkWinner().equals("X")) {
        winner = players[0].getName();
        loser = players[1].getName();


    }

    else if(logic.checkWinner().equals("O")){
        winner = players[1].getName();
        loser = players[0].getName();

    }
    if( !winner.isEmpty() ){
        statusText = logic.checkWinner() + " Wins!";
    }
    if(logic.checkWinner().equals("TIE")){
        statusText = "It's a tie.";
        String player1 = players[0].getName();
        String player2 = players[1].getName();
    }

    status.setText(statusText);
}

    // Create the top status bar
    private HBox createOptions(){

        Font font = new Font(14);
        HBox rtn = new HBox();

        Button back = new Button("Back");
        Button reset = new Button( "Reset" );
        Button leaderboardbutton = new Button("Leaderboard");

        status = new Label();

        back.setFont(font);
        leaderboardbutton.setFont(font);
        reset.setFont(font);
        status.setFont(font);

        // leaderboard button logic to get it to scene

        leaderboardbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // Change the scene to the leaderboard scene
                //((Stage)getScene().getWindow()).setScene(new Scene(leaderboard, 500,500));
            }
        });

        // When reset is clicked, reset the logic and update the board
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                logic.reset();
                updateBoard();
            }
        });
        // back button functionality
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                TTTOptionsGUI player = new TTTOptionsGUI();

                // Change the scene to the Tic Tac Toe board
                ((Stage)getScene().getWindow()).setScene(new Scene(player, 500,500));
            }
        });

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        rtn.getChildren().addAll(back, leftSpacer, status, rightSpacer, reset);

        Insets insets = new Insets(5,5,5,5);
        rtn.setPadding(insets);

        return rtn;
    }

    // Create the graphical board
    private GridPane createBoard(){
        GridPane board = new GridPane();

        final int ROW_GAP = 10;

        board.setHgap(ROW_GAP);
        board.setVgap(ROW_GAP);
        board.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        int dim = logic.getDimension();

        // Add the rows and columns
        for( int i = 0; i < dim; i++ ){
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0/dim);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0/dim);

            board.getColumnConstraints().add(col);
            board.getRowConstraints().add(row);
        }

        // Create and add the buttons to the board
        for( int row = 0; row < dim; row++ ){
            for( int col = 0; col < dim; col++ ){
                int spotNum = row * dim + col;

                Button space = new Button(""+spotNum);
                space.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                // Add a marker to ID the button
                space.setUserData(""+spotNum);
                this.board[spotNum] = space;

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

                // When a button is clicked, make a move!
                space.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        // Grab the ID off the button
                        Button source = (Button) actionEvent.getSource();
                        int spotNum = Integer.parseInt((String)source.getUserData());

                        logic.makeMove(spotNum);
                        updateBoard();
                    }
                });
            }
        }
        return board;
    }

}
