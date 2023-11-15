package tn.esprit.spring.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class InvoiceServiceImpltest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    public void testRetrieveAllInvoices() {
        // Given
        List<Invoice> invoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceRepository.findAll()).thenReturn(invoices);

        // When
        List<Invoice> result = invoiceService.retrieveAllInvoices();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    public void testCancelInvoice() {
        // Given
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // When
        invoiceService.cancelInvoice(invoiceId);

        // Then
        verify(invoiceRepository, times(1)).save(invoice);
    }

    // Add more test methods for other service methods



    @Test
    public void testAssignOperatorToInvoice() {
        // Given
        Long idOperator = 1L;
        Long idInvoice = 2L;
        Operator operator = new Operator();
        Invoice invoice = new Invoice();
        when(operatorRepository.findById(idOperator)).thenReturn(Optional.of(operator));
        when(invoiceRepository.findById(idInvoice)).thenReturn(Optional.of(invoice));

        // When
        invoiceService.assignOperatorToInvoice(idOperator, idInvoice);

        // Then
        verify(operatorRepository, times(1)).save(operator);
    }

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date();
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(100.0f);

        // When
        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Then
        assertEquals(100.0f, result);
    }
}
