package theater;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

import theater.Plaats.Status;

/**
 * Klasse verantwoordelijk voor het beheer van de voorstelling en het bijhouden van de zaalbezetting.
 */

public class Voorstelling {

// Attributen
  private String naam;
  private GregorianCalendar datumVoorstelling;
  
//  Zaalbezetting worden vastgelegd in een 2 dimensionale array van de rijnummers en stoelnummers.
  private Plaats[][] zaalbezetting;
  
  /**
   * Maakt een nieuwe voorstelling aan met een naam en een bepaalde datum. 
   * Initialiseert de zaal van 15 rijen en 10 stoelnummers met vrije plaatsen als standaard beginwaarde voor deze voorstelling.
   * @param naam Neemt de naam van de voorstelling.
   * @param datumVoorstelling Neemt de datum van de voorstelling.
   */
  public Voorstelling (String naam, String datum) {
    this.naam = naam;
    zaalbezetting = new Plaats[Theater.AANTALRIJEN + 1][Theater.AANTALPERRIJ + 1];

//  Initialiseert de beginwaarden van de plaatsobjecten in de zaalbezetting.
    for (int i = 1; i < zaalbezetting.length; i++) {
      for(int j = 1; j < zaalbezetting[i].length; j++) {
        zaalbezetting[i][j] = new Plaats(i, j);
      }
    }
    
    SimpleDateFormat DATUMLEZER = new SimpleDateFormat("dd MM yyyy");
    Date date = DATUMLEZER.parse(datum, new ParsePosition(0));
    GregorianCalendar datumVoorstelling = new GregorianCalendar();
    datumVoorstelling.setTime(date);
  }
   
//Methoden
  
  /**
   * Retourneert de naam van de voorstelling.
   * @return Naam voorstelling.
   */
  
  public String getNaamVoorstelling()   {
    return naam;
  }
  
  /**
   * Checkt of de status van de te reserveren plaats vrij is.
   * @param rijnummer Neemt rijnummer als int.
   * @param stoelnummet Neemt stoelnummer als int.
   * @return boolean geeft true wanneer de plaats vrij is en false wanneer deze niet vrij is.
   */
  public boolean statusVrij(int rijnummer, int stoelnummer)  {
    if ((rijnummer > 0 || rijnummer <= Theater.AANTALRIJEN || stoelnummer > 0 || stoelnummer <= Theater.AANTALPERRIJ)) {
      if (getZaalbezetting()[rijnummer][stoelnummer].getStatus().equals(Status.VRIJ))  {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Wijzigt de status van VRIJ naar GERESERVEERD of omgekeerd. 
   * Rijnummer en stoelnummer worden met 1 verlaagd ivm met de index van de Array.
   * @param rijnummer Neemt rijnummer als int.
   * @param stoelnummet Neemt stoelnummer als int.
   */
  public void reserveringsStatusWijzigen(int rijnummer, int stoelnummer, String statusWijziging) {
    if ((rijnummer > 0 || rijnummer <= Theater.AANTALRIJEN || stoelnummer > 0 || stoelnummer <= Theater.AANTALPERRIJ)) {
      if (statusVrij(rijnummer, stoelnummer) && statusWijziging.equals("reserveer")) {
        getZaalbezetting()[rijnummer][stoelnummer].reserveerPlaats();
      }  else if (!statusVrij(rijnummer, stoelnummer) && statusWijziging.equals("annuleer")) {
        getZaalbezetting()[rijnummer][stoelnummer].maakPlaatsVrij();
      }
    }
  }
  
  /**
   * Plaats klant op alle gereserveerde plaatsen.
   * @param klant Klant wordt meegegeven om de bezette plaats aan de klant te koppelen.
   */
  public void plaatsKlant(Klant klant) {
    for (int i = 1; i < getZaalbezetting().length; i++) {
      for(int j = 1; j <getZaalbezetting()[i].length; j++) {
        if (getZaalbezetting()[i][j].getStatus().equals(Status.GERESERVEERD)) {
          getZaalbezetting()[i][j].bezetPlaats(klant);
        }
      }
    }
  }
  
  /**
   * Maakt gereserveerde plaats vrij bij annuleren van transactie.
   */
  public void maakGereserveerdePlaatsenVrij() {
    for (int i = 1; i < getZaalbezetting().length; i++) {
      for(int j = 1; j < getZaalbezetting()[i].length;j++) {
        if (getZaalbezetting()[i][j].getStatus().equals(Status.GERESERVEERD)) {
          getZaalbezetting()[i][j].annuleerReservering();
        }
      }
    }
  }

  /**
   * Zoekt het aantal plaatsen voor deze voorstelling met een status van VRIJ, GERESERVEERD of BEZET.
   * @return int Geeft een int van het totaal aantal plaatsen als int.
   */
  public int getAantalPlaatsen(Status status)  {
    int aantalPlaatsen = 0;
    for (int i = 1; i < getZaalbezetting().length; i++) {
      for(int j = 1; j < getZaalbezetting()[i].length;j++) {
        if (getZaalbezetting()[i][j].getStatus().equals(status)) {
          aantalPlaatsen += 1;
        }
      }
    }
    return aantalPlaatsen;
  }
/**
 * Verantwoordelijk voor het teruggeven van de zaalbezetting.
 * @return Plaats[][] Teruggave van de zaalbezeting t.b.v de testklassen.
 */
  
  public Plaats[][] getZaalbezetting() {
    return zaalbezetting;
  }
  
  
}
