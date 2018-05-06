package theater;

import java.util.ArrayList;

import theater.Plaats.Status;

/**
 * Opdracht 4 CPP Java december 2017.
 * Klasse verantwoordelijk voor het beheer van het theater en de klanten.
 * Via deze klasse worden methoden op klanten en boekingen aangeroepen.
 * @author Steven Minken.
 */

public class Theater {
  
//Attributen
public static final int AANTALRIJEN = 15;;
public static final int AANTALPERRIJ = 10;
private String theaterNaam;
private int hoogsteKlantNummer = 0;

//lijsten ten behoeve van het beheren van klanten en voorstellingen voor het theater. 
private ArrayList<Klant> klantenLijst;
private Voorstelling voorstelling;

  /**
   * Initialiseert een nieuw theater.
   * Een nieuw theater heeft per specificatie 15 rijen en 10 stoelen per rij.
   * @param theaterNaam De naam van het theater.
   */
  public Theater(String theaterNaam, String voorstellingsNaam, String datum)   {
    this.theaterNaam = theaterNaam;
    klantenLijst = new ArrayList<Klant>();
    nieuweVoorstelling(voorstellingsNaam, datum);
  }
  
  /**
   * Initialiseert een nieuwe voorstelling met gegeven naam en datum.
   * @param voorstellingsNaam De naam van de voorstelling.
   * @param datum De datum van de voorstelling.
   */
  public void nieuweVoorstelling(String voorstellingsNaam, String datum) {
    voorstelling = new Voorstelling(voorstellingsNaam, datum); 
  }
  
  /**
   * Maakt nieuwe klant aan met een naam en een telefoonnummer. 
   * Het klantnummer wordt automatisch aangemaakt door 1 op te tellen bij hoogsteKlantNummer. 
   * De nieuwe klant wordt aan de klantenlijst toegevoegd.
   * @param naam Naam van de klant.
   * @param telefoon Telefoonnummer van de klant.
   */
  public void nieuweKlant(String naam, String telefoon) {
    if (controleerBestaandeKlant(naam, telefoon) != true) {
      int nieuwKlantnummer = zoekHoogsteKlantnummer() + 1;
      klantenLijst.add(new Klant(nieuwKlantnummer, naam, telefoon));
    }
  }
  
  /**
   * Zoekt klant met gegeven naam en telefoonnummer.
   * @param naam Naam van de klant.
   * @param telefoonNummer telefoonnummer van de klant.
   * @return Retourneert Klant indien gevonden, zo niet retouneert null.
   */
  
  public boolean controleerBestaandeKlant(String naam, String telefoonNummer) {
    for (Klant klant: klantenLijst)     {
      if(klant.getNaam().equals(naam) && klant.getTelefoon().equals(telefoonNummer))  {
        return true;
      }
    }
    return false;
  }
  
  /**    
   * Reserveert plaats met een rijnummer en een stoelnummer.
   * @param rijnummer Neemt een rijnummer.
   * @param stoelnummer Neemt een stoelnummer.
   * 
   */
  public void reserveer(int rijnummer, int stoelnummer) {
      voorstelling.reserveringsStatusWijzigen(rijnummer, stoelnummer, "reserveer");
    }
  
  /**    
   * Annuleert plaats met een rijnummer en een stoelnummer.
   * @param rijnummer Neemt een rijnummer.
   * @param stoelnummer Neemt een stoelnummer.
   * 
   */
  public void annuleer(int rijnummer, int stoelnummer) {
      voorstelling.reserveringsStatusWijzigen(rijnummer, stoelnummer, "annuleer");
    }

  
  /**
   * Bezet gereserveerde plaats definitief. 
   * Dubble check of klant al bestaat, zo niet dan wordt er eerst een nieuwe klant aangemaakt.
   * @param naam Naam van de klant.
   * @param telefoonnummer Telefoonnummer van de klant.
   */
  
  public void plaatsKlant(String naam, String telefoonnummer) {
    if(controleerBestaandeKlant(naam, telefoonnummer) == false)  {
    nieuweKlant(naam, telefoonnummer);
    }
    for (Klant klant: klantenLijst)     {
      if(klant.getNaam().equals(naam) && klant.getTelefoon().equals(telefoonnummer))  {
        voorstelling.plaatsKlant(klant);
      }
    }
 }

  /**
   * Reset reservering en maak alle gereserveerde plaatsen vrij.
   */
  public void resetReserveringen() {
    voorstelling.maakGereserveerdePlaatsenVrij();
  }

/**
 * Retourneert aantal plaatsen met een bepaalde status
 * @param status Status van de plaats.
 * @return int Geeft integer met het totaal van de plaatsen.  
 */
  public int getAantalPlaatsen(Status status)  {
    int aantalPlaatsen = 0;
    aantalPlaatsen = voorstelling.getAantalPlaatsen(status);
    
    return aantalPlaatsen;
  }
  
  /**
   * Geeft het hoogste actuele klantnummer.
   * @return int Hoogste klantnummer.
   */
  public int zoekHoogsteKlantnummer() {
    int hoogsteNummer = 0;
    for(Klant klant: klantenLijst)  {
      if(klant.getKlantnummer() > hoogsteNummer)  {
        hoogsteNummer = klant.getKlantnummer();
      }
    }
    return hoogsteNummer;
  }
  
  /**
   * Geeft de status VRIJ, GERESERVEERD of BEZET van de betreffende stoel.
   * @param i Rijnummer
   * @param j Stoelnummer
   * @return De status VRIJ, GERESERVEERD of BEZET
   */
  public Status getStoelStatus (int i, int j){
    return voorstelling.getZaalbezetting()[i][j].getStatus();
   }
  
  /**
   * Print informatie over het aantal vrije, gereserveerde en bezette plaatsen.
   * @param theater Theater waarop het aantal is toegepast voor de huidige voorstelling.
   */
  public static void printInfo(Theater theater) {
    System.out.println("VRIJ: " + theater.getAantalPlaatsen(Status.VRIJ) +
        " / GERESERVEERD: " + theater.getAantalPlaatsen(Status.GERESERVEERD) +
        " / BEZET: " + theater.getAantalPlaatsen(Status.BEZET));
  }

}
