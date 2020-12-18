package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService{

    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public void addInstitution(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public List<Institution> getInstsWithOddIndex(){
        List<Institution> institutions= institutionRepository.findAll();
        List<Institution> oddInsts = new ArrayList<>();
        for (int i = 0; i < institutions.size()-1; i+=2) {
            oddInsts.add(institutions.get(i));
        }
        return oddInsts;
    }

    @Override
    public List<Institution> getInstsWithEvenIndex(){
        List<Institution> institutions= institutionRepository.findAll();
        List<Institution> evenInsts = new ArrayList<>();
        for (int i = 1; i < institutions.size()-1; i+=2) {
            evenInsts.add(institutions.get(i));
        }
        return evenInsts;
    }
}
