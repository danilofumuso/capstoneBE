package it.epicode.capstone.active_users.student;

import lombok.Data;

import java.util.Set;

@Data
public class StudentSectorsOfInterestDTO {
    private Set<Long> sectorsOfInterestId;
}
