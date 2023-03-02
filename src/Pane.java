import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class Pane extends JLayeredPane {
    final static int WIDTH = 640;
    final static int HEIGHT = 480;
    final static int FIELD_SIZE = 120;
    Button onePlayerButton;
    Button twoPlayersButton;
    Button restart;
    Label label;
    JButton[][] fields;
    int[] xCoordinates = new int[3];
    int[] yCoordinates = new int[3];
    final static Color O_COLOR = Color.orange;
    final static Color X_COLOR = new Color(0, 150, 200);
    final static Color GAME_OVER_FIELD_COLOR = Color.lightGray;
    final static Color BACKGROUND_COLOR = new Color(220, 230, 250);
    // First panel constructor
    Pane(){
        standardSettings();
        onePlayerButton = new Button("One player");
        onePlayerButton.setLocation((int)(WIDTH-2*onePlayerButton.getWidth())/3, (int)(HEIGHT-onePlayerButton.getHeight())/2);
        onePlayerButton.setOpaque(true);
        this.add(onePlayerButton);

        twoPlayersButton = new Button("Two players");
        twoPlayersButton.setLocation((int)(WIDTH-2*twoPlayersButton.getWidth())/3*2+twoPlayersButton.getWidth(), (int)(HEIGHT-twoPlayersButton.getHeight())/2);
        twoPlayersButton.setOpaque(true);
        this.add(twoPlayersButton);

        label = new Label("Welcome to TicTacToe!");
        label.setLocation((int)(WIDTH-label.getWidth())/2, ((int)(HEIGHT-3*FIELD_SIZE)/2-label.getHeight())/2);
        this.add(label);
    }
    // Second panel constructor
    Pane(boolean whoseTurn, boolean isRunning, boolean draw){
        standardSettings();
        restart = new Button("Restart");
        restart.setLocation((int)(WIDTH-restart.getWidth())/2, (int)(HEIGHT-restart.getHeight())/2);
        restart.setOpaque(true);

        for(int i=0; i<xCoordinates.length; i++){
            xCoordinates[i] = (int)(WIDTH-3*FIELD_SIZE)/2 + i*FIELD_SIZE;
        }
        for(int i=0; i<yCoordinates.length; i++){
            yCoordinates[i] = (int)(HEIGHT-3*FIELD_SIZE)/2 + i*FIELD_SIZE;
        }
        label = new Label("");
        updateLabel(whoseTurn, isRunning, draw);
        label.setLocation((int)(WIDTH-label.getWidth())/2, (int)(yCoordinates[0]-label.getHeight())/2);
        this.add(label, Integer.valueOf(1));

        fields = new JButton[3][3];

        for(int i=0; i<xCoordinates.length; i++){
            for(int j=0; j<yCoordinates.length; j++){
                fields[i][j] = new JButton();
                fields[i][j].setBounds(xCoordinates[i], yCoordinates[j], FIELD_SIZE, FIELD_SIZE);
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
        this.setBackground(BACKGROUND_COLOR);
        this.setOpaque(true);
        this.setLayout(null);
    }
    public void updateLabel(boolean whoseTurn, boolean isRunning, boolean draw){
        if(whoseTurn && isRunning && !draw){
            label.setForeground(O_COLOR);
            label.setText("Turn: O");
        } else if (!whoseTurn && isRunning && !draw) {
            label.setForeground(X_COLOR);
            label.setText("Turn: X" );
        }
        else if (!whoseTurn && !isRunning && !draw) {
            label.setForeground(O_COLOR);
            label.setText("O wins");
        }
        else if (whoseTurn && !isRunning && !draw){
            label.setForeground(X_COLOR);
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
                    return O_COLOR;
                }
            });
        } else if (whoseTurn) {
            fields[i][j].setText("X");
            fields[i][j].setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return X_COLOR;
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
                    fields[0][0].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[0][1].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[0][2].setBackground(GAME_OVER_FIELD_COLOR);
                }
                case 2 -> {
                    fields[1][0].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[1][1].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[1][2].setBackground(GAME_OVER_FIELD_COLOR);
                }
                case 3 -> {
                    fields[2][0].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[2][1].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[2][2].setBackground(GAME_OVER_FIELD_COLOR);
                }
                case 4 -> {
                    fields[0][0].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[1][0].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[2][0].setBackground(GAME_OVER_FIELD_COLOR);
                }
                case 5 -> {
                    fields[0][1].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[1][1].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[2][1].setBackground(GAME_OVER_FIELD_COLOR);
                }
                case 6 -> {
                    fields[0][2].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[1][2].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[2][2].setBackground(GAME_OVER_FIELD_COLOR);
                }
                case 7 -> {
                    fields[0][0].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[1][1].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[2][2].setBackground(GAME_OVER_FIELD_COLOR);
                }
                case 8 -> {
                    fields[2][0].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[1][1].setBackground(GAME_OVER_FIELD_COLOR);
                    fields[0][2].setBackground(GAME_OVER_FIELD_COLOR);
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
