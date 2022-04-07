//Simulation, boucle de temps
import java.io.*;
import java.util.*;
public class Main {

	public static int strategie;

	public static void main (String []args) throws IOException {
		try {
		Entrepot.n = Integer.parseInt(args[1]);
		Entrepot.m = Integer.parseInt(args[2]);
		Entrepot.treso = Double.parseDouble(args[3]);
		Entrepot.rangee_list = Entrepot.Init_Entrepot();
		Scanner lecture = new Scanner(System.in);
		System.out.println("Stratégies gestion de stockage, après chaque montage de meuble :\n1) Décalage vers la gauche de tous les lots, pour chaque rangée, pour combler les espaces vides.");
		System.out.println("2) À chaque espace vide, recherche un lot de volume inférieur ou égal à l'espace dans les rangées d'après pour combler le vide.");
		System.out.println(" Choisissez votre stratégie (1 ou 2)");
		strategie = Integer.parseInt(lecture.nextLine());
		int numConsigne = 1;
		if (args[0].equals("0")) {
			String consigne;
			do {
				avantConsigne(lecture);
				System.out.println("Donnez une consigne (lot/meuble/rien)");
				consigne = lecture.nextLine();
				Commande.commandeType(consigne, numConsigne, false);
				apresConsigne(lecture);
				numConsigne++;
				System.out.println("Continuer ? (o/n)");
				consigne = lecture.nextLine();
			} while (consigne.equals("o") && Entrepot.treso > 0);
		}
		else {
			File fichier = new File(args[0]);
			List<String> consignes = new ArrayList<String>();
			Commande.commandeTexte(fichier, consignes);
			for (int i = 0; i < consignes.size(); i++) {
				avantConsigne(lecture);
				Commande.commandeType(consignes.get(i),i + 1, true);
				apresConsigne(lecture);
				numConsigne++;
			}
		}
		}
		catch(IOException e){
				System.out.println("Fichier introuvable");
		}
	}

	public static void avantConsigne(Scanner lecture) {
		Ouvrier.surEffectif();
		Ouvrier.sousEffectif();
		if (Chef.sousEffectif) { Chef.recruter(); }
		else if (Ouvrier.sousEffectif) { Ouvrier.recruter(); }
		System.out.println("Montrer les chefs ? (o/n)");
		String consigne = lecture.nextLine();
		if (consigne.equals("o")) {
			System.out.println("ID Nom Prenom Type NombreOuvriers");
			Chef.showChef();
		}
	}

	public static void apresConsigne(Scanner lecture) {
		Entrepot.payerPersonnel();
		Chef.reduireOccupation();
		System.out.println("Montrer le stock et la trésorerie ? (o/n)");
		String consigne = lecture.nextLine();
		if (consigne.equals("o")) { Lot.showStock(); System.out.println(Entrepot.rangee_list.get(0).getll()); System.out.println(Entrepot.treso); }
	}

}
