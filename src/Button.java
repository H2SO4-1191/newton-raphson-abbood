import javax.swing.*;
import java.awt.*;
public class Button extends JButton
{
    Button(String text, int x, int y, int width)
    {
        this.setText(text);
        this.setBounds(x, y, width, 30);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.CYAN);
        this.setFont(new Font("Cambria Math", Font.ITALIC, 20));
        this.setFocusable(false);
    }
}