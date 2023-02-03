import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    Label(String text){
        this.setText(text);
        this.setSize(400, 50);
        this.setFont(new Font("Cuckoo", Font.BOLD, 30));
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
