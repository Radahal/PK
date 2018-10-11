import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalculatorPanel extends JPanel {
	private JTextField textField;
	private String lastChar = "";
	private int countOpenedParenthesis=0;
	private boolean isPointInCurrentNumber = false;
	ReversPolishNotation ONP = new ReversPolishNotation("");
	/**
	 * Create the panel.
	 */
	public CalculatorPanel() {
		//============================================================
		setBorder(new EmptyBorder(10, 10, 10, 10));
		FlowLayout mainPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		mainPanel.setAlignOnBaseline(true);
		setLayout(mainPanel);
		
		Box mainVerticalBox = Box.createVerticalBox();
		mainVerticalBox.setToolTipText("");
		mainVerticalBox.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(mainVerticalBox);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainVerticalBox.add(headerPanel);
		GridBagLayout gbl_headerPanel = new GridBagLayout();
		gbl_headerPanel.columnWidths = new int[] {250};
		gbl_headerPanel.rowHeights = new int[] {35};
		gbl_headerPanel.columnWeights = new double[]{0.0};
		gbl_headerPanel.rowWeights = new double[]{0.0};
		headerPanel.setLayout(gbl_headerPanel);
		
		Box verticalBox_textField = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox_textField = new GridBagConstraints();
		gbc_verticalBox_textField.gridx = 0;
		gbc_verticalBox_textField.gridy = 0;
		headerPanel.add(verticalBox_textField, gbc_verticalBox_textField);
		
		JPanel buttonsPanel = new JPanel();
		mainVerticalBox.add(buttonsPanel);
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonsPanel.setLayout(new GridLayout(5, 4, 10, 10));
		
		//============================================================
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setAlignmentY(Component.TOP_ALIGNMENT);
		textField.setToolTipText("");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		verticalBox_textField.add(textField);
		textField.setColumns(23);
		
		//============================================================
		
		JButton button0 = new JButton("0");
		button0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String txt = textField.getText();
				if(!(txt.length()==1 && lastChar=="0")) {
					textField.setText(txt+"0");
					lastChar="0";
				}
				
			}
		});
		
		JButton buttonDividion = new JButton("/");
		buttonDividion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()>=1) {
					if( (lastChar!="/") && (lastChar!="*") && (lastChar!="-") && (lastChar!="+") && (lastChar!="(")) {
						textField.setText(txt+"/");
					} else {
						txt= txt.substring(0, txt.length()-1) + "/";
						textField.setText(txt);
					}
					lastChar="/";
					isPointInCurrentNumber=false;
				}
				
			}
		});

	
		JButton button7 = new JButton("7");
		button7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				textField.setText(txt+"7");
				lastChar="7";
				
			}
		});
		JButton buttonBP = new JButton("BP");
		buttonBP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				System.out.println(txt);
				if(txt.length()>1) {
					txt = txt.substring(0, txt.length()-1);
					System.out.println(txt);
					if(lastChar=="(")
						countOpenedParenthesis--;
					if(lastChar==")")
						countOpenedParenthesis++;
					lastChar= txt.substring(txt.length()-1,txt.length());
					
				} else {
					lastChar="";
					countOpenedParenthesis=0;
					isPointInCurrentNumber=false;
					txt="";
				}
						
				textField.setText(txt);
			}
		});
		
		
		JButton buttonCLR = new JButton("CLR");
		buttonCLR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				lastChar="";
				isPointInCurrentNumber=false;
				countOpenedParenthesis=0;
			}
		});
		
		JButton buttonParenthesis0 = new JButton("(");
		buttonParenthesis0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((lastChar=="(") || (lastChar=="/") || (lastChar=="*") || (lastChar=="-") || (lastChar=="+")) {
					String txt = textField.getText();
					textField.setText(txt+"(");
					lastChar="(";
					countOpenedParenthesis++;
					isPointInCurrentNumber=false;
				}
				
			}
		});
		
		JButton buttonParenthesis1 = new JButton(")");
		buttonParenthesis1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(countOpenedParenthesis>0) {
					if((lastChar!=".") && (lastChar!="/") && (lastChar!="*") && (lastChar!="-") && (lastChar!="+") && (lastChar!="(")) {
						String txt = textField.getText();
						textField.setText(txt+")");
						lastChar=")";
						countOpenedParenthesis--;
						isPointInCurrentNumber=false;
					}
				}
			}
		});

		
		
		JButton button8 = new JButton("8");
		button8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("8");
				} else 
					textField.setText(txt+"8");
				lastChar="8";
			}
		});
	
		
		JButton button9 = new JButton("9");
		button9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("9");
				} else 
					textField.setText(txt+"9");
				lastChar="9";
			}
		});
		
		
		JButton buttonAddition = new JButton("+");
		buttonAddition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()>=1) {
					if( (lastChar!="/") && (lastChar!="*") && (lastChar!="-") && (lastChar!="+") && (lastChar!="(")) {
						textField.setText(txt+"+");
					} else {
						txt= txt.substring(0, txt.length()-1) + "+";
						textField.setText(txt);
					}
					lastChar="+";
					isPointInCurrentNumber=false;
				}
			}
		});
		
		
		JButton button4 = new JButton("4");
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("4");
				} else 
					textField.setText(txt+"4");
				lastChar="4";
			}
		});
		
		
		JButton button5 = new JButton("5");
		button5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("5");
				} else 
					textField.setText(txt+"5");
				lastChar="5";
			}
		});
		
		
		JButton button6 = new JButton("6");
		button6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("6");
				} else 
					textField.setText(txt+"6");
				lastChar="6";
			}
		});
		
		
		JButton buttonSubstract = new JButton("-");
		buttonSubstract.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()>=1) {
					if( (lastChar!="/") && (lastChar!="*") && (lastChar!="-") && (lastChar!="+") && (lastChar!="(")) {
						textField.setText(txt+"-");
					} else {
						txt= txt.substring(0, txt.length()-1) + "-";
						textField.setText(txt);
					}
					lastChar="-";
					isPointInCurrentNumber=false;
				}
				else {
					textField.setText("-");
					lastChar="-";
					isPointInCurrentNumber=false;
				}
			}
		});
		
		
		JButton buttonMultiply = new JButton("*");
		buttonMultiply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()>=1) {
					if( (lastChar!="/") && (lastChar!="*") && (lastChar!="-") && (lastChar!="+") && (lastChar!="(")) {
						textField.setText(txt+"*");
					} else {
						txt= txt.substring(0, txt.length()-1) + "*";
						textField.setText(txt);
					}
					lastChar="*";
					isPointInCurrentNumber=false;
				}
			}
		});
		
		
		JButton button1 = new JButton("1");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("1");
				} else 
					textField.setText(txt+"1");
				lastChar="1";
			}
		});
		
		
		JButton button2 = new JButton("2");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("2");
				} else 
					textField.setText(txt+"2");
				lastChar="2";
			}
		});
		
		
		JButton button3 = new JButton("3");
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(txt.length()==1 && lastChar=="0") {
					textField.setText("3");
				} else if (lastChar!=")")
					textField.setText(txt+"3");
				lastChar="3";
			}
		});
		
		
		JButton buttonPoint = new JButton(".");
		buttonPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				if(!isPointInCurrentNumber) {
					if( (lastChar!="/") && (lastChar!="*") && (lastChar!="-") && (lastChar!="+") && (lastChar!="(") && (lastChar!=")") && (lastChar!="")) {
						textField.setText(txt+".");
						isPointInCurrentNumber=true;
					}
					
				}
				
			}
		});
		
		
		JButton buttonEqual = new JButton("=");
		buttonEqual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String txt = textField.getText();
				ONP.setNewStatement(txt);
				System.out.println(ONP.getRPNForm());
				textField.setText(String.valueOf(ONP.countRPNForm()));
			}
		});
		
		//============================================================
		
		button0.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonDividion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonBP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonCLR.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonParenthesis0.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonParenthesis1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonAddition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonSubstract.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonMultiply.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonPoint.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonEqual.setFont(new Font("Tahoma", Font.PLAIN, 12));

		//============================================================
		
		buttonsPanel.add(buttonBP);
		buttonsPanel.add(buttonCLR);
		buttonsPanel.add(buttonParenthesis0);
		buttonsPanel.add(buttonParenthesis1);
		buttonsPanel.add(button7);
		buttonsPanel.add(button8);
		buttonsPanel.add(button9);
		buttonsPanel.add(buttonAddition);
		buttonsPanel.add(button4);
		buttonsPanel.add(button5);
		buttonsPanel.add(button6);
		buttonsPanel.add(buttonSubstract);
		buttonsPanel.add(button1);
		buttonsPanel.add(button2);
		buttonsPanel.add(button3);
		buttonsPanel.add(buttonMultiply);
		buttonsPanel.add(buttonPoint);
		buttonsPanel.add(button0);
		buttonsPanel.add(buttonEqual);
		buttonsPanel.add(buttonDividion);
	}

}
