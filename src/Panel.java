import javax.swing.*;
import java.awt.*;
public class Panel extends JPanel
{
    Panel()
    {
        this.setBounds(0, 0, 900, 700);
        this.setBackground(Color.BLACK);
        this.setLayout(null);
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image back = new ImageIcon("Gadgets/Background.png").getImage();
        g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
    }
}