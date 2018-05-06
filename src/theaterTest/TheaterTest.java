package theaterTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import theater.Klant;
import theater.Plaats;
import theater.Theater;
import theater.Voorstelling;
import theater.Plaats.Status;

class TheaterTest {

  public Theater theater;
  public Voorstelling voorstelling;
  public Plaats[][] zaalbezetting;
  
  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }
  
  @Test 
  void testNieuweVoorstelling()  {
    
    Voorstelling voorstellingTwee = new Voorstelling("Doornroosje", "03 04 2018");
    assertEquals("Doornroosje", voorstellingTwee.getNaamVoorstelling());
    
  }

  @Test
  void testNieuweKlant() {
    Theater theater = new Theater("Chassé Theater", "Steven en de draak", "01 01 2050");
    
    theater.nieuweKlant("Steven", "1234");
    assertTrue(theater.controleerBestaandeKlant("Steven", "1234"));
    theater.nieuweKlant("Joris", "6543");
    assertTrue(theater.controleerBestaandeKlant("Joris", "6543"));
    assertFalse(theater.controleerBestaandeKlant("Jopie", "3456"));
  }
  
  @Test
  void testControleerBestaandeKlant() {
    Theater theater = new Theater("Chassé Theater", "Steven en de draak", "01 01 2050");
    
    theater.nieuweKlant("Steven", "1234");
    assertTrue(theater.controleerBestaandeKlant("Steven", "1234"));
    theater.nieuweKlant("Joris", "6543");
    assertTrue(theater.controleerBestaandeKlant("Joris", "6543"));
    assertFalse(theater.controleerBestaandeKlant("Jopie", "3456"));
  }

  @Test
  void testReserveer() {
    Theater theater = new Theater("Chassé Theater", "Steven en de draak", "01 01 2050");

    assertEquals(Status.VRIJ, theater.getStoelStatus(7,7));
    theater.reserveer(7, 7);
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(7,7));
    
    assertEquals(Status.VRIJ, theater.getStoelStatus(15,10));
    theater.reserveer(15, 10);
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(15,10));
    
    assertEquals(Status.VRIJ, theater.getStoelStatus(1,1));
    theater.reserveer(1, 1);
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(1,1));
     
  }
  
  @Test
  void testAnnuleer() {
    Theater theater = new Theater("Chassé Theater", "Roodkapje", "01 01 2018");
    
    assertEquals(Status.VRIJ, theater.getStoelStatus(1,3));
    assertEquals(Status.VRIJ, theater.getStoelStatus(1,4));
    assertEquals(Status.VRIJ, theater.getStoelStatus(1,5));
    
    theater.reserveer(1, 3);
    theater.reserveer(1, 4);
    theater.reserveer(1, 5);
    
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(1,3));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(1,4));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(1,5));

    theater.annuleer(1, 3);
    theater.annuleer(1, 4);
    theater.annuleer(1, 5);
    
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,3));
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,4));
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,5));
  }

  @Test
  void testPlaatsKlant() {
    Theater theater = new Theater("Chassé Theater", "Roodkapje", "01 01 2018");
 
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,3));
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,4));
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,5));
    
    theater.reserveer(12, 3);
    theater.reserveer(12, 4);
    theater.reserveer(12, 5);
    
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(12,3));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(12,4));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(12,5));

    theater.nieuweKlant("Steven", "1234");
    theater.plaatsKlant("Steven", "1234");
    
    assertEquals(Status.BEZET, theater.getStoelStatus(12,3));
    assertEquals(Status.BEZET, theater.getStoelStatus(12,4));
    assertEquals(Status.BEZET, theater.getStoelStatus(12,5));


  }
  
  @Test
  void testResetReserveringen() {
    Theater theater = new Theater("Chassé Theater", "Roodkapje", "01 01 2018");
    
    assertEquals(Status.VRIJ, theater.getStoelStatus(9,3));
    assertEquals(Status.VRIJ, theater.getStoelStatus(9,4));
    assertEquals(Status.VRIJ, theater.getStoelStatus(9,5));
    
    theater.reserveer(9, 3);
    theater.reserveer(9, 4);
    theater.reserveer(9, 5);
    
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(9,3));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(9,4));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(9,5));
    
    theater.resetReserveringen();
    
    assertEquals(Status.VRIJ, theater.getStoelStatus(9,3));
    assertEquals(Status.VRIJ, theater.getStoelStatus(9,4));
    assertEquals(Status.VRIJ, theater.getStoelStatus(9,5));
  }
  
  @Test 
  void testGetAantalPlaatsen()  {
    Theater theater = new Theater("Chassé Theater", "Roodkapje", "01 01 2018");
    
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,3));
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,4));
    assertEquals(Status.VRIJ, theater.getStoelStatus(12,5));
    
    assertEquals(150, theater.getAantalPlaatsen(Status.VRIJ));
    
    theater.reserveer(12, 3);
    theater.reserveer(12, 4);
    theater.reserveer(12, 5);
    
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(12,3));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(12,4));
    assertEquals(Status.GERESERVEERD, theater.getStoelStatus(12,5));
    
    assertEquals(3, theater.getAantalPlaatsen(Status.GERESERVEERD));

    theater.nieuweKlant("Steven", "1234");
    theater.plaatsKlant("Steven", "1234");
    
    assertEquals(Status.BEZET, theater.getStoelStatus(12,3));
    assertEquals(Status.BEZET, theater.getStoelStatus(12,4));
    assertEquals(Status.BEZET, theater.getStoelStatus(12,5));
    
    assertEquals(3, theater.getAantalPlaatsen(Status.BEZET));
    assertEquals(147, theater.getAantalPlaatsen(Status.VRIJ));
    
  }

  @Test
  void testZoekHoogsteKlantnummer() {
    ArrayList<Klant> klantenLijst = new ArrayList<Klant>();
    klantenLijst.add(new Klant(0, "Steven", "1234"));
    klantenLijst.add(new Klant(1, "Joris", "3456"));
    klantenLijst.add(new Klant(2, "Annabel", "5678"));
    assertEquals(3, klantenLijst.size());
  }

}
