package theaterTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import theater.Klant;
import theater.Plaats;
import theater.Theater;
import theater.Voorstelling;
import theater.Plaats.Status;

class VoorstellingTest {

private Voorstelling voorstelling;
private Klant nieuweKlant;

@Before
public void setUp() {

}

  @Test
  void testGetNaamVoorstelling()  {
    
    Voorstelling voorstelling = new Voorstelling("Doornroosje", "03 04 2018");
    assertEquals("Doornroosje", voorstelling.getNaamVoorstelling());
  }

  @Test
  void testStatusVrij() {
    voorstelling = new Voorstelling("Roodkapje", "25 12 2017");

    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[1][1].getStatus());
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[15][10].getStatus());
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[7][7].getStatus());
  }

  @Test
  void testReserveringsStatusWijzigen() {
    voorstelling = new Voorstelling("Steven en de Draak", "17 01 2018");
    nieuweKlant = new Klant(2, "Joris", "53245");
    
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[7][9].getStatus());
    voorstelling.reserveringsStatusWijzigen(7, 9, "reserveer");
    assertEquals(Status.GERESERVEERD, voorstelling.getZaalbezetting()[7][9].getStatus());
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[15][10].getStatus());
    voorstelling.reserveringsStatusWijzigen(15, 10, "reserveer");
    assertEquals(Status.GERESERVEERD, voorstelling.getZaalbezetting()[15][10].getStatus());
  }

  @Test
  void testPlaatsKlant() {
    voorstelling = new Voorstelling("Wintercircus", "26 12 2017");
    nieuweKlant = new Klant(2, "Joris", "53245");
    
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[8][8].getStatus());
    voorstelling.reserveringsStatusWijzigen(8, 8, "reserveer");
    assertEquals(Status.GERESERVEERD, voorstelling.getZaalbezetting()[8][8].getStatus());
    voorstelling.plaatsKlant(nieuweKlant);
    assertEquals(Status.BEZET, voorstelling.getZaalbezetting()[8][8].getStatus());
  }

  @Test
  void testMaakGereserveerdePlaatsenVrij() {
    voorstelling = new Voorstelling("Andre Rieu", "30 06 2018");

    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[8][8].getStatus());
    voorstelling.reserveringsStatusWijzigen(8, 8, "reserveer");
    assertEquals(Status.GERESERVEERD, voorstelling.getZaalbezetting()[8][8].getStatus());
    voorstelling.maakGereserveerdePlaatsenVrij();
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[8][8].getStatus());
  }
  
  @Test
  void testGetAantalPlaatsen()  {

    voorstelling = new Voorstelling("Andre Rieu", "30 06 2018");
    
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[12][3].getStatus());
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[12][4].getStatus());
    assertEquals(Status.VRIJ, voorstelling.getZaalbezetting()[12][5].getStatus());
    
    assertEquals(150, voorstelling.getAantalPlaatsen(Status.VRIJ));
    
    voorstelling.reserveringsStatusWijzigen(12, 3, "reserveer");
    voorstelling.reserveringsStatusWijzigen(12, 4, "reserveer");
    voorstelling.reserveringsStatusWijzigen(12, 5, "reserveer");
    
    assertEquals(Status.GERESERVEERD, voorstelling.getZaalbezetting()[12][3].getStatus());
    assertEquals(Status.GERESERVEERD, voorstelling.getZaalbezetting()[12][4].getStatus());
    assertEquals(Status.GERESERVEERD, voorstelling.getZaalbezetting()[12][5].getStatus());
    
    assertEquals(3, voorstelling.getAantalPlaatsen(Status.GERESERVEERD));

    Klant klant = new Klant(1, "Steven", "1234");
    voorstelling.plaatsKlant(klant);
    
    assertEquals(Status.BEZET, voorstelling.getZaalbezetting()[12][3].getStatus());
    assertEquals(Status.BEZET, voorstelling.getZaalbezetting()[12][4].getStatus());
    assertEquals(Status.BEZET, voorstelling.getZaalbezetting()[12][5].getStatus());
    
    assertEquals(3, voorstelling.getAantalPlaatsen(Status.BEZET));
    assertEquals(147, voorstelling.getAantalPlaatsen(Status.VRIJ));
    
  }

}
