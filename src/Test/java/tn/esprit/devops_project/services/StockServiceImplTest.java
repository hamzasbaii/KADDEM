package tn.esprit.devops_project.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

 class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testAddStock() {
        Stock stockToAdd = new Stock();
        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        Stock addedStock = stockService.addStock(stockToAdd);

        assertEquals(stockToAdd, addedStock);
    }

    @Test
     void testRetrieveStock() {
        Long stockId = 1L;
        Stock expectedStock = new Stock();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(expectedStock));

        Stock retrievedStock = stockService.retrieveStock(stockId);

        assertEquals(expectedStock, retrievedStock);
    }

    @Test
     void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> retrievedStockList = stockService.retrieveAllStock();

        assertEquals(stockList, retrievedStockList);
    }
}
