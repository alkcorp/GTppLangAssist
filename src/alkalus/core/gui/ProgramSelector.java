package alkalus.core.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import net.alkalus.api.objects.misc.AcLog;
import net.alkalus.core.locale.LocaleCache;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class ProgramSelector {

	public JFrame mFrame;
	public static final LogicThread mInstance;	
	protected static final LocaleCache LOCALE;
	
	static {
		mInstance = new LogicThread();		
		Locale locale = Locale.getDefault();
		String lang = locale.getDisplayLanguage();
		String country = locale.getDisplayCountry();
		String built1 = lang+"_"+country;
		country = System.getProperty("user.country"); 
		lang = System.getProperty("user.language");
		String built2 = lang+"_"+country;		
		if (built1 != null && built2 != null && built1.equals(built2)) {
			AcLog.INFO("Creating Locale cache Using: "+built1+".");
			LOCALE = new LocaleCache(built1);			
		}
		else {
			AcLog.INFO("Creating Locale cache using default locale. Found "+built1+", "+built2+".");
			LOCALE = new LocaleCache();			
		}
		
		
	}
	
	
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(mInstance);
	}

	/**
	 * Create the application.
	 */
	public ProgramSelector() {
		initialize();
	}
	
	private JTabbedPane tabbedPane;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mFrame = new JFrame();
		mFrame.setBounds(100, 100, 450, 300);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton(LOCALE.getLocalizedString("button.go"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int aTab = tabbedPane.getSelectedIndex();
				
				if (aTab < 0) {
					//Do Nothing
				}
				else if (aTab == 0) {
					LocaleCopier.main();
					mFrame.setVisible(false);
				}
				else if (aTab == 1) {
					MainWindow.main();
					mFrame.setVisible(false);
					
				}
				else if (aTab == 3) {
					
				}
				else {
					//Do Nothing
				}
				
			}
		});
		mFrame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		JLabel lblTextselectprogram = new JLabel(LOCALE.getLocalizedString("text.info.selectprogram"));
		lblTextselectprogram.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextselectprogram.setFont(new Font("Malgun Gothic", Font.PLAIN, 31));
		mFrame.getContentPane().add(lblTextselectprogram, BorderLayout.NORTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab(LOCALE.getLocalizedString("menu.tab.1"), null, panel, null);		
		JTextArea txtrDesca = new JTextArea();
		txtrDesca.setText(LOCALE.getLocalizedString("menu.tab.1.info"));
		txtrDesca.setEditable(false);
		txtrDesca.setLineWrap(true);
		txtrDesca.setWrapStyleWord(true);
		panel.add(txtrDesca);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab(LOCALE.getLocalizedString("menu.tab.2"), null, panel_1, null);		
		JTextArea txtrDescb = new JTextArea();
		txtrDescb.setText(LOCALE.getLocalizedString("menu.tab.2.info"));
		txtrDescb.setEditable(false);
		txtrDescb.setLineWrap(true);
		txtrDescb.setWrapStyleWord(true);
		panel_1.add(txtrDescb);
		
		
		
		
		
		

		mFrame.setVisible(true);
	}

}
