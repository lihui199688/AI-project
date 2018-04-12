package student_player;
import java.util.HashSet;

import coordinates.Coord;
import coordinates.Coordinates;
import tablut.TablutBoardState;
import tablut.TablutMove;
//import tablut.TablutPlayer;



public class MyTools {
    public static double getSomething() {
        return Math.random();
    }
    
    public static double closeConer (TablutBoardState boardstate){
     double counter =0;
     double value=0;
   	 HashSet<Coord> set = boardstate.getPlayerPieceCoordinates();

   	 for(Coord loc: set ){
   		 if(loc.x <3 && loc.y<3) {
   			 counter++;
   			 }
   		 if(loc.x <3 && loc.y<9 &&loc.y>6) {
   			 counter++;
   			 }	 

   		 if(loc.x<9 &&loc.x>6 && loc.y<9 &&loc.y>6) {
   			 counter++;
   			 }
   		 
   		 if(loc.x<9 &&loc.x>6 && loc.y<3) {
   			 counter++;
   			 }
   		 
   	 }
   	 value = counter;
 
   	 return value;
    }
    
    
    public TablutMove tilledge(Coord king, TablutBoardState boardstate) {
        TablutMove king1=new TablutMove(king,Coordinates.get(king.x,0),1);
        TablutMove king2=new TablutMove(king,Coordinates.get(0,king.y),1);
        TablutMove king3=new TablutMove(king,Coordinates.get(king.x,8),1);
        TablutMove king4=new TablutMove(king,Coordinates.get(8,king.y),1);
       System.out.println("helper");
        if(boardstate.isLegal( king1)){
         boolean left=true;
         boolean right=true;

         for(int i=1;i<king.x;i++) {
         if(left !=boardstate.coordIsEmpty(Coordinates.get(i,0))){
          left=false;break;
         }
         }
            for(int i=king.x+1;i<8;i++) {
             if(right !=boardstate.coordIsEmpty(Coordinates.get(i,0))){
              right=false;break;
             }
            }
         if((left==true || right == true)&& (boardstate.coordIsEmpty(Coordinates.get(king.x-1,0))) &&  (boardstate.coordIsEmpty(Coordinates.get(king.x+1,0)))) {
           //  System.out.println("--------------1--------------------");
          return king1;
         }
         }
        
        if(boardstate.isLegal( king2)){
           boolean left = true;
           boolean right = true;
           
           for(int i=1; i<king.y; i++) {
            if(left !=boardstate.coordIsEmpty(Coordinates.get(0,i))) {
             left = false;
             break;
            }
           }
           for(int i=king.y + 1; i<8; i++) {
            if(left !=boardstate.coordIsEmpty(Coordinates.get(0,i))) {
             left = false;
             break;
            }
           }
           if((left==true || right == true)&&(boardstate.coordIsEmpty(Coordinates.get(0,king.y-1)))&&(boardstate.coordIsEmpty(Coordinates.get(0,king.y+1)))) {
           // System.out.println("--------------2--------------------");
      return king2;
       }  
           }
        
        
        if(boardstate.isLegal( king3)){
         boolean left=true;
         boolean right=true;

         for(int i=1;i<king.x;i++) {
         if(left !=boardstate.coordIsEmpty(Coordinates.get(i,8))){
          left=false;
          break;
         }
         }
            for(int i=king.x+1;i<8;i++) {
             if(right !=boardstate.coordIsEmpty(Coordinates.get(i,8))){
              right=false;
              break;
             }
            }
            
      if((left==true || right == true)&& (boardstate.coordIsEmpty(Coordinates.get(king.x-1,8))) &&  (boardstate.coordIsEmpty(Coordinates.get(king.x+1,8)))) {
         // System.out.println("--------------3--------------------");
          return king3;
         }
         }
        
        if(boardstate.isLegal( king4)){
            boolean left = true;
            boolean right = true;
            
            for(int i=1; i<king.y; i++) {
             if(left !=boardstate.coordIsEmpty(Coordinates.get(8,i))) {
              left = false;
              break;
             }
            }
            for(int i=king.y + 1; i<8; i++) {
             if(left !=boardstate.coordIsEmpty(Coordinates.get(8,i))) {
              left = false;
              break;
             }
            }
       if((left==true || right == true)
    		   &&(boardstate.coordIsEmpty(Coordinates.get(8,king.y-1))) 
    		   &&  (boardstate.coordIsEmpty(Coordinates.get(8,king.y+1)))) {
       return king4;
        }  
      }
                 
    return null;
       }
    
    public TablutMove  kingAtEdge(Coord king, TablutBoardState boardstate, TablutMove m) {
        TablutMove k1=new TablutMove(king,Coordinates.get(0,0),1);
        TablutMove k2=new TablutMove(king,Coordinates.get(8,0),1);

        TablutMove k3=new TablutMove(king,Coordinates.get(0,8),1);

        TablutMove k4=new TablutMove(king,Coordinates.get(8,8),1);

        if(boardstate.isLegal( k1)){
         return k1;
        }
        else if(boardstate.isLegal( k2)){
         return k2;
        }else if(boardstate.isLegal( k3)){
         return k3;
        }else if(boardstate.isLegal( k4)){
        return k4;
        } 
        else {return null;}
         
        
     
       }
    
    

    
    
}
