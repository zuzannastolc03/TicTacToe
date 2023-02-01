import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game_panel extends Panel_settings{
    final static int number_of_fields = 9;
    JButton[] fields;
    int[] x_coordinates = new int[3];
    int[] y_coordinates = new int[3];
    boolean whose_turn;
    boolean is_running = false;
    JLabel label;
    Color player1_color = Color.orange;
    Color player2_color = new Color(0, 150, 200);
    Game_panel(){
        for(int i=0; i<x_coordinates.length; i++){
            x_coordinates[i] = (int)(width-3*120)/2 + i*120;
        }
        for(int i=0; i<y_coordinates.length; i++){
            y_coordinates[i] = (int)(height-3*120)/2 + i*120;
        }

        label = new JLabel("Turn: O");
        label.setFont(new Font("Cuckoo", Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(player1_color);
        label.setBounds((int)(width-300)/2, (int)(y_coordinates[0]-50)/2, 300, 50);
        this.add(label);

        fields = new JButton[number_of_fields];

        for(int i=0; i<number_of_fields; i++){
            fields[i] = new JButton();
            fields[i].setBounds(x_coordinates[i%3], y_coordinates[(int)(i/3)], 120, 120);
            fields[i].setBackground(Color.WHITE);
            fields[i].setFont(new Font("Cuckoo", Font.BOLD, 100));
//            fields[i].addActionListener(this);
            this.add(fields[i]);
        }

        is_running = true;
        whose_turn = false;
    }
    public void game_over(){
        for (JButton field : fields){
            field.setEnabled(false);
        }
    }
    public void check_winner(){
        if((fields[0].getText().equals(fields[1].getText()) && fields[0].getText().equals(fields[2].getText()) && !fields[0].getText().isEmpty()) ||
                (fields[3].getText().equals(fields[4].getText()) && fields[3].getText().equals(fields[5].getText()) && !fields[3].getText().isEmpty()) ||
                (fields[6].getText().equals(fields[7].getText()) && fields[6].getText().equals(fields[8].getText()) && !fields[6].getText().isEmpty()) ||
                (fields[0].getText().equals(fields[3].getText()) && fields[0].getText().equals(fields[6].getText()) && !fields[0].getText().isEmpty()) ||
                (fields[1].getText().equals(fields[4].getText()) && fields[1].getText().equals(fields[7].getText()) && !fields[1].getText().isEmpty()) ||
                (fields[2].getText().equals(fields[5].getText()) && fields[2].getText().equals(fields[8].getText()) && !fields[2].getText().isEmpty()) ||
                (fields[0].getText().equals(fields[4].getText()) && fields[0].getText().equals(fields[8].getText()) && !fields[0].getText().isEmpty()) ||
                (fields[2].getText().equals(fields[4].getText()) && fields[2].getText().equals(fields[6].getText()) && !fields[2].getText().isEmpty())){
            is_running = false;
        }
    }

    public void update_label(){
        if(!whose_turn && is_running){
            label.setForeground(player2_color);
            label.setText("Turn: X" );
        }
        else if (whose_turn && is_running){
            label.setForeground(player1_color);
            label.setText("Turn: O");
        } else if (!whose_turn && !is_running) {
            label.setForeground(player1_color);
            label.setText("O wins");
        }
        else if (whose_turn && !is_running){
            label.setForeground(player2_color);
            label.setText("Turn: X");
        }
    }
}
