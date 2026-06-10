package com.example.demo.service;

import com.example.demo.domain.Receipt;
import com.example.demo.dto.ReceiptRequest;
import com.example.demo.dto.ReceiptResponse;

import java.util.List;

public interface ReceiptService {
    ReceiptResponse createReceipt(ReceiptRequest receiptRequest);
    List<ReceiptResponse> getAllReceipts();
    ReceiptResponse getReceiptByReservationId(Integer reservationId);
    ReceiptResponse deleteReceiptByReservationId(Integer reservationId);
}
