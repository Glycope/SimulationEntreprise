import java.util.*;
public class Chef extends Personnel {

	private String type; //soit stock soit bricolage
	private int nbOuvrier = 0; //de 0 Ã  4
	protected List<Ouvrier> list_ouvrier = new LinkedList<Ouvrier>();
	public static int nbStock = 0;
	public static int nbBrico = 0;
	public static boolean sousEffectif = true;

	public Chef(String type) {
		this.type = type;
	}

	public String getType() { return this.type; }
	public int getNbOuv() { return this.nbOuvrier; }
	public List<Ouvrier> getListOuv() { return this.list_ouvrier; }

	public void setType(String type) { this.type = type; }
	public void setNbOuv(int nbOuvrier) { this.nbOuvrier = nbOuvrier; }
	public void setListOuv(List<Ouvrier> list_ouvrier) { this.list_ouvrier = list_ouvrier; }

	public static void showChef() {
		List<Chef> chef_list = Entrepot.chef_list;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			System.out.println(chef.getID() + " " + chef.getNom() + " " + chef.getPrenom() + " " + chef.type + " " + chef.nbOuvrier);
		}
	}

	public static boolean rechercheStock() {
		List<Chef> chef_list = Entrepot.chef_list;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			if (chef.type.equals("Stock") && chef.getOccupee() == 0) {
				chef.setOccupee(1);
				return true;
			}
		}
		sousEffectif = true;
		return false;
	}

	public static boolean rechercheBrico(int dureeConstruction) {
		List<Chef> chef_list = Entrepot.chef_list;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			if (chef.type.equals("Brico") && chef.getOccupee() == 0) {
				chef.setOccupee(dureeConstruction);
				return true;
			}
		}
		sousEffectif = true;
		return false;
	}

	public static void recruter() {
		String type;
		if (Chef.nbStock <= Chef.nbBrico) { type = "Stock"; Chef.nbStock++; }
		else { type = "Brico"; Chef.nbBrico++; }
		Chef chef = new Chef(type);
		String nom = "NC" + chef.getID();
		String prenom = "PC" + chef.getID();
		chef.setNom(nom); chef.setPrenom(prenom);
		Entrepot.chef_list.add(chef);
		sousEffectif = false;
	}

	public static boolean licencier() {
		List<Chef> chef_list = Entrepot.chef_list;
		String type;
		if (Chef.nbStock >= Chef.nbBrico) { type = "Stock";}
		else { type = "Brico";}
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			if (chef.type.equals(type) && Ouvrier.reaffectOuvs(chef.list_ouvrier)) {
				chef_list.remove(chef);
				if (type.equals("Brico")) { nbBrico--; } else { nbStock--; }
				return true;
			}
		}
		return false;
	}

	public static void reduireOccupation() {
		List<Chef> chef_list = Entrepot.chef_list;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			if (chef.getOccupee() > 0) { chef.setOccupee(chef.getOccupee() - 1); }
			for (int j = 0; j < chef.list_ouvrier.size(); j++) {
				Ouvrier ouv = chef.list_ouvrier.get(j);
				if (ouv.getOccupee() > 0) { ouv.setOccupee(ouv.getOccupee() - 1); }
			}
		}
	}

}
