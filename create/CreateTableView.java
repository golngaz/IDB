package create;

import interf.IDBFrame;

import java.awt.BorderLayout;
import java.awt.ItemSelectable;
import java.awt.event.*;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class CreateTableView
extends JFrame implements ActionListener,ItemListener {
	private final int elementHeight = 20;

	private final int margin = 20;

	private int height = 600;

	private int width = 900;

	private String errorAttribute = "ERREUR : \n";

	private AttributesAbstractTableModel [] models;


	private JTable table;

	private JTable [] tables;


	private JScrollPane scrollPane;

	private JScrollPane [] scrollPanes;


	private JTextField tableNameField;

	private JTextField attributeNameField;

	private JTextField attributeSizeField;

	private JTextField [] fields;

	private final int fieldNumber = 3;


	private JLabel tableNameLabel;

	private JLabel tableLabel;

	private JLabel attributeLabel;

	private JLabel [] labels;
	
	private JLabel errorAttributesLabel;

	private final int labelNumber = 4;


	private JButton attributeButton;

	private JButton createTableButton;

	private JButton buttons[];

	private final int buttonNumber = 2;


	private JPanel panelAttributes;

	private JPanel [] panels;

	private final int panelNumber = 1;


	private JComboBox attributeTypeComboBox;

	private JComboBox tableFKComboBox;

	private JComboBox attributeFKComboBox;

	private JComboBox [] comboBox;

	private final int comboBoxNumber = 3;


	private JCheckBox notNullCheck;

	private JCheckBox uniqueCheck;

	private JCheckBox pkCheck;

	private JCheckBox fkCheck;

	private JCheckBox [] checkBoxs;

	private final int checkBoxNumber = 4;



	private Object[] types = new Object[]{"VARCHAR", "INT", "DATE", "CHAR"};
	
	private Object[] tablesFK = new Object[]{"Clients", "Produits", "Commande"};
	
	private Object[] attributesFK = new Object[]{"code", "nom"};


	public CreateTableView()
	{
		this.setLayout(null);
		this.handlePanels();
		this.handleComboBox();
		this.handleFields();
		this.handleLabels();
		this.handleButtons();
		this.handleCheckBox();
		this.setProperties();
	}


	// ==========================BUTTONS========================
	private void createButtons()
	{
		this.buttons = new JButton [this.buttonNumber];
		this.buttons[0] = this.attributeButton = new JButton("Ajouter l'attribut") ;
		this.buttons[1] = this.createTableButton = new JButton("Cr�er la Table") ;
	}


	private void bindButtons()
	{
		this.attributeButton.setBounds((int)(1.35*this.margin), (int)(0.37*height), 150, (int)(1.5*this.elementHeight));
		this.createTableButton.setBounds((int)(1.35*this.margin), (int)(0.87*height), 150, (int)(1.5*this.elementHeight));
	}


	private void addButtons()
	{
		for (JButton jb : this.buttons) {
			jb.addActionListener(this);
			this.add(jb);
		}
	}

	private void handleButtons()
	{
		this.createButtons();
		this.bindButtons();
		this.addButtons();
	}



	// ==========================FIELDS========================
	private void createFields()
	{	
		this.fields = new JTextField [this.fieldNumber];
		this.fields[0] = this.attributeNameField = new JTextField();
		this.fields[1] = this.attributeSizeField = new JTextField();
		this.fields[2] = this.tableNameField = new JTextField();
	}



	private void bindFields()
	{
		this.tableNameField.setBounds((int)((1.35*this.margin)+115), (int)(0.09*height), 100, (int)(1.5*this.elementHeight));	
		this.attributeNameField.setBounds((int)(1.35*this.margin), (int)(0.28*height), 100, (int)(1.5*this.elementHeight));	
		this.attributeSizeField.setBounds((int)((1.35*this.margin)+210), (int)(0.28*height), 50, (int)(1.5*this.elementHeight));	
		this.attributeNameField.setText("nomAttribut");
		this.attributeSizeField.setText("Taille");
	}


	private void addFields()
	{
		for (JTextField jtf : this.fields) {
			this.add(jtf);
		}
	}


	private void handleFields()
	{
		this.createFields();
		this.bindFields();
		this.addFields();
	}

	// ==========================LABEL========================
	private void createLabels()
	{
		this.labels = new JLabel [this.labelNumber];
		this.labels[0] = this.tableLabel = new JLabel("Table :");
		this.labels[1] = this.attributeLabel = new JLabel("Attribut :");
		this.labels[2] = this.tableNameLabel= new JLabel("Nom de la table :");
		this.labels[3] = this.errorAttributesLabel= new JLabel("");
	}


	private void bindLabels()
	{
		this.tableLabel.setBounds(this.margin, (int)(0.03*height), 75, (int)(1.5*this.elementHeight));
		this.tableLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
		this.attributeLabel.setBounds(this.margin, (int)(0.20*height), 75, (int)(1.5*this.elementHeight));
		this.attributeLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
		this.tableNameLabel.setBounds((int)(1.35*this.margin), (int)(0.09*height), 105, (int)(1.5*this.elementHeight));
		this.tableNameLabel.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 14));
		this.errorAttributesLabel.setBounds(300,(int)(0.37*height), 600, (int)(1.5*this.elementHeight));
		this.errorAttributesLabel.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 14));
	}


	private void addLabels()
	{
		for (JLabel jl : this.labels) {
			this.add(jl);
		}
	}

	private void handleLabels()
	{
		this.createLabels();
		this.bindLabels();
		this.addLabels();
	}

	// ==========================PANELS========================
	private void createPanels()
	{
		this.panels = new JPanel[this.panelNumber];
		this.panels[0] = this.panelAttributes = new JPanel(new BorderLayout());
		this.tables = new JTable[1];
		this.models = new AttributesAbstractTableModel[1];
		this.models[0] = new AttributesAbstractTableModel();
		this.tables[0] = new JTable(this.models[0]){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		this.scrollPanes = new JScrollPane[1];
		this.scrollPanes[0] = new JScrollPane(this.tables[0]);
	}


	private void bindPanels()
	{
		this.panels[0].setBounds(0, (int)(0.5*height), width-5,  (int)(0.5*height)-100);
		this.tables[0].setFillsViewportHeight(true);
		this.scrollPanes[0].setVisible(true);
		this.panels[0].add( this.scrollPanes[0]);

	}


	private void addPanels()
	{
		for (JPanel jl : this.panels) {
			this.add(jl);
		}
	}

	private void handlePanels()
	{
		this.createPanels();
		this.bindPanels();
		this.addPanels();
	}

	// ==========================COMBOBOX========================
	private void createComboBox()
	{
		this.comboBox = new JComboBox [this.comboBoxNumber];
		this.comboBox[0] = this.attributeTypeComboBox = new JComboBox(types);
		this.comboBox[1] = this.tableFKComboBox = new JComboBox(tablesFK);
		this.comboBox[2] = this.attributeFKComboBox = new JComboBox(attributesFK);
	}


	private void bindComboBox()
	{
		this.attributeTypeComboBox.setBounds((int)((1.35*this.margin)+110), (int)(0.28*height), 85, (int)(1.5*this.elementHeight));
		this.tableFKComboBox.setBounds((int)((1.35*this.margin)+655), (int)(0.28*height), 75, (int)(1.5*this.elementHeight));	
		this.attributeFKComboBox.setBounds((int)((1.35*this.margin)+745), (int)(0.28*height), 75, (int)(1.5*this.elementHeight));
	}


	private void addComboBox()
	{
		for (JComboBox jc : this.comboBox) {
			this.add(jc);
		}
	}


	private void handleComboBox()
	{
		this.createComboBox();
		this.bindComboBox();
		this.addComboBox();
	}

	// ==========================CHECKBOX========================

	private void createCheckBox()
	{
		this.checkBoxs = new JCheckBox [this.checkBoxNumber];
		this.checkBoxs[0] = this.notNullCheck = new JCheckBox("NOT NULL");
		this.checkBoxs[1] = this.uniqueCheck = new JCheckBox("UNIQUE");
		this.checkBoxs[2] = this.pkCheck = new JCheckBox("PRIMARY KEY");
		this.checkBoxs[3] = this.fkCheck = new JCheckBox("FOREIGN KEY");
	}


	private void bindCheckBox()
	{
		this.notNullCheck.setBounds((int)((1.35*this.margin)+265), (int)(0.28*height), 85, (int)(1.5*this.elementHeight));	
		this.uniqueCheck.setBounds((int)((1.35*this.margin)+355), (int)(0.28*height), 70, (int)(1.5*this.elementHeight));	
		this.pkCheck.setBounds((int)((1.35*this.margin)+432), (int)(0.28*height), 105, (int)(1.5*this.elementHeight));	
		this.pkCheck.addItemListener(this);
		this.fkCheck.setBounds((int)((1.35*this.margin)+545), (int)(0.28*height), 105, (int)(1.5*this.elementHeight));	
	}


	private void addCheckBox()
	{
		for (JCheckBox jl : this.checkBoxs) {
			this.add(jl);
		}
	}

	private void handleCheckBox()
	{
		this.createCheckBox();
		this.bindCheckBox();
		this.addCheckBox();
	}

	private void setProperties()
	{
		this.setSize(width, height);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setVisible(true);    
		this.setResizable(false);
	}
	
	private void clearAttribute(){
		this.attributeNameField.setText("");
		this.attributeSizeField.setText("");
		this.notNullCheck.setSelected(false);
		this.uniqueCheck.setSelected(false);
		this.fkCheck.setSelected(false);
		this.pkCheck.setSelected(false);
	}

	public boolean isInteger(String i){
		try {
		    Integer.parseInt(i);
		    return true;
		} catch (NumberFormatException nfe) {
		    return false;
		}
	}
	
	public boolean isCompleteAttribute(){
		if(this.attributeNameField.getText().equals((String)"") || this.attributeSizeField.getText().equals((String)"")){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isGoodSizeValue(int size){
		if(size<=64 && size>0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isGoodSize(String size){
		if(isInteger(size)){
			if(isGoodSizeValue(Integer.parseInt(size))){
				return true;				
			}else{
				this.errorAttribute=this.errorAttribute + "La taille de l'attribut doit �tre un entier compris entre 0 et 64.";
				return false;
			}
		}else{
			this.errorAttribute=this.errorAttribute + "La taille de l'attribut doit �tre un entier.";
			return false;
		}
	}
	
	public Boolean isValidateAttributes(){
		this.errorAttribute = "ERREUR : \n";
		if(isCompleteAttribute()){
			if(isGoodSize(attributeSizeField.getText())){
				return true;
			}else{
				return false;
			}
		}else{
			this.errorAttribute=this.errorAttribute + "Les Champs nomAttribut et/ou Taille ne sont pas renseign�(s).";
			return false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.attributeButton) {
			this.addAttributeButtonAction();
		}

	}
	
	public void itemStateChanged(ItemEvent item) {
		Object obj = item.getItem();
		int status = item.getStateChange();
		if((JCheckBox)obj==this.pkCheck){
			if (status == ItemEvent.SELECTED){
				this.notNullCheck.setSelected(true);
				this.notNullCheck.setEnabled(false);
				this.uniqueCheck.setSelected(true);
				this.uniqueCheck.setEnabled(false);
			}else if(status == ItemEvent.DESELECTED){
				this.notNullCheck.setSelected(false);
				this.notNullCheck.setEnabled(true);
				this.uniqueCheck.setSelected(false);
				this.uniqueCheck.setEnabled(true);
			}
		}
	}
	
	
	
	private void addAttributeButtonAction()
	{
		if(isValidateAttributes()){
		this.errorAttributesLabel.setText("");
		this.errorAttributesLabel.setText("SUCCES : Attribut ajout�.");
		this.models[0].addAttribute(new Attribute(attributeNameField.getText(),(String)attributeTypeComboBox.getSelectedItem(), Integer.parseInt(attributeSizeField.getText()), notNullCheck.isSelected(), uniqueCheck.isSelected(),pkCheck.isSelected(),fkCheck.isSelected()," "," "));
		this.clearAttribute();
		}else{
			this.errorAttributesLabel.setText("");
			this.errorAttributesLabel.setText(errorAttribute);
		}
	}
}

