package alkalus.core.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import gtPlusPlus.core.util.Utils;
import net.alkalus.api.objects.misc.AcLog;
import net.alkalus.core.locale.LocaleCache;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JTextArea textArea = new JTextArea();
	private static LocaleCache LOCALE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LOCALE = new LocaleCache();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		LOCALE.dumpLocaleMappings();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Full Material", "Custom Material", "Ore", "Ions" }));
		frame.getContentPane().add(comboBox, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String aMode = (String) comboBox.getSelectedItem();

				textArea.setText("");

				if (aMode.toLowerCase().contains("ore")) {
					String mName = textField.getText();
					mName = (mName == null || mName.length() <= 0 ? "ERROR" : mName);
					String mNameFormatted = Utils.sanitizeString(mName);
					textArea.setText("tile.Ore" + mNameFormatted + ".name=" + mName + " Ore" + "\n" + "item.crushed"
							+ mNameFormatted + ".name=Crushed " + mName + " Ore" + "\n" + "item.crushedCentrifuged"
							+ mNameFormatted + ".name=Centrifuged Crushed " + mName + " Ore" + "\n"
							+ "item.crushedPurified" + mNameFormatted + ".name=Purified Crushed " + mName + " Ore"
							+ "\n" + "item.dustImpure" + mNameFormatted + ".name=Impure " + mName + " Dust" + "\n"
							+ "item.dustPure" + mNameFormatted + ".name=Purified " + mName + " Dust" + "\n"
							+ "item.itemDust" + mNameFormatted + ".name=" + mName + " Dust");
				} else if (aMode.toLowerCase().contains("ion")) {

					String[] aElements = new String[] { "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon",
							"Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminum", "Silicon",
							"Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium", "Scandium", "Titanium",
							"Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc",
							"Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium",
							"Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium",
							"Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium",
							"Iodine", "Xenon", "Cesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium",
							"Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium",
							"Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium",
							"Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead", "Bismuth",
							"Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Thorium",
							"Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium", "Berkelium",
							"Californium", "Einsteinium", "Fermium", "Mendelevium", "Nobelium", "Lawrencium",
							"Rutherfordium", "Dubnium", "Seaborgium", "Bohrium", "Hassium", "Meitnerium",
							"Darmstadtium", "Roentgenium", "Copernicium", "Nihonium", "Flerovium", "Moscovium",
							"Livermorium", "Tennessine", "Oganesson" };
					String[] aBoson = new String[] { "Graviton", "Up", "Down", "Charm", "Strange", "Top", "Bottom",
							"Electron", "Electron Neutrino", "Muon", "Muon Neutrino", "Tau", "Tau Neutrino", "Gluon",
							"Photon", "Z Boson", "W Boson", "Higgs Boson", "Proton", "Neutron", "Lambda", "Omega",
							"Pion", "ETA Meson", };
					String aResult = "";
					for (String s : aElements) {
						aResult += ("item.particle" + ".ion." + s + ".name=" + s + " Ion" + "\n");
					}
					for (String s : aBoson) {
						aResult += ("item.particle" + ".base." + s + ".name=" + s + "\n");
					}
					textArea.setText(aResult);
				} 
				else if (aMode.toLowerCase().contains("custom")) {
					String mName = textField.getText();
					mName = (mName == null || mName.length() <= 0 ? "ERROR" : mName);
					String mNameFormatted = Utils.sanitizeString(mName);
					textArea.setText("item.itemIngot" + mNameFormatted + ".name=" + mName + " Ingot" + "\n"
							+ "item.itemHotIngot" + mNameFormatted + ".name=Hot " + mName + " Ingot" + "\n"
							+ "item.itemDust" + mNameFormatted + ".name=" + mName + " Dust" + "\n" + "item.itemDustTiny"
							+ mNameFormatted + ".name=Tiny Pile of " + mName + " Dust" + "\n" + "item.itemDustSmall"
							+ mNameFormatted + ".name=Small Pile of " + mName + " Dust" + "\n" + "item.itemNugget"
							+ mNameFormatted + ".name=" + mName + " Nugget" + "\n" + "item.itemPlate" + mNameFormatted
							+ ".name=" + mName + " Plate" + "\n" + "item.itemPlateDouble" + mNameFormatted
							+ ".name=Double " + mName + " Plate" + "\n" + "item.itemBolt" + mNameFormatted + ".name="
							+ mName + " Bolt" + "\n" + "item.itemRod" + mNameFormatted + ".name=" + mName + " Rod"
							+ "\n" + "item.itemRodLong" + mNameFormatted + ".name=Long " + mName + " Rod" + "\n"
							+ "item.itemRing" + mNameFormatted + ".name=" + mName + " Ring" + "\n" + "item.itemScrew"
							+ mNameFormatted + ".name=" + mName + " Screw" + "\n" + "item.itemRotor" + mNameFormatted
							+ ".name=" + mName + " Rotor" + "\n" + "item.itemGear" + mNameFormatted + ".name=" + mName
							+ " Gear" + "\n" + "item.itemCell" + mNameFormatted + ".name=" + mName + " Cell" + "\n"
							+ "tile.Block of " + mName + ".name=Block of " + mName + "" + "\n" + "tile."
							+ mName + " Frame Box.name=" + mName + " Frame Box" + "\n" + 
							"item.itemDustTiny"+mNameFormatted+".name=Tiny Pile of "+mName+" Dust" + "\n" + 
							"item.itemDustSmall"+mNameFormatted+".name=Small Pile of "+mName+" Dust");

				}
				else {

					String[] aOreString = new String[] {
							"Irarsite",
							"Miessiite",
							"Comancheite",
							"Koboldite",
							"Perroudite",
							"Demicheleite",
							"Alburnite",
							"Lautarite",
							"BariteRd",
							"Honeaite",
							"Lafossaite",
							"Kashinite",
							"RadioactiveMineralMix",
					};
					String aReiss = "";
					for (String h : aOreString) {

						String mName = h;
						mName = (mName == null || mName.length() <= 0 ? "ERROR" : mName);
						String mNameFormatted = Utils.sanitizeString(mName);
						aReiss += ("tile.Ore" + mNameFormatted + ".name=" + mName + " Ore" + "\n" + "item.crushed"
								+ mNameFormatted + ".name=Crushed " + mName + " Ore" + "\n" + "item.crushedCentrifuged"
								+ mNameFormatted + ".name=Centrifuged Crushed " + mName + " Ore" + "\n"
								+ "item.crushedPurified" + mNameFormatted + ".name=Purified Crushed " + mName + " Ore"
								+ "\n" + "item.dustImpure" + mNameFormatted + ".name=Impure " + mName + " Dust" + "\n"
								+ "item.dustPure" + mNameFormatted + ".name=Purified " + mName + " Dust" + "\n"
								+ "item.itemDust" + mNameFormatted + ".name=" + mName + " Dust" + "\n" + 
								"item.itemDustTiny"+mNameFormatted+".name=Tiny Pile of "+mName+" Dust" + "\n" + 
								"item.itemDustSmall"+mNameFormatted+".name=Small Pile of "+mName+" Dust"+ "\n" + "\n" );
					}

					textArea.setText(aReiss);
				}
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);

		frame.getContentPane().add(textArea, BorderLayout.CENTER);

		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.WEST);
		textField.setColumns(10);
	}

}
