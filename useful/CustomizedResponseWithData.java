package useful;

import java.util.ArrayList;

public class CustomizedResponseWithData <T>
extends CustomizedResponse
{
	//Attributes
	/** Contient des données à récupérer.*/
	protected ArrayList<T> data;
	
	
	//Constructeur
	/**
	 * Constructeur commun.
	 * 
	 * @param response :  vrai ssi la tentative a réussie, faux sinon.
	 * @param msg : message associé à la tentative.
	 * @param data : liste des données à retourner.
	 */
	public CustomizedResponseWithData(
			boolean response, String msg, ArrayList<T> data)
	{
		super(response, msg);
		this.data = data; //TODO : virer ce pointeur par un ajout element par élément
	}
	
	/**
	 * Constructeur pour ajout au fur et à mesure.
	 * 
	 * @param response
	 * @param msg
	 */
	public CustomizedResponseWithData(boolean response, String msg)
	{
		super(response, msg);
		this.data = new ArrayList<T> ();
	}
	
	
	/**
	 * Recopie $copy, 
	 * 
	 * @param copy : un objet CustomizedResponse à recopier.
	 * @param data : liste des données à retourner.
	 */
	public CustomizedResponseWithData(CustomizedResponse copy, ArrayList<T> data)
	{
		super(copy);
		this.data = data; //TODO : virer ce pointeur par un ajout element par élément
	}
	
	
	//Accesseurs
	/**
	 * Retourne l'ensemble des données de $this.
	 * 
	 * @return ArrayList
	 */
	public ArrayList<T> getCollection(){return this.data;}
	
	
	//Méthodes
	/**
	 * Ajoute $element à $this.
	 * 
	 * @param element
	 */
	public void add(T element)
	{
		if (! this.data.contains(element)) {
			this.data.add(element);
		}
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder(super.toString());
		if (data != null) result.append('\n' + data.toString());
		return result.toString();
	}
}
