package projetexceptions;

public class AffecterException extends Exception{

  public AffecterException() {
    super("Affectation impossible, veuillez recruter un chef");
  }

  public AffecterException(String message) {
    super(message);
  }

}
