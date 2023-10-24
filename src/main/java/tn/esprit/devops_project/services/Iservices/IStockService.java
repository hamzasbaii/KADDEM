package tn.esprit.devops_project.services.Iservices;

import tn.esprit.devops_project.entities.Stock;

import java.util.List;

public interface IStockService {
    int deleteStock(Long stockId);
    Stock addStock(Stock stock);
    Stock retrieveStock(Long id);
    List<Stock> retrieveAllStock();

}
