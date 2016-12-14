package ddl.modify;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import ddl.DDLController;
import manager.ddl.DDLManager;
import interf.ListeningGUI;



public class ModifyTableChoiceGUI
extends ListeningGUI
implements ActionListener, ItemListener
{
	private final int width=280;
	private final int height=200;
	private int elementHeight=30;
	private int elementWidth;
	private final int marge=15;
	private static ModifyTableChoiceGUI INSTANCE = null;
	
    private JButton buttonConfirm;
    private JLabel label1;
	private JComboBox<String> comboTables;

	private DDLController controller;

	
	ModifyTableChoiceGUI(){
		super("modifier table vue");
		INSTANCE = this;	
	
		this.controller = DDLController.getInstance();
		
		this.elementWidth=width-(2*marge);

		
		this.handleCombos();
		this.handleButtons();
		this.handleLabels();
		
		JLabel lab = new JLabel("rien");
		lab.setEnabled(false);
		lab.setVisible(false);
		this.add(lab);
		
		setProperties();
		this.addWindowListener(this);
		
				
	}



	private void handleCombos() {
		this.createCombos();
		this.bindCombos();
		this.initCombos();
		this.addCombos();
		
	}



	private void initCombos() {
		List<String> valeurs = new ArrayList<String>();
		DDLManager manager = DDLManager.getInstance();
		valeurs = manager.getTablesString();
		if (valeurs.isEmpty()){
			this.comboTables.setEnabled(false);
			valeurs.add("pas de tables");
		}

		String[] strings = valeurs.stream().toArray(String[]::new);
		
		this.comboTables.setModel(new javax.swing.DefaultComboBoxModel<>(strings));
	}



	private void addCombos() {
		this.add(this.comboTables);		
	}



	private void bindCombos() {
		this.comboTables.setBounds(marge,elementHeight+marge,elementWidth,elementHeight);
	}



	private void createCombos() {
		this.comboTables = new JComboBox<String>();
	}



	private void handleButtons() {
		this.createButtons();
		this.bindButtons();
		this.initButtons();
		this.addButton();
		
	}

	private void initButtons() {
		this.buttonConfirm.addActionListener(this);
		
	}



	private void addButton() {
		this.add(buttonConfirm);
		
	}

	private void bindButtons() {
		this.buttonConfirm.setBounds(marge,(elementHeight*2)+(marge*2),elementWidth,elementHeight);
		
	}

	private void createButtons() {
		this.buttonConfirm = new JButton("Modifier");
		
	}
	
	private void handleLabels() {
		this.createLabels();
		
		this.bindLabels();
		this.addLabels();
		
	}

	private void addLabels() {
		this.add(label1);
	}



	private void bindLabels() {
		this.label1.setBounds(marge, marge, elementWidth, elementHeight);
		//this.label1.setBounds(500, 0, 0, 0);
		
	}



	private void createLabels() {
		this.label1 = new JLabel("Choisissez une table � modifier :");
		
	}

	
	private void setProperties()
	{
		this.setSize(width, height);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);    
		this.setResizable(false);
	}
	
	
	@Override
	public void windowClosing(WindowEvent we){INSTANCE = null;}

	@Override
	public void itemStateChanged(ItemEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		DDLController controller = DDLController.getInstance();
		this.controller.modifier(this.comboTables.getSelectedItem().toString());
		
	}



	public static ModifyTableChoiceGUI getInstance() {
		if (INSTANCE == null) new ModifyTableChoiceGUI();
		return INSTANCE;
	}

}