package com.example.demo.service.impl;

import com.example.demo.domain.Receipt;
import com.example.demo.domain.Reservation;
import com.example.demo.dto.ReceiptRequest;
import com.example.demo.dto.ReceiptResponse;
import com.example.demo.mapper.ReceiptMapper;
import com.example.demo.repository.ReceiptRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;
    private final ReservationRepository reservationRepository;

    @Override
    public ReceiptResponse createReceipt(ReceiptRequest receiptRequest) {
        Reservation reservation = reservationRepository.findById(receiptRequest.reservationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Reservation not found with id: " + receiptRequest.reservationId()));

        if (receiptRepository.findByReservationId(receiptRequest.reservationId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"A receipt has already been issued for Reservation ID: " + receiptRequest.reservationId());
        }

        Receipt receipt = receiptMapper.toEntity(receiptRequest, reservation);

        return receiptMapper.toResponse(receiptRepository.save(receipt));
    }

    @Override
    public List<ReceiptResponse> getAllReceipts() {
        List<Receipt> receipts = receiptRepository.findAll();
        return receipts.stream().map(receiptMapper::toResponse).collect(Collectors.toList());
    }


    @Override
    public ReceiptResponse getReceiptByReservationId(Integer reservationId) {
        Receipt receipt = receiptRepository.findByReservationId(reservationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no receipt with id " + reservationId));
        return receiptMapper.toResponse(receipt);
    }

    @Override
    public ReceiptResponse deleteReceiptByReservationId(Integer reservationId) {
        Receipt receipt = receiptRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Receipt not found for this reservation."));

        receipt.setStatus("VOIDED");
        Receipt updatedReceipt = receiptRepository.save(receipt);
        return receiptMapper.toResponse(updatedReceipt);
    }
}
