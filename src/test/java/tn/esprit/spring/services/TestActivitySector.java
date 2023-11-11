package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import tn.esprit.devops_project.services.ActivitySectorImpl;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestActivitySector {

  @Mock
  private ActivitySectorRepository activitySectorRepository;

  @InjectMocks
  private ActivitySectorImpl activitySectorService;

  @BeforeEach
  public void setUp(){}
  @Test
  void addActivitySectorTest() {
    // Mocking data
    ActivitySector activitySector = new ActivitySector();
    activitySector.setIdsecteuractivite(1L);
    activitySector.setCodeSecteurActivite("CODE1");
    activitySector.setLibelleSecteurActivite("Sector 1");
    activitySector.setSuppliers(null);

    // Mocking repository behavior
    Mockito.when(activitySectorRepository.save(Mockito.any(ActivitySector.class))).thenReturn(activitySector);

    // Call the service method
    ActivitySector result = activitySectorService.addActivitySector(activitySector);

    // Verify the result
    assertEquals(activitySector, result);
  }
  @Test
  void updateActivitySectorTest() {
    // Mocking data
    ActivitySector activitySector = new ActivitySector();
    activitySector.setIdsecteuractivite(1L);
    activitySector.setCodeSecteurActivite("CODE2");
    activitySector.setLibelleSecteurActivite("Sector 2");
    activitySector.setSuppliers(null);
    // Mocking repository behavior
    Mockito.when(activitySectorRepository.save(Mockito.any(ActivitySector.class))).thenReturn(activitySector);

    // Call the service method
    ActivitySector result = activitySectorService.updateActivitySector(activitySector);

    // Verify the result
    assertEquals(activitySector, result);

    // Verify that the repository method was called
  }
  @Test
  void retrieveActivitySectorTest() {
    // Mocking data
    Long idToRetrieve = 1L;
    ActivitySector activitySector = new ActivitySector();
    activitySector.setIdsecteuractivite(1L);
    activitySector.setCodeSecteurActivite("CODE2");
    activitySector.setLibelleSecteurActivite("Sector 2");
    activitySector.setSuppliers(null);
    // Mocking repository behavior
    Mockito.when(activitySectorRepository.findById(idToRetrieve)).thenReturn(Optional.of(activitySector));

    // Call the service method
    ActivitySector result = activitySectorService.retrieveActivitySector(idToRetrieve);

    // Verify the result
    assertEquals(activitySector, result);

    }

}
