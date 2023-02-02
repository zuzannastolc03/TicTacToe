import java.util.Random;

public class Game {
    boolean whose_turn;
    boolean is_running = false;
    int[] fields;
    final static int number_of_fields = 9;
    Game(){
        is_running = true;
        whose_turn = pick_who_starts();
        fields = new int[number_of_fields];
        for(int i=0; i<number_of_fields; i++){
            fields[i] = 0;
        }
    }
    public boolean pick_who_starts(){
        Random random = new Random();
        return random.nextBoolean();
    }
    public void check_winner(){
        if((fields[0] == fields[1] && fields[0] == fields[2] && fields[0] != 0) ||
                (fields[3] == fields[4] && fields[3] == fields[5] && fields[3] != 0) ||
                (fields[6] == fields[7] && fields[6] == fields[8] && fields[6] != 0) ||
                (fields[0] == fields[3] && fields[0] == fields[6] && fields[0] != 0) ||
                (fields[1] == fields[4] && fields[1] == fields[7] && fields[1] != 0) ||
                (fields[2] == fields[5] && fields[2] == fields[8] && fields[2] != 0) ||
                (fields[0] == fields[4] && fields[0] == fields[8] && fields[0] != 0) ||
                (fields[2] == fields[4] && fields[2] == fields[6] && fields[2] != 0)){
            is_running = false;
        }
    }
    public void update(int i){
        if (whose_turn){
            fields[i] = 1;
        } else if (!whose_turn){
            fields[i] = 2;
        }
        check_winner();
        whose_turn = !whose_turn;
    }
}
