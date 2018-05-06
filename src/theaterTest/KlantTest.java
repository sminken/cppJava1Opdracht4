package theaterTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import theater.Klant;
import theater.Plaats;
import theater.Theater;
import theater.Voorstelling;

class KlantTest {

  Klant klant;
  
  @Before
  public void setUp(){

  }

  @Test
  void testGetKlantnummer() {
    klant = new Klant(3, "Jaap", "23423");
    assertEquals(3, klant.getKlantnummer());
  }

  @Test
  void testGetNaam() {
    klant = new Klant(3, "Jaap", "23423");
    assertEquals("Jaap", klant.getNaam());
  }

  @Test
  void testGetTelefoon() {
    klant = new Klant(3, "Jaap", "23423");
    assertEquals("23423", klant.getTelefoon());
  }

  @Test
  void testToString() {
    klant = new Klant(3, "Jaap", "23423");
    assertEquals("3 Jaap 23423", klant.toString());
  }

}
