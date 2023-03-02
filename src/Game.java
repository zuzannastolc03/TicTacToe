import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    boolean OTurn;
    boolean isRunning;
    boolean draw;
    int[][] fields = new int[3][3];
    int typeOfWin = 0;
    boolean numberOfPlayers;
    Game(){
        isRunning = true;
        draw = false;
        OTurn = pickWhoStarts();
        for(int i=0; i<fields.length; i++){
            for(int j=0; j<fields[0].length; j++){
                fields[i][j] = 0;
            }
        }
    }
    public boolean pickWhoStarts(){
        Random random = new Random();
        return random.nextBoolean();
    }
    public int checkWinner(){
        if(fields[0][0] == fields[0][1] && fields[0][0] == fields[0][2] && fields[0][0] != 0){
            isRunning = false;
            return 1;
        } else if (fields[1][0] == fields[1][1] && fields[1][0] == fields[1][2] && fields[1][0] != 0) {
            isRunning = false;
            return 2;
        } else if (fields[2][0] == fields[2][1] && fields[2][0] == fields[2][2] && fields[2][0] != 0) {
            isRunning = false;
            return 3;
        } else if (fields[0][0] == fields[1][0] && fields[0][0] == fields[2][0] && fields[0][0] != 0) {
            isRunning = false;
            return 4;
        } else if (fields[0][1] == fields[1][1] && fields[0][1] == fields[2][1] && fields[0][1] != 0) {
            isRunning = false;
            return 5;
        } else if (fields[0][2] == fields[1][2] && fields[0][2] == fields[2][2] && fields[0][2] != 0) {
            isRunning = false;
            return 6;
        } else if (fields[0][0] == fields[1][1] && fields[0][0] == fields[2][2] && fields[0][0] != 0) {
            isRunning = false;
            return 7;
        } else if (fields[0][2] == fields[1][1] && fields[0][2] == fields[2][0] && fields[0][2] != 0) {
            isRunning = false;
            return 8;
        }
        else {
            return 0;
        }
    }
    public void update(int i, int j){
        if (OTurn){
            fields[i][j] = 1;
        } else if (!OTurn){
            fields[i][j] = 2;
        }
        typeOfWin = checkWinner();
        draw = checkDraw();
        OTurn = !OTurn;
    }
    public boolean checkDraw(){
        if(isRunning){
            for(int i=0; i<fields.length; i++){
                for(int j=0; j<fields[0].length; j++){
                    if(fields[i][j] == 0){
                        return false;
                    }
                }
            }
            isRunning = false;
            return true;
        }
        else {
            return false;
        }
    }
    public Point2D computersShot(){
        List<Point2D> tempList = new ArrayList<>();
        for(int i=0; i<fields.length; i++){
            for(int j=0; j<fields[0].length; j++){
                if(fields[i][j]==0){
                    Point2D p = new Point2D.Double(i,j);
                    tempList.add(p);
                }
            }
        }
        if(!tempList.isEmpty()){
            Random random = new Random();
            Point2D k;
            k = tempList.get(random.nextInt(tempList.size()));
            return k;
        }
        else {
            return new Point2D.Double(3,3);
        }
    }
}
