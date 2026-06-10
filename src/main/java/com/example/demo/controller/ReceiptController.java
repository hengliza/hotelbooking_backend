package com.example.demo.controller;

import com.example.demo.dto.ReceiptRequest;
import com.example.demo.dto.ReceiptResponse;
import com.example.demo.service.ReceiptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipt")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiptResponse createReceipt(@Valid @RequestBody ReceiptRequest receiptRequest) {
        return receiptService.createReceipt(receiptRequest);
    }

    @GetMapping
    public List<ReceiptResponse> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/byReservation/{reservationId}")
    public ReceiptResponse getReceiptByReservationId(@PathVariable Integer reservationId) {
        return receiptService.getReceiptByReservationId(reservationId);
    }

    @PatchMapping("/cancel/{reservationId}")
    public ReceiptResponse deleteReceiptByReservationId(@PathVariable Integer reservationId) {
        return receiptService.deleteReceiptByReservationId(reservationId);
    }

}
