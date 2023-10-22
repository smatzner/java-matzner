package com.example.entitiesaufgabe.controllers;

import com.example.entitiesaufgabe.dto.PurchaseDTO;
import com.example.entitiesaufgabe.dto.ResponsePurchaseDTO;
import com.example.entitiesaufgabe.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<?> addPurchase(@RequestBody PurchaseDTO purchaseDTO){
        ResponsePurchaseDTO responsePurchaseDTO = purchaseService.addPurchase(purchaseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responsePurchaseDTO);
    }
}
