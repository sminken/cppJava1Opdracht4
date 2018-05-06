package theaterTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import theater.Plaats;
import theater.Plaats.Status;
import theater.Theater;
import theater.Voorstelling;
import theater.Klant;

class PlaatsTest {

  private Plaats[][] zaalbezetting;
  
  @Before
  public void setUp() {

  }

  @Test
  void testGetStatus() {
    zaalbezetting = new Plaats[Theater.AANTALRIJEN + 1][Theater.AANTALPERRIJ + 1];
    for (int i = 1; i < zaalbezetting.length; i++) {
      for(int j = 1; j <zaalbezetting[i].length; j++) {
        zaalbezetting[i][j] = new Plaats(i, j);
      }
    }
    assertEquals(Status.VRIJ, zaalbezetting[1][1].getStatus());
    assertEquals(Status.VRIJ, zaalbezetting[15][10].getStatus());
  }

  @Test
  void testToString() {
    zaalbezetting = new Plaats[Theater.AANTALRIJEN + 1][Theater.AANTALPERRIJ + 1];
    for (int i = 1; i < zaalbezetting.length; i++) {
      for(int j = 1; j <zaalbezetting[i].length; j++) {
        zaalbezetting[i][j] = new Plaats(i, j);
      }
    }
    assertEquals("Rijnummer: 2, Stoelnummer: 2", zaalbezetting[2][2].toString());
  }

  @Test
  void testReserveerPlaats() {
    zaalbezetting = new Plaats[Theater.AANTALRIJEN + 1][Theater.AANTALPERRIJ + 1];
    for (int i = 1; i < zaalbezetting.length; i++) {
      for(int j = 1; j <zaalbezetting[i].length; j++) {
        zaalbezetting[i][j] = new Plaats(i, j);
      }
    }
    assertEquals(Status.VRIJ, zaalbezetting[8][8].getStatus());
    zaalbezetting[8][8].reserveerPlaats();
    assertEquals(Status.GERESERVEERD, zaalbezetting[8][8].getStatus());
    assertEquals(Status.VRIJ, zaalbezetting[15][10].getStatus());
    zaalbezetting[15][10].reserveerPlaats();
    assertEquals(Status.GERESERVEERD, zaalbezetting[15][10].getStatus());
    
  }

  @Test
  void testBezetPlaats() {
  zaalbezetting = new Plaats[Theater.AANTALRIJEN + 1][Theater.AANTALPERRIJ + 1];
  for (int i = 1; i < zaalbezetting.length; i++) {
    for(int j = 1; j <zaalbezetting[i].length; j++) {
      zaalbezetting[i][j] = new Plaats(i, j);
    }
  }
    assertEquals(Status.VRIJ, zaalbezetting[8][8].getStatus());
    zaalbezetting[8][8].reserveerPlaats();
    assertEquals(Status.GERESERVEERD, zaalbezetting[8][8].getStatus());
    Klant klant = new Klant(1, "Steven", "6543");
    zaalbezetting[8][8].bezetPlaats(klant);
    assertEquals(Status.BEZET, zaalbezetting[8][8].getStatus());
  }

  @Test
  void testMaakPlaatsVrij() {
  zaalbezetting = new Plaats[Theater.AANTALRIJEN + 1][Theater.AANTALPERRIJ + 1];
  for (int i = 1; i < zaalbezetting.length; i++) {
    for(int j = 1; j <zaalbezetting[i].length; j++) {
      zaalbezetting[i][j] = new Plaats(i, j);
    }
  }
    assertEquals(Status.VRIJ, zaalbezetting[8][8].getStatus());
    zaalbezetting[8][8].reserveerPlaats();
    assertEquals(Status.GERESERVEERD, zaalbezetting[8][8].getStatus());
    Klant klant = new Klant(1, "Steven", "6543");
    zaalbezetting[8][8].bezetPlaats(klant);
    assertEquals(Status.BEZET, zaalbezetting[8][8].getStatus());
    zaalbezetting[8][8].maakPlaatsVrij();
    assertEquals(Status.VRIJ, zaalbezetting[8][8].getStatus());
  }

  @Test
  void testAnnuleerReservering() {
  zaalbezetting = new Plaats[Theater.AANTALRIJEN + 1][Theater.AANTALPERRIJ + 1];
  for (int i = 1; i < zaalbezetting.length; i++) {
    for(int j = 1; j <zaalbezetting[i].length; j++) {
      zaalbezetting[i][j] = new Plaats(i, j);
    }
  }
    assertEquals(Status.VRIJ, zaalbezetting[9][7].getStatus());
    zaalbezetting[9][7].reserveerPlaats();
    assertEquals(Status.GERESERVEERD, zaalbezetting[9][7].getStatus());
    zaalbezetting[9][7].annuleerReservering();
    assertEquals(Status.VRIJ, zaalbezetting[8][8].getStatus());
  }

}
