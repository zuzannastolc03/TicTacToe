import java.awt.geom.Point2D;
import java.util.*;

public class Game {
    boolean OTurn;
    boolean isRunning;
    HashMap<String, Integer> typeOfWin = new HashMap<>();
    boolean draw;
    int[][] fields;
    boolean numberOfPlayers;
    Game(int SIZE_OF_BOARD){
        fields = new int[SIZE_OF_BOARD][SIZE_OF_BOARD];
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
    public HashMap<String, Integer> checkWinner(int SIZE_OF_BOARD){
        HashMap<String, Integer> localTypeOfWin = new HashMap<>();
        for(int i = 0; i<SIZE_OF_BOARD; i++){
            HashSet<Integer> horizontal = new HashSet<>();
            for(int j = 0; j<SIZE_OF_BOARD; j++){
                horizontal.add(fields[i][j]);
            }
            if(horizontal.size() == 1 && !horizontal.contains(0)){
                localTypeOfWin.put("horizontal", i);
                isRunning = false;
            }
        }
        for(int i = 0; i<SIZE_OF_BOARD; i++){
            HashSet<Integer> vertical = new HashSet<>();
            for(int j = 0; j<SIZE_OF_BOARD; j++){
                vertical.add(fields[j][i]);
            }
            if(vertical.size() == 1 && !vertical.contains(0)){
                localTypeOfWin.put("vertical", i);
                isRunning = false;
            }
        }
        HashSet<Integer> diagonal1 = new HashSet<>();
        for(int j = 0; j<SIZE_OF_BOARD; j++){
            diagonal1.add(fields[j][j]);
        }
        if(diagonal1.size() == 1 && !diagonal1.contains(0)){
            localTypeOfWin.put("diagonal1", 0);
            isRunning = false;
        }
        HashSet<Integer> diagonal2 = new HashSet<>();
        for(int j = 0; j<SIZE_OF_BOARD; j++){
            diagonal2.add(fields[j][SIZE_OF_BOARD - 1 - j]);
        }
        if(diagonal2.size() == 1 && !diagonal2.contains(0)){
            localTypeOfWin.put("diagonal2", 0);
            isRunning = false;
        }
        return localTypeOfWin;
    }
    public void update(int i, int j, int SIZE_OF_BOARD){
        if (OTurn){
            fields[i][j] = 1;
        } else if (!OTurn){
            fields[i][j] = 2;
        }
        typeOfWin = checkWinner(SIZE_OF_BOARD);
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
            return new Point2D.Double(-1,-1);
        }
    }
}
