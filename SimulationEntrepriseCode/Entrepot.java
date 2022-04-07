import java.util.*;
public class Entrepot {

	public static int n,m; //n intervalles sur m rangées
	public static double treso;
	public static List<Chef> chef_list = new LinkedList<Chef>();
	public static List<Rangee> rangee_list = new ArrayList<Rangee>();

	public static List<Rangee> Init_Entrepot() {
		for (int i = 0; i < Entrepot.m; i++) {
			Rangee r = Rangee.Init_Rangee(i + 1);
			Entrepot.rangee_list.add(r);
		}
		return rangee_list;
	}


	public static void payerPersonnel() {
		List<Chef> chef_list = Entrepot.chef_list;
		int somme = 0;
		for (int i = 0; i < chef_list.size(); i++) {
			Chef chef = chef_list.get(i);
			somme = somme + 10 + chef.getNbOuv() * 5;
		}
		Entrepot.treso = Entrepot.treso - somme;
	}

}
