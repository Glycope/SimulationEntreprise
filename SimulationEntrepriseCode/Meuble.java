import java.util.*;
public class Meuble {

	private String nom;
	private List<Pair<String,Integer>> lots_list = new ArrayList<Pair<String,Integer>>();
	private String pieceMaison;
	private int dureeConstruction;
	public static List<String> piece_list = new ArrayList<String>(Arrays.asList("Salon","Chambre","Cuisine","Salle à manger","Salle de bain","WC"));

	public void setMeuble (String nom, List<Pair<String,Integer>> lots_list, String pieceMaison, int dureeConstruction) {
		this.nom = nom;
		this.lots_list = lots_list;
		this.pieceMaison = pieceMaison;
		this.dureeConstruction = dureeConstruction;
	}

	public void setNom (String nom) { this.nom = nom; };
	public void setLots(List<Pair<String,Integer>> lots_list) { this.lots_list = lots_list; };
	public void setPiece(String pieceMaison) { this.pieceMaison = pieceMaison; };
	public void setDuree(int dureeConstruction) { this.dureeConstruction = dureeConstruction; };

	public String getNom() { return this.nom; };
	public List<Pair<String,Integer>> getLots() { return this.lots_list; };
	public String getPiece() { return this.pieceMaison; };
	public int getDuree() { return this.dureeConstruction; };



}
