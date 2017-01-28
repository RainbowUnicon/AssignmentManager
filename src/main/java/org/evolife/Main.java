package org.evolife;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.google.common.io.Files;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class Main {
	public static final String CONFIG_FILENAME = "config.properties";
	public static final String WEB_ASSIGNMENT = "http://people.umass.edu/youngkyunlee/assignment";

	private JFrame frmCsPdfMerge;
	private JComboBox<String> typeComboBox;

	private JButton btnSetting;
	private JButton btnGenerate;
	private JButton btnWebsite;
	private JButton btnOpen;

	private Configuration config;
	private ActionListener listener;
	private ArrayList<File> selectedFiles;
	private JList<File> list;
	private JButton btnRemove;
	private JTextField lastNameTextField;
	private JPanel panel_5;
	private JSpinner spinner;
	private JTextField firstNameTextField;
	private Box horizontalBox;
	private Box verticalBox;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_4;
	private Component horizontalStrut_6;
	private Component horizontalStrut_5;
	private Component horizontalStrut_7;
	private Component horizontalStrut_8;
	private Box verticalBox_1;
	private Box horizontalBox_4;
	private Component verticalGlue;
	private Component horizontalStrut_3;
	private Component verticalStrut;
	private Component horizontalStrut_9;
	private JLabel lblHwCategory;
	private Component horizontalStrut_10;
	private JSpinner spinner_1;
	private JButton btnUp;
	private JButton btnDown;
	private JPanel panel_1;
	private JLabel lblSetting;
	private Box horizontalBox_5;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Box horizontalBox_6;
	private JTextField saveAtTextfield;
	private JButton btnChooseButton;
	private Component horizontalStrut_11;
	private Component horizontalStrut_12;
	private Component horizontalStrut_13;
	private JLabel lblSaveLocation;
	private JButton btnBackButton;
	private Box horizontalBox_7;
	private Component verticalGlue_1;
	private Component verticalStrut_3;
	private Box horizontalBox_8;
	private JPanel panel_2;
	private Component horizontalStrut_14;
	private Component horizontalStrut_15;
	private JCheckBox chckbxFullFilePath;
	private JCheckBox chckbxUnequalPageSize;
	private JCheckBox chckbxManualMode;
	private JLabel lblNewLabel;
	private Component verticalGlue_2;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_6;
	private JLabel lblAssignment;
	private JComboBox<Assignment> comboBoxAssignment;
	private Component horizontalStrut_16;
	private Component horizontalStrut_17;
	private JPanel panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmCsPdfMerge.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		selectedFiles = new ArrayList<File>();
		initializeConfig();
		initialize();
		CardLayout layout = (CardLayout)panel_3.getLayout();
		if(chckbxManualMode.isSelected())
			layout.first(Main.this.panel_3);
		else
			layout.last(Main.this.panel_3);
	}

	private void initializeConfig(){
		Configurations configs = new Configurations();
		final File propFile = new File(CONFIG_FILENAME);
		final FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configs.propertiesBuilder(propFile);
		builder.setAutoSave(true);
		try
		{
			config = builder.getConfiguration();
		}
		catch (ConfigurationException cex)
		{
			cex.printStackTrace();
			System.exit(-1);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCsPdfMerge = new JFrame();
		frmCsPdfMerge.setTitle("CS453 Assignment Manager");
		frmCsPdfMerge.setResizable(false);
		frmCsPdfMerge.setBounds(100, 100, 560, 300);
		frmCsPdfMerge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		listener = new MyListener();
		frmCsPdfMerge.getContentPane().setLayout(new CardLayout(0, 0));

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frmCsPdfMerge.getContentPane().add(panel, "name_101361563781040");

		verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);

		horizontalBox = Box.createHorizontalBox();
		horizontalBox.setMaximumSize(new Dimension(560, 25));
		horizontalBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox.add(horizontalBox);

		horizontalStrut_5 = Box.createHorizontalStrut(17);
		horizontalBox.add(horizontalStrut_5);

		JLabel lblFirstName = new JLabel("First Name:");
		horizontalBox.add(lblFirstName);

		firstNameTextField = new JTextField();
		horizontalBox.add(firstNameTextField);
		firstNameTextField.setColumns(15);
		String firstName = config.getString("firstName");
		if(firstName == null){
			firstNameTextField.setText("");
		}else{
			firstNameTextField.setText(firstName);
		}

		horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_7);

		JLabel lblLastName = new JLabel("Last Name:");
		horizontalBox.add(lblLastName);

		lastNameTextField = new JTextField();
		horizontalBox.add(lastNameTextField);
		lastNameTextField.setColumns(15);
		String lastName = config.getString("lastName");
		if(lastName == null){
			lastNameTextField.setText("");
		}else{
			lastNameTextField.setText(lastName);
		}

		horizontalStrut_6 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_6);

		panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(32767, 28));
		verticalBox.add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));

		panel_4 = new JPanel();
		panel_4.setName("manual");
		panel_3.add(panel_4, "name_111746893155582");
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		Box horizontalBox_1 = Box.createHorizontalBox();
		panel_4.add(horizontalBox_1);
		horizontalBox_1.setMaximumSize(new Dimension(560, 30));
		horizontalBox_1.setBorder(new EmptyBorder(0, 0, 0, 0));

		horizontalStrut_4 = Box.createHorizontalStrut(54);
		horizontalBox_1.add(horizontalStrut_4);

		JLabel lblNumber = new JLabel("HW #:");
		horizontalBox_1.add(lblNumber);

		spinner = new JSpinner();
		spinner.setMinimumSize(new Dimension(50, 26));
		horizontalBox_1.add(spinner);

		int hwNumber = config.getInt("previousHWNumber");
		int categoryNumber = config.getInt("previousCategoryNumber");

		spinner.setModel(new SpinnerNumberModel(hwNumber,0,99,1));


		horizontalStrut_8 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_8);

		JLabel lblProjectType = new JLabel("Type:");
		horizontalBox_1.add(lblProjectType);

		typeComboBox = new JComboBox<String>();
		typeComboBox.setMinimumSize(new Dimension(70, 27));
		horizontalBox_1.add(typeComboBox);
		typeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Problem Homework[PRB]", "Wireshark Lab Homework[LAB]", "Programming Homework[PRG]"}));

		horizontalStrut_10 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_10);

		lblHwCategory = new JLabel("Category #:");
		horizontalBox_1.add(lblHwCategory);


		spinner_1 = new JSpinner();
		spinner_1.setMinimumSize(new Dimension(60, 26));
		horizontalBox_1.add(spinner_1);
		spinner_1.setModel(new SpinnerNumberModel(categoryNumber,0,99,1));

		horizontalStrut_9 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_9);
		
		panel_6 = new JPanel();
		panel_6.setName("auto");
		panel_3.add(panel_6, "name_116162073956175");
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		horizontalStrut_16 = Box.createHorizontalStrut(38);
		panel_6.add(horizontalStrut_16);
		
		lblAssignment = new JLabel("Choose:");
		panel_6.add(lblAssignment);
		
		List<Assignment> assignments = parse();
		comboBoxAssignment = new JComboBox<Assignment>();
		for(Assignment assignment : assignments){
			comboBoxAssignment.addItem(assignment);
		}
		comboBoxAssignment.setMinimumSize(new Dimension(200, 27));
		panel_6.add(comboBoxAssignment);
		
		horizontalStrut_17 = Box.createHorizontalStrut(88);
		panel_6.add(horizontalStrut_17);


		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox.add(horizontalBox_2);

		verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		horizontalBox_2.add(verticalBox_1);

		verticalStrut = Box.createVerticalStrut(5);
		verticalStrut.setMaximumSize(new Dimension(0, 20));
		verticalBox_1.add(verticalStrut);

		horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setMaximumSize(new Dimension(100, 25));
		horizontalBox_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox_1.add(horizontalBox_4);

		horizontalStrut_2 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_2);

		JLabel lblFileSelected = new JLabel("File Selected:");
		horizontalBox_4.add(lblFileSelected);

		horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_3);

		verticalGlue = Box.createVerticalGlue();
		verticalBox_1.add(verticalGlue);

		JScrollPane scrollPane = new JScrollPane();
		horizontalBox_2.add(scrollPane);

		list = new JList<File>();
		scrollPane.setViewportView(list);

		panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 0, 0, 0));
		horizontalBox_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		btnOpen = new JButton("   Add   ");
		panel_5.add(btnOpen);

		btnRemove = new JButton("Remove");
		panel_5.add(btnRemove);

		btnUp = new JButton("    Up    ");
		panel_5.add(btnUp);

		btnDown = new JButton(" Down  ");
		panel_5.add(btnDown);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUp.addActionListener(listener);
		btnDown.addActionListener(listener);
		btnRemove.addActionListener(listener);
		btnOpen.addActionListener(listener);

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setMaximumSize(new Dimension(560, 25));
		horizontalBox_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalBox.add(horizontalBox_3);

		horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut);


		btnWebsite = new JButton("Website");
		horizontalBox_3.add(btnWebsite);
		btnWebsite.setHorizontalAlignment(SwingConstants.LEFT);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_3.add(horizontalGlue_1);

		btnGenerate = new JButton("Generate");
		horizontalBox_3.add(btnGenerate);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox_3.add(horizontalGlue);

		btnSetting = new JButton("Settings");
		horizontalBox_3.add(btnSetting);

		horizontalStrut_1 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_1);

		panel_1 = new JPanel();
		frmCsPdfMerge.getContentPane().add(panel_1, "name_101378567290148");
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		verticalStrut_1 = Box.createVerticalStrut(5);
		panel_1.add(verticalStrut_1);

		horizontalBox_5 = Box.createHorizontalBox();
		horizontalBox_5.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1.add(horizontalBox_5);

		lblSetting = new JLabel("Settings");
		horizontalBox_5.add(lblSetting);

		verticalStrut_2 = Box.createVerticalStrut(5);
		panel_1.add(verticalStrut_2);

		horizontalBox_6 = Box.createHorizontalBox();
		horizontalBox_6.setBorder(new EmptyBorder(0, 0, 0, 0));
		horizontalBox_6.setMaximumSize(new Dimension(560, 25));
		panel_1.add(horizontalBox_6);

		horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_11);

		lblSaveLocation = new JLabel("Save At:");
		horizontalBox_6.add(lblSaveLocation);

		saveAtTextfield = new JTextField();
		saveAtTextfield.setEditable(false);
		saveAtTextfield.setMaximumSize(new Dimension(2147483647, 25));
		horizontalBox_6.add(saveAtTextfield);
		saveAtTextfield.setColumns(10);

		String saveAt = config.getString("saveAt");
		if(saveAt == null){
			saveAtTextfield.setText(System.getProperty("user.home"));
		}else{
			saveAtTextfield.setText(saveAt);
		}

		horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_13);

		btnChooseButton = new JButton("Choose");
		horizontalBox_6.add(btnChooseButton);
		btnChooseButton.addActionListener(listener);

		horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_12);

		verticalGlue_1 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_1);

		horizontalBox_8 = Box.createHorizontalBox();
		horizontalBox_8.setMaximumSize(new Dimension(560, 120));
		horizontalBox_8.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1.add(horizontalBox_8);

		horizontalStrut_15 = Box.createHorizontalStrut(5);
		horizontalBox_8.add(horizontalStrut_15);

		panel_2 = new JPanel();
		panel_2.setForeground(Color.LIGHT_GRAY);
		panel_2.setBorder(new LineBorder(Color.GRAY));
		horizontalBox_8.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {250, 250};
		gbl_panel_2.rowHeights = new int[] {25, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		lblNewLabel = new JLabel("Misc.");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);

		chckbxFullFilePath = new JCheckBox("Full File Path");
		chckbxFullFilePath.setToolTipText("Show full file path for selected files.");
		chckbxFullFilePath.setPreferredSize(new Dimension(160, 23));
		GridBagConstraints gbc_chckbxFullFilePath = new GridBagConstraints();
		gbc_chckbxFullFilePath.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFullFilePath.gridx = 0;
		gbc_chckbxFullFilePath.gridy = 1;
		panel_2.add(chckbxFullFilePath, gbc_chckbxFullFilePath);
		chckbxFullFilePath.setSelected(config.getBoolean("useFullFilePath"));

		checkBox = new JCheckBox("Unimplemented    ");
		checkBox.setEnabled(false);
		checkBox.setPreferredSize(new Dimension(160, 23));
		checkBox.setMinimumSize(new Dimension(160, 23));
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox.gridx = 1;
		gbc_checkBox.gridy = 1;
		panel_2.add(checkBox, gbc_checkBox);

		chckbxUnequalPageSize = new JCheckBox("Unequal Page Size");
		chckbxUnequalPageSize.setEnabled(false);
		chckbxUnequalPageSize.setPreferredSize(new Dimension(160, 23));
		chckbxUnequalPageSize.setMaximumSize(new Dimension(160, 23));
		chckbxUnequalPageSize.setMinimumSize(new Dimension(160, 23));
		GridBagConstraints gbc_chckbxUnequalPageSize = new GridBagConstraints();
		gbc_chckbxUnequalPageSize.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxUnequalPageSize.gridx = 0;
		gbc_chckbxUnequalPageSize.gridy = 2;
		panel_2.add(chckbxUnequalPageSize, gbc_chckbxUnequalPageSize);

		checkBox_1 = new JCheckBox("Unimplemented    ");
		checkBox_1.setEnabled(false);
		checkBox_1.setPreferredSize(new Dimension(160, 23));
		checkBox_1.setMinimumSize(new Dimension(160, 23));
		GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_1.gridx = 1;
		gbc_checkBox_1.gridy = 2;
		panel_2.add(checkBox_1, gbc_checkBox_1);

		chckbxManualMode = new JCheckBox("Manual Mode");
		chckbxManualMode.setPreferredSize(new Dimension(160, 23));
		chckbxManualMode.setMinimumSize(new Dimension(160, 23));
		GridBagConstraints gbc_chckbxManualMode = new GridBagConstraints();
		gbc_chckbxManualMode.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxManualMode.gridx = 0;
		gbc_chckbxManualMode.gridy = 3;
		panel_2.add(chckbxManualMode, gbc_chckbxManualMode);
		chckbxManualMode.addActionListener(listener);
		chckbxManualMode.setSelected(config.getBoolean("manualMode"));

		checkBox_2 = new JCheckBox("Unimplemented    ");
		checkBox_2.setEnabled(false);
		checkBox_2.setPreferredSize(new Dimension(160, 23));
		checkBox_2.setMinimumSize(new Dimension(160, 23));
		GridBagConstraints gbc_checkBox_2 = new GridBagConstraints();
		gbc_checkBox_2.gridx = 1;
		gbc_checkBox_2.gridy = 3;
		panel_2.add(checkBox_2, gbc_checkBox_2);

		horizontalStrut_14 = Box.createHorizontalStrut(5);
		horizontalBox_8.add(horizontalStrut_14);

		verticalGlue_2 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_2);

		horizontalBox_7 = Box.createHorizontalBox();
		horizontalBox_7.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_1.add(horizontalBox_7);

		btnBackButton = new JButton("Back");
		horizontalBox_7.add(btnBackButton);
		btnBackButton.addActionListener(listener);

		verticalStrut_3 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_3);
		btnSetting.addActionListener(listener);
		btnGenerate.addActionListener(listener);
		btnWebsite.addActionListener(listener);
	}

	/*
	 * Parse information from website
	 */
	private List<Assignment> parse(){
		List<Assignment> toReturn = new ArrayList<Assignment>();
		List<String> buffer = new ArrayList<String>();
		try {
			URL url = new URL(WEB_ASSIGNMENT);
			Scanner s = new Scanner(url.openStream());
			parseHelper(s, buffer, toReturn);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(this.frmCsPdfMerge,
					ex.getMessage(),
					"Parse failed!",
					JOptionPane.WARNING_MESSAGE);
			toReturn.clear();
			buffer.clear();
			Scanner s = null;
			try {
				s = new Scanner(new File("assignment_cache"));
				parseHelper(s,buffer,toReturn);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.frmCsPdfMerge,
						ex.getMessage(),
						"Fatal Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}finally{
				s.close();
			}
		}
		PrintWriter writer = null;
		File output = new File("assignment_cache");

		try {
			output.createNewFile();
			writer = new PrintWriter(output);
			for(String line : buffer){
				writer.println(line);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.frmCsPdfMerge,
					e.getMessage(),
					"FATAL ERROR",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		} finally{
			writer.close();
		}
		return toReturn;
	}


	/*
	 * Helper for parse
	 */
	private void parseHelper(Scanner s, List<String> buffer, List<Assignment> toReturn) throws Exception{
		while(s.hasNextLine()){
			String line = null;
			String[] splitted = null;
			Assignment assignment = new Assignment();
			LocalDate startDate = null, endDate = null, currDate = LocalDate.now();

			//read assignment detail
			{
				line = s.nextLine();
				buffer.add(line);
				splitted = line.split(",");
				assignment.name = splitted[0];
				assignment.type = splitted[1];
				assignment.hwN = Integer.parseInt(splitted[2]);
				assignment.categoryN = Integer.parseInt(splitted[3]);
			}

			//read start date
			{
				line = s.nextLine();
				buffer.add(line);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, hh:mm a", Locale.ENGLISH);
				startDate = LocalDate.parse(line, formatter);
			}

			//read end date
			{
				line = s.nextLine();
				buffer.add(line);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, hh:mm a", Locale.ENGLISH);
				endDate = LocalDate.parse(line, formatter);
			}
			if(currDate.isAfter(startDate) && currDate.isBefore(endDate)){
				toReturn.add(assignment);
			}
		}
		s.close();
	}

	private class Assignment{
		String name;
		String type;
		int hwN, categoryN;

		@Override
		public String toString(){
			return name;
		}
	}

	private class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			final Object actor = e.getSource();
			if(actor == btnOpen){
				openFileChooser();
			}
			else if(actor == btnWebsite){
				openBrowser();
			}
			else if(actor == btnGenerate){
				make();
			}
			else if(actor == btnSetting){
				CardLayout layout = (CardLayout)(Main.this.frmCsPdfMerge.getContentPane().getLayout());
				layout.next(Main.this.frmCsPdfMerge.getContentPane());
			}
			else if(actor == btnBackButton){
				saveConfig();
				CardLayout layout = (CardLayout)(Main.this.frmCsPdfMerge.getContentPane().getLayout());
				layout.previous(Main.this.frmCsPdfMerge.getContentPane());
			}
			else if(actor == btnRemove){
				removeEntries();
			}
			else if(actor == btnDown){
				moveDown();
			}
			else if(actor == btnUp){
				moveUp();
			}
			else if(actor == btnChooseButton){
				openFileChooser2();
			}
		}

		/*
		 * Update change configrations/settings
		 */
		private void saveConfig(){
			config.setProperty("saveAt", saveAtTextfield.getText());
			config.setProperty("useFullFilePath", chckbxFullFilePath.isSelected());
			config.setProperty("manualMode", chckbxManualMode.isSelected());
			CardLayout layout = (CardLayout)panel_3.getLayout();
			if(chckbxManualMode.isSelected())
				layout.first(Main.this.panel_3);
			else
				layout.last(Main.this.panel_3);
			updateSelectedFileList(selectedFiles.toArray(new File[selectedFiles.size()]));
		}
		private final URI fURI = URI.create("http://www-edlab.cs.umass.edu/cs453/");
		private void openBrowser(){
			if (Desktop.getDesktop() == null || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) return;
			try {
				System.out.println("A");
				Desktop.getDesktop().browse(fURI);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void removeEntries(){
			for(File file: list.getSelectedValuesList()){
				selectedFiles.remove(file);
			}
			File[] temp = new File[selectedFiles.size()];
			for(int i = 0; i < selectedFiles.size(); i++){
				temp[i] = selectedFiles.get(i);
			}
			updateSelectedFileList(temp);		
		}

		private void moveUp(){
			int[] foo = list.getSelectedIndices();
			for(File file: list.getSelectedValuesList()){
				int index = selectedFiles.indexOf(file);
				if(index == 0) continue;
				selectedFiles.set(index, selectedFiles.get(index-1));
				selectedFiles.set(index-1, file);
			}
			File[] temp = new File[selectedFiles.size()];
			for(int i = 0; i < selectedFiles.size(); i++){
				temp[i] = selectedFiles.get(i);
			}
			updateSelectedFileList(temp);
			for(int i =0; i <foo.length; i++){
				if(foo[i] == 0) continue;
				foo[i] = foo[i] -1;
			}
			list.setSelectedIndices(foo);
		}

		private void moveDown(){
			int[] foo = list.getSelectedIndices();
			for(File file: list.getSelectedValuesList()){
				int index = selectedFiles.indexOf(file);
				if(index == selectedFiles.size()-1) continue;
				selectedFiles.set(index, selectedFiles.get(index+1));
				selectedFiles.set(index+1, file);
			}
			File[] temp = new File[selectedFiles.size()];
			for(int i = 0; i < selectedFiles.size(); i++){
				temp[i] = selectedFiles.get(i);
			}
			updateSelectedFileList(temp);
			for(int i =0; i <foo.length; i++){
				if(foo[i] == 0) continue;
				foo[i] = foo[i] +1;
			}
			list.setSelectedIndices(foo);
		}
		private void openFileChooser(){
			final JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(config.getString("prevAt")));
			fc.setMultiSelectionEnabled(true);
			fc.setAcceptAllFileFilterUsed(true);
			//fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			//fc.addChoosableFileFilter(new FileNameExtensionFilter("All Available Documents ", "pdf", "txt", "rtf","jpg", "jpeg", "png", "tiff", "gif", "ccitt", "wmf"));
			fc.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents [pdf]", "pdf"));
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents [txt, rtf]", "txt", "rtf"));
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Images [jpg, jpeg, png, tiff, gif, bmp, ccitt, wmf]", "jpg", "jpeg", "png", "tiff", "gif", "ccitt", "wmf"));
			int v = fc.showOpenDialog(Main.this.frmCsPdfMerge);
			if (v == JFileChooser.APPROVE_OPTION) {
				config.setProperty("prevAt", fc.getSelectedFiles()[0].getParent() +"/");
				for(File file : fc.getSelectedFiles()){
					if(!selectedFiles.contains(file))
						selectedFiles.add(file);
				}
				File[] temp = new File[selectedFiles.size()];
				for(int i = 0; i < selectedFiles.size(); i++){
					temp[i] = selectedFiles.get(i);
				}
				updateSelectedFileList(temp);
			}
		}

		private void updateSelectedFileList(File[] temp){
			list.setCellRenderer(new EntryRenderer(config.getBoolean("useFullFilePath")));
			list.setListData(temp);
		}
		/*
		 * Manipulate name of selected file entries
		 */
		private class EntryRenderer extends DefaultListCellRenderer{
			boolean fullFilePath;
			EntryRenderer(boolean ffp){
				this.fullFilePath =ffp;
			}
			@Override
			public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object o, int index, boolean isSelected, boolean cellHasFocus){
				JLabel label = (JLabel)super.getListCellRendererComponent(list, o, index, isSelected, cellHasFocus);
				if(fullFilePath == false){
					label.setText(((File) o).getName());
				}
				return label;
			}

		}


		/*
		 * Open file choose for "Save At" function
		 */
		private void openFileChooser2(){
			final JFileChooser fc = new JFileChooser();
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(new FileFilter(){

				@Override
				public boolean accept(File f) {
					return f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "Directory Only";
				}

			});
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setDialogTitle("Open");
			int v = fc.showDialog(Main.this.frmCsPdfMerge, "Open");
			if (v == JFileChooser.APPROVE_OPTION) {
				saveAtTextfield.setText(fc.getSelectedFile().toString()+"/");
			}
		}

		private void make(){
			Assignment assignment = null;
			if(chckbxManualMode.isSelected()){
				assignment = new Assignment();
				try{
					assignment.hwN = (Integer)spinner.getValue();
					assignment.categoryN = (Integer)spinner_1.getValue();
				}catch (NumberFormatException e){
					JOptionPane.showMessageDialog(Main.this.frmCsPdfMerge, "Error: Number plz",
							"Error",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				String choice = (String)Main.this.typeComboBox.getSelectedItem();
				if(choice.equals("Problem Homework[PRB]")){
					assignment.type="PRB";
				}else if(choice.equals("Wireshark Lab Homework[LAB]")){
					assignment.type="LAB";
				}else if(choice.equals("Programming Homework[PRG]")){
					assignment.type="PRG";
				}
				config.setProperty("previousHWNumber", assignment.hwN);
				config.setProperty("previousCategoryNumber", assignment.categoryN);
			}
			else{
				assignment = (Assignment)comboBoxAssignment.getSelectedItem();
			}
			
			String firstName = Main.this.firstNameTextField.getText();
			String lastName = Main.this.lastNameTextField.getText();
			config.setProperty("firstName", firstName);
			config.setProperty("lastName", lastName);

			String saveAt = config.getString("saveAt") != null ? config.getString("saveAt") : "";
			final String fileName ="HW" + String.format("%02d", assignment.hwN) + "_" + lastName +"_" +firstName+","+assignment.type+assignment.categoryN;
			
			if(assignment.type.equals("PRG")){
				makeZip(saveAt+fileName);
			}else{
				makePDF(saveAt+fileName);
			}
		}
		
		/*
		 * make PDF
		 */
		private void makePDF(String filePath){
			try {
				PdfDocument outputDoc = new PdfDocument(new PdfWriter(filePath+".pdf"));
				for(File file: selectedFiles){
					if(add(outputDoc, file) == false){
						JOptionPane.showMessageDialog(Main.this.frmCsPdfMerge,
								"Failed: Could not generate pdf file successfully.",
								"Failed",
								JOptionPane.ERROR_MESSAGE);
						outputDoc.close();
						return;
					}
				}
				JOptionPane.showMessageDialog(Main.this.frmCsPdfMerge,
						filePath+".pdf" + " is generated!\n"
								+ "MAKE SURE FILE IS VALID!!!",
								"Success",
								JOptionPane.PLAIN_MESSAGE);
				outputDoc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		private boolean add(PdfDocument outputDoc, File file){
			switch(Files.getFileExtension(file.getName()).toLowerCase()){
			case "pdf":
				return addPdf(outputDoc, file);
			case "txt":
			case "rtf":
				return addText(outputDoc, file);
			case "tiff":
			case "gif":
			case "png":
			case "bmp":
			case "jpeg":
			case "jpg":
			case "wmf":
			case "ccitt":
				return addImage(outputDoc, file);
			default:
				return false;
			}	
		}

		private boolean addPdf(PdfDocument outputDoc, File file){
			try {
				PdfMerger merger = new PdfMerger(outputDoc);
				PdfDocument doc = new PdfDocument(new PdfReader(file.toString()));

				merger.merge(doc,1,doc.getNumberOfPages());
				doc.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} 
		}

		/*
		 * Append Texts to output pdf
		 */
		private boolean addText(PdfDocument outputDoc, File file){
			try{
				PdfDocument doc = new PdfDocument(new PdfWriter(".temp.pdf"));
				Document tempDoc = new Document(doc);
				tempDoc.setTextAlignment(TextAlignment.JUSTIFIED);
				PdfFont normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

				//Texts to process
				List<String> texts = null;
				if(Files.getFileExtension(file.getName()).equals("txt")){
					texts = Files.readLines(file, Charset.forName("UTF-8"));
				}else if(Files.getFileExtension(file.getName()).equals("rtf")){
					texts = readRtf(file);
					//Protect null pointer exception.
					if(texts == null){
						tempDoc.close();
						return false;
					}
				}

				for(String line : texts){
					tempDoc.add(new Paragraph(line).setFont(normal));
				}
				tempDoc.close();
				File tempFile = new File(".temp.pdf");
				boolean result = addPdf(outputDoc, tempFile);
				if(result == false) 
					return false;
				return tempFile.delete();
			} catch(IOException e) {
				e.printStackTrace();
				return false;
			} 
		}

		/*
		 * Deal with rtf format
		 * read rtf format
		 */
		private List<String> readRtf(File file){
			RTFEditorKit rtfParser = new RTFEditorKit();
			javax.swing.text.Document document = rtfParser.createDefaultDocument();
			try {
				rtfParser.read(new ByteArrayInputStream(Files.toByteArray(file)), document, 0);
				return Arrays.asList(document.getText(0, document.getLength()).split("\\r?\\n"));
			} catch (IOException | BadLocationException e) {
				e.printStackTrace();
				return null;
			}
		}

		/*
		 * Add images to pdf
		 */

		private boolean addImage(PdfDocument outputDoc, File file){
			try{
				PdfDocument doc = new PdfDocument(new PdfWriter(".temp.pdf"));
				Document tempDoc = new Document(doc);
				tempDoc.add(new Image(ImageDataFactory.create(file.getAbsolutePath())));
				tempDoc.close();
				File tempFile = new File(".temp.pdf");
				boolean result = addPdf(outputDoc, tempFile);
				if(result == false) 
					return false;
				return tempFile.delete();
			} catch(IOException e) {
				e.printStackTrace();
				return false;
			} 
		}
		
		private void makeZip(String filePath){
			try {
				   ZipFile output = new ZipFile(filePath+".zip");
				   ZipParameters parameters = new ZipParameters();
				   parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
				   parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
				   for(File file : selectedFiles){
					   output.addFile(file, parameters);
				   }
				   JOptionPane.showMessageDialog(Main.this.frmCsPdfMerge,
						   filePath+".zip" + " is generated!\n"
									+ "MAKE SURE FILE IS VALID!!!",
									"Success",
							JOptionPane.PLAIN_MESSAGE);
				   
			} catch (Exception e) {
				JOptionPane.showMessageDialog(Main.this.frmCsPdfMerge,
						"Failed: Could not generate pdf file successfully.",
						"Failed",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
