package com.example.demo.service;

import com.example.demo.domain.Receipt;
import com.example.demo.dto.ReceiptResponse;

import java.util.List;

public interface ReceiptService {
    List<ReceiptResponse> getReceiptsByUserId(Integer userId);
    ReceiptResponse getReceiptByReservationId(Integer reservationId);
    void  deleteReceiptByReservationId(Integer reservationId);
}
