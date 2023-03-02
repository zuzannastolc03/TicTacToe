import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;


public class Frame extends JFrame implements ActionListener{
    Pane firstPanel;
    Pane gamePanel;
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
        firstPanel = new Pane();

        cards.setLayout(c1);
        cards.add(firstPanel, "First panel");
        newGame();

        firstPanel.onePlayerButton.addActionListener(this);
        firstPanel.twoPlayersButton.addActionListener(this);

        this.add(cards);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void newGame(){
        game = new Game();
        gamePanel = new Pane(game.OTurn, game.isRunning, game.draw);
        gamePanel.restart.addActionListener(this);
        for(int i=0; i<gamePanel.fields.length; i++){
            for(int j=0; j<gamePanel.fields[0].length; j++){
                gamePanel.fields[i][j].addActionListener(this);
            }
        }
        cards.add(gamePanel, "Game panel");
        c1.show(cards, "First panel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == firstPanel.onePlayerButton){
            game.numberOfPlayers = false;
            c1.show(cards, "Game panel");
        } else if (e.getSource() == firstPanel.twoPlayersButton) {
            game.numberOfPlayers = true;
            c1.show(cards, "Game panel");
        } else if(e.getSource() == gamePanel.restart){
            newGame();
        } else {
            for(int i=0; i<gamePanel.fields.length; i++){
                for(int j=0; j<gamePanel.fields[0].length; j++){
                    if (e.getSource() == gamePanel.fields[i][j]) {
                        game.update(i, j);
                        gamePanel.update(game.OTurn, game.isRunning, game.draw, i, j, game.typeOfWin);
                        if(!game.numberOfPlayers && game.isRunning){
                            Point2D k = game.computersShot();
                            int x = (int)k.getX();
                            int y = (int)k.getY();
                            if(x<=2 && y<=2){
                                game.update(x, y);
                                gamePanel.update(game.OTurn, game.isRunning, game.draw, x, y, game.typeOfWin);
                            }
                        }
                    }
                }
            }
        }
    }
}
