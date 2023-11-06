package tn.esprit.devops_project;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SupplierServiceImplTest {

    @Autowired
    private SupplierServiceImpl supplierService;

    @Autowired
    private SupplierRepository supplierRepository;

    private Supplier testSupplier;

    @BeforeAll
    public void setUp() {
        testSupplier = new Supplier();
        testSupplier.setCode("TestCode");
        testSupplier.setLabel("TestLabel");
        // You can set other properties of the Supplier class here
    }

    @Test
    @Order(1)
    public void testAddSupplier() {
        // Act
        Supplier addedSupplier = supplierService.addSupplier(testSupplier);

        // Assert
        Assertions.assertNotNull(addedSupplier.getIdSupplier());
        Assertions.assertEquals(testSupplier.getCode(), addedSupplier.getCode());
        Assertions.assertEquals(testSupplier.getLabel(), addedSupplier.getLabel());
        // Add assertions for other properties as needed
    }

    @Test
    @Order(2)
    public void testRetrieveAllSuppliers() {
        // Act
        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        // Assert
        Assertions.assertFalse(suppliers.isEmpty());
    }

    @Test
    @Order(3)
    public void testRetrieveSupplier() {
        // Act
        Optional<Supplier> optionalSupplier = supplierRepository.findById(testSupplier.getIdSupplier());

        // Assert
        Assertions.assertTrue(optionalSupplier.isPresent());
        Supplier retrievedSupplier = optionalSupplier.get();
        Assertions.assertEquals(testSupplier.getIdSupplier(), retrievedSupplier.getIdSupplier());
        Assertions.assertEquals(testSupplier.getCode(), retrievedSupplier.getCode());
        Assertions.assertEquals(testSupplier.getLabel(), retrievedSupplier.getLabel());
        // Add assertions for other properties as needed
    }

    @Test
    @Order(4)
    public void testUpdateSupplier() {
        // Arrange
        testSupplier.setCode("UpdatedCode");
        testSupplier.setLabel("UpdatedLabel");
        // Update other properties as needed

        // Act
        Supplier updatedSupplier = supplierService.updateSupplier(testSupplier);

        // Assert
        Assertions.assertEquals(testSupplier.getIdSupplier(), updatedSupplier.getIdSupplier());
        Assertions.assertEquals(testSupplier.getCode(), updatedSupplier.getCode());
        Assertions.assertEquals(testSupplier.getLabel(), updatedSupplier.getLabel());
        // Add assertions for other properties as needed
    }

    @Test
    @Order(5)
    public void testDeleteSupplier() {
        // Act
        supplierService.deleteSupplier(testSupplier.getIdSupplier());

        // Assert
        Optional<Supplier> optionalSupplier = supplierRepository.findById(testSupplier.getIdSupplier());
        Assertions.assertTrue(optionalSupplier.isEmpty());
    }
}
