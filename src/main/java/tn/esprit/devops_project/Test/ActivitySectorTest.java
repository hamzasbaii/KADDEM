package tn.esprit.devops_project.Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.entities.Supplier;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/*public class ActivitySectorTest {

  @Mock
  private ActivitySector activitySector;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIdSecteurActivite() {
    // Test du getter et du setter pour idSecteurActivite
    activitySector.setIdSecteurActivite(1L);
    assertEquals(1L, activitySector.getIdSecteurActivite().longValue());
  }

  @Test
  public void testCodeSecteurActivite() {
    // Test du getter et du setter pour codeSecteurActivite
    activitySector.setCodeSecteurActivite("TEST_CODE");
    assertEquals("TEST_CODE", activitySector.getCodeSecteurActivite());
  }

  @Test
  public void testLibelleSecteurActivite() {
    // Test du getter et du setter pour libelleSecteurActivite
    activitySector.setLibelleSecteurActivite("Test Libelle");
    assertEquals("Test Libelle", activitySector.getLibelleSecteurActivite());
  }

  @Test
  public void testSuppliers() {
    // Test de la relation ManyToMany avec Supplier
    Supplier supplier1 = new Supplier();
    Supplier supplier2 = new Supplier();
    Set<Supplier> suppliers = new HashSet<>();
    suppliers.add(supplier1);
    suppliers.add(supplier2);

    // Assurez-vous que l'objet activitySector est correctement initialisé
    activitySector = new ActivitySector();
    activitySector.setSuppliers(suppliers);

    assertNotNull(activitySector.getSuppliers());
    assertEquals(2, activitySector.getSuppliers().size());
  }
}*/
