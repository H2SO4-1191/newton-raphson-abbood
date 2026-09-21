import javax.swing.*;
import java.awt.*;
public class Box extends JTextField
{
    Box(int x, int y, int width)
    {
        this.setBounds(x, y, width, 30);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.YELLOW);
        this.setFont(new Font("Cambria Math", Font.PLAIN, 20));
    }
}