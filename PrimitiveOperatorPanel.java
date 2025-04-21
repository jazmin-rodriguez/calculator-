package calculator;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;

public class PrimitiveOperatorPanel extends JPanel {
    public final JButton add, sub, mult, div, equal;

    public PrimitiveOperatorPanel(ActionListener actionListener) {
        setLayout(new GridLayout(5, 1));
        
        add = new JButton("+");
        sub = new JButton("-");
        mult = new JButton("*");
        div = new JButton("/");
        equal = new JButton("=");

        add.addActionListener(actionListener);
        sub.addActionListener(actionListener);
        mult.addActionListener(actionListener);
        div.addActionListener(actionListener);
        equal.addActionListener(actionListener);

        add(add);
        add(sub);
        add(mult);
        add(div);
        add(equal);
    }
    public void setButtonBackgrounds(Color color) {
        add.setBackground(color);
        sub.setBackground(color);
        mult.setBackground(color);
        div.setBackground(color);
        equal.setBackground(color);
    }

    public boolean isBinaryOperatorButton(Object source) {
        // Check if the source is a binary operator button
        return source == add || source == sub || source == mult || source == div || source == equal;
    }

    public Calculator.twoOperator getOperatorForButton(JButton button) {
        // Check which button was clicked and return the corresponding operator
        if (button == add) {
            return Calculator.twoOperator.add;
        } else if (button == sub) {
            return Calculator.twoOperator.subtract;
        } else if (button == mult) {
            return Calculator.twoOperator.multiply;
        } else if (button == div) {
            return Calculator.twoOperator.divide;
        } else if (button == equal) {
            return Calculator.twoOperator.normal; // Return a special operator for the equal sign
        } else {
            return null; // Return null for buttons that are not binary operators
        }
    }
    
}