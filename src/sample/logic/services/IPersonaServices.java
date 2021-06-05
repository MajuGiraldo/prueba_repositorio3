package sample.logic.services;

import sample.logic.entities.Persona;

import java.util.List;

public interface IPersonaServices {

    List<Persona> getAll();

    Persona insert (Persona persona);

    void delete (List<Persona> personas);

    Persona update (Persona persona, Persona persona2);
}
