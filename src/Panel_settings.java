import javax.swing.*;
import java.awt.*;

public class Panel_settings extends JPanel {
    public final static int width = 640;
    public final static int height = 480;
    Panel_settings(){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(220, 230, 250));
        this.setLayout(null);
    }
}
