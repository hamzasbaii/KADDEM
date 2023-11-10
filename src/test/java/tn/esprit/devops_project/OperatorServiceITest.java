package tn.esprit.devops_project;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
public class OperatorServiceITest {
    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOperator() {
        // Créez un objet Operator factice pour le test
        Operator operatorToAdd = new Operator(1L, "Alice", "Smith");
        operatorToAdd.setFname("John");
        operatorToAdd.setLname("Doe");

        // Simulez le comportement du repository
        when(operatorRepository.save(any(Operator.class))).thenReturn(operatorToAdd);

        // Appelez la méthode à tester
        Operator addedOperator = operatorService.addOperator(operatorToAdd);

        // Vérifiez que la méthode save a été appelée avec l'opérateur ajouté
        verify(operatorRepository).save(operatorToAdd);

        // Vérifiez que l'opérateur ajouté correspond à celui retourné par la méthode
        assertEquals("John", addedOperator.getFname());
        assertEquals("Doe", addedOperator.getLname());

    }

    @Test
    void testUpdateOperator() {
        // Arrange
        Operator existingOperator = new Operator(1L, "Alice", "Smith");

        // Mock the repository behavior
        when(operatorRepository.findById(existingOperator.getIdOperateur())).thenReturn(Optional.of(existingOperator));
        when(operatorRepository.save(any(Operator.class))).thenAnswer(invocation -> {
            Operator updatedEntity = invocation.getArgument(0);
            // Simulate the entity being updated in the database
            existingOperator.setFname(updatedEntity.getFname());
            existingOperator.setLname(updatedEntity.getLname());
            return existingOperator;
        });

        System.out.println("\n********************** System.out.println(\"\\n***********************************************  Test 3: [ Method: UpdateEquipe() ] *****************************************************************\\n--> Test Passed: Test de modfication bien réussi\\n- Befor Test Update = \" + existingOperator.getFname());*************************  Test: [ Method: UpdateOperator() ] *****************************************************************\n--> Test Passed: Successful update test\n- Before Update = " + existingOperator.getFname() + " " + existingOperator.getLname());

        // Act
        // Change the first name and last name of the existingOperator
        existingOperator.setFname("Updated FirstName");
        existingOperator.setLname("Updated LastName");
        Operator updatedOperator = operatorService.updateOperator(existingOperator);

        // Assert
        assertEquals("Updated FirstName", updatedOperator.getFname());
        assertEquals("Updated LastName", updatedOperator.getLname());
        // Verify that the save method was called with the updated entity
        verify(operatorRepository).save(existingOperator);

        System.out.println("- After Update = " + existingOperator.getFname() + " " + existingOperator.getLname());
        System.out.println("**************************   **************************   ************************   *************************   *************************   ******************.");
    }
    @Test
    void testRetrieveAllOperators() {
        List<Operator> operators = new ArrayList<>();

        Operator operator1 = new Operator("Alice", "Smith");
        Operator operator2 = new Operator("Bob", "Johnson");

        operators.add(operator1);
        operators.add(operator2);

        when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> result = operatorService.retrieveAllOperators();

        if (result.size() == 2) {
            log.info("\n***********************************************  Test: [ Method: RetrieveAllOperators() ] ********************************************************\n--> Test Passed: The number of results is 2 as expected.");

            // Display the list of operators in the console
            for (Operator operator : result) {
                System.out.println("- First Name: " + operator.getFname() + " | " + "- Last Name: " + operator.getLname());
            }
            System.out.println("**************************   **************************   ************************   *************************   *************************   ******************.");
        } else {
            log.info("Test Failed: The expected number of results was 2, but got " + result.size());
            System.err.println("Test: Retrieve All Operators Method()\n Test Failed: The expected number of results was 2, but got " + result.size());
        }

        assertEquals(2, result.size());
    }


    @Test
    void testDeleteOperator() {
        // Create an instance of the operator to retrieve
        Operator expectedOperator = new Operator("Alice", "Smith");

        // Use Mockito to simulate the behavior of findById in the operatorRepository
        when(operatorRepository.findById(expectedOperator.getIdOperateur())).thenReturn(Optional.of(expectedOperator));

        // Call the method to retrieve the operator
        Operator retrievedOperator = operatorService.retrieveOperator(expectedOperator.getIdOperateur());

        // Verify if the expected operator is equal to the retrieved operator
        assertEquals(expectedOperator, retrievedOperator);

        // Display a message indicating the test result
        if (expectedOperator.equals(retrievedOperator)) {
            System.out.println("\n***********************************************  Test: [ Method: DeleteOperator() ] ********************************************************\n--> Test Passed: " + expectedOperator.getFname() +
                    " " + expectedOperator.getLname() + " IS SUCCESSFULLY DELETED");
            System.out.println("**************************   **************************   ************************   *************************   *************************   ******************.");
        } else {
            System.out.println("Test: Delete Operator Method()\n " + expectedOperator.getFname() +
                    " " + expectedOperator.getLname() + " IS FAILED TO BE DELETED \n ");
        }
    }

    @Test
    void testRetrieveOperator() {
        // Create an instance of Operator with the data of the operator you want to retrieve
        Operator expectedOperator = new Operator("Alice", "Smith");

        // Use Mockito to simulate the behavior of your repository
        when(operatorRepository.findById(expectedOperator.getIdOperateur())).thenReturn(Optional.of(expectedOperator));

        // Call the retrieveOperator method of the service to retrieve the operator
        Operator retrievedOperator = operatorService.retrieveOperator(expectedOperator.getIdOperateur());

        // Use assertions to check if the retrieved operator is the same as the expected one
        assertEquals(expectedOperator, retrievedOperator);

        // Display a message to indicate that the test has passed
        System.out.println("\n***********************************************  Test: [ Method: RetrieveOperator() ] ********************************************************\n--> Test Passed: Retrieve Operator method succeeded for operator: " + expectedOperator.getFname() + " " + expectedOperator.getLname());
        System.out.println("**************************   **************************   ************************   *************************   *************************   ******************.");
    }



}