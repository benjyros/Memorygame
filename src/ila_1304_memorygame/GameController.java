/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ila_1304_memorygame;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author benny
 */
public class GameController implements Initializable {
    
    Game game;
    
    ImageView imageview;
    
    @FXML
    private GridPane gPane;
    @FXML
    private Label lblPlayerTurn;
    @FXML
    private Label lblScore;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game();
        game.createImages();
        game.createBackgroundFiles();
        
        AssignIconsToSquares();
        game.newGame();
        
        lblPlayerTurn.textProperty().bind(game.stringTurnProperty());
        lblScore.textProperty().bind(game.scoreProperty());
    }    
    
    private void AssignIconsToSquares(){
        Random random = new Random();
        int rndNum = random.nextInt(game.getBackgroundFilesSize());
        
         
        for(int i = 0; i < gPane.getChildren().size(); i++){
            if(!gPane.getChildren().isEmpty()){
                imageview = (ImageView) gPane.getChildren().get(i);
            }
            else{
                continue;   
            }
            game.setBackground(rndNum);
            imageview.setImage(game.getBackground(rndNum));
        }
    }

    @FXML
    private void iconExit(MouseEvent event) {
        ImageView imgView = (ImageView) event.getSource();
        imgView.setScaleX(1);
        imgView.setScaleY(1);
    }

    @FXML
    private void iconEnter(MouseEvent event) {
        ImageView imgView = (ImageView) event.getSource();
        imgView.setScaleX(1.05);
        imgView.setScaleY(1.05);
    }

    @FXML
    private void iconPressed(MouseEvent event) throws IOException {        
        ImageView clicked = (ImageView) event.getSource();
        game.gameLogic(clicked);
        
        if(game.getIsWinner()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game ended!");
            alert.setHeaderText(null);
            alert.setContentText(game.getResult());
            alert.show();
        }
    }

    @FXML
    private void onAction_mItemExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onAction_mItemNewGame(ActionEvent event) {
        game.newGame();
        game.createImages();
        AssignIconsToSquares();
    }

    @FXML
    private void onAction_mItemAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("The Game - About");
        alert.setHeaderText(null);
        alert.setContentText("This is a memorygame where you need to match two "
                + "identical cards/pictures. After each move the player's turn "
                + "will switch. But if the player matches two cards then it's "
                + "his turn again.\nThe game ends when all pictures are "
                + "revealed. Whoever has more points is the winner.");
        alert.show();
    }  
    
}
