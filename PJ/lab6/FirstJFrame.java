import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FirstJFrame extends JFrame {

	private int width, height;
	private int marginX, marginY;
	private JPanel panelMain, panelFigure;
	private ArrayList<JPanel> panelFigures = new ArrayList<JPanel>();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstJFrame frame = new FirstJFrame();
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
	public FirstJFrame() {
		setScreenSize();
		setJFrame(this);
		initMainParameters();
		initComponents(this);
	}

	public void setScreenSize() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)(d.getWidth()/2);
		height = (int)(d.getHeight()/2);
		marginX = (int) (d.getWidth() - width)/2;
		marginY = (int) (d.getHeight() - height)/2;
	}
	
	public void setJFrame(FirstJFrame frame) {
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLayout(new BorderLayout(0,0));
		frame.setBounds(marginX, marginY, width, height);
		frame.setSize(width, height);
	}
	
	public void initMainParameters() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSizeAndScreen();
	}
	
	public void initComponents(FirstJFrame frame) {
		panelMain = new JPanel();
		panelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMain.setLayout(new BorderLayout(0, 0));

		panelFigure = new FigureComponent();
		panelFigure.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelFigure.setLayout(new BorderLayout(0, 0));
		panelFigure.setMinimumSize(new Dimension(width, height));

		
		panelFigures.add(panelFigure);
		
		for(int i=0;i<panelFigures.size();i++) {
			panelMain.add(panelFigures.get(i));
	    }
		setContentPane(panelMain);
		frame.pack();
	}
	
	public void setSizeAndScreen() {
		setBounds(marginX, marginY, width, height);
		setSize(width, height);
	}
	
}
