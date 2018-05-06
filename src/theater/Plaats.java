package theater;

/**
 * Klasse die verantwoordelijk is voor het beheer van de status van de plaatsen.
 */

public class Plaats {
  
  /**
   * Enum om de status te kunnen beheren met de mogelijkheden vrij, gereserveerd en bezet.
   */
  public enum Status {VRIJ, GERESERVEERD, BEZET}
  
//  Atrributen
  /**
   * De beginstatus is vrij.
   */
  private Status status = Status.VRIJ;
  private int rijnummer;
  private int stoelnummer;
  private Klant klant;
  
  /**
   * Constructor
   * @param rijnummer Neemt een rijnummer als int.
   * @param stoelnummer Neemt een stoelnummer als int.
   */
  public Plaats (int rijnummer, int stoelnummer) {
    this.rijnummer = rijnummer;
    this.stoelnummer = stoelnummer;
  }
  
  /**
   * Retourneert de status van de plaats.
   * @return waarde van status. 
   */
  public Status getStatus() {
      return status;
  }
  
  /**
   * Geeft een stringrepresentatie terug van de plaats.
   * Stoelnummer en Rijnummer worden met 1 verhoogd omdat de index van de array begint op 0.
   * @param String
   */
  public String toString() {
    return ("Rijnummer: " + rijnummer + ", " + "Stoelnummer: " + stoelnummer);
  }

  /**
   * Reserveert plaats.
   */
  public void reserveerPlaats() {
    status = Status.GERESERVEERD;
  }
  
  /**
   * Bezet gereserveerde plaats(en) voor de klant.
   * @param bezetKlant is de actuele klant die de kaarten definitief wil boeken.
   */
  public void bezetPlaats(Klant bezetKlant) {
    if (status.equals(Status.GERESERVEERD)) {
      status = Status.BEZET;
      klant = bezetKlant;
    }
  }
  
  /**
   * Maakt plaats vrij van plaatsinstantie waarop de methode wordt aangeroepen.
   */
  public void maakPlaatsVrij() {
    status = Status.VRIJ;
    klant = null;
  }
  
  /**
   * Annuleert de gereserveerde plaats waarop de methode wordt aangeroepen.
   */
  public void annuleerReservering() {
    if (status.equals(Status.GERESERVEERD)) {
      status = Status.VRIJ;
    }
  }
  
}

