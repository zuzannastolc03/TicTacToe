import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame implements ActionListener{
    First_panel first_panel;
    Game_panel game_panel;
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
        first_panel = new First_panel();
        game_panel = new Game_panel(game.whose_turn, game.is_running);

        cards.setLayout(c1);
        cards.add(first_panel, "First panel");
        cards.add(game_panel, "Game panel");
        c1.show(cards, "First panel");

        first_panel.one_player.addActionListener(this);
        first_panel.two_players.addActionListener(this);

        for(int i=0; i<game_panel.fields.length; i++){
            game_panel.fields[i].addActionListener(this);
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
                if (e.getSource() == game_panel.fields[i]) {
                    game.update(i);
                    game_panel.update_label(game.whose_turn, game.is_running);
                    game_panel.update_fields(game.whose_turn, i);
//                    if (!game_panel.whose_turn) {
//                        game_panel.fields[i].setForeground(game_panel.player1_color);
//                        game_panel.fields[i].setText("O");
//                        UIManager.put("Button.disabledText", (game_panel.player1_color));
//                    } else {
//                        game_panel.fields[i].setForeground(game_panel.player2_color);
//                        game_panel.fields[i].setText("X");
//                        UIManager.put("Button.disabledText", (game_panel.player2_color));
//                    }
//                    game_panel.fields[i].setEnabled(false);
//                    game_panel.fields[i].setFocusable(false);
//                    if(!game_panel.is_running){
//                        game_panel.game_over();
//                    }
//                    game_panel.whose_turn = !game_panel.whose_turn;
                }
            }
        }
    }
}
