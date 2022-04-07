import java.io.*;
import java.util.*;
import java.lang.StringIndexOutOfBoundsException;
import projetexceptions.*;

public class Commande {

    public static void commandeTexte(File fichier, List<String> lf) throws IOException {
        try{
          Scanner lecture = new Scanner(fichier);
          String test;
          while (lecture.hasNextLine()) {
            test = lecture.nextLine();
            lf.add(test);
          }
        }
        catch(IOException e){
            System.out.println("Fichier introuvable");
        }

    }

    public static void commandeType(String consigne, int numConsigne, boolean fichier) {
      try {
        String debutConsigne = "";
        if (fichier) { debutConsigne = "<" + numConsigne + ">" + " "; }
        int longueur = debutConsigne.length();
        String lot = debutConsigne + "lot";
        String meuble = debutConsigne + "meuble";
        String rien = debutConsigne + "rien";
      if (consigne.substring(0, 4 + longueur).equals(rien)) {/* Nouvelle itération */}
        else if (consigne.substring(0, 3 + longueur).equals(lot)) { commandeLot(consigne.substring(4 + longueur, consigne.length())); }
        else if (consigne.substring(0, 6 + longueur).equals(meuble)) { commandeMeuble(consigne.substring(7 + longueur, consigne.length())); }
        else { throw new FormatException(); }
      }
      catch(StringIndexOutOfBoundsException | FormatException e) {
        System.out.println("Format invalide !");
      }
    }

    public static void commandeLot(String consigne) {
      Scanner lecture = new Scanner(consigne);
      String x;
      x = lecture.next();
      String nom_lot = x.substring(1,x.length() - 1);
      x = lecture.next();
      double poids = Double.parseDouble(x.substring(1,x.length() - 1));
      x = lecture.next();
      double prix = Double.parseDouble(x.substring(1,x.length() - 1));
      x = lecture.next();
      int volume = Integer.parseInt(x.substring(1,x.length() - 1));
      Lot lot = new Lot(volume, nom_lot, prix, poids);
      Simulation.verif_stockage(lot);
    }
    public static void commandeMeuble(String consigne) {
      try {
        Scanner lecture = new Scanner(consigne);
        String x,y;
        x = lecture.next();
        String nom = x.substring(1, x.length() - 1);
        x = lecture.next();
        String pieceMaison = x.substring(1, x.length() - 1);
        if (Meuble.piece_list.contains(pieceMaison) == false) { throw new PieceException(); }
        x = lecture.next();
        int dureeConstruction = Integer.parseInt(x.substring(1, x.length() - 1));
        List<Pair<String,Integer>> lots_list = new ArrayList<Pair<String,Integer>>();
        do {
          Pair<String,Integer> p = new Pair<String,Integer>();
          x = lecture.next();
          y = lecture.next();
          p.setPair(x.substring(1, x.length() - 1),Integer.parseInt(y.substring(1, y.length() - 1)));
          lots_list.add(p);
        } while (lecture.hasNext());
        Meuble meuble = new Meuble();
        meuble.setMeuble(nom, lots_list, pieceMaison, dureeConstruction);
        boolean b = Simulation.recherche_meuble(meuble);
        if (Main.strategie == 1) { Rangee.rearrange1(); }
        else if (Main.strategie == 2) { Rangee.rearrange2(); }
      }
      catch(StringIndexOutOfBoundsException | PieceException p) {
        System.out.println("La pièce doit figurer parmi : Salon, Chambre, Cuisine, Salle à manger, Salle de bain, WC");
      }

    }
}
