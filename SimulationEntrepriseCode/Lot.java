import java.util.*;
public class Lot {

	private int id_lot;
	private int volume; //Volume = longueur car largeur = hauteur = 1
	private String nom_lot;
	private double prix, poids; //prix = celui au metre cube
	public static int count = 1;
	public static List<Lot> list_lots = new LinkedList<Lot>();

	public void l_Unique () {
		this.id_lot = count++;
	}


	public void setVol(int volume) { this.volume = volume; };
	public void setNom(String nom_lot) { this.nom_lot = nom_lot; };
	public void setPrix(double prix) { this.prix = prix; };
	public void setPoids(double poids) { this.poids = poids; };

	public int getID() { return this.id_lot; };
	public int getVol() { return this.volume; };
	public String getNom() { return this.nom_lot; };
	public double getPrix() { return this.prix; };
	public double getPoids() { return this.poids; };

	public Lot(int volume, String nom_lot, double prix, double poids) {
		this.volume = volume;
		this.nom_lot = nom_lot;
		this.prix = prix;
		this.poids = poids;
		l_Unique();
	}

	public void addLot() {
		list_lots.add(this);
	}

	public static void showStock () {
        for(int i = 0; i < list_lots.size(); i++) {
            Lot l = list_lots.get(i);
            System.out.println("ID : " + l.id_lot + " Volume : " + l.volume + " Nom : " + l.nom_lot + " Prix : " + l.prix + " Poids : " + l.poids);
        }
    }

	public static Lot accesLot(int id_lot) {
    List<Lot> l = Lot.list_lots;
    int longueur = l.size();
    for (int i = 0; i < longueur; i++) {
      if (id_lot == l.get(i).id_lot) {
        return l.get(i);
      }
    }
    return l.get(0);
  }

	public void suppLot(int indice, Rangee r, int volume) {
    int x = Math.min(volume, this.volume);
    for (int i = 0; i < x; i++) {
      r.getll().set(indice + i,0);
    }
    for (int i = 0; i < list_lots.size(); i++) {
      if (list_lots.get(i) == this) {
        this.volume = this.volume - x;
        if (this.volume <= 0) { list_lots.remove(i); }
      }
    }
  }

	public void fairePlace() {
		boolean stop = false;
		boolean test_personnel = false;
		for (int i = 0; i < list_lots.size(); i++) {
			Lot lot = list_lots.get(i);
			if (this.volume <= lot.volume && (this.volume * this.prix) > (lot.volume * lot.prix)) {
				if (Chef.rechercheStock()) { test_personnel = true; }
				else if (Ouvrier.rechercheStock()) { test_personnel = true; }
				if (test_personnel) {
					for (int j = 0; j < Entrepot.m; j++) {
						Rangee r = Entrepot.rangee_list.get(j);
						List<Integer> lot_list = r.getll();
						for (int k = 0; k < Entrepot.n; k++) {
							if (lot_list.get(k) == lot.id_lot) {
								lot.suppLot(k, r, lot.volume);
								stop = true; break;
							}
						}
						if (stop) { break; }
					}
					Simulation.verif_stockage(this);
					return;
				}
				else { return; }
			}
		}
	}

}
