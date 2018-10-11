import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;

import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class MainJFrame extends JFrame {

	private int width, height;
	private Dimension dimension;
	private int marginX, marginY;
	private JPanel mainPanel, canvas, buttonPanel;
	
	final static int minThreads = 30;
	static int threadsCounter = 0;
	private static ArrayList<MyThread> threads = new ArrayList<MyThread>();
	private int state = 0;
	private boolean stoppedThreads = true;
	
	private javax.swing.Timer timer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
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
	public MainJFrame() {
		state = 0;
		setScreenSize();
		setJFrame(this);
		initMainParameters();
		initComponents(this);
		initThreads();
		initAnimation();
		runThreads();
		
	}

	
	public void setScreenSize() {
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)(dimension.getWidth()/2);
		height = (int)(dimension.getHeight()/2);
		marginX = (int) (dimension.getWidth() - width)/2;
		marginY = (int) (dimension.getHeight() - height)/2;
	}
	
	public void setJFrame(MainJFrame frame) {
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(width, height));
		frame.getContentPane().setLayout(new BorderLayout(0,0));
		frame.setBounds(marginX, marginY, width, height);
		frame.setSize(width, height);
		frame.setResizable(false);
		
	}
	
	public void initMainParameters() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSizeAndScreen();
	}
	
	public void initComponents(MainJFrame frame) {
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(51, 51, 51));
		mainPanel.setForeground(new Color(0, 0, 0));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		//=========================
		// CANVAS

		canvas = new CanvasComponent(threads);
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.setForeground(Color.WHITE);
		mainPanel.add(canvas);
		/*
		GridBagLayout gbl_canvas = new GridBagLayout();
		gbl_canvas.columnWidths = new int[]{0};
		gbl_canvas.rowHeights = new int[]{0};
		gbl_canvas.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_canvas.rowWeights = new double[]{Double.MIN_VALUE};
		canvas.setLayout(gbl_canvas);
		*/
		
		//=========================
		// BTN PANEL
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(51, 51, 51));
		buttonPanel.setBorder(new EmptyBorder(0, 0, 25, 0));
		mainPanel.add(buttonPanel);
		
		// BTNS
		
		JButton btnStop = new JButton("Stop");

		JButton btnAdd = new JButton("Add new");
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton btnResume = new JButton("Resume");
		
		//BUTTON PANEL - LAYOUT
		
		buttonPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnStop, btnAdd, btnResume}));
		GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
		gl_buttonPanel.setHorizontalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(btnResume, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
		);
		gl_buttonPanel.setVerticalGroup(
			gl_buttonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnResume, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
		);
		buttonPanel.setLayout(gl_buttonPanel);
		
		
		//BTN ACTIONS
		
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				state=0;
				timer.stop();
			}
		});

		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addNewThread();
			}
		});
		
		btnResume.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				state=1;
				timer.start();
			}
		});
		
		
	}
	
	public void setSizeAndScreen() {
		setBounds(marginX, marginY, width, height);
		setSize(width, height);
	}
	
	public void initThreads() {
		for(int i=0; i<minThreads; i++) {
			threads.add(i, new MyThread(threadsCounter++, width,height));
		}
	}
	
	
	public void runThreads() {
		state=1;
		timer.start();
		
	}
	
	public void stopAllThreads() {
		for(int i=0; i<threadsCounter; i++) {
			threads.get(i).stop();
		}}
	
	public void addNewThread() {
		threads.add(threadsCounter, new MyThread(threadsCounter++, canvas.getWidth(), canvas.getHeight()));
	}
	
	public void resumeAllThreads() {
		for(int i=0; i<threadsCounter; i++) {
			threads.get(i).resume();
		}
	}
	
	public void waitForAllThreads() {
		for(int i=0; i<minThreads; i++) {
			threads.get(i).join();
		}
	}
	
	public void initAnimation() {
		
		timer= new javax.swing.Timer(1, new ActionListener() {
			//swingWorkter - inny animator
			int licznik=0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(licznik++);
				switch(state) {
				//stop
				case 0:
					//System.out.println("stop "+licznik++);
					if(!stoppedThreads) {
						
						stopAllThreads();
						stoppedThreads=true;
					}
					break;
				//dziala		
				case 1:
					//System.out.println("run " + licznik++);
					waitForAllThreads();
					resumeAllThreads();
					if(stoppedThreads) {
						stoppedThreads=false;
					}
					break;
				default:
					break;
			}
			invalidate();
			repaint();

			}
		});
	}
}
