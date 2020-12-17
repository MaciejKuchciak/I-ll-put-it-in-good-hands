package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Institution;

import java.util.List;

public interface InstitutionService {

    void addInstitution(Institution institution);

    List<Institution> getAllInstitutions();
}
