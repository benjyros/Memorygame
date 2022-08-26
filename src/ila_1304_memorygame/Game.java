/*
 * Code by @benny
 * b.peterhans.inf20@stud.bbbaden.ch
 */
package ila_1304_memorygame;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javafx.beans.property.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author benny
 */
public class Game {
    //Static values
    static final private int MAX_COL = 4;
    
    //Integerproperties
    private final IntegerProperty scorePlayerOne = new SimpleIntegerProperty();
    private final IntegerProperty scorePlayerTwo = new SimpleIntegerProperty();
    private final IntegerProperty playerTurn = new SimpleIntegerProperty(1);
    private final IntegerProperty winsPlayerOne = new SimpleIntegerProperty();
    private final IntegerProperty winsPlayerTwo = new SimpleIntegerProperty();
    
    //Stringproperties
    private final StringProperty textTurn = new SimpleStringProperty();
    private final StringProperty textScore = new SimpleStringProperty();
    
    //Integers
    private int indexFirstClicked, indexSecondClicked;
    
    //Strings
    private String result = "";
    
    //Booleans
    private boolean checkForWin = false;
    
    //Arraylists
    ArrayList<File> backgroundfiles = new ArrayList<>();
    ArrayList<Names> names = new ArrayList<>();
    ArrayList<File> files = new ArrayList<>();
    ArrayList<Boolean> imageIsFront = new ArrayList<>();
    
    //Others
    private ImageView firstClicked, secondClicked;
    
    File backgroundfile;
    
    Timer timer = new Timer();
    
    //When initializing the game this code will run
    public void createImages(){
        setTextTurn();
        setTextScore();
        checkForWin = false;
        
        createImageFiles();
        createImageBools();
    }
    
    //Logic for creating images and files
    private void createImageFiles(){
        names.clear();
        Collections.addAll(names, Names.values());
        Collections.addAll(names, Names.values());
        Collections.shuffle(names);
        files.clear();
        for(Names name : names){
            files.add(new File("src/ila_1304_memorygame/FrontPics/" + name.toString() + ".png"));
        }
    }
    
    private void createImageBools(){
        imageIsFront.clear();
        for(int i = 0; i < files.size(); i++){
            imageIsFront.add(Boolean.FALSE);
        }
    }
    
    public void createBackgroundFiles(){
        backgroundfiles.clear();
        
        for(int i = 0; i < 11; i++){
            backgroundfiles.add(new File("src/ila_1304_memorygame/BackPics/Alain" + (i + 1) + ".png"));
        }
        backgroundfiles.add(new File("src/ila_1304_memorygame/BackPics/ok.png"));
    }
    
    //Getters
    public Image getImage(int index){
        return new Image(files.get(index).toURI().toString());
    }
    
    public Image getBackground(int index){
        return new Image(backgroundfiles.get(index).toURI().toString());
    }
    
    public int getBackgroundFilesSize(){
        return backgroundfiles.size();
    }
    
    public boolean getImageIsFront(int index){
        return imageIsFront.get(index);
    }
    
    public ArrayList<Boolean> getImageIsFrontList(){
        return imageIsFront;
    }
    
    public String getName(int index){
        return names.get(index).toString();
    }
    
    public Boolean getIsWinner(){
        return checkForWin;
    }
    
    public String getResult(){
        return result + "Player 1: Score of " + scorePlayerOne.get() + ", " + winsPlayerOne.get() + " Wins\nPlayer 2: Score of " + scorePlayerTwo.get() + ", " + winsPlayerTwo.get() + " Wins";
    }
    
    //Setters
    public void setBackground(int index){
        backgroundfile = backgroundfiles.get(index);
    }
    
    public void setImageIsFront(int index, boolean bool){
        imageIsFront.set(index, bool);
    }
    
    //Basic gamelogic when the player clicks an image
    public void gameLogic(ImageView clicked) throws IOException{
        int row = 1;
        int col = 1;
        
        if(GridPane.getRowIndex(clicked) != null){
            row = GridPane.getRowIndex(clicked) + 1;
        }
        
        if(GridPane.getColumnIndex(clicked) != null){
            col = GridPane.getColumnIndex(clicked) + 1;
        }
        
        int indexClicked = (row * MAX_COL) + col - MAX_COL - 1;
        
        if(firstClicked != null && secondClicked != null){
            return;
        }
        
        if(clicked == null){
            return;
        }
        
        if(getImageIsFront(indexClicked)){
            return;
        }
        
        if(firstClicked == null){
            firstClicked = clicked;
            indexFirstClicked = indexClicked;
            firstClicked.setImage(getImage(indexFirstClicked));
            setImageIsFront(indexFirstClicked, true);
            return;
        }
        
        secondClicked = clicked;
        indexSecondClicked = indexClicked;
        secondClicked.setImage(getImage(indexClicked));
        setImageIsFront(indexSecondClicked, true);
        
        if(getName(indexFirstClicked).equals(getName(indexSecondClicked))){
            if(playerTurn.get() == 1){
                scorePlayerOne.set(scorePlayerOne.get() + 2);
            }
            else{
                scorePlayerTwo.set(scorePlayerTwo.get() + 2);
            }
            
            CheckWinner();
            
            firstClicked = null;
            secondClicked = null;
        }
        else{
            TimerTask tt = new TimerTask(){
                @Override
                public void run(){
                    firstClicked.setImage(new Image(backgroundfile.toURI().toString()));
                    secondClicked.setImage(new Image(backgroundfile.toURI().toString()));
                    
                    firstClicked = null;
                    secondClicked = null;
                }
            };
            timer.schedule(tt, 1000l);
            
            setImageIsFront(indexFirstClicked, false);
            setImageIsFront(indexSecondClicked, false);
            
            SwitchPlayer();
        }
        setTextTurn();
        setTextScore();
    }
    
    //Switch player's turn
    private void SwitchPlayer(){
        playerTurn.set(playerTurn.get() * -1);
    }
    
    //Setting the texts of "textTurn", "textScore" and "result"
    private void setTextTurn(){
        if(playerTurn.get() == 1){
            textTurn.set("Player turn: Player 1");
        }
        else{
            textTurn.set("Player turn: Player 2");
        }
    }
    
    private void setTextScore(){
        textScore.set("Score: " + scorePlayerOne.get() + " / " + scorePlayerTwo.get());
    }
    
    private void setResult(int player){
        switch (player) {
            case 1:
                result = "Player 1 won the game!\n\n";
                break;
            case 2:
                result = "Player 2 won the game!\n\n";
                break;
            default:
                result = "It's a tie!\n";
                break;
        }
    }
    
    //Logic for checking for winner
    private void CheckWinner() throws IOException{
        checkForWin = true;
        for(Boolean isFront : imageIsFront){
            if(!isFront){
                checkForWin = false;
            }
        }
        
        if(checkForWin){
            if(scorePlayerOne.get() > scorePlayerTwo.get()){
                winsPlayerOne.set(winsPlayerOne.get() + 1);
                playerTurn.set(-1);
                setResult(1);
            }
            if(scorePlayerOne.get() < scorePlayerTwo.get()){
                winsPlayerTwo.set(winsPlayerTwo.get() + 1);
                playerTurn.set(1);
                setResult(2);
            }
            if(scorePlayerOne == scorePlayerTwo){
                setResult(3);
            }
        }
        
    }
    
    //Logic if player wants a new game
    public void newGame(){
        scorePlayerOne.set(0);
        scorePlayerTwo.set(0);
        firstClicked = null;
        secondClicked = null;
    }
    
    //Properties for the Stringproperties of "textTurn" and "textScore"
    public StringProperty stringTurnProperty(){
        return textTurn;
    }
    
    public StringProperty scoreProperty(){
        return textScore;
    }
    
}
