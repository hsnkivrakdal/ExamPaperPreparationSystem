package com.example.eppms.impl;

import com.example.eppms.models.Program;
import com.example.eppms.repositories.ProgramRepository;
import com.example.eppms.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Optional<Program> getProgramById(Integer id) {
        return programRepository.findById(id);
    }

    @Override
    public void deleteProgram(Integer id) {
        programRepository.deleteById(id);
    }

    @Override
    public Program updateProgram(Integer id, Program updatedProgram) {
        Optional<Program> existing = programRepository.findById(id);
        if (existing.isPresent()) {
            Program program = existing.get();
            program.setProgramName(updatedProgram.getProgramName());
            program.setDepartment(updatedProgram.getDepartment());
            return programRepository.save(program);
        }
        return null;
    }
}
