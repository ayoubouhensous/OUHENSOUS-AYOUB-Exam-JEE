package com.example.ouhensousayoubexamjeebackend.repository;

import com.example.ouhensousayoubexamjeebackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
