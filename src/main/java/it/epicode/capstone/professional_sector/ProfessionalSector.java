package it.epicode.capstone.professional_sector;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProfessionalSector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
