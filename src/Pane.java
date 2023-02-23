import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class Pane extends JLayeredPane {
    final static int width = 640;
    final static int height = 480;
    Button one_player;
    Button two_players;
    Button restart;
    Label label;
    JButton[][] fields;
    int[] x_coordinates = new int[3];
    int[] y_coordinates = new int[3];
    final static Color player1_color = Color.orange;
    final static Color player2_color = new Color(0, 150, 200);
    final static Color game_over_field_color = Color.lightGray;
    // First panel constructor
    Pane(){
        standard_settings();
        one_player = new Button("One player");
        one_player.setLocation((int)(width-2*one_player.getWidth())/3, (int)(height-one_player.getHeight())/2);
        one_player.setOpaque(true);
        this.add(one_player);

        two_players = new Button("Two players");
        two_players.setLocation((int)(width-2*two_players.getWidth())/3*2+two_players.getWidth(), (int)(height-two_players.getHeight())/2);
        two_players.setOpaque(true);
        this.add(two_players);

        label = new Label("Welcome to TicTacToe!");
        label.setLocation((int)(width-label.getWidth())/2, ((int)(height-3*120)/2-label.getHeight())/2);
        this.add(label);
    }
    // Second panel constructor
    Pane(boolean whose_turn, boolean is_running, boolean draw){
        standard_settings();
        restart = new Button("Restart");
        restart.setLocation((int)(width-restart.getWidth())/2, (int)(height-restart.getHeight())/2);
        restart.setOpaque(true);
//        this.add(restart, Integer.valueOf(0));

        for(int i=0; i<x_coordinates.length; i++){
            x_coordinates[i] = (int)(width-3*120)/2 + i*120;
        }
        for(int i=0; i<y_coordinates.length; i++){
            y_coordinates[i] = (int)(height-3*120)/2 + i*120;
        }
        label = new Label("");
        update_label(whose_turn, is_running, draw);
        label.setLocation((int)(width-label.getWidth())/2, (int)(y_coordinates[0]-label.getHeight())/2);
        this.add(label, Integer.valueOf(1));

        fields = new JButton[3][3];

        for(int i=0; i<x_coordinates.length; i++){
            for(int j=0; j<y_coordinates.length; j++){
                fields[i][j] = new JButton();
                fields[i][j].setBounds(x_coordinates[i], y_coordinates[j], 120, 120);
                fields[i][j].setBackground(Color.WHITE);
                fields[i][j].setFont(new Font("Cuckoo", Font.BOLD, 100));
                fields[i][j].setOpaque(true);
                this.add(fields[i][j], Integer.valueOf(1));
            }
        }
    }
    // Standard settings for both panels
    public void standard_settings(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(220, 230, 250));
        this.setOpaque(true);
        this.setLayout(null);
    }
    public void update_label(boolean whose_turn, boolean is_running, boolean draw){
        if(whose_turn && is_running && !draw){
            label.setForeground(player1_color);
            label.setText("Turn: O");
        } else if (!whose_turn && is_running && !draw) {
            label.setForeground(player2_color);
            label.setText("Turn: X" );
        }
        else if (!whose_turn && !is_running && !draw) {
            label.setForeground(player1_color);
            label.setText("O wins");
        }
        else if (whose_turn && !is_running && !draw){
            label.setForeground(player2_color);
            label.setText("X wins");
        } else if (!is_running && draw) {
            label.setForeground(Color.BLACK);
            label.setText("It's a draw!");
        }
        else {
            label.setForeground(Color.BLACK);
            label.setText("Unpredicted situation!");
        }
    }
    public void update_fields(boolean whose_turn, int i, int j){
        if (!whose_turn) {
            fields[i][j].setText("O");
            fields[i][j].setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return player1_color;
                }
            });
        } else if (whose_turn) {
            fields[i][j].setText("X");
            fields[i][j].setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return player2_color;
                }
            });
        }
        fields[i][j].setEnabled(false);
        fields[i][j].setFocusable(false);
    }
    public void game_over(boolean is_running, int type_of_win){
        if(!is_running) {
            for (JButton[] field : fields) {
                for (int l = 0; l < fields[0].length; l++) {
                    if(field[l].isEnabled()){
                        field[l].setEnabled(false);
                    }
                }
            }
            switch (type_of_win) {
                case 1 -> {
                    fields[0][0].setBackground(game_over_field_color);
                    fields[0][1].setBackground(game_over_field_color);
                    fields[0][2].setBackground(game_over_field_color);
                }
                case 2 -> {
                    fields[1][0].setBackground(game_over_field_color);
                    fields[1][1].setBackground(game_over_field_color);
                    fields[1][2].setBackground(game_over_field_color);
                }
                case 3 -> {
                    fields[2][0].setBackground(game_over_field_color);
                    fields[2][1].setBackground(game_over_field_color);
                    fields[2][2].setBackground(game_over_field_color);
                }
                case 4 -> {
                    fields[0][0].setBackground(game_over_field_color);
                    fields[1][0].setBackground(game_over_field_color);
                    fields[2][0].setBackground(game_over_field_color);
                }
                case 5 -> {
                    fields[0][1].setBackground(game_over_field_color);
                    fields[1][1].setBackground(game_over_field_color);
                    fields[2][1].setBackground(game_over_field_color);
                }
                case 6 -> {
                    fields[0][2].setBackground(game_over_field_color);
                    fields[1][2].setBackground(game_over_field_color);
                    fields[2][2].setBackground(game_over_field_color);
                }
                case 7 -> {
                    fields[0][0].setBackground(game_over_field_color);
                    fields[1][1].setBackground(game_over_field_color);
                    fields[2][2].setBackground(game_over_field_color);
                }
                case 8 -> {
                    fields[2][0].setBackground(game_over_field_color);
                    fields[1][1].setBackground(game_over_field_color);
                    fields[0][2].setBackground(game_over_field_color);
                }
            }
            this.add(restart, Integer.valueOf(2));
        }
    }
    public void update(boolean whose_turn, boolean is_running, boolean draw, int i, int j, int type_of_win){
        update_label(whose_turn, is_running, draw);
        update_fields(whose_turn, i, j);
        game_over(is_running, type_of_win);
    }
}
