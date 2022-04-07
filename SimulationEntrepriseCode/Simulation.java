import java.util.*;
public class Simulation {

  public static void verif_stockage(Lot lot) {
    int volume = lot.getVol();
    if (volume > Entrepot.n) { return; }
    for (int i = 0; i < Entrepot.m; i++) {
      int cpt = 0;
      Rangee r = Entrepot.rangee_list.get(i);
      for (int j = 0; j < Entrepot.n; j++) {
        int k = r.getll().get(j);
        if (k == 0) { cpt++; }
        else { cpt = 0; }
        if (cpt == volume) {
          boolean test_personnel = false;
          if (Chef.rechercheStock()) { test_personnel = true; }
          else if (Ouvrier.rechercheStock()) { test_personnel = true; }
          if (test_personnel) {
            stocker(lot, r, j);
          }
          return;
        }
      }
    }
    lot.fairePlace();
  }

  public static void stocker(Lot lot, Rangee r, int place) {
    int volume = lot.getVol();
    int k = place;
    while (k > place - volume) {
      r.getll().set(k,lot.getID());
      k--;
    }
    lot.addLot();
  }

  public static boolean recherche_meuble(Meuble meuble) {
    List<Lot> test_lot = new ArrayList<Lot>();
    List<Integer> test_place = new ArrayList<Integer>();
    List<Rangee> test_rangee = new ArrayList<Rangee>();
    List<Integer> test_volume = new ArrayList<Integer>();
    double argent = 0;
    List<Pair<String,Integer>> lots_list = meuble.getLots();
    int longueur = lots_list.size();
    for (int i = 0; i < longueur; i++) {
      Pair<String,Integer> p = lots_list.get(i);
      String nom = p.getL();
      int volume = p.getR();
      double ajout = recherche_lot(nom, volume, argent, test_lot, test_place, test_rangee, test_volume, 0);
      if (ajout == argent) { return false; }
      argent = ajout;
    }
    boolean test_personnel = false;
    if (Ouvrier.rechercheBrico(meuble.getDuree(), meuble.getPiece())) { test_personnel = true; }
    else if (Chef.rechercheBrico(meuble.getDuree())) { test_personnel = true; }
    if (test_personnel) {
      for (int i = 0; i < test_lot.size(); i++) {
        test_lot.get(i).suppLot(test_place.get(i), test_rangee.get(i), test_volume.get(i));
      }
      Entrepot.treso += argent;
    }
    return true;
  }

  public static double recherche_lot(String nom, int volume, double argent, List<Lot> test_lot, List<Integer> test_place, List<Rangee> test_rangee, List<Integer> test_volume, int lotInterdit) {
    List<Integer> l = recherche_IDlots(nom, lotInterdit);
    for (int i = 0; i < Entrepot.m; i++) {
      Rangee r = Entrepot.rangee_list.get(i);
      int x = l1containsl2(r.getll(), l);
      if (x != 0) {
        Lot lot = Lot.accesLot(x);
        for (int j = 0; j < Entrepot.n; j++) {
          if (r.getll().get(j) == x) {
            int v = Math.min(volume, lot.getVol());
            test_lot.add(lot); test_place.add(j); test_rangee.add(r); test_volume.add(volume);
            lotInterdit  = lot.getID();
            volume = volume - v;
            argent = argent + lot.getPrix() * v;
            if (volume == 0) { return argent; }
            else { argent = recherche_lot(nom, volume, argent, test_lot, test_place, test_rangee, test_volume, lotInterdit); }
            return argent;
          }
        }
      }
    }
    return 0;
  }

  public static List<Integer> recherche_IDlots(String nom, int lotInterdit) {
    List<Integer> l = new LinkedList<Integer>();
    int longueur = Lot.list_lots.size();
    for (int i = 0; i < longueur; i++) {
      Lot lot = Lot.list_lots.get(i);
      if (nom.equals(lot.getNom()) && lotInterdit != lot.getID()) { l.add(lot.getID()); }
    }
    return l;
  }

  public static int l1containsl2(List<Integer> l1, List<Integer> l2) {
    int longueur = l2.size();
    for (int i = 0; i < longueur; i++) {
      if (l1.contains(l2.get(i))) {
        return l2.get(i);
      }
    }
    return 0;
  }


}
