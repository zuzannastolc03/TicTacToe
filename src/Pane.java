import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class Pane extends JLayeredPane {
    final static int WIDTH = 640;
    final static int HEIGHT = 480;
    Button onePlayer;
    Button twoPlayers;
    Button restart;
    Label label;
    JButton[][] fields;
    int[] xCoordinates = new int[3];
    int[] yCoordinates = new int[3];
    final static Color player1Color = Color.orange;
    final static Color player2Color = new Color(0, 150, 200);
    final static Color gameOverFieldColor = Color.lightGray;
    // First panel constructor
    Pane(){
        standardSettings();
        onePlayer = new Button("One player");
        onePlayer.setLocation((int)(WIDTH-2*onePlayer.getWidth())/3, (int)(HEIGHT-onePlayer.getHeight())/2);
        onePlayer.setOpaque(true);
        this.add(onePlayer);

        twoPlayers = new Button("Two players");
        twoPlayers.setLocation((int)(WIDTH-2*twoPlayers.getWidth())/3*2+twoPlayers.getWidth(), (int)(HEIGHT-twoPlayers.getHeight())/2);
        twoPlayers.setOpaque(true);
        this.add(twoPlayers);

        label = new Label("Welcome to TicTacToe!");
        label.setLocation((int)(WIDTH-label.getWidth())/2, ((int)(HEIGHT-3*120)/2-label.getHeight())/2);
        this.add(label);
    }
    // Second panel constructor
    Pane(boolean whoseTurn, boolean isRunning, boolean draw){
        standardSettings();
        restart = new Button("Restart");
        restart.setLocation((int)(WIDTH-restart.getWidth())/2, (int)(HEIGHT-restart.getHeight())/2);
        restart.setOpaque(true);

        for(int i=0; i<xCoordinates.length; i++){
            xCoordinates[i] = (int)(WIDTH-3*120)/2 + i*120;
        }
        for(int i=0; i<yCoordinates.length; i++){
            yCoordinates[i] = (int)(HEIGHT-3*120)/2 + i*120;
        }
        label = new Label("");
        updateLabel(whoseTurn, isRunning, draw);
        label.setLocation((int)(WIDTH-label.getWidth())/2, (int)(yCoordinates[0]-label.getHeight())/2);
        this.add(label, Integer.valueOf(1));

        fields = new JButton[3][3];

        for(int i=0; i<xCoordinates.length; i++){
            for(int j=0; j<yCoordinates.length; j++){
                fields[i][j] = new JButton();
                fields[i][j].setBounds(xCoordinates[i], yCoordinates[j], 120, 120);
                fields[i][j].setBackground(Color.WHITE);
                fields[i][j].setFont(new Font("Cuckoo", Font.BOLD, 100));
                fields[i][j].setOpaque(true);
                this.add(fields[i][j], Integer.valueOf(1));
            }
        }
    }
    // Standard settings for both panels
    public void standardSettings(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(220, 230, 250));
        this.setOpaque(true);
        this.setLayout(null);
    }
    public void updateLabel(boolean whoseTurn, boolean isRunning, boolean draw){
        if(whoseTurn && isRunning && !draw){
            label.setForeground(player1Color);
            label.setText("Turn: O");
        } else if (!whoseTurn && isRunning && !draw) {
            label.setForeground(player2Color);
            label.setText("Turn: X" );
        }
        else if (!whoseTurn && !isRunning && !draw) {
            label.setForeground(player1Color);
            label.setText("O wins");
        }
        else if (whoseTurn && !isRunning && !draw){
            label.setForeground(player2Color);
            label.setText("X wins");
        } else if (!isRunning && draw) {
            label.setForeground(Color.BLACK);
            label.setText("It's a draw!");
        }
        else {
            label.setForeground(Color.BLACK);
            label.setText("Unpredicted situation!");
        }
    }
    public void updateFields(boolean whoseTurn, int i, int j){
        if (!whoseTurn) {
            fields[i][j].setText("O");
            fields[i][j].setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return player1Color;
                }
            });
        } else if (whoseTurn) {
            fields[i][j].setText("X");
            fields[i][j].setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return player2Color;
                }
            });
        }
        fields[i][j].setEnabled(false);
        fields[i][j].setFocusable(false);
    }
    public void gameOver(boolean isRunning, int typeOfWin){
        if(!isRunning) {
            for (JButton[] field : fields) {
                for (int l = 0; l < fields[0].length; l++) {
                    if(field[l].isEnabled()){
                        field[l].setEnabled(false);
                    }
                }
            }
            switch (typeOfWin) {
                case 1 -> {
                    fields[0][0].setBackground(gameOverFieldColor);
                    fields[0][1].setBackground(gameOverFieldColor);
                    fields[0][2].setBackground(gameOverFieldColor);
                }
                case 2 -> {
                    fields[1][0].setBackground(gameOverFieldColor);
                    fields[1][1].setBackground(gameOverFieldColor);
                    fields[1][2].setBackground(gameOverFieldColor);
                }
                case 3 -> {
                    fields[2][0].setBackground(gameOverFieldColor);
                    fields[2][1].setBackground(gameOverFieldColor);
                    fields[2][2].setBackground(gameOverFieldColor);
                }
                case 4 -> {
                    fields[0][0].setBackground(gameOverFieldColor);
                    fields[1][0].setBackground(gameOverFieldColor);
                    fields[2][0].setBackground(gameOverFieldColor);
                }
                case 5 -> {
                    fields[0][1].setBackground(gameOverFieldColor);
                    fields[1][1].setBackground(gameOverFieldColor);
                    fields[2][1].setBackground(gameOverFieldColor);
                }
                case 6 -> {
                    fields[0][2].setBackground(gameOverFieldColor);
                    fields[1][2].setBackground(gameOverFieldColor);
                    fields[2][2].setBackground(gameOverFieldColor);
                }
                case 7 -> {
                    fields[0][0].setBackground(gameOverFieldColor);
                    fields[1][1].setBackground(gameOverFieldColor);
                    fields[2][2].setBackground(gameOverFieldColor);
                }
                case 8 -> {
                    fields[2][0].setBackground(gameOverFieldColor);
                    fields[1][1].setBackground(gameOverFieldColor);
                    fields[0][2].setBackground(gameOverFieldColor);
                }
            }
            this.add(restart, Integer.valueOf(2));
        }
    }
    public void update(boolean whoseTurn, boolean isRunning, boolean draw, int i, int j, int typeOfWin){
        updateLabel(whoseTurn, isRunning, draw);
        updateFields(whoseTurn, i, j);
        gameOver(isRunning, typeOfWin);
    }
}
