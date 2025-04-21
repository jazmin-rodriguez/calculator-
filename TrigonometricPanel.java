// TrigonometricPanel.java
package calculator;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class TrigonometricPanel extends JPanel {
    private final JButton cos, sin, tan, acos, asin, atan;

    public TrigonometricPanel(ActionListener actionListener) {
        setLayout(new GridLayout(6, 1));
        cos = new JButton("Cos");
        sin = new JButton("Sin");
        tan = new JButton("Tan");
        acos = new JButton("Cos⁻¹");
        asin = new JButton("Sin⁻¹");
        atan = new JButton("Tan⁻¹");

        cos.addActionListener(actionListener);
        sin.addActionListener(actionListener);
        tan.addActionListener(actionListener);
        acos.addActionListener(actionListener);
        asin.addActionListener(actionListener);
        atan.addActionListener(actionListener);

        add(cos);
        add(sin);
        add(tan);
        add(acos);
        add(asin);
        add(atan);
    }
    public void setButtonBackgrounds(Color color) {
        cos.setBackground(color);
        sin.setBackground(color);
        tan.setBackground(color);
    }

    public boolean isTrigonometricButton(Object source) {
        return source == cos || source == sin || source == tan ||
               source == acos || source == asin || source == atan;
    }

    public Calculator.singleOperator getOperatorForButton(JButton button) {
        if (button == cos) {
            return Calculator.singleOperator.cos;
        } else if (button == sin) {
            return Calculator.singleOperator.sin;
        } else if (button == tan) {
            return Calculator.singleOperator.tan;
        } else if (button == acos) {
            return Calculator.singleOperator.acos;
        } else if (button == asin) {
            return Calculator.singleOperator.asin;
        } else if (button == atan) {
            return Calculator.singleOperator.atan;
        }
        return null; // Or throw an exception if none of the buttons match
    }
}
