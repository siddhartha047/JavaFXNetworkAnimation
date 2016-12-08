/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticgame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.ConnectionUtillities;


/**
 *
 * @author user
 */
public abstract class Main extends Application{
    
    ConnectionUtillities ncCon;
    boolean isMyTurn;
    
    public abstract void CreateConnection();
    
    public void update(Player2 player2,long now){
        player2.update();
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        CreateConnection();
        
        final Player1 player1=new Player1(isMyTurn,ncCon);
        final Player2 player2=new Player2(ncCon);
        
      
       
        //GridPane board=new GridPane();
        //board.setHgap(20);
        //board.setHgap(20);
        //board.setAlignment(Pos.CENTER);        
      //  board.add(player1.design, 0, 1);
       // board.add(player2.design, 1, 1);
        
        
        
        Group root=new Group();
        root.getChildren().add(player1.design);
        root.getChildren().add(player2.design);
        Scene scene=new Scene(root,400,400);
        
        player1.setHandler(scene);  
        
        
        new AnimationTimer() {

            @Override
            public void handle(long l) {
                update(player2,l);
            }
        }.start();
        
        stage.setScene(scene);
        stage.show();       
        
        ReadThread myRead=new ReadThread(player1,player2,ncCon);
        
        new Thread(myRead).start();
    }
}