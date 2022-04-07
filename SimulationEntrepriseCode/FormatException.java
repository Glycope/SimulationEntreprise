package projetexceptions;

public class FormatException extends Exception{

	public FormatException() {
		super("Format invalide");
	}

	public FormatException(String message) {
		super(message);
	}

}
