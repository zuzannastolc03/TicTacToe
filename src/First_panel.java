import javax.swing.*;
import java.awt.*;

public class First_panel extends Panel_settings{
    JButton one_player;
    JButton two_players;
    JLabel label;
    First_panel(){
        one_player = new JButton("One player");
        one_player.setBounds((int)(width-2*200)/3, (int)(height-100)/2, 200, 100);
        one_player.setBackground(Color.WHITE);
        one_player.setFont(new Font("Cuckoo", Font.PLAIN, 20));
        one_player.setFocusable(false);
        this.add(one_player);

        two_players = new JButton("Two players");
        two_players.setBounds((int)(width-2*200)/3*2+200, (int)(height-100)/2, 200, 100);
        two_players.setBackground(Color.WHITE);
        two_players.setFont(new Font("Cuckoo", Font.PLAIN, 20));
        two_players.setFocusable(false);
        this.add(two_players);

        label = new JLabel("Welcome to TicTacToe!");
        label.setBounds((int)(width-400)/2, (int)((int)(height-3*120)/2-50)/2, 400, 50);
        label.setFont(new Font("Cuckoo", Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);
    }
}
