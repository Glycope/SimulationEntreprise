import java.util.*;
public class Personnel {

	private String nom, prenom;
	private int id_staff;
	private static int count = 1;
	private int occupee = 0;

	public void Unic(int id_staff) { //Dès qu'on crée un employé, il prend la valeur du précédent + 1 , voir pour le licenciement
		this.id_staff = count++;
	}

	public Personnel() {
		Unic(count);
	}

	public String getNom() { return this.nom; }
	public String getPrenom() { return this.prenom; }
	public int getID() { return this.id_staff; }
	public int getOccupee() { return this.occupee; }

	public void setOccupee(int occupee) { this.occupee = occupee; }
	public void setNom(String nom) { this.nom = nom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }

}
