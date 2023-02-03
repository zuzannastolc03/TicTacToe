import java.util.Random;

public class Game {
    boolean whose_turn;
    boolean is_running;
    boolean draw;
    int[][] fields = new int[3][3];
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
    public void check_winner(){
        if((fields[0][0] == fields[0][1] && fields[0][0] == fields[0][2] && fields[0][0] != 0) ||
                (fields[1][0] == fields[1][1] && fields[1][0] == fields[1][2] && fields[1][0] != 0) ||
                (fields[2][0] == fields[2][1] && fields[2][0] == fields[2][2] && fields[2][0] != 0) ||
                (fields[0][0] == fields[1][0] && fields[0][0] == fields[2][0] && fields[0][0] != 0) ||
                (fields[0][1] == fields[1][1] && fields[0][1] == fields[2][1] && fields[0][1] != 0) ||
                (fields[0][2] == fields[1][2] && fields[0][2] == fields[2][2] && fields[0][2] != 0) ||
                (fields[0][0] == fields[1][1] && fields[0][0] == fields[2][2] && fields[0][0] != 0) ||
                (fields[0][2] == fields[1][1] && fields[0][2] == fields[2][0] && fields[0][2] != 0)){
            is_running = false;
        }
    }
    public void update(int i, int j){
        if (whose_turn){
            fields[i][j] = 1;
        } else if (!whose_turn){
            fields[i][j] = 2;
        }
        check_winner();
        draw = check_draw(is_running);
        whose_turn = !whose_turn;
    }
    public boolean check_draw(boolean runs){
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
}
