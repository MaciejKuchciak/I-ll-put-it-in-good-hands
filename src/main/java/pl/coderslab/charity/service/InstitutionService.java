package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Institution;

import java.util.ArrayList;
import java.util.List;

public interface InstitutionService {

    void addInstitution(Institution institution);

    List<Institution> getAllInstitutions();

    List<Institution> getInstsWithOddIndex();

    List<Institution> getInstsWithEvenIndex();

    ArrayList<ArrayList<Institution>> getListOfInstsLists();

    Institution getById(Long id);

    void delete(Long id);


}
