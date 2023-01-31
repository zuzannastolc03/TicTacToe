import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame implements ActionListener{
    public final static int width = 640;
    public final static int height = 480;
    First_panel first_panel;
    Game_panel game_panel;
    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("TicTacToe");
        this.setIconImage((new ImageIcon("tictactoe.png")).getImage());

        first_panel = new First_panel();
        this.add(first_panel);
        first_panel.one_player.addActionListener(this);
        first_panel.two_players.addActionListener(this);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void create_game_panel(){
        game_panel = new Game_panel();
        this.add(game_panel);
        first_panel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((e.getSource()==first_panel.one_player || e.getSource() == first_panel.two_players)) {
            create_game_panel();
            first_panel.setVisible(false);
        }
    }
}
