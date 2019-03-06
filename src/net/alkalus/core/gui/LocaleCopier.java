package net.alkalus.core.gui;

import static net.alkalus.core.gui.ProgramSelector.LOCALE;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import net.alkalus.api.objects.data.AutoMap;
import net.alkalus.api.objects.misc.AcLog;
import net.alkalus.core.locale.LocaleCache;
import net.alkalus.core.util.data.FileUtils;
import net.alkalus.core.util.data.StringUtils;

public class LocaleCopier {

	private JFrame frmAlkcoreLangAssistant;
	private JLabel lblStatus;
	private JLabel lblFilename;
	private JLabel lblFilename_1;	
	private JLabel lblNewLabel;
	
	private File[] mLocaleFiles = new File[3];

	@Override
	protected void finalize() throws Throwable {
		ProgramSelector.mInstance.mMainGuiInstance.mFrame.setVisible(true);;
		super.finalize();
	}

	
	public static void main() {
		main(new String[] {});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocaleCopier window = new LocaleCopier();
					window.frmAlkcoreLangAssistant.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LocaleCopier() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAlkcoreLangAssistant = new JFrame();
		frmAlkcoreLangAssistant.setTitle("AlkCore Lang Assistant");
		frmAlkcoreLangAssistant.setResizable(false);
		frmAlkcoreLangAssistant.setBounds(100, 100, 420, 281);
		frmAlkcoreLangAssistant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlkcoreLangAssistant.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmAlkcoreLangAssistant.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab(LOCALE.getLocalizedString("menu.tab.1"), null, panel, null);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		


		lblFilename = new JLabel("");
		panel.add(lblFilename, "6, 2");
		
		lblFilename_1 = new JLabel("");
		panel.add(lblFilename_1, "6, 4");		
		
		JLabel lblFile = new JLabel(LOCALE.getLocalizedString("text.info.file.1"));
		panel.add(lblFile, "2, 2");
		
		JButton btnSelect = new JButton(LOCALE.getLocalizedString("button.select.1"));
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mLocaleFiles[0] = getFileFromSystem();
				if (mLocaleFiles[0] == null) {
					lblFilename.setText(LOCALE.getLocalizedString("text.info.badfile"));
				} else {
					lblFilename.setText(mLocaleFiles[0].getName());
				}
			}
		});
		panel.add(btnSelect, "4, 2, left, default");
		
		JLabel lblFile_1 = new JLabel(LOCALE.getLocalizedString("text.info.file.2"));
		panel.add(lblFile_1, "2, 4");
		
		JButton btnSelect_1 = new JButton(LOCALE.getLocalizedString("button.select.2"));
		btnSelect_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mLocaleFiles[1] = getFileFromSystem();
				if (mLocaleFiles[1] == null) {
					lblFilename_1.setText(LOCALE.getLocalizedString("text.info.badfile"));
				} else {
					lblFilename_1.setText(mLocaleFiles[1].getName());
				}
			}
		});
		panel.add(btnSelect_1, "4, 4, left, default");
		
		JButton btnButtongo = new JButton(LOCALE.getLocalizedString("button.go"));
		btnButtongo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				if (mLocaleFiles[0] != null && mLocaleFiles[1] != null  && mLocaleFiles[2] != null) {
					lblStatus.setText("GOOD");	
					generateNewLocaleFile();
				}
				else {
					lblStatus.setText("BAD");
				}				
			}
		});
		
		lblNewLabel = new JLabel(LOCALE.getLocalizedString("text.info.file.3"));
		panel.add(lblNewLabel, "2, 10");
		
		JButton btnNewButton = new JButton(LOCALE.getLocalizedString("button.save"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mLocaleFiles[2] = saveFileToSystem();
				if (mLocaleFiles[2] == null) {
					//lblNewLabel.setText(LOCALE.getLocalizedString("text.info.badfile"));
				} else {
					//lblNewLabel.setText(mLocaleFiles[2].getName());
				}
			}
		});
		panel.add(btnNewButton, "4, 10");
		panel.add(btnButtongo, "2, 14");
		
		lblStatus = new JLabel("");
		panel.add(lblStatus, "4, 14");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab(LOCALE.getLocalizedString("menu.tab.2"), null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab(LOCALE.getLocalizedString("menu.tab.3"), null, panel_2, null);
	}
	
	
	
	private File getFileFromSystem() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setDialogTitle("Select File");
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Language Files [.lang/.txt]", "lang", "txt");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File files = jfc.getSelectedFile();	
			
			if (files != null) {
				return files;
			}
			
			/*if (files.length > 0 && files[0] != null) {
				return files[0];				
			}*/
		}
		return null;
	}
	
	private File saveFileToSystem() {		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setDialogTitle("Save As..");	
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Language Files [.lang]", "lang");
		jfc.addChoosableFileFilter(filter);	
	    int retrival = jfc.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
			File files = jfc.getSelectedFile();			
			if (files != null) {
				return files;
			}
		}
		return null;		
	}
	
	
	private final void generateNewLocaleFile() {
		try {
			net.alkalus.core.gui.ProgramSelector.logToFile("Trying to save new locale data to system.");
            //FileWriter fw = new FileWriter(mLocaleFiles[2]);
            
            Map<String, String> aLocaleMap = new LinkedHashMap<String, String>();
            AutoMap<String> aKeys = new AutoMap<String>();
            
            //Iterate all Data One
            AutoMap<String> x1 = FileUtils.readLines(mLocaleFiles[0]);
			net.alkalus.core.gui.ProgramSelector.logToFile("Read first locale file ["+mLocaleFiles[0].getName()+"]");           
            
            //Iterate all Data Two
            AutoMap<String> x2 = FileUtils.readLines(mLocaleFiles[1]);
			net.alkalus.core.gui.ProgramSelector.logToFile("Read second locale file ["+mLocaleFiles[1].getName()+"]");   

			net.alkalus.core.gui.ProgramSelector.logToFile("Inserting Key Data.");
			
            //Copy Keys 
			int r1 = 0;
            for (String i1 : x1) {
            	String[] aSegment = i1.split("=");
            	if (aSegment != null && aSegment.length > 1) {
                	aLocaleMap.put(aSegment[0], aSegment[1]);  
                	aKeys.put(aSegment[0]);
            	} 
            	else {
                	aLocaleMap.put("PLACEHOLDER-"+r1, i1);  
                	aKeys.remove(i1);            		
            	}
            	r1++;
            }
			net.alkalus.core.gui.ProgramSelector.logToFile("Inserted "+aLocaleMap.size()+" keys, now to overwrite them with the secondary locale data. Iterated "+r1+" times.");
            
            //Override existing keys with disered locale
			int r2 = 0;
            for (String i1 : x2) {
            	String[] aSegment = i1.split("=");
            	if (aSegment != null && aSegment.length > 1) {
                	aLocaleMap.put(aSegment[0], aSegment[1]); 
                	r2++;
                	aKeys.remove(aSegment[0]);
            	}
            	else {
                	aLocaleMap.put("PLACEHOLDER-"+21, i1); 
                	aKeys.remove(i1);            		
            	}
            } 
			net.alkalus.core.gui.ProgramSelector.logToFile("Reinserted "+r2+"/"+aLocaleMap.size()+" keys");     

            //Sort it?
            //aLocaleMap = ArrayUtils.sortMapByValues(aLocaleMap);
			
			net.alkalus.core.gui.ProgramSelector.logToFile("Saving new Locale file to "+mLocaleFiles[2].getAbsolutePath()); 
            

			if (FileUtils.doesFileExist(mLocaleFiles[2])) {
				FileUtils.removeFile(mLocaleFiles[2]);
			}			
			if (!FileUtils.doesFileExist(mLocaleFiles[2])) {				
				mLocaleFiles[2].createNewFile();
			}
			
			
            //Now we remap this to file
            for (String g : aLocaleMap.keySet()) {
            	String aLocaleString = g+"="+aLocaleMap.get(g);
            	FileUtils.appendLineToFile(mLocaleFiles[2], aLocaleString);
            	FileUtils.appendLineToFile(mLocaleFiles[2], StringUtils.linebreak);
    			net.alkalus.core.gui.ProgramSelector.logToFile("Wrote '"+aLocaleString+"' to file"); 
            }
            
            for (String h : aKeys) {
    			net.alkalus.core.gui.ProgramSelector.logToFile("Key: '"+h+"' was missing from "+mLocaleFiles[1].getName()+", however it existed in "+mLocaleFiles[0].getName()+", so it has been added.");             	
            }
            
            
            net.alkalus.core.gui.ProgramSelector.logToFile("Completed writing data to "+mLocaleFiles[2].getAbsolutePath());
            //fw.write(sb.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }		
	}
	
	
	

}
