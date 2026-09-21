import javax.swing.JLabel;
import java.awt.*;
public class Label extends JLabel
{
    Label(String text, int x, int y, int width, int q)
    {
        this.setText(text);
        this.setBounds(x, y, width, 30);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.MAGENTA);
        this.setFont(new Font("Cambria Math", Font.PLAIN, 20));
        if(q == 0) this.setOpaque(true);
    }
}