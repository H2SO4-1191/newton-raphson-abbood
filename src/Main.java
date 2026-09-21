import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
public class Main
{
    static int z = 0;
    static Frame mainFrame;
    static Panel mainPanel, panel1, panel2;
    static Button button1, button2, button3, button4, button5, button6, button7;
    static Box box1, box2, box3, box4;
    static Clip music, sound;
    public static void main(String[] args)
    {
        try
        {
            sound();
            setMusic(0);
            mainFrame = new Frame();
            setMainPanel();
        }
        catch(Exception e) {}
    }
    public static void setMainPanel()
    {
        mainPanel = new Panel();
        logo(mainPanel);
        button1 = new Button("Non-Linear Equations", 650, 130, 225);
        button2 = new Button("Square Root", 650, 170, 225);
        button3 = new Button("N'th Root", 650, 210, 225);
        button4 = new Button("Inverse", 650, 250, 225);
        button5 = new Button("About", 650, 290, 225);
        button6 = new Button("Exit", 650, 330, 225);
        button7 = new Button("Music", 20, 620, 100);
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);
        mainPanel.add(button4);
        mainPanel.add(button5);
        mainPanel.add(button6);
        mainPanel.add(button7);
        button1.addActionListener(e -> setInformation(0));
        button2.addActionListener(e -> setInformation(1));
        button3.addActionListener(e -> setInformation(3));
        button4.addActionListener(e -> setInformation(2));
        button5.addActionListener(e -> about());
        button6.addActionListener(e -> exit());
        button7.addActionListener(e -> setMusic(1));
        mainFrame.add(mainPanel);
        mainFrame.repaint();
    }
    public static void setInformation(int q)
    {
        sound();
        mainPanel.setVisible(false);
        panel1 = new Panel();
        logo(panel1);
        String line = null;
        int x = 0, y = 0, w = 0;
        if(q == 0)
        {
            line = "Equation";
            x = 10; y = 250; w = 230;
            panel1.add(new Label("Calculator", x, y-40, 90, 0));
            box4 = new Box(x+90, y-40, w-30);
            panel1.add(box4);
            button3 = new Button("Calculate", x, y-80, 120);
            button4 = new Button("Insert", x+130, y-80, 90);
            button5 = new Button("Generate", x+40, y+160, 120);
            button3.addActionListener(e -> calculate());
            button4.addActionListener(e -> insert());
            button5.addActionListener(e -> generate());
            panel1.add(button3);
            panel1.add(button4);
            panel1.add(button5);
        }
        else if (q == 1)
        {
            line = "Number";
            x = 260; y = 10; w = 80;
        }
        else if(q == 2)
        {
            line = "Number";
            x = 120; y = 500; w = 80;
        }
        else if(q == 3)
        {
            line = "Number";
            x = 610; y = 180; w = 80;
            panel1.add(new Label("Root", x, y-40, w, 0));
            box4 = new Box(x+80, y-40, w);
            panel1.add(box4);
        }
        panel1.add(new Label(line, x, y, 80, 0));
        panel1.add(new Label("Interval", x, y+40, 80, 0));
        panel1.add(new Label("Epsylon", x, y+80, 80, 0));
        box1 = new Box(x+80, y, w);
        box2 = new Box(x+80, y+40, 80);
        box3 = new Box(x+80, y+80, 80);
        panel1.add(box1);
        panel1.add(box2);
        panel1.add(box3);
        button1 = new Button("Submit", x, y+120, 100);
        button2 = new Button("Back", x+110, y+120, 80);
        if(q == 0) button1.addActionListener(e -> check(q));
        if(q == 1) button1.addActionListener(e -> check(q));
        if(q == 2) button1.addActionListener(e -> check(q));
        if(q == 3) button1.addActionListener(e -> check(q));
        button2.addActionListener(e -> back(panel1, mainPanel));
        panel1.add(button1);
        panel1.add(button2);
        mainFrame.add(panel1);
    }
    public static void check(int q)
    {
        sound();
        String equation = null, derivative = null;
        double a = 0.0, b = 0.0, epsylon = 0.0, number = 0.0, k = 0.0;
        boolean b1 = true, b2 = true, b3 = true, b4 = true;
        if(box1.getText().isBlank())
        {
            if(q == 0) JOptionPane.showMessageDialog(mainFrame, "An equation is required!", "Error" , JOptionPane.ERROR_MESSAGE);
            else JOptionPane.showMessageDialog(mainFrame, "A number is required!", "Error" , JOptionPane.ERROR_MESSAGE);
            b1 = false;
        }
        else
        {
            if(q == 0)
            {
                try
                {
                    derivative = f.derivative(box1.getText());
                    equation = box1.getText();
                }
                catch(Exception e) {JOptionPane.showMessageDialog(mainFrame, "Something is wrong with the equation you entered!\nNote: read the rules in 'About' section.", "Error" , JOptionPane.ERROR_MESSAGE); b1 = false;}
            }
            else
            {
                try {number = Double.valueOf(box1.getText());}
                catch(Exception e) {JOptionPane.showMessageDialog(mainFrame, "Something is wrong with the number you entered!\nNote: enter a number only.", "Error" , JOptionPane.ERROR_MESSAGE); b1 = false;}
            }
        }
        if(!box2.getText().isBlank())
        {
            try
            {
                a = Double.valueOf(box2.getText().substring(0, box2.getText().indexOf(",")));
                b = Double.valueOf(box2.getText().substring(box2.getText().indexOf(",")+1));
            }
            catch(Exception e) {JOptionPane.showMessageDialog(mainFrame, "Something is wrong with the interval you entered!\nNote: enter the interval in the form of 'a, b'.\nNote: An interval is not mandetory.", "Error" , JOptionPane.ERROR_MESSAGE); b2 = false;}
        }
        if(q == 0)
        {
            if(box3.getText().isBlank()) {JOptionPane.showMessageDialog(mainFrame, "The approximation of errors (Epsylon) is required!", "Error" , JOptionPane.ERROR_MESSAGE); b3 = false;}
            else
            {
                try
                {
                    if(Double.valueOf(box3.getText()) >= 1 || Double.valueOf(box3.getText()) <= 0) {JOptionPane.showMessageDialog(mainFrame, "Epsylon must be between 0 & 1", "Error" , JOptionPane.ERROR_MESSAGE); b3 = false;}
                    else epsylon = Double.valueOf(box3.getText());
                }
                catch(Exception e) {JOptionPane.showMessageDialog(mainFrame, "Something is wrong with the epsylon you entered!", "Error" , JOptionPane.ERROR_MESSAGE); b3 = false; b3 = false;}
            }
        }
        else
        {
            if(!box3.getText().isBlank())
            {
                try
                {
                    if(Double.valueOf(box3.getText()) >= 1 || Double.valueOf(box3.getText()) <= 0) {JOptionPane.showMessageDialog(mainFrame, "Epsylon must be between 0 & 1.\nNote: Epsylon is not mandetory for applications.", "Error" , JOptionPane.ERROR_MESSAGE); b3 = false;}
                    else epsylon = Double.valueOf(box3.getText());
                }
                catch(Exception e) {JOptionPane.showMessageDialog(mainFrame, "Something is wrong with the epsylon you entered!\nNote: Epsylon is not mandetory for applications.", "Error" , JOptionPane.ERROR_MESSAGE); b3 = false;}
            }
        }
        if(q == 3)
        {
            if(box4.getText().isBlank()) {JOptionPane.showMessageDialog(mainFrame, "A root is required!", "Error" , JOptionPane.ERROR_MESSAGE); b4 = false;}
            else
            {
                try {k = Double.valueOf(box4.getText());}
                catch(Exception e) {JOptionPane.showMessageDialog(mainFrame, "Something is wrong with the root you entered!\nNote: enter a number only.", "Error" , JOptionPane.ERROR_MESSAGE); b4 = false;}
            }
        }
        if(q == 3) {if(b1 && b2 && b3 && b4) solve(equation, derivative, a, b, number, k, epsylon, q);}
        else {if(b1 && b2 && b3) solve(equation, derivative, a, b, number, k, epsylon, q);}
    }
    public static void solve(String equation, String derivative, double a, double b, double number,double k, double epsylon, int q)
    {
        sound();
        panel1.setVisible(false);
        panel2 = new Panel();
        button1 = new Button("Home", 770, 620, 100);
        button1.addActionListener(e -> back(panel2, mainPanel));
        panel2.add(button1);
        mainFrame.add(panel2);
        int x = 10, y = 0;
        if(q == 1) equation = "x^[2]-[".concat(String.valueOf(number)).concat("]");
        else if(q == 2) equation = "[".concat(String.valueOf(number)).concat("]-[1]/x");
        else if(q == 3) equation = "x^[".concat(String.valueOf(k)).concat("]-[").concat(String.valueOf(number)).concat("]");
        panel2.add(new Label("f(x) = " + equation, x, y, 450, 1)); y+=30;
        if(q == 0) {panel2.add(new Label("df(x) = " + derivative, x, y, 450, 1));y+=30;}
        if(a != 0.0 && b != 0.0) {panel2.add(new Label("a = " + a + ", b = " + b + ", e = " + epsylon, x, y, 450, 1)); y+=30;}
        else {panel2.add(new Label("e = " + epsylon, x, y, 450, 1)); y+=30;}
        panel2.add(new Label("Solution:" , x, y, 450, 1)); y+=30;
        if(q == 2)
        {
            if(a == 0.0 && b == 0.0)
            {
                a = 1.0; b = 2.0;
                while(f.result(equation, a/10.0) * f.result(equation, b/10.0) >= 0 && b/10.0 <= 10) {a++; b++;}
                if(b <= 10)
                {
                    a/=10.0;
                    b/=10.0;
                    panel2.add(new Label("Suppose a = " + a + ", b = " + b, x, y, 450, 1)); y+=30;
                }
                else
                {
                    panel2.add(new Label("No solution was found in any interval.", x, y, 450, 1)); y+=30;
                    panel2.add(new Label("Note: try to specify an interval.", x, y, 450, 1)); y+=30;
                    return;
                }
            }
        }
        else
        {
            if(a == 0.0 && b == 0.0)
            {
                a = 1; b = 2;
                while(f.result(equation, a) * f.result(equation, b) > 0 && b <= 10) {a++; b++;}
                if(b <= 10) {panel2.add(new Label("Suppose a = " + a + ", b = " + b, x, y, 450, 1)); y+=30;}
                else
                {
                    panel2.add(new Label("No solution was found in any integer interval.", x, y, 450, 1)); y+=30;
                    panel2.add(new Label("Note: try to specify an interval.", x, y, 450, 1)); y+=30;
                    return;
                }
            }
        }
        if(f.result(equation, a) * f.result(equation, b) >= 0) {panel2.add(new Label("f(" + a + ")*f(" + b + ") > 0, No solution.", x, y, 450, 1)); y+=30; return;}
        else {panel2.add(new Label("f(" + a + ")*f(" + b + ") < 0, Solution exists.", x, y, 450, 1)); y+=30;}
        double xPrevious, xNext = (a+b)/2.0;
        panel2.add(new Label("X0 = (a+b)/2 = " + xNext, x, y, 450, 1)); y+=30;
        switch(q)
        {
            case 0:
            {
                panel2.add(new Label("Xn+1 = Xn - f(Xn)/df(Xn)", x, y, 450, 1)); y+=30;
                int i = 1;
                do
                {
                    if(y >= 630) {x = 450; y = 10;}
                    xPrevious = xNext;
                    xNext = xPrevious - (f.result(equation, xPrevious) / f.result(f.derivative(equation), xPrevious));
                    panel2.add(new Label("X" + i + " = " + xNext, x, y, 450, 1)); y+=30;
                    if(Math.abs(xNext-xPrevious) > epsylon) {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| > e", x, y, 450, 1)); y+=30; i++;}
                    else {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| < e", x, y, 450, 1)); y+=30;}
                } while(Math.abs(xNext-xPrevious) > epsylon);
                panel2.add(new Label("Root is X" + i + " = " + xNext, x, y, 450, 0)); y+=30;
                break;
            }
            case 1:
            {
                panel2.add(new Label("Xn+1 = [0.5]*(Xn + [" + number + "]/Xn)", x, y, 450, 1)); y+=30;
                int i = 1;
                do
                {
                    if(y >= 690) {x = 450; y = 10;}
                    xPrevious = xNext;
                    xNext = (1.0/2.0)*(xPrevious + (number/xPrevious));
                    panel2.add(new Label("X" + i + " = " + xNext, x, y, 450, 1)); y+=30;
                    if(Math.abs(xNext-xPrevious) > epsylon) {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| > e", x, y, 450, 1)); y+=30; i++;}
                    else {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| < e", x, y, 450, 1)); y+=30;}
                } while(Math.abs(xNext-xPrevious) > epsylon);
                panel2.add(new Label("The square root is X" + i + " = " + xNext, x, y, 450, 0)); y+=30;
                break;
            }
            case 2:
            {
                panel2.add(new Label("Xn+1 = [2]*Xn - [" + number + "]*Xn^[2]", x, y, 450, 1)); y+=30;
                int i = 1;
                do
                {
                    if(y >= 690) {x = 450; y = 10;}
                    xPrevious = xNext;
                    xNext = (2.0*xPrevious) - (number*Math.pow(xPrevious, 2.0));
                    panel2.add(new Label("X" + i + " = " + xNext, x, y, 450, 1)); y+=30;
                    if(Math.abs(xNext-xPrevious) > epsylon) {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| > e", x, y, 450, 1)); y+=30; i++;}
                    else {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| < e", x, y, 450, 1)); y+=30;}
                } while(Math.abs(xNext-xPrevious) > epsylon);
                panel2.add(new Label("The inverse is X" + i + " = " + xNext, x, y, 450, 0)); y+=30;
                break;
            }
            case 3:
            {
                panel2.add(new Label("Xn+1 = ([" + (k-1) + "]/[" + k + "])*Xn + [" + number + "]/[" + k + "]*Xn^[" + (k-1) + "]", x, y, 450, 1)); y+=30;
                int i = 1;
                do
                {
                    if(y >= 690) {x = 450; y = 10;}
                    xPrevious = xNext;
                    xNext = (((k-1)/k)*xPrevious) + (number/(k*Math.pow(xPrevious, k-1)));
                    panel2.add(new Label("X" + i + " = " + xNext, x, y, 450, 1)); y+=30;
                    if(Math.abs(xNext-xPrevious) > epsylon) {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| > e", x, y, 450, 1)); y+=30; i++;}
                    else {panel2.add(new Label("|X" + i + " - X" + (i-1) + "| < e", x, y, 450, 1)); y+=30;}
                } while(Math.abs(xNext-xPrevious) > epsylon);
                panel2.add(new Label("The root is X" + i + " = " + xNext, x, y, 450, 0)); y+=30;
                break;
            }
        }
    }
    public static void about()
    {
        sound();
        mainPanel.setVisible(false);
        panel1 = new Panel();
        int y = 0;
        panel1.add(new Label("Newton-Raphson-Abbood is a program that was developped by \"Mustafa Muhammad\" aka", 10, y, 890,1)); y+=30;
        panel1.add(new Label("\"H2S04-1191\", it is a program that can solve issues using Newton Raphson method.", 10, y, 890,1)); y+=30;
        panel1.add(new Label("Although it still has some limitations so there are some rules that need to be followed in order to get", 10, y, 890,1));  y+=30;
        panel1.add(new Label("accurate results, and the rules are:", 10, y, 890,1)); y+=50;
        panel1.add(new Label("1- Simplify the equation before entering it..fractions, roots and all. (Using the calculator ", 60, y, 890,1)); y+=30;
        panel1.add(new Label("section is recommended).", 100, y, 890,1)); y+=30;
        panel1.add(new Label("2- Put constant values between [].        e.g.: [2]*x^[3]", 60, y, 890,1)); y+=30;
        panel1.add(new Label("3- Put all operations in place.", 60, y, 890,1)); y+=30;
        panel1.add(new Label("4- Ln, e, sin and cos must have their values between ().      e.g: ln([2]*x) or e^(x).", 60, y, 890,1)); y+=30;
        panel1.add(new Label("5- For multiplication and division between functions use '&' and '\\'.        e.g.: [2]*x&ln(x) ", 60, y, 890,1)); y+=30;
        panel1.add(new Label("or e^(x)\\sin([3]*x).", 100, y, 890,1)); y+=30;
        panel1.add(new Label("6- Never leave parenthases open!!!.", 60, y, 890,1)); y+=50;
        panel1.add(new Label("Over 1000 lines of Java code was what it took to bring N.R.S. to life...      Enjoy it.", 10, y, 890,1)); y+=100;
        panel1.add(new Label("Feel free to contact me on:", 10, y, 890,1)); y+=30;
        panel1.add(new Label("Phone number: 0782 242 7149", 10, y, 890,1)); y+=30;
        panel1.add(new Label("Instagram: H2SO4-1191", 10, y, 890,1)); y+=10;
        panel1.add(new Label("30/APR/2024", 750, y, 150,1)); y+=30;
        button1 = new Button("Back", 775, y, 80); y+=30;
        panel1.add(new Label("N.R.A. Ver: 1.6", 750, y, 150, 1)); y+=30;
        button1.addActionListener(e -> back(panel1, mainPanel));
        panel1.add(button1);
        mainFrame.add(panel1);
    }
    public static void exit()
    {
        sound();
        if(JOptionPane.showConfirmDialog(null, "Do you wish to exit?" ,"Confirm", JOptionPane.YES_NO_OPTION) == 0) System.exit(0);
    }
    public static void calculate()
    {
        sound();
        try
        {
            String input = box4.getText().strip();
            String op = "";
            double _op, op_;
            for(int i = 0; i < input.length(); i++)
            {
                try {Integer.valueOf(input.substring(i, i+1));}
                catch(Exception e) {op = input.substring(i, i+1); i = input.length();}
            }
            if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("^"))
            {
                _op = Double.valueOf(input.substring(0, input.indexOf(op)));
                op_ = Double.valueOf(input.substring(input.indexOf(op)+1));
                switch(op)
                {
                    case "+": box4.setText(String.valueOf(_op+op_)); break;
                    case "-": box4.setText(String.valueOf(_op-op_)); break;
                    case "*": box4.setText(String.valueOf(_op*op_)); break;
                    case "/": box4.setText(String.valueOf(_op/op_)); break;
                    case "^": box4.setText(String.valueOf(Math.pow(_op, op_))); break;
                    default:  box4.setText("");
                }
            }
            else
            {
                op_ = Double.valueOf(input.substring(input.indexOf("(")+1, input.indexOf(")")));
                switch(op)
                {
                    case "e": box4.setText(String.valueOf(Math.exp(op_))); break;
                    case "l": box4.setText(String.valueOf(Math.log(op_))); break;
                    case "s": box4.setText(String.valueOf(Math.sin(op_))); break;
                    case "c": box4.setText(String.valueOf(Math.cos(op_))); break;
                    default:  box4.setText("");
                }
            }
        }
        catch(Exception e) {box4.setText("");}
    }
    public static void insert()
    {
        sound();
        box1.setText(box1.getText().concat("[".concat(box4.getText().concat("]"))));
        box4.setText("");
    }
    public static void generate()
    {
        sound();
        String equations[] = {"x&ln(x)-[1]", "x+e^([-2]*x)-[1]", "x^[3]-[2]*x-[5]"};
        String intervals[] = {"1, 2", "0.5, 1", "2, 3"};
        String epsylons[] = {"0.001", "0.001", "0.00001"};
        box1.setText(equations[z]);
        box2.setText(intervals[z]);
        box3.setText(epsylons[z]);
        z++;
        if(z == 3) z = 0;
    }
    public static void logo(Panel current)
    {
        current.add(new Label("Newton", 425, 248, 100, 1));
        current.add(new Label("Raphson", 420, 361, 100, 1));
        current.add(new Label("Abbood", 425, 472, 100, 1));
        current.add(new Label("N.R.A. Ver: 1.6", 750, 630, 150, 1));
    }
    public static void setMusic(int q)
    {
        try
        {
            File musicFile = new File("Gadgets/Music.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            if(q == 0)
            {
                music = AudioSystem.getClip();
                music.open(audioInput);
                music.start();
                music.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else
            {
                sound();
                if(music.isRunning()) music.stop();
                else music.start();
            }
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {e.printStackTrace();}
    }
    public static void sound()
    {
        try
        {
            File musicFile = new File("Gadgets/Sound.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            sound = AudioSystem.getClip();
            sound.open(audioInput);
            sound.start();
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {e.printStackTrace();}
    }
    
    public static void back(Panel current, Panel previous)
    {
        sound();
        current.setVisible(false);
        previous.setVisible(true);
    }
}