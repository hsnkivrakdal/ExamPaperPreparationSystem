package com.example.eppms.services;

import com.example.eppms.models.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    Program saveProgram(Program program);
    List<Program> getAllPrograms();
    Optional<Program> getProgramById(Integer id);
    void deleteProgram(Integer id);
    Program updateProgram(Integer id, Program updatedProgram);
}
