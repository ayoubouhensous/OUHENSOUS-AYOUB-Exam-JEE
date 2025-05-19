package com.example.ouhensousayoubexamjeebackend.repository;

import com.example.ouhensousayoubexamjeebackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContains(String nom);
}
