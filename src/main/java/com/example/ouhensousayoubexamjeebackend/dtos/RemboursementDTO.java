package com.example.ouhensousayoubexamjeebackend.dtos;

import lombok.Data;

import java.util.Date;
@Data
public class RemboursementDTO {

    private Long id;
    private double montant;
    private String type;
    private Long creditId;

}
