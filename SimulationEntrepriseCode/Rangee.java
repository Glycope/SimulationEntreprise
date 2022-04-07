import java.util.*;
public class Rangee {

	private int num_rangee;
	private List<Integer> lot_list = new ArrayList<Integer>();

	public int getnr() { return this.num_rangee; };
  public List<Integer> getll() { return this.lot_list;};

	public void setRangee (int num_rangee, List<Integer> lot_list) {
		this.num_rangee = num_rangee;
		this.lot_list = lot_list;
	}

	public void setnr(int num_rangee) { this.num_rangee = num_rangee; };
	public void setll(List<Integer> lot_list) { this.lot_list = lot_list; };

	public static Rangee Init_Rangee (int k) {
		Rangee r = new Rangee();
		r.num_rangee = k;
		for (int i = 0; i < Entrepot.n; i++) {
			r.lot_list.add(0);
		}
		return r;
	}

	public static void rearrange1() {
		int lotOcc; int lotPrec = 0;
		boolean stop = false;
		for (int i = 0; i < Entrepot.m; i++) {
			Rangee r = Entrepot.rangee_list.get(i);
			List<Integer> lot_list = r.lot_list;
			int k = -1;
			for (int j = 0; j < Entrepot.n; j++) {
				boolean b = true;
				lotOcc = lot_list.get(j);
				if (lot_list.get(j) == 0) {
					if (k == -1) { k = j; }
				}
				else if (k != -1) {
					if (lotPrec != lotOcc) {
						if (Chef.rechercheStock() == false) {
							if (Ouvrier.rechercheStock() == false) { b = false; }
						}
					}
					if (b) {
						lot_list.set(k, lot_list.get(j));
						lot_list.set(j, 0);
						lotPrec = lotOcc;
						k++;
					}
					else { stop = true; System.out.println("bite"); break; }
				}
			}
			if (stop) { break; }
		}
	}

	public static void rearrange2() {

		for(int i = 0; i < Entrepot.m; i++) {
			Rangee r = Entrepot.rangee_list.get(i);
			int espace = 0;
			int indice = 0;
			for(int j = 0; j < Entrepot.n - 1; j++) {
				int k = r.getll().get(j);
				if(k == 0)  {
					if(espace == 0) { indice = j; }
					espace++;
				}
				else if (k != 0 && espace != 0) {
					for(int l = i + 1; l < Entrepot.m; l++) {
						boolean stop = false;
						Rangee r1 = Entrepot.rangee_list.get(l);
						for(int p = 0; p < Entrepot.n; p++) {
							if (r1.getll().get(p) != 0) {
								Lot affect = Lot.accesLot(r1.getll().get(p));
								if(affect.getVol() <= espace) {
									Lot.list_lots.remove(affect);
									Simulation.verif_stockage(affect);
									for (int w = 0; w < affect.getVol(); w++) {
							      r1.getll().set(p + w,0);
							    }
									stop = true;
									break;
								}
							}
						}
						if (stop) { break; }
					}
					espace = 0;
				}
			}
		}
	}

}
