package sample.logic.persistence.impl;

import javafx.collections.FXCollections;
import sample.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.persistence.IPersonaPersistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaPersistence implements IPersonaPersistence {

    private static final String PERSONAS_FILE_PATH = "lideres.colombia";

    public PersonaPersistence() throws IOException {
        File file = new File(PERSONAS_FILE_PATH);
        if (file.createNewFile()){
            System.out.println("The file " + file.getName() + " was created");
        }
    }

    @Override
    public void save(Persona persona) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PERSONAS_FILE_PATH, true));
        out.writeObject(persona);
        out.close();

    }

    @Override
    public List<Persona> read() throws IOException, ClassNotFoundException {

        List<Persona> result = FXCollections.observableArrayList();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(PERSONAS_FILE_PATH));
        try {
            while (true){
                result.add((Persona) in.readObject());
            }
        }catch (IOException io){
            System.out.println("Reached end of file");
        }finally {
            in.close();
        }


        return result;
    }

    @Override
    public List<Persona> read(String path) throws IOException, ClassNotFoundException {


        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path == null ? PERSONAS_FILE_PATH : path));
        return readPersonasWithColombiaExtension(in);
    }

    public List<Persona> read(File file) throws Exception {


        return read(file.getAbsolutePath());
    }


    private List<Persona> readPersonasWithColombiaExtension(ObjectInputStream in) throws IOException, ClassNotFoundException {

        List<Persona> result = new ArrayList<>();

        try {
            while (true) {
                result.add((Persona) in.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Reached end of file");
        } finally {
            in.close();
        }

        return result;
    }

}
