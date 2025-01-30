package it.epicode.capstone.profession;

import it.epicode.capstone.professional_sector.ProfessionalSector;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "professional_sector_id")
    private ProfessionalSector professionalSector;

}
