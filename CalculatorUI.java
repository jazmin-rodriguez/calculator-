package calculator;
import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
//import java.awt.Dimension;
//import java.awt.*;

//import javax.swing.Box;
//import javax.swing.BoxLayout;




import drawing.DrawingComponent;

/**
 * CalculatorUI class that creates and adds buttons, event handling for the buttons, and calls calculator
 * methods and functions for logic when necessary
 * @author Soria, Steckler, Tesic, Metsis
 */

public class CalculatorUI implements ActionListener {
	public final JFrame frame;
	public final JPanel panel;
	public final JTextArea text;
	public final JButton jButtons[], cancel; //add, sub, mult, div, equal, cancel, sqrRt, sqr, inverse, cos, sin, tan;
	public final String[] buttonValue = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	public final Calculator calc;
	
	private final PrimitiveOperatorPanel binaryOperatorPanel;
    private final TrigonometricPanel trigonometricPanel;
    private final UnaryFunctionPanel unaryFunctionPanel;
	/**
	 * The main top level GUI of the calculator and it's frame, button, and text area for # display
	 */
	public CalculatorUI(Calculator calculator) {
		frame = new JFrame("Graphing Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		panel = new JPanel(new BorderLayout());
		text = new JTextArea(2, 25);
		jButtons = new JButton[10];
		DrawingComponent drawingComponent = new DrawingComponent();
		

		for (int i = 0; i < 10; i++) {
			jButtons[i] = new JButton(String.valueOf(i));
		}

		binaryOperatorPanel = new PrimitiveOperatorPanel(this);
        trigonometricPanel = new TrigonometricPanel(this);
        unaryFunctionPanel = new UnaryFunctionPanel(this);

		// add = new JButton("+");
		// sub = new JButton("-");
		// mult = new JButton("*");
		// div = new JButton("/");
		// equal = new JButton("=");
		// sqrRt = new JButton("√");
		// sqr = new JButton("x*x");
		// inverse = new JButton("1/x");
		// cos = new JButton("Cos");
		// sin = new JButton("Sin");
		// tan = new JButton("Tan");
		cancel = new JButton("C");

		drawingComponent.createDrawingUI(frame);
		frame.pack();
		frame.revalidate(); 
		calc = calculator;
	}

	/**
	 * Initializes and sets the frame size, buttons, panels. The main runner method of the UI class.
	 */
	public void init() {
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(new BorderLayout());
        panel.add(text, BorderLayout.NORTH);
        panel.add(createButtonPanel(), BorderLayout.CENTER);
        panel.add(createFunctionPanel(), BorderLayout.WEST);
        panel.add(createOperatorPanel(), BorderLayout.EAST);
        panel.add(cancel, BorderLayout.SOUTH);

        trigonometricPanel.setButtonBackgrounds(Color.BLUE);
        unaryFunctionPanel.setButtonBackgrounds(Color.GREEN);
        binaryOperatorPanel.setButtonBackgrounds(Color.ORANGE);
        cancel.setBackground(Color.RED);

        cancel.addActionListener(this);

        frame.setVisible(true);
		}

		// // add operand buttons
		// // panel.add(add);
		// // panel.add(sub);
		// // panel.add(mult);
		// // panel.add(div);
		// // panel.add(sqr);
		// // panel.add(sqrRt);
		// // panel.add(inverse);
		// // panel.add(cos);
		// // panel.add(sin);
		// // panel.add(tan);
		// // // panel.add(equal);
		// //  panel.add(cancel);
		// //  panel.add(binaryOperatorPanel);
		// //  panel.add(trigonometricPanel);
		// //  panel.add(unaryFunctionPanel);
		// // // add event listeners
		// // add.addActionListener(this);
		// // sub.addActionListener(this);
		// // mult.addActionListener(this);
		// // div.addActionListener(this);
		// // sqr.addActionListener(this);
		// // sqrRt.addActionListener(this);
		// // inverse.addActionListener(this);
		// // cos.addActionListener(this);
		// // sin.addActionListener(this);
		// // tan.addActionListener(this);
		// // equal.addActionListener(this);
		// cancel.addActionListener(this);

		 //frame.setVisible(true);

	//}
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(4, 3, 5, 5));
		for (int i = 1; i <= 9; i++) {
			buttonPanel.add(jButtons[i]);
			jButtons[i].addActionListener(this);
		}
		buttonPanel.add(jButtons[0]);
		jButtons[0].addActionListener(this);
		return buttonPanel;
	}
	
	private JPanel createFunctionPanel() {
		JPanel functionPanel = new JPanel(new GridLayout(3, 1, 0, 5));
		functionPanel.add(trigonometricPanel);
		functionPanel.add(Box.createVerticalStrut(5));
		functionPanel.add(unaryFunctionPanel);
		return functionPanel;
	}
	
	private JPanel createOperatorPanel() {
		JPanel operatorPanel = new JPanel(new GridLayout(1, 1));
		operatorPanel.add(binaryOperatorPanel);
		return operatorPanel;
	}
	
	

	/**
	 * Event handling implementation for Calculator button pressing
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();
		// check 0-9 and update textfield
		for (int i = 0; i < 10; i++) {
			if (source == jButtons[i]) {
				text.replaceSelection(buttonValue[i]);
				return;
			}
		}

		// Check if the source is from the binary operator panel
		if (binaryOperatorPanel.isBinaryOperatorButton(source)) {
			JButton button = (JButton) source;
			Calculator.twoOperator operator = binaryOperatorPanel.getOperatorForButton(button);
			writer(calc.twoOpCaller(operator, reader()));
			return;
		}
	
		// Check if the source is from the trigonometric panel
		if (trigonometricPanel.isTrigonometricButton(source)) {
			JButton button = (JButton) source;
			Calculator.singleOperator operator = trigonometricPanel.getOperatorForButton(button);
			writer(calc.calcScience(operator, reader()));
			return;
		}
	
		// Check if the source is from the unary function panel
		if (unaryFunctionPanel.isUnaryFunctionButton(source)) {
			JButton button = (JButton) source;
			Calculator.singleOperator operator = unaryFunctionPanel.getOperatorForButton(button);
			writer(calc.calcScience(operator, reader()));
			return;
		}
		// if (source == add) {
		// 	writer(calc.twoOpCaller(Calculator.twoOperator.add, reader()));
		// }
		// if (source == sub) {
		// 	writer(calc.twoOpCaller(Calculator.twoOperator.subtract, reader()));
		// }
		// if (source == mult) {
		// 	writer(calc.twoOpCaller(Calculator.twoOperator.multiply,
		// 			reader()));
		// }
		// if (source == div) {
		// 	writer(calc.twoOpCaller(Calculator.twoOperator.divide, reader()));
		// }
		// if (source == sqr) {
		// 	writer(calc.calcScience(Calculator.singleOperator.square,
		// 			reader()));
		// }
		// if (source == sqrRt) {
		// 	writer(calc.calcScience(Calculator.singleOperator.squareRoot,
		// 			reader()));
		// }
		// if (source == inverse) {
		// 	writer(calc.calcScience(
		// 			Calculator.singleOperator.oneDevidedBy, reader()));
		// }
		// if (source == cos) {
		// 	writer(calc.calcScience(Calculator.singleOperator.cos,
		// 			reader()));
		// }
		// if (source == sin) {
		// 	writer(calc.calcScience(Calculator.singleOperator.sin,
		// 			reader()));
		// }

		// if (source == tan) {
		// 	writer(calc.calcScience(Calculator.singleOperator.tan,
		// 			reader()));
		// }
		// if (source == equal) {
		// 	writer(calc.calculateEqual(reader()));
		// }
		if (source == cancel) {
			writer(calc.reset());
			return;
		}
		// for easy continued calculations/copy
		text.selectAll();
	}

	/**
	 * Helper function that gets the texfield area and updates the number input
	 * @return the updated number
	 */
	public Double reader() {
		Double num;
		String str;
		str = text.getText();
		num = Double.valueOf(str);

		return num;
	}

	/**
	 * Helper function that sets the textfield with the number, and avoids NaN issues
	 * @param num
	 */
	public void writer(final Double num) {
		if (Double.isNaN(num)) {
			text.setText("");
		} else {
			text.setText(Double.toString(num));
		}
	}
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		CalculatorUI ui = new CalculatorUI(calculator);
		ui.init(); // Call the init() method to initialize and display the UI
	}
	
}