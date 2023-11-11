package tn.esprit.devops_project.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.entities.Supplier;

import java.util.Collections;
@ExtendWith(MockitoExtension.class)
public class TestActivitySector {

  @Mock
  private Supplier supplier;

  private ActivitySector activitySector;

  @BeforeEach
  public void setUp() {
    activitySector = new ActivitySector();
  }

  @Test
  public void testIdSecteurActivite() {
    activitySector.setIdsecteuractivite(1L);
    assertEquals(1L, activitySector.getIdsecteuractivite());
  }

  @Test
  public void testCodeSecteurActivite() {
    activitySector.setCodeSecteurActivite("Code1");
    assertEquals("Code1", activitySector.getCodeSecteurActivite());
  }

  @Test
  public void testLibelleSecteurActivite() {
    activitySector.setLibelleSecteurActivite("Libelle1");
    assertEquals("Libelle1", activitySector.getLibelleSecteurActivite());
  }

  @Test
  public void testSuppliers() {
    activitySector.setSuppliers(Collections.singleton(supplier));
    assertEquals(Collections.singleton(supplier), activitySector.getSuppliers());
  }
}
