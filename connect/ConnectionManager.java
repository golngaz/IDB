package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère la connection à un SGBD.
 */
public class ConnectionManager 
{
	//Attributes
	/**
	 * Mot de passe d'utilisateur de la Base de Données.
	 */
	private String pswd;
	
	/**
	 * driver utilisé pour la base de données
	 */
	Class<?> driver;

	
	/**
	 * indentificateur
	 */
	private String baseName;
	
	/**
	 * Le port nécessaire à la connexion
	 */
	private String port;
	
	
	/**
	 * Un objet qui représente la connexion à un SGBD.
	 */
	private Connection connection;
	
	/**
	 * Adresse du SGBD connecté avec succès.
	 */
	private String url;
	
	/**
	 * Nom d'utilisateur connecté au SGBD avec succès.
	 */
	private String user;
	
	
	
	//Constructeur
	/**
	 * Constructeur commun.
	 */
	public ConnectionManager()
	{
		this.connection = null;
		this.url = null;
		this.user = null;
	}
	
	
	//Methods
	/**
	 * Etablit une connexion vers le SGBD $url, pour l'utilisateur $user,
	 * avec son mot de passe $pswd.
	 * Retourne un objet qui décrit grossièrement la tentative de connexion.
	 * 
	 * @param url : url du SGBD.
	 * @param user : nom d'utilisateur souhaitant se connecter.
	 * @param pswd : mot de passe de l'utilisateur.
	 * @return ConnectionResponse
	 */
	public ConnectionResponse connect(String driver, String url, String user, String pswd, String baseName, String port)
	{
		Connection conn;
		try{
			if (driver.equals("Oracle")){
				this.driver = Class.forName("oracle.jdbc.OracleDriver");
			}
			
		}
		catch(Exception e1){
			return new ConnectionResponse(false, "problème de pilote.");
		}
		
		
		 String entireUrl = this.getEntireUrl(url,port,baseName);
		try {
			conn = DriverManager.getConnection(entireUrl, user, pswd);
		}
		catch(SQLException e){
			return new ConnectionResponse(false, "lors de la connexion à la base de Données");//message recu par un utilisateur non initié
			//return new ConnectionResponse(false, "SQLException");
		}
		catch(Exception e2){
			return new ConnectionResponse(false, "Exception");
		}
		this.connection = conn;
		this.user = user;
		this.port=port;
		this.baseName=baseName;
		this.url=url;
		return new ConnectionResponse(true, "Connexion réussie.");
	}
	
	
	/**
	 * retourne une chaine de charactère permettant de se connecter à jdbc d'après
	 * toutes les informations disponibles dans les attributs de la classe
	 * @param baseName 
	 * @param port 
	 * @param url 
	 * @return String
	 */
	private String getEntireUrl(String url, String port, String baseName){	
		String stringReturn = "jdbc:";
		stringReturn +=":thin:@" + url + ":" + port +":" + baseName;
		return stringReturn;
}


	/**
	 * Retourne vrai si et seulement si $this est connecté à un SGBD,
	 * faux sinon.
	 * 
	 * @return boolean
	 */
	public boolean isConnected() {return this.connection != null;}
	
	
	/**
	 * Retourne le SGBD avec lequel est connecté $this.
	 * 
	 * @return Connection
	 */
	public Connection dbms() {return this.connection;}
	
	
	/**
	 * Retourne le nom de l'utilisateur connecté.
	 * 
	 * @return String
	 */
	public String user(){return this.user;}
	
	
	/**
	 * Retourne une chaîne de caractères qui décrit $this.
	 *
	 *@return String
	 */
	public String toString()
	{
		StringBuilder result = new StringBuilder ();
		result.append(this.isConnected() 
				? "Connecté à l'adresse : " 
				: "Non connecté.\n");
		if (this.isConnected()) {
			result.append(this.url + "\n");
			result.append("Nom d'utilisateur : " + this.user + "\n");
		}
		return result.toString();
	}
}
