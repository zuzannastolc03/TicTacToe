import javax.swing.*;
import java.awt.*;

public class Button_settings extends JButton {
    Button_settings(String text) {
        this.setText(text);
        this.setBackground(Color.WHITE);
        this.setSize(200, 100);
        this.setFont(new Font("Cuckoo", Font.PLAIN, 20));
        this.setFocusable(false);
    }
}
