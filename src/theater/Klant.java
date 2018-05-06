package theater;

/**
 * Klasse die verantwoordelijk is voor het vastleggen van instanties van Klant en het opvragen van gegevens van de Klant.
 */

public class Klant {

//  Attributen
  private final int klantnummer;
  private String naam;
  private String telefoon;
  
/**
 * Maakt een nieuwe klant aan met een gegeven klantnummer, naam en telefoonnummer.
 * @param klantnummer Het unieke klantnummer van deze klant.
 * @param naam De naam van deze klant.
 * @param telefoon Het telefoonnummer van deze klant.
 */
  public Klant(int klantnummer, String naam, String telefoon)      {
    this.klantnummer = klantnummer;
    this.naam = naam;
    this.telefoon = telefoon;
  }
  
//  Methoden
/**
 * Retouneert het klantnummer van deze klant.
 * @return Klantnummer als integer.
 */
  public int getKlantnummer()   {
    return klantnummer;
  }
  
/**
 * Retourneert de naam van de klant.
 * @return: Naam als String.
 */
  public String getNaam()       {
    return naam;
  }
  
/**
 * Retourneert het telefoonnummer van de klant.
 * @return Telefoonnummer als integer.
 */
  public String getTelefoon()      {
    return telefoon;
  }
  
/**
 * Retourneert de volledige klantinformatie als String.
 * @return Volledige klantinformatie als String.
 */
  public String toString()      {
    return(String.valueOf(klantnummer) + " " + naam + " " + telefoon);
  }
  
}
