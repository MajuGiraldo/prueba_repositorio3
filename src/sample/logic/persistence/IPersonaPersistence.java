package sample.logic.persistence;

import sample.logic.entities.Persona;

import java.io.IOException;
import java.util.List;

public interface IPersonaPersistence {

    void save(Persona persona) throws IOException;

    List<Persona> read() throws IOException, ClassNotFoundException;

    List<Persona> read(String path) throws IOException, ClassNotFoundException;
}
