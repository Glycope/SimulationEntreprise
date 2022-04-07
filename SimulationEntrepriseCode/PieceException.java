package projetexceptions;

public class PieceException extends Exception{

  public PieceException() {
    super("La pièce doit figurer parmi : Salon, Chambre, Cuisine, Salle à manger, Salle de bain, WC");
  }

  public PieceException(String message) {
    super(message);
  }

}
