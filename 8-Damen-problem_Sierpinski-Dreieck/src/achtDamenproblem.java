import java.util.Arrays;

/**
* @author Ali Can Gürbüz
*
*/
public class achtDamenproblem {
    public static void main(String[] args) {
        spielfeld spielfeld = new spielfeld();
        spielfeld.start();
    }

}
class spielfeld{
    private static int board[][];
    private int numQueens;
    public spielfeld(){
        numQueens = 0;
        board = new int[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i] [j] = 0;
            }
        }
    }

    public void start(){
        printBoard();
        solve(0);

    }
    public boolean solve(int numQueens){
        if (numQueens == 8){
            System.out.println("Done");
            this.printBoard();
            return true;
        } else {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if(validMove(i,j) == 0){
                        this.placeQueen(i,j,0);
                        numQueens++;
                        if (solve(numQueens)){
                            return true;
                        } else {
                            this.placeQueen(i,j,1);
                            numQueens--;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int validMove(int x, int y){
        for (int i = 0; i < board.length; i++) {
            if (get(x, i) == 1) {
                return -1;
            }
            if (get(i, y) == 1) {
                return -1;
            }
        }
            for (int i = 0; i < 8; i++) {

                if (get(x-i,y-i) == 1){
                    return -1;
                }
                if (get(x-i,y+i) == 1){
                    return -1;
                }
                if (get(x+i,y-i) == 1){
                    return -1;
                }
                if (get(x+i,y+i) == 1){
                    return -1;
                }
            }
        return 0;
    }

    public int getNumQueens(){
        return numQueens;
    }

    public int placeQueen(int x, int y, int type){
        if (type == 0){
            board[x][y] = 1;
            numQueens++;
            return 0;
        } else if ( type == 1){
            board[x][y] = 0;
            return 0;
        }
        System.out.println("place Queen error ");
        return -3;
    }

    public static int get(int x, int j){
        if (x < 0 || j < 0 || x > 7 || j > 7){
            //System.out.println(x + " oder " + j + " ist falsch ");
            return -1;
        }
        return board[x][j];
    }
    public void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(this.get(i,j) + " ");
            }
            System.out.println("");
        }
    }
}

