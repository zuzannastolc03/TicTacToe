import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    boolean whose_turn;
    public boolean is_running;
    boolean draw;
    int[][] fields = new int[3][3];
    int type_of_win = 0;
    boolean number_of_players;
    Game(){
        is_running = true;
        draw = false;
        whose_turn = pick_who_starts();
        for(int i=0; i<fields.length; i++){
            for(int j=0; j<fields[0].length; j++){
                fields[i][j] = 0;
            }
        }
    }
    public boolean pick_who_starts(){
        Random random = new Random();
        return random.nextBoolean();
    }
    public int check_winner(){
        if(fields[0][0] == fields[0][1] && fields[0][0] == fields[0][2] && fields[0][0] != 0){
            is_running = false;
            type_of_win = 1;
        } else if (fields[1][0] == fields[1][1] && fields[1][0] == fields[1][2] && fields[1][0] != 0) {
            is_running = false;
            type_of_win = 2;
        } else if (fields[2][0] == fields[2][1] && fields[2][0] == fields[2][2] && fields[2][0] != 0) {
            is_running = false;
            type_of_win = 3;
        } else if (fields[0][0] == fields[1][0] && fields[0][0] == fields[2][0] && fields[0][0] != 0) {
            is_running = false;
            type_of_win = 4;
        } else if (fields[0][1] == fields[1][1] && fields[0][1] == fields[2][1] && fields[0][1] != 0) {
            is_running = false;
            type_of_win = 5;
        } else if (fields[0][2] == fields[1][2] && fields[0][2] == fields[2][2] && fields[0][2] != 0) {
            is_running = false;
            type_of_win = 6;
        } else if (fields[0][0] == fields[1][1] && fields[0][0] == fields[2][2] && fields[0][0] != 0) {
            is_running = false;
            type_of_win = 7;
        } else if (fields[0][2] == fields[1][1] && fields[0][2] == fields[2][0] && fields[0][2] != 0) {
            is_running = false;
            type_of_win = 8;
        }
//        System.out.println(is_running);
        return type_of_win;
    }
    public void update(int i, int j){
        if (whose_turn){
            fields[i][j] = 1;
        } else if (!whose_turn){
            fields[i][j] = 2;
        }
        check_winner();
        draw = check_draw();
        whose_turn = !whose_turn;
    }
    public boolean check_draw(){
        if(is_running){
            for(int i=0; i<fields.length; i++){
                for(int j=0; j<fields[0].length; j++){
                    if(fields[i][j] == 0){
                        return false;
                    }
                }
            }
            is_running = false;
            return true;
        }
        else {
            return false;
        }
    }
    public Point2D computers_shot(){
        List<Point2D> temp_list = new ArrayList<>();
        for(int i=0; i<fields.length; i++){
            for(int j=0; j<fields[0].length; j++){
                if(fields[i][j]==0){
                    Point2D p = new Point2D.Double(i,j);
                    temp_list.add(p);
                }
            }
        }
        if(!temp_list.isEmpty()){
            Random random = new Random();
            Point2D k;
            k = temp_list.get(random.nextInt(temp_list.size()));
            return k;
        }
        else {
            Point2D k = new Point2D.Double(3,3);
            return k;
        }
    }
}
