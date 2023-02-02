import javax.swing.*;
import java.awt.*;

public class First_panel extends Panel_settings{
    Button_settings one_player;
    Button_settings two_players;
    Label_settings label;
    First_panel(){
        one_player = new Button_settings("One player");
        one_player.setLocation((int)(width-2*one_player.getWidth())/3, (int)(height-100)/2);
        this.add(one_player);

        two_players = new Button_settings("Two players");
        two_players.setLocation((int)(width-2*two_players.getWidth())/3*2+two_players.getWidth(), (int)(height-100)/2);
        this.add(two_players);

        label = new Label_settings("Welcome to TicTacToe!");
        label.setLocation((int)(width-label.getWidth())/2, ((int)(height-3*120)/2-label.getHeight())/2);
        this.add(label);
    }
}
