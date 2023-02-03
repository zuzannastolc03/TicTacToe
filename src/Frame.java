import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame implements ActionListener{
    Panel first_panel;
    Panel game_panel;
    JPanel cards;
    CardLayout c1;
    Game game;
    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("TicTacToe");
        this.setIconImage((new ImageIcon("tictactoe.png")).getImage());

        game = new Game();
        c1 = new CardLayout();
        cards = new JPanel();
        first_panel = new Panel();
        game_panel = new Panel(game.whose_turn, game.is_running, game.draw);

        cards.setLayout(c1);
        cards.add(first_panel, "First panel");
        cards.add(game_panel, "Game panel");
        c1.show(cards, "First panel");

        first_panel.one_player.addActionListener(this);
        first_panel.two_players.addActionListener(this);

        for(int i=0; i<game_panel.fields.length; i++){
            for(int j=0; j<game_panel.fields[0].length; j++){
                game_panel.fields[i][j].addActionListener(this);
            }
        }

        this.add(cards);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == first_panel.one_player){
            c1.show(cards, "Game panel");
        } else if (e.getSource() == first_panel.two_players) {
            c1.show(cards, "Game panel");
        }
        else {
            for(int i=0; i<game_panel.fields.length; i++){
                for(int j=0; j<game_panel.fields[0].length; j++){
                    if (e.getSource() == game_panel.fields[i][j]) {
                        game.update(i, j);
                        game_panel.update(game.whose_turn, game.is_running, game.draw, i, j);
                    }
                }
            }
        }
    }
}
