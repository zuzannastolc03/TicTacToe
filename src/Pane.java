import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class Pane extends JLayeredPane {
    final static int WIDTH = 640;
    final static int HEIGHT = 480;
    Button onePlayerButton;
    Button twoPlayersButton;
    Button restart;
    Label label;
    JRadioButton threeFieldsButton;
    JRadioButton fourFieldsButton;
    JRadioButton fiveFieldsButton;
    JButton[][] fields;
    Point2D firstFieldCoordinates;
    final static Color O_COLOR = Color.orange;
    final static Color X_COLOR = new Color(0, 150, 200);
    final static Color GAME_OVER_FIELD_COLOR = Color.lightGray;
    final static Color BACKGROUND_COLOR = new Color(220, 230, 250);
    final static int RADIO_BUTTON_SIZE = 140;
    // First panel constructor
    Pane(int fieldSize){
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
        label.setLocation((int)(WIDTH-label.getWidth())/2, ((int)(HEIGHT-3*fieldSize)/2-label.getHeight())/2);
        this.add(label);

        ButtonGroup group = new ButtonGroup();
        threeFieldsButton = radioButtonSettings("3x3 Board", group);
        fourFieldsButton = radioButtonSettings("4x4 Board", group);
        fiveFieldsButton = radioButtonSettings("5x5 Board", group);
        threeFieldsButton.setLocation((int)((WIDTH-3*RADIO_BUTTON_SIZE)/4), ((int)onePlayerButton.getLocation().getY() - RADIO_BUTTON_SIZE/2));
        fourFieldsButton.setLocation((int)((WIDTH-3*RADIO_BUTTON_SIZE)/4)*2 + RADIO_BUTTON_SIZE, ((int)onePlayerButton.getLocation().getY() - RADIO_BUTTON_SIZE/2));
        fiveFieldsButton.setLocation((int)((WIDTH-3*RADIO_BUTTON_SIZE)/4)*3 + 2 * RADIO_BUTTON_SIZE, ((int)onePlayerButton.getLocation().getY() - RADIO_BUTTON_SIZE/2));
        threeFieldsButton.setSelected(true);
    }
    // Second panel constructor
    Pane(boolean whoseTurn, boolean isRunning, boolean draw, int sizeOfBoard, int fieldSize){
        standardSettings();
        restart = new Button("Restart");
        restart.setLocation((int)(WIDTH-restart.getWidth())/2, (int)(HEIGHT-restart.getHeight())/2);
        restart.setOpaque(true);

        firstFieldCoordinates = new Point2D.Double((double)(WIDTH-sizeOfBoard*fieldSize)/2, (double)(HEIGHT-sizeOfBoard*fieldSize)/2);

        label = new Label("");
        updateLabel(whoseTurn, isRunning, draw);
        label.setLocation((int)(WIDTH-label.getWidth())/2, (int)(firstFieldCoordinates.getY()-label.getHeight())/2);
        this.add(label, Integer.valueOf(1));

        fields = new JButton[sizeOfBoard][sizeOfBoard];

        for(int i=0; i<sizeOfBoard; i++){
            for(int j=0; j<sizeOfBoard; j++){
                fields[i][j] = new JButton();
                fields[i][j].setBounds((int)(firstFieldCoordinates.getX() + (i * fieldSize)), (int)(firstFieldCoordinates.getY() + (j * fieldSize)), fieldSize, fieldSize);
                fields[i][j].setBackground(Color.WHITE);
                fields[i][j].setFont(new Font("Cuckoo", Font.BOLD, (int)(fieldSize*2/3)));
                fields[i][j].setOpaque(true);
                this.add(fields[i][j], Integer.valueOf(1));
            }
        }
    }
    // Standard settings for both panels
    public JRadioButton radioButtonSettings(String text, ButtonGroup gr){
        JRadioButton radioButton = new JRadioButton(text);
        gr.add(radioButton);
        radioButton.setSize(RADIO_BUTTON_SIZE, RADIO_BUTTON_SIZE/2);
        radioButton.setBackground(BACKGROUND_COLOR);
        radioButton.setOpaque(true);
        radioButton.setFocusable(false);
        radioButton.setFont(new Font("Cuckoo", Font.PLAIN, 25));
        this.add(radioButton);
        return radioButton;
    }
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
    public void gameOver(boolean isRunning, HashMap<String, Integer> typeOfWin, int sizeOfBoard){
        if(!isRunning) {
            for (JButton[] field : fields) {
                for (int l = 0; l < fields[0].length; l++) {
                    if(field[l].isEnabled()){
                        field[l].setEnabled(false);
                    }
                }
            }
            if (typeOfWin.containsKey("vertical")){
                for(int i = 0; i<sizeOfBoard; i++){
                    fields[i][typeOfWin.get("vertical")].setBackground(GAME_OVER_FIELD_COLOR);
                }
            } else if (typeOfWin.containsKey("horizontal")) {
                for(int i = 0; i<sizeOfBoard; i++){
                    fields[typeOfWin.get("horizontal")][i].setBackground(GAME_OVER_FIELD_COLOR);
                }
            } else if (typeOfWin.containsKey("diagonal1")) {
                for(int i = 0; i<sizeOfBoard; i++){
                    fields[i][i].setBackground(GAME_OVER_FIELD_COLOR);
                }
            } else if (typeOfWin.containsKey("diagonal2")) {
                for(int i = 0; i<sizeOfBoard; i++){
                    fields[i][sizeOfBoard - 1 - i].setBackground(GAME_OVER_FIELD_COLOR);
                }
            }
            this.add(restart, Integer.valueOf(2));
        }
    }
    public void update(boolean whoseTurn, boolean isRunning, boolean draw, int i, int j, HashMap<String, Integer> typeOfWin, int sizeOfBoard){
        updateLabel(whoseTurn, isRunning, draw);
        updateFields(whoseTurn, i, j);
        gameOver(isRunning, typeOfWin, sizeOfBoard);
    }
}
