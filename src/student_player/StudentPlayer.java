package student_player;

import java.util.ArrayList;
import java.util.HashMap;
import student_player.MyTools;
import boardgame.Move;
import boardgame.Player;
import coordinates.Coord;
import coordinates.Coordinates;
import tablut.TablutBoardState;
import tablut.TablutMove;
import tablut.TablutPlayer;

/** A player file submitted by a student. */
public class StudentPlayer extends TablutPlayer {

 public static void main(String[] args) { 
	    Player muscovite = new StudentPlayer();
	    Player player = muscovite;     
     
	    TablutBoardState b = new TablutBoardState();
        muscovite.setColor(TablutBoardState.MUSCOVITE);
       
        Player swede = new StudentPlayer();
        swede.setColor(TablutBoardState.SWEDE);


        while (!b.gameOver()) {
            Move m = player.chooseMove(b);
            b.processMove((TablutMove) m);
            player = (player == muscovite) ? swede : muscovite;
            System.out.println("\nMOVE PLAYED: " + m.toPrettyString());
            b.printBoard();
        }
        System.out.println(TablutMove.getPlayerName(b.getWinner()) + " WIN!");
    }
    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
 
    public StudentPlayer() {
        super("260670415");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
 
    public Move chooseMove(TablutBoardState boardState) {
        
        int max=0;
        int min=10000000;
        TablutMove max_move = (TablutMove) boardState.getRandomMove();
        TablutMove min_move = (TablutMove) boardState.getRandomMove();
    
           HashMap<TablutMove, Integer> h = new HashMap<TablutMove, Integer>();
           for(TablutMove t: boardState.getAllLegalMoves()) {
            
           TablutBoardState cloneBS = (TablutBoardState) boardState.clone();
           cloneBS.processMove(t);
      
           double alpha =-100000;
           double beta = 100000;
           int a = 0;
           if(this.player_id == 0) {
           a = (int)minmaxForAll(cloneBS, 2,t,alpha,beta);
           }
           
           if(this.player_id == 1) {
            a = (int)minmaxForAll(cloneBS, 2,t,alpha,beta); 
           }
            h.put(t, a);
           }
           
           for(TablutMove t: h.keySet()) {
               if(h.get(t)>max) {
                
                max = h.get(t);
                max_move = t;
     
               }else if(h.get(t)<min){
                min = h.get(t);
                min_move = t;
               }
              }
           
           if(this.player_id==0) {//black player

               return max_move;
              }
           else {
                if (null==kingAtEdge(boardState.getKingPosition(),boardState,min_move)) {
                  return min_move;
                }
                else {
             //    System.out.println("run kingAtEdge");
                 return kingAtEdge(boardState.getKingPosition(),boardState,min_move);
               }
              }

       }    
    
    public double minmaxForAll(TablutBoardState boardState, int depth,TablutMove prev_move, double alpha, double beta) {
        double bestValue;
        
        if(boardState.gameOver()){
         if(boardState.getWinner()==1){//white wins
          return -99999;
         }else{
          return 10000000;
         }
        }
        
        if (depth==0) {
        	 bestValue = evaluate(this.player_id,boardState); 
             return bestValue;
        }

        
        if(boardState.getTurnPlayer()==0) {//black
         double v = -99999;
         ArrayList<TablutMove> moves = boardState.getAllLegalMoves();

         for(TablutMove t: moves) {
          TablutBoardState cloneBS = (TablutBoardState) boardState.clone();
          cloneBS.processMove(t);
          v = minmaxForAll(cloneBS, depth-1,t,alpha,beta);
          
          alpha = Math.max(alpha, v);
               if(beta<=alpha) { 
            	   break; }

         }
         return v;
         
        }else {
         double v = 100000;
    
         ArrayList<TablutMove> moves = boardState.getAllLegalMoves();
        // int a = boardState.getNumberPlayerPieces(1);

         for(TablutMove t: moves) {
             TablutBoardState cloneBS = (TablutBoardState) boardState.clone();
             
             cloneBS.processMove(t);
             v =minmaxForAll(cloneBS, depth-1,t,alpha,beta);            
             beta = Math.min(beta, v);
               if(beta<=alpha) { 
            	   break;  }
         }
         return v;
        }
       }

     public double evaluate(int player,TablutBoardState boardState){
    	 double bestValue=0;
    	 if(player == 0) {//black heuristic
             bestValue = boardState.getNumberPlayerPieces(0)-boardState.getNumberPlayerPieces(1);
           		//  + 0.3*Coordinates.distanceToClosestCorner(boardState.getKingPosition());

             return bestValue;
            }else {//white  heuristic
           	 double corValue =MyTools.closeConer(boardState);
                bestValue = boardState.getNumberPlayerPieces(0) - boardState.getNumberPlayerPieces(1)
              + 0.3*Coordinates.distanceToClosestCorner(boardState.getKingPosition()) - 0.5*corValue ;
             return bestValue;
            }
    	 
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