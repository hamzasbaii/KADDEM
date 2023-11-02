/*package tn.esprit.devops_project.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StockTestJunit {

    @Autowired
    private StockServiceImpl stockService;

    @MockBean
    private StockRepository stockRepository;

    @Test
    public void testAddStockWithTestJunit() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("addStock");

        Stock addedStock = stockService.addStock(stock);

        assertEquals(stock.getIdStock(), addedStock.getIdStock());
        assertEquals(stock.getTitle(), addedStock.getTitle());
    }

    @Test
    public void testRetrieveStock() {
        Long stockId = 1L; // Remplacer par l'ID de stock existant dans votre base de données de test

        Stock stock = new Stock(); // créer un objet Stock factice
        stock.setIdStock (stockId);
        stock.setTitle ("Test Stock");

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock)); // simuler le comportement de stockRepository.findById

        Stock retrievedStock = stockService.retrieveStock(stockId); // appeler la méthode à tester

        assertEquals(stock, retrievedStock); // vérifier si l'objet retourné est le même que celui attendu
    }

    @Test
    public void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<> (); // créer une liste de stocks factice

        // Ajouter des stocks à la liste

        when(stockRepository.findAll()).thenReturn(stockList); // simuler le comportement de stockRepository.findAll

        List<Stock> retrievedStockList = stockService.retrieveAllStock(); // appeler la méthode à tester

        assertEquals(stockList, retrievedStockList); // vérifier si la liste retournée est la même que celle attendue
    }
}
*/

