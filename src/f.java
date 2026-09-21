import java.util.ArrayList;
public abstract class f
{
    public static String derivative(String equation)
    {
        String derivative = manager(equation);
        return derivative;
    }
    public static double result(String equation, double x)
    {
        while(equation.contains("x"))
        {
            String hold = equation.substring(equation.indexOf("x")+1);
            equation = equation.substring(0, equation.indexOf("x")).concat("[").concat(String.valueOf(x)).concat("]").concat(hold);
        }
        return checker(equation);
    }
    public static String manager(String equation)
    {
        String[] sub = equation.split("(?=[+\\-](?![^\\[\\(]*[\\]\\)]))");
        for(int i = 0; i < sub.length; i++) if(sub[i].contains("(") && !sub[i].contains(")")) for(int j = i; j < sub.length; j++) if(sub[j].contains(")"))
        {
            sub[i] = sub[i].concat(sub[j]);
            sub[i] = sub[i].replace(")", "@");
            sub[j] = "";
            j += sub.length;
        }
        for(int i = 0; i < sub.length; i++) if(sub[i].contains("@")) sub[i] = sub[i].replace("@", ")");
        for(int i = 0; i < sub.length; i++) if(sub[i].contains("[") && !sub[i].contains("]")) for(int j = i; j < sub.length; j++) if(sub[j].contains("]"))
        {
            sub[i] = sub[i].concat(sub[j]);
            sub[i] = sub[i].replace("]", "@");
            sub[j] = "";
            j += sub.length;
        }
        for(int i = 0; i < sub.length; i++) if(sub[i].contains("@")) sub[i] = sub[i].replace("@", "]");
        equation = "";
        for(int i = 0; i < sub.length; i++) if(!judge(sub[i]).equals("0")) equation = equation.concat(judge(sub[i]));
        equation = organizer(equation);
        return equation;
    }
    public static String judge(String sub)
    {
        String sign = "";
        if(sub.startsWith("+") || sub.startsWith("-"))
        {
            sign = sub.substring(0, 1);
            sub = sub.substring(1);
        }
        if(sub.contains("x"))
        {
            if (sub.contains("&")) sub = sign.concat(x_x(sub));
            else if(sub.contains("\\")) sub = sign.concat(x$x(sub));
            else if(sub.contains("e^")) sub = sign.concat(e(sub));
            else if(sub.contains("ln")) sub = sign.concat(ln(sub));
            else if(sub.contains("sin")) sub = sign.concat(sin(sub));
            else if(sub.contains("cos")) sub = sign.concat(cos(sub));
            else if(sub.contains("(")) sub = sign.concat(inX(sub));
            else sub = sign.concat(x(sub));
        }
        else sub = "0";
        return sub;
    }
    public static String organizer(String equation)
    {
        while
        (
                equation.contains("-+") || equation.contains("+-") || equation.contains("++") || equation.contains("--") || equation.contains("*+") ||
                        equation.contains("*[1.0]") || equation.contains("[1.0]*") || equation.contains("[-1.0]*") || equation.contains("^[1.0]") ||
                        equation.contains("*[1]") || equation.contains("[1]*") || equation.contains("[-1]*") || equation.contains("^[1]") ||
                        equation.contains("x^[0.0]")
        )
        {

            if(equation.contains("-+")) equation = equation.replace("-+", "-");
            else if(equation.contains("+-"))equation = equation.replace("+-", "-");
            else if(equation.contains("++"))equation = equation.replace("++", "+");
            else if(equation.contains("--"))equation = equation.replace("--", "+");
            else if(equation.contains("*+"))equation = equation.replace("*+", "*");
            else if(equation.contains("*[1.0]"))equation = equation.replace("*[1.0]", "");
            else if(equation.contains("[1.0]*"))equation = equation.replace("[1.0]*", "");
            else if(equation.contains("[-1.0]*"))equation = equation.replace("[-1.0]*", "-");
            else if(equation.contains("^[1.0]"))equation = equation.replace("^[1.0]", "");
            else if(equation.contains("*[1]"))equation = equation.replace("*[1]", "");
            else if(equation.contains("[1]*"))equation = equation.replace("[1]*", "");
            else if(equation.contains("[-1]*"))equation = equation.replace("[-1]*", "-");
            else if(equation.contains("^[1]"))equation = equation.replace("^[1]", "");
            else if(equation.contains("x^[0.0]"))equation = equation.replace("x^[0.0]", "[1]");
        }
        equation = equation.strip();
        return equation;
    }
    public static double checker(String sub)
    {
        ArrayList<Double> order = new ArrayList<Double>();
        if(sub.startsWith("-")) sub = "[-1]*".concat(sub.substring(1));
        else if(sub.startsWith("+")) sub = sub.substring(1);
        while(sub.length() > 0)
        {
            switch(sub.substring(0, 1))
            {
                case "[":
                {
                    order.add(Double.valueOf(sub.substring(1, sub.indexOf("]"))));
                    sub = sub.substring(sub.indexOf("]")+1).strip();
                    break;
                }
                case "e":
                {
                    order.add(3000000.0);
                    sub = sub.substring(3).strip();
                    break;
                }
                case "l":
                {
                    order.add(3000000.1);
                    sub = sub.substring(3).strip();
                    break;
                }
                case "s":
                {
                    order.add(3000000.2);
                    sub = sub.substring(4).strip();
                    break;
                }
                case "c":
                {
                    order.add(3000000.3);
                    sub = sub.substring(4).strip();
                    break;
                }
                case "(":
                {
                    order.add(2000000.0);
                    sub = sub.substring(1);
                    break;
                }
                case ")":
                {
                    order.add(2000001.0);
                    sub = sub.substring(1);
                    break;
                }
                case "^":
                {
                    order.add(1000000.0);
                    sub = sub.substring(1).strip();
                    break;
                }
                case "*":
                case "&":
                {
                    order.add(1000000.1);
                    sub = sub.substring(1).strip();
                    break;
                }
                case "/":
                case "\\":
                {
                    order.add(1000000.2);
                    sub = sub.substring(1).strip();
                    break;
                }
                case "+":
                {
                    order.add(1000000.3);
                    sub = sub.substring(1).strip();
                    break;
                }
                case "-":
                {
                    order.add(1000000.4);
                    sub = sub.substring(1).strip();
                    break;
                }
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.0)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.exp(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.1)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.log(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.2)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.sin(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.3)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.cos(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) >= 2000000.0 && order.get(i) < 2000001.0)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, checker(in));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 1000000.0)
            {
                order.set(i-1, Math.pow(order.get(i-1), order.get(i+1)));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 1000000.1)
            {
                order.set(i-1, order.get(i-1) * order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
            else if(order.get(i) == 1000000.2)
            {
                order.set(i-1, order.get(i-1) / order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 1000000.3)
            {
                order.set(i-1, order.get(i-1) + order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
            else if(order.get(i) == 1000000.4)
            {
                order.set(i-1, order.get(i-1) - order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
        }
        return order.get(0);
    }
    public static double checker(ArrayList<Double> order)
    {
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.0)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.exp(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.1)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.log(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.2)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.sin(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 3000000.3)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, Math.cos(checker(in)));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) >= 2000000.0 && order.get(i) < 2000001.0)
            {
                i++;
                ArrayList<Double> in = new ArrayList<Double>();
                while(order.get(i) != 2000001.0)
                {
                    in.add(order.get(i));
                    order.remove(i);
                }
                order.remove(i);
                order.set(i-1, checker(in));
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 1000000.0)
            {
                order.set(i-1, Math.pow(order.get(i-1), order.get(i+1)));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 1000000.1)
            {
                order.set(i-1, order.get(i-1) * order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
            else if(order.get(i) == 1000000.2)
            {
                order.set(i-1, order.get(i-1) / order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
        }
        for(int i = 0; i < order.size(); i++)
        {
            if(order.get(i) == 1000000.3)
            {
                order.set(i-1, order.get(i-1) + order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
            else if(order.get(i) == 1000000.4)
            {
                order.set(i-1, order.get(i-1) - order.get(i+1));
                order.remove(i);
                order.remove(i);
                i = 0;
            }
        }
        return order.get(0);
    }
    public static String x(String sub)
    {
        double _x, x_;
        if(sub.substring(0, sub.indexOf("x")).endsWith("*")) _x = Double.valueOf(sub.substring(sub.indexOf("[")+1, sub.indexOf("]")));
        else
        {
            if(sub.substring(0, sub.indexOf("x")).contains("-")) _x = -1.0;
            else _x = 1.0;
        }
        if(sub.substring(sub.indexOf("x")+1).startsWith("^")) x_ = Double.valueOf(sub.substring(sub.indexOf("^[")+2, sub.lastIndexOf("]")));
        else x_ = 1.0;
        _x *= x_;
        x_--;
        sub = "[".concat(String.valueOf(_x)).concat("]*x^[").concat(String.valueOf(x_)).concat("]");
        sub = sub.strip();
        return sub;
    }
    public static String e(String sub)
    {
        String e_ = sub.substring(sub.indexOf("(")+1, sub.lastIndexOf(")"));
        sub = sub.concat("*").concat(manager(e_));
        return sub;
    }
    public static String ln(String sub)
    {
        String _ln = sub.substring(0, sub.indexOf("ln"));
        String ln_ = sub.substring(sub.indexOf("(")+1, sub.lastIndexOf(")"));
        sub = _ln.concat(manager(ln_)).concat("/").concat(ln_);
        return sub;
    }
    public static String sin(String sub)
    {
        sub = sub.replace(sub.substring(sub.indexOf("sin"), sub.indexOf("sin")+3), "cos");
        return sub;
    }
    public static String cos(String sub)
    {
        sub = "-".concat(sub.replace(sub.substring(sub.indexOf("cos"), sub.indexOf("cos")+3), "sin"));
        return sub;
    }
    public static String x_x(String sub)
    {
        String _by = sub.substring(0, sub.indexOf("&"));
        String by_ = sub.substring(sub.indexOf("&")+1);
        sub = "(".concat(manager(_by)).concat("*").concat(by_).concat("+").concat(manager(by_)).concat("*").concat(_by).concat(")");
        return sub;
    }
    public static String x$x(String sub)
    {
        String _div = sub.substring(0, sub.indexOf("\\"));
        String div_ = sub.substring(sub.indexOf("\\")+1);
        sub = div_.concat("*").concat(manager(_div)).concat("-").concat(_div).concat("*").concat(manager(div_)).concat("/");
        if(!div_.startsWith("(")) div_ = "(".concat(div_).concat(")^[2]");
        else
        {
            double div_p = Double.valueOf(div_.substring(div_.lastIndexOf("[")+1, div_.lastIndexOf("]"))) + 2;
            div_ = div_.substring(0, div_.lastIndexOf("^")+1).concat("[").concat(String.valueOf(div_p)).concat("]");
        }
        sub = sub.concat(div_);
        return sub;
    }
    public static String inX(String sub)
    {
        String xIn = sub.substring(sub.indexOf("("), sub.lastIndexOf(")")+1);
        sub = sub.replace(xIn, "x");
        sub = x(sub);
        String hold = sub.substring(sub.indexOf("x")+1);
        sub = sub.substring(0, sub.indexOf("x")).concat(xIn).concat(hold).concat("*");
        xIn = xIn.substring(1, xIn.length()-1);
        sub = sub.concat(manager(xIn)).strip();
        return sub;
    }
}