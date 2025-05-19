package com.example.ouhensousayoubexamjeebackend.controller;

import com.example.ouhensousayoubexamjeebackend.dtos.CreditDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ouhensousayoubexamjeebackend.service.CreditService;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    private CreditService creditService;
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping
    public ResponseEntity<CreditDTO> createCredit( @RequestBody CreditDTO creditDTO) {
        CreditDTO created = creditService.createCredit(creditDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        CreditDTO credit = creditService.getCreditById(id);
        return ResponseEntity.ok(credit);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClient(@PathVariable Long clientId) {
        List<CreditDTO> credits = creditService.getCreditsByClientId(clientId);
        return ResponseEntity.ok(credits);
    }

    @GetMapping
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        List<CreditDTO> credits = creditService.getAllCredits();
        return ResponseEntity.ok(credits);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }
}
