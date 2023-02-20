import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
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
    int final_answer;
    // First panel constructor
    Panel(){
        standard_settings();
        one_player = new Button("One player");
        one_player.setLocation((int)(width-2*one_player.getWidth())/3, (int)(height-one_player.getHeight())/2);
        this.add(one_player);

        two_players = new Button("Two players");
        two_players.setLocation((int)(width-2*two_players.getWidth())/3*2+two_players.getWidth(), (int)(height-two_players.getHeight())/2);
        this.add(two_players);

        label = new Label("Welcome to TicTacToe!");
        label.setLocation((int)(width-label.getWidth())/2, ((int)(height-3*120)/2-label.getHeight())/2);
        this.add(label);
    }
    // Second panel constructor
    Panel(boolean whose_turn, boolean is_running, boolean draw){
        standard_settings();
        restart = new Button("Restart");
        restart.setLocation((int)(width-restart.getWidth())/2, (int)(height-restart.getHeight())/2);
        restart.setVisible(false);
        this.add(restart);

        for(int i=0; i<x_coordinates.length; i++){
            x_coordinates[i] = (int)(width-3*120)/2 + i*120;
        }
        for(int i=0; i<y_coordinates.length; i++){
            y_coordinates[i] = (int)(height-3*120)/2 + i*120;
        }
        label = new Label("");
        update_label(whose_turn, is_running, draw);
        label.setLocation((int)(width-label.getWidth())/2, (int)(y_coordinates[0]-label.getHeight())/2);
        this.add(label);

        fields = new JButton[3][3];

        for(int i=0; i<x_coordinates.length; i++){
            for(int j=0; j<y_coordinates.length; j++){
                fields[i][j] = new JButton();
                fields[i][j].setBounds(x_coordinates[i], y_coordinates[j], 120, 120);
                fields[i][j].setBackground(Color.WHITE);
                fields[i][j].setFont(new Font("Cuckoo", Font.BOLD, 100));
                this.add(fields[i][j]);
            }
        }
    }
    // Standard settings for both panels
    public void standard_settings(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(220, 230, 250));
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
    public void update_fields(boolean whose_turn, boolean is_running, int i, int j){
        UIManager.put("Button.disabledText", (Color.GRAY));
        if (!whose_turn) {
            fields[i][j].setForeground(player1_color);
            fields[i][j].setText("O");
            UIManager.put("Button.disabledText", (player1_color));
        } else {
            fields[i][j].setForeground(player2_color);
            fields[i][j].setText("X");
            UIManager.put("Button.disabledText", (player2_color));
        }
        fields[i][j].setEnabled(false);
        fields[i][j].setFocusable(false);
        if(!is_running) {
            for (JButton[] field : fields) {
                for (int l = 0; l < fields[0].length; l++) {
                    field[l].setEnabled(false);
                    UIManager.put("Button.disabledText", (Color.GRAY));
                    restart.setVisible(true);
                }
            }
        }
    }
    public void update(boolean whose_turn, boolean is_running, boolean draw, int i, int j){
        update_label(whose_turn, is_running, draw);
        update_fields(whose_turn, is_running, i, j);
    }
}
