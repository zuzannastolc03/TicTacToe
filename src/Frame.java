import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;


public class Frame extends JFrame implements ActionListener{
    Pane first_panel;
    Pane game_panel;
    JPanel cards;
    CardLayout c1;
    Game game;
    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("TicTacToe");
        this.setIconImage((new ImageIcon("tictactoe.png")).getImage());

        c1 = new CardLayout();
        cards = new JPanel();
        first_panel = new Pane();

        cards.setLayout(c1);
        cards.add(first_panel, "First panel");
        new_game();

        first_panel.one_player.addActionListener(this);
        first_panel.two_players.addActionListener(this);

        this.add(cards);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void new_game(){
        game = new Game();
        game_panel = new Pane(game.whose_turn, game.is_running, game.draw);
        game_panel.restart.addActionListener(this);
        for(int i=0; i<game_panel.fields.length; i++){
            for(int j=0; j<game_panel.fields[0].length; j++){
                game_panel.fields[i][j].addActionListener(this);
            }
        }
        cards.add(game_panel, "Game panel");
        c1.show(cards, "First panel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == first_panel.one_player){
            game.number_of_players = false;
            c1.show(cards, "Game panel");
        } else if (e.getSource() == first_panel.two_players) {
            game.number_of_players = true;
            c1.show(cards, "Game panel");
        } else if(e.getSource() == game_panel.restart){
            new_game();
        } else {
            for(int i=0; i<game_panel.fields.length; i++){
                for(int j=0; j<game_panel.fields[0].length; j++){
                    if (e.getSource() == game_panel.fields[i][j]) {
                        game.update(i, j);
                        game_panel.update(game.whose_turn, game.is_running, game.draw, i, j, game.type_of_win);
                        if(!game.number_of_players && game.is_running){
                            Point2D k = game.computers_shot();
                            int x = (int)k.getX();
                            int y = (int)k.getY();
                            if(x<=2 && y<=2){
                                game.update(x, y);
                                game_panel.update(game.whose_turn, game.is_running, game.draw, x, y, game.type_of_win);
                            }
                        }
                    }
                }
            }
        }
    }
}
