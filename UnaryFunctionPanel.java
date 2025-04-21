// UnaryFunctionPanel.java

package calculator;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.event.ActionListener;

public class UnaryFunctionPanel extends JPanel {
    public final JButton sqrRt, sqr, inverse;

    public UnaryFunctionPanel(ActionListener actionListener) {
        setLayout(new GridLayout(3, 1));

        sqrRt = new JButton("√");
        sqr = new JButton("x*x");
        inverse = new JButton("1/x");

        sqrRt.addActionListener(actionListener);
        sqr.addActionListener(actionListener);
        inverse.addActionListener(actionListener);

        add(sqrRt);
        add(sqr);
        add(inverse);
    }
    public void setButtonBackgrounds(Color color) {
        sqrRt.setBackground(color);
        sqr.setBackground(color);
        inverse.setBackground(color);
    }
    public boolean isUnaryFunctionButton(Object source) {
        return source == sqrRt || source == sqr || source == inverse;
    }

    public Calculator.singleOperator getOperatorForButton(JButton button) {
        if (button == sqrRt) {
            return Calculator.singleOperator.squareRoot;
        } else if (button == sqr) {
            return Calculator.singleOperator.square;
        } else if (button == inverse) {
            return Calculator.singleOperator.oneDevidedBy; 
        }
        return null; // Or throw an exception if none of the buttons match
    }
}
