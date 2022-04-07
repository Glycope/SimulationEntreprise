import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;



class ClassTest {

	
	
	static Rangee testRangee;
	
	static List<Integer> list_lots = new ArrayList<Integer>();

	
	static Chef Chef1 = new Chef("Stock");
	static Chef Chef2 = new Chef("Stock");
	static Chef Chef3 = new Chef("Brico");
	
	static Ouvrier ouv1 = new Ouvrier("Chambre");
	static Ouvrier ouv2 = new Ouvrier("Cuisine");
	static Ouvrier ouv3 = new Ouvrier("Table");
	static Ouvrier ouv4 = new Ouvrier("Table");
	static Ouvrier ouv5 = new Ouvrier("Table");
	static Ouvrier ouv6 = new Ouvrier("Table");
	static Ouvrier ouv7 = new Ouvrier("Table");
	static Ouvrier ouv8 = new Ouvrier("Table");
	static Ouvrier ouv9 = new Ouvrier("Table");
	
	static Lot lot1 =  new Lot(1, "lot1", 1, 1);
	static Lot lot2 =  new Lot(1, "lot2", 1, 1);
	static Lot lot3 =  new Lot(2, "lot3", 1, 1);
	
	static List<Chef> list_Chef = new LinkedList<Chef>();
	
	static List<Ouvrier> list_Ouvrier1 = new LinkedList<Ouvrier>();
	static List<Ouvrier> list_Ouvrier2 = new LinkedList<Ouvrier>();
	static List<Ouvrier> list_Ouvrier3 = new LinkedList<Ouvrier>();
	
	static List<Personnel> list_staff = new LinkedList<Personnel>();
	
	
	@BeforeAll
	public static void start () {
		
		Entrepot.chef_list.add(Chef1);
		Entrepot.chef_list.add(Chef2);
		Entrepot.chef_list.add(Chef3);
		
		
		Ouvrier.affectOuv(ouv1, 0);
		Ouvrier.affectOuv(ouv2, 0);
		Ouvrier.affectOuv(ouv3, 0);
		Ouvrier.affectOuv(ouv4, 0);
		Ouvrier.affectOuv(ouv5, 0);
		Ouvrier.affectOuv(ouv6, 0);
		Ouvrier.affectOuv(ouv7, 0);
		Ouvrier.affectOuv(ouv8, 0);
		Ouvrier.affectOuv(ouv9, 0);
		
	}
	
	@BeforeEach
	public void init() {
		
		
		Lot.list_lots.add(lot1);
		Lot.list_lots.add(lot2);
		Lot.list_lots.add(lot3);
		
		list_lots.add(lot1.getID());
		list_lots.add(lot2.getID());
		list_lots.add(lot3.getID());
		list_lots.add(lot3.getID());
		
		
	
		
		
		Entrepot.n = 4;
		Entrepot.m = 2;
		Entrepot.treso = 10000;
		

		Entrepot.rangee_list = Entrepot.Init_Entrepot();

		
		
		testRangee = Entrepot.rangee_list.get(0);
	

		Chef1.setOccupee(0);
		Chef2.setOccupee(0);
		Chef3.setOccupee(0);
		ouv1.setOccupee(0);
		ouv2.setOccupee(0);
		ouv3.setOccupee(0);
		ouv4.setOccupee(0);
		ouv5.setOccupee(0);
		ouv6.setOccupee(0);
		ouv7.setOccupee(0);
		ouv8.setOccupee(0);	
		ouv9.setOccupee(0);
	
	} 
	
	@Test
	public void testStockage () {

		Simulation.verif_stockage(lot1);
		Simulation.verif_stockage(lot2);
		Simulation.verif_stockage(lot3);
	
		assertEquals(list_lots, testRangee.getll());
	}
	

	@Test
	public void testStockage2 () { //Aucune chef dispo

		Chef1.setOccupee(1);
		Chef2.setOccupee(1);
		Chef3.setOccupee(1); 
		
		Simulation.verif_stockage(lot1);
		Simulation.verif_stockage(lot2);
		Simulation.verif_stockage(lot3);
		
		assertEquals(list_lots, testRangee.getll());

	}
	
	@Test
	public void testStockage3 () { //1 chef dispo, 2 ouvriers dispo

		ouv1.setOccupee(1);
		ouv2.setOccupee(1);
		ouv3.setOccupee(1);
		ouv4.setOccupee(1);
		ouv5.setOccupee(1);
		ouv6.setOccupee(1);
		ouv7.setOccupee(1);
		
		
		
		Simulation.verif_stockage(lot1);
		Simulation.verif_stockage(lot2);
		Simulation.verif_stockage(lot3);
		
		assertEquals(list_lots, testRangee.getll());

	} 

	public void testStockage4 () { //Personne de dispo

		Chef1.setOccupee(1);
		Chef2.setOccupee(1);
		Chef3.setOccupee(1);
		ouv1.setOccupee(1);
		ouv2.setOccupee(1);
		ouv3.setOccupee(1);
		ouv4.setOccupee(1);
		ouv5.setOccupee(1);
		ouv6.setOccupee(1);
		ouv7.setOccupee(1);
		ouv8.setOccupee(1);
		ouv9.setOccupee(1);
		
		Simulation.verif_stockage(lot1);
		Simulation.verif_stockage(lot2);
		Simulation.verif_stockage(lot3);
		list_lots.set(0,0);
		list_lots.set(1,0);
		list_lots.set(2,0);
		list_lots.set(3,0);
		assertEquals(list_lots, testRangee.getll());

	}
	@Test
	public void testAcces () {
		
		assertEquals(lot1, Lot.accesLot(1));
		
	}
	
	@Test
	public void testOccupation () {
		
		
		Chef1.setOccupee(1);
		Chef2.setOccupee(1);
		Chef3.setOccupee(1);
		ouv1.setOccupee(1);
		ouv2.setOccupee(1);
		ouv3.setOccupee(1);
		ouv4.setOccupee(1);
		ouv5.setOccupee(1);
		ouv6.setOccupee(1);
		ouv7.setOccupee(1);
		ouv8.setOccupee(1);
		ouv9.setOccupee(1);
		Chef.reduireOccupation();
		int nb = ouv1.getOccupee();
		assertEquals(0, nb);
	}

	@Test 
	public void testRecherche () {
		Chef.rechercheBrico(3);
		assertEquals(3,Chef3.getOccupee());
	}
	
	@Test 
	public void testRecherche2 () {
		Ouvrier.rechercheBrico(3,"Chambre");
		assertEquals(3,ouv1.getOccupee());
	}

	@AfterEach
	public void reset() {
		Entrepot.rangee_list.clear();
		testRangee = null;
		list_lots.clear();
	
	
		Lot.list_lots.clear();
	} 

}

