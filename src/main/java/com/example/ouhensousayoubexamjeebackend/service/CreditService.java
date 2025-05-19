package com.example.ouhensousayoubexamjeebackend.service;

import com.example.ouhensousayoubexamjeebackend.dtos.CreditDTO;

import java.util.List;

public interface CreditService {

    CreditDTO createCredit(CreditDTO dto);
    void deleteCredit(Long id);
    CreditDTO getCreditById(Long id);
    List<CreditDTO> getCreditsByClientId(Long clientId);
    List<CreditDTO> getAllCredits();
}
