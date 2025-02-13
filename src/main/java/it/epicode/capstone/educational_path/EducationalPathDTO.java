package it.epicode.capstone.educational_path;

import lombok.Data;

@Data
public class EducationalPathDTO {
    private Long id;
    private Long universityId;
    private Long facultyId;
    private Long degreeCourseId;
}
