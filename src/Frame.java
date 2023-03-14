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
    int sizeOfBoard = 3;
    final static int MAP_DIMENSION = 360;
    int fieldSize = MAP_DIMENSION/sizeOfBoard;
    Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("TicTacToe");
        this.setIconImage((new ImageIcon("tictactoe.png")).getImage());

        c1 = new CardLayout();
        cards = new JPanel();
        firstPanel = new Pane(fieldSize);

        cards.setLayout(c1);
        cards.add(firstPanel, "First panel");
        newGame(fieldSize);

        firstPanel.onePlayerButton.addActionListener(this);
        firstPanel.twoPlayersButton.addActionListener(this);
        firstPanel.threeFieldsButton.addActionListener(this);
        firstPanel.fourFieldsButton.addActionListener(this);
        firstPanel.fiveFieldsButton.addActionListener(this);

        this.add(cards);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void newGame(int fieldSize){
        game = new Game(sizeOfBoard);
        gamePanel = new Pane(game.OTurn, game.isRunning, game.draw, sizeOfBoard, fieldSize);
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
            newGame(fieldSize);
        } else if (e.getSource() == firstPanel.threeFieldsButton) {
            sizeOfBoard = 3;
            fieldSize = MAP_DIMENSION/sizeOfBoard;
            newGame(fieldSize);
        } else if (e.getSource() == firstPanel.fourFieldsButton) {
            sizeOfBoard = 4;
            fieldSize = MAP_DIMENSION/sizeOfBoard;
            newGame(fieldSize);
        } else if (e.getSource() == firstPanel.fiveFieldsButton) {
            sizeOfBoard = 5;
            fieldSize = MAP_DIMENSION/sizeOfBoard;
            newGame(fieldSize);
        } else {
            for(int i=0; i<gamePanel.fields.length; i++){
                for(int j=0; j<gamePanel.fields[0].length; j++){
                    if (e.getSource() == gamePanel.fields[i][j]) {
                        game.update(i, j, sizeOfBoard);
                        gamePanel.update(game.OTurn, game.isRunning, game.draw, i, j, game.typeOfWin, sizeOfBoard);
                        if(!game.numberOfPlayers && game.isRunning){
                            Point2D k = game.computersShot();
                            int x = (int)k.getX();
                            int y = (int)k.getY();
                            Timer timer = new Timer(200, f -> gamePanel.update(game.OTurn, game.isRunning, game.draw, x, y, game.typeOfWin, sizeOfBoard));
                            timer.setRepeats(false);
                            timer.start();
                            game.update(x, y, sizeOfBoard);
                        }
                    }
                }
            }
        }
    }
}
