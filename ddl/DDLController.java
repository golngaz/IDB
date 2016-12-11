package ddl;

import ddl.create.Attribute;
import ddl.create.CreateTableView;
import ddl.create.Table;
import ddl.drop.DropTableGUI;
import modify.ModifyTableView;
import useful.CustomizedResponse;
import useful.CustomizedResponseWithData;

public class DDLController 
{
	/** Controleur en cours.*/
	private static DDLController INSTANCE;
	
	//Attributs
	/** IHM pour créer une table et ses attributs.*/
	private CreateTableView createGUI;
	
	/** IHM pour supprimer une table.*/
	private DropTableGUI dropGUI;
	
	/** Objet pour gérer la communication avec un SGBD 
	 * dans l'optique d'utiliser le LDD.*/
	private DDLManager manager;
	
	
	private ModifyTableView modifyGUI;
	
	
	//Contructeur
	/**
	 * Constructeur commun.
	 */
	private DDLController()
	{
		INSTANCE = this;
		this.manager = DDLManager.getInstance();
	}
	
	
	/**
	 * Retourne le controleur actif si et seulement s'il
	 * existe déjà. Retourne un nouveau controleur sinon.
	 * 
	 * @return DDLController
	 */
	public static DDLController getInstance()
	{
		if (INSTANCE == null) new DDLController();
		return INSTANCE;
	}
	
	/**
	 * Ouvre l'IHM de création des tables si et seulement si 
	 * elle n'existe pas, sinon tente de l'afficher au premier plan.
	 */
	public void openCreateGUI()
	{
		this.createGUI = CreateTableView.getInstance();
		this.createGUI.toFront();
	}
	
	
	/**
	 * Ouvre l'IHM de création des tables si et seulement si 
	 * elle n'existe pas, sinon tente de l'afficher au premier plan.
	 */
	public void openDropGUI()
	{
		this.dropGUI = DropTableGUI.getInstance();
		this.dropGUI.toFront();
	}
	
	/**
	 * 
	 */
	public void closeStatement(){
		this.manager.closeStatement();
	}


	public void openModifyGUI() {
		this.modifyGUI = ModifyTableView.getInstance();
		
	}
	
	
	/**
	 * Envoie $table au DDLManager dans l'optique de la créer.
	 * 
	 * @param table : une table à créer. L'objet peut être erroné;
	 */
	public void createTable(Table table)
	{
		CustomizedResponse response = this.manager.createTable(table);
		if (response.hasSuccess()) {
			this.createGUI.resetView();
		}
		this.createGUI.talk(response.toString());
	}
	
	
	/**
	 * Envoie $table au DDLManager dans l'optique de la supprimer.
	 * 
	 * @param table : une table à supprimé. L'objet peut être erroné.
	 * @return CustomizedResponse
	 */
	public CustomizedResponse dropTable(Table table)
	{
		return this.manager.dropTable(table);
	}
	
	
	/**
	 * Retourne une réponse personnalisée contenant le nom des tables
	 * de la base, si et seulement si (ces dernières existent et 
	 * il n'y a pas eu d'exceptions).
	 * 
	 * @return CustomizedResponseWithData
	 */
	public CustomizedResponseWithData<String> getTables()
	{
		return this.manager.getTables();
	}


	public void modifier(String string) {
		System.out.println("Je vais modifier la table"+string);
		//TODO
		
	}
	
	




	
	
	//Privées

}
