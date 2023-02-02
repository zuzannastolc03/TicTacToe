import javax.swing.*;
import java.awt.*;

public class Label_settings extends JLabel {
    Label_settings(String text){
        this.setText(text);
        this.setSize(400, 50);
        this.setFont(new Font("Cuckoo", Font.BOLD, 30));
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
