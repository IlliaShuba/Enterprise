package com.example.server.service;

import com.example.server.entity.Laboratory;
import com.example.server.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryService {
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Laboratory create(){
        Laboratory laboratory = new Laboratory();
        return laboratoryRepository.save(laboratory);
    }

    public Laboratory getById(Integer id){return laboratoryRepository.findById(id).get();}
    public List<Laboratory> getAll(){return laboratoryRepository.findAll();}
    public void delete(Integer id){laboratoryRepository.deleteById(id);}
}
