import java.util.*;
import projetexceptions.*;
public class Ouvrier extends Personnel {

	private String spe; //Spécialité de l'ouvrier
	private int chef_id;
	public static int nbCuisine = 0;
	public static int nbSDB = 0;
	public static int nbSAM = 0;
	public static int nbChambre = 0;
	public static int nbWC = 0;
	public static int nbSalon = 0;
	public static boolean sousEffectif = true;

	public Ouvrier(String spe) {
		this.spe = spe;
	}

	//Fonction suivant la spécialité
	public String getSpe() { return this.spe; }
	public void setSpe(String spe) { this.spe = spe; }

	public static Ouvrier rechercheID(int id_staff) {
		List<Chef> chef_list = Entrepot.chef_list;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i); List<Ouvrier> list_ouvrier = chef.list_ouvrier;
			for (int j = 0; j < list_ouvrier.size(); j++) {
				Ouvrier ouv = list_ouvrier.get(i);
				if (ouv.getID() == id_staff) { return ouv; }
			}
		}
		return null;
	}

	public static int affectOuv(Ouvrier ouv, int k) {
		try {
			List<Chef> chef_list = Entrepot.chef_list;
			for (int i = k; i < chef_list.size(); i++) {
				Chef chef = chef_list.get(i);
				if (chef.getNbOuv() < 4) {
					chef.getListOuv().add(ouv);
					chef.setNbOuv(chef.getNbOuv() + 1);
					ouv.chef_id = chef.getID();
					return i;
				}
			}
			throw new AffecterException();
		}
		catch(AffecterException a) {
			System.out.println("Affectation impossible, veuillez recruter un chef");
		}
		return -1;
	}

	public static boolean reaffectOuvs(List<Ouvrier> list_ouvrier) {
		List<Chef> chef_list = Entrepot.chef_list;
		int k = 0;
		int cpt = 0;
		for (int i = 0; i < list_ouvrier.size(); i++) {
			Ouvrier ouv = list_ouvrier.get(i);
			k = affectOuv(ouv, k);
			if (k != -1) { cpt++; }
			else {  }
		}
		if (cpt == list_ouvrier.size()) { return true; }
		else { return false; }
	}

	public static boolean rechercheStock() {
		List<Chef> chef_list = Entrepot.chef_list;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			List<Ouvrier> list_ouvrier = chef.getListOuv();
			for (int j = 0; j < list_ouvrier.size(); j++) {
				Ouvrier ouv = list_ouvrier.get(j);
				if (ouv.getOccupee() == 0) {
					ouv.setOccupee(1);
					return true;
				}
			}
		}
		sousEffectif = true;
		return false;
	}

	public static boolean rechercheBrico(int dureeConstruction, String pieceMaison) {
		List<Chef> chef_list = Entrepot.chef_list;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			List<Ouvrier> list_ouvrier = chef.getListOuv();
			for (int j = 0; j < list_ouvrier.size(); j++) {
				Ouvrier ouv = list_ouvrier.get(j);
				if (ouv.getOccupee() == 0 && pieceMaison.equals(ouv.spe)) {
					ouv.setOccupee(dureeConstruction);
					return true;
				}
			}
		}
		sousEffectif = true;
		return false;
	}

	public static void recruter() {
		String spe = minSpe();
		Ouvrier ouv = new Ouvrier(spe);
		String nom = "NO" + ouv.getID();
		String prenom = "PO" + ouv.getID();
		ouv.setNom(nom); ouv.setPrenom(prenom);
		affectOuv(ouv, 0);
		if (spe.equals("Cuisine")) { Ouvrier.nbCuisine++; }
		else if (spe.equals("Salle à manger")) { Ouvrier.nbSAM++; }
		else if (spe.equals("Salon")) { Ouvrier.nbSalon++; }
		else if (spe.equals("Salle de bain")) { Ouvrier.nbSDB++; }
		else if (spe.equals("Chambre")) { Ouvrier.nbChambre++; }
		else if (spe.equals("WC")) { Ouvrier.nbWC++; }
		sousEffectif = false;
	}

	public static void licencier() {
		List<Chef> chef_list = Entrepot.chef_list;
		String spe = maxSpe();
		if (spe.equals("Cuisine")) { Ouvrier.nbCuisine--; }
		else if (spe.equals("Salle à manger")) { Ouvrier.nbSAM--; }
		else if (spe.equals("Salon")) { Ouvrier.nbSalon--; }
		else if (spe.equals("Salle de bain")) { Ouvrier.nbSDB--; }
		else if (spe.equals("Chambre")) { Ouvrier.nbChambre--; }
		else if (spe.equals("WC")) { Ouvrier.nbWC--; }
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			List<Ouvrier> list_ouvrier = chef.getListOuv();
			for (int j = 0; j < list_ouvrier.size(); j++) {
				Ouvrier ouv = list_ouvrier.get(j);
				if (spe.equals(ouv.spe)) {
					list_ouvrier.remove(ouv);
					chef.setNbOuv(chef.getNbOuv() - 1);
				}
			}
		}
	}

	public static String minSpe() {
		List<Integer> l = new ArrayList<Integer>();
		List<String> m = new ArrayList<String>();
		l.add(nbCuisine); l.add(nbSDB); l.add(nbSAM); l.add(nbChambre); l.add(nbWC); l.add(nbSalon);
		m.add("Cuisine"); m.add("Salle de bain"); m.add("Salle à manger"); m.add("Chambre"); m.add("WC"); m.add("Salon");
		int imin = 0;
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i) < l.get(imin)) { imin = i; }
		}
		return m.get(imin);
	}

	public static String maxSpe() {
		List<Integer> l = new ArrayList<Integer>();
		List<String> m = new ArrayList<String>();
		l.add(nbCuisine); l.add(nbSDB); l.add(nbSAM); l.add(nbChambre); l.add(nbWC); l.add(nbSalon);
		m.add("Cuisine"); m.add("Salle de bain"); m.add("Salle à manger"); m.add("Chambre"); m.add("WC"); m.add("Salon");
		int imax = 0;
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i) > l.get(imax)) { imax = i; }
		}
		return m.get(imax);
	}

	public static int sumOuv() {
		List<Chef> chef_list = Entrepot.chef_list;
		int cpt = 0;
		for (int i = 0; i < chef_list.size(); i++) {
			cpt = cpt + chef_list.get(i).getNbOuv();
		}
		return cpt;
	}

	public static void sousEffectif() {
		if (sumOuv() < 6) { sousEffectif = true; }
		if (sumOuv() >= 3 * Entrepot.chef_list.size()) { Chef.sousEffectif = true; }
	}

	public static void surEffectif() {
		int sumChefs = Entrepot.chef_list.size();
		double sumPaie = sumOuv() * 5 + sumChefs * 10;
		if (sumPaie > Entrepot.treso / 10.0) {
			if (sumOuv() < 2 * sumChefs) { Chef.licencier(); }
			else { licencier(); }
		}
	}

}
