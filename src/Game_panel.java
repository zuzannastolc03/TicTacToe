import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game_panel extends Panel_settings{
    final static int number_of_fields = 9;
    JButton[] fields;
    int[] x_coordinates = new int[3];
    int[] y_coordinates = new int[3];
    Label_settings label;
    Color player1_color = Color.orange;
    Color player2_color = new Color(0, 150, 200);
    Game_panel(boolean whose_turn, boolean is_running){
        for(int i=0; i<x_coordinates.length; i++){
            x_coordinates[i] = (int)(width-3*120)/2 + i*120;
        }
        for(int i=0; i<y_coordinates.length; i++){
            y_coordinates[i] = (int)(height-3*120)/2 + i*120;
        }
        label = new Label_settings("");
        update_label(whose_turn, is_running);
        label.setLocation((int)(width-label.getWidth())/2, (int)(y_coordinates[0]-label.getHeight())/2);
        this.add(label);

        fields = new JButton[number_of_fields];

        for(int i=0; i<number_of_fields; i++){
            fields[i] = new JButton();
            fields[i].setBounds(x_coordinates[i%3], y_coordinates[(int)(i/3)], 120, 120);
            fields[i].setBackground(Color.WHITE);
            fields[i].setFont(new Font("Cuckoo", Font.BOLD, 100));
            this.add(fields[i]);
        }
    }
    public void update_label(boolean whose_turn, boolean is_running){
        if(whose_turn && is_running){
            label.setForeground(player1_color);
            label.setText("Turn: O");
        } else if (!whose_turn && is_running) {
            label.setForeground(player2_color);
            label.setText("Turn: X" );
        }
        else if (!whose_turn && !is_running) {
            label.setForeground(player1_color);
            label.setText("O wins");
        }
        else if (whose_turn && !is_running){
            label.setForeground(player2_color);
            label.setText("Turn: X");
        }
    }
    public void update_fields(boolean whose_turn, int i){
        if (!whose_turn) {
            fields[i].setForeground(player1_color);
            fields[i].setText("O");
            UIManager.put("Button.disabledText", (player1_color));
        } else {
            fields[i].setForeground(player2_color);
            fields[i].setText("X");
            UIManager.put("Button.disabledText", (player2_color));
        }
        fields[i].setEnabled(false);
        fields[i].setFocusable(false);
    }
    public void game_over(){
        for (JButton field : fields){
            field.setEnabled(false);
        }
    }


}
