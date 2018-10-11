import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JFrame extends javax.swing.JFrame {

	private int width, height, marginX, marginY;
	private JPanel mainPanel, calculatorPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrame() {
		setScreenSize();
		setJFrame(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents(this);
	}

	public void setScreenSize() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		width = 300;
		height = 300;
	}
	
	public void setJFrame(JFrame frame) {
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setLayout(new BorderLayout(marginX,marginY));
		frame.setBounds(marginX, marginY, width, height);
		frame.setSize(width, height);
	}
	
	public void initComponents(JFrame frame) {
		mainPanel = new JPanel();
		
		//header=new
		//calc=new
		//main.add(header)
		calculatorPanel = new CalculatorPanel();
		mainPanel.add(calculatorPanel);
		setContentPane(mainPanel);
	}
	
}
