package com.example.ouhensousayoubexamjeebackend;

import com.example.ouhensousayoubexamjeebackend.models.*;
import com.example.ouhensousayoubexamjeebackend.repository.ClientRepository;
import com.example.ouhensousayoubexamjeebackend.repository.CreditRepository;
import com.example.ouhensousayoubexamjeebackend.repository.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class OuhensousAyoubExamJeeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OuhensousAyoubExamJeeBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            CreditRepository creditRepository,
                            RemboursementRepository remboursementRepository) {
        return args -> {

            // Création de clients
            Stream.of("Hassan", "Yassine", "AICHA").forEach(name -> {
                Client client = new Client();
                client.setNom(name);
                client.setEmail(name.toLowerCase() + "@gmail.com");
                clientRepository.save(client);

                // Pour chaque client, on ajoute 2 crédits différents
                // 1 - Credit Personnel
                CreditPersonnel cp = new CreditPersonnel();
                cp.setClient(client);
                cp.setDateDemande(new Date());
                cp.setStatut(StatutCredit.EN_COURS);
                cp.setMontant(10000);
                cp.setDureeRemboursement(24);
                cp.setTauxInteret(5.5);
                cp.setMotif("Achat voiture");
                creditRepository.save(cp);

                // 2 - Credit Immobilier
                CreditImmobilier ci = new CreditImmobilier();
                ci.setClient(client);
                ci.setDateDemande(new Date());
                ci.setStatut(StatutCredit.ACCEPTE);
                ci.setMontant(150000);
                ci.setDureeRemboursement(120);
                ci.setTauxInteret(3.2);
                ci.setTypeBien(TypeBien.MAISON);
                creditRepository.save(ci);

                // Ajout de remboursements pour ces crédits
                Remboursement remb1 = new Remboursement();
                remb1.setCredit(cp);
                remb1.setDate(new Date());
                remb1.setMontant(450);
                remb1.setType(TypeRemboursement.MENSUALITE);
                remboursementRepository.save(remb1);

                Remboursement remb2 = new Remboursement();
                remb2.setCredit(ci);
                remb2.setDate(new Date());
                remb2.setMontant(1200);
                remb2.setType(TypeRemboursement.MENSUALITE);
                remboursementRepository.save(remb2);
            });

            // Affichage simple pour vérifier la persistance
            clientRepository.findAll().forEach(client -> {
                System.out.println("Client: " + client.getNom() + ", credits: " + client.getCredits().size());
            });
        };
    }

}
