import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame
{
    Frame()
    {
        ImageIcon icon = new ImageIcon("Gadgets/Icon.png");
        this.setIconImage(icon.getImage());
        this.setTitle("Newton-Raphson-Abbood");
        this.setSize(900, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }
}