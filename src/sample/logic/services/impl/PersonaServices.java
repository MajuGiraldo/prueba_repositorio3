package sample.logic.services.impl;

import javafx.collections.FXCollections;
import sample.logic.entities.Persona;
import sample.logic.persistence.IExport;
import sample.logic.persistence.IPersonaPersistence;
import sample.logic.persistence.impl.Export;
import sample.logic.persistence.impl.PersonaPersistence;
import sample.logic.services.IPersonaServices;

import java.io.IOException;
import java.util.List;

public class PersonaServices implements IPersonaServices {

    private IPersonaPersistence personaPersistence;
    private IExport export;
    private List<Persona> personas;

    public PersonaServices() {
        this.personas = FXCollections.observableArrayList();
        try {
            this.personaPersistence = new PersonaPersistence();
            this.export = new Export();
            this.personas.addAll(this.personaPersistence.read());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Persona> getAll() {
        return this.personas;
    }

    @Override
    public Persona insert(Persona persona) {
        personas.add(persona);
        try {
            this.personaPersistence.save(persona);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }

    @Override
    public void delete(List<Persona> personas) {
        for (Persona p : personas){
            this.personas.remove(p);
        }
        //personas.forEach(this.personas::remove);
    }

    @Override
    public Persona update(Persona persona, Persona persona2) {
        if (!this.personas.contains(persona2)){

            persona.setName(persona2.getName());
            persona.setLastName(persona2.getLastName());
            persona.setDeathDate(persona2.getDeathDate());
            persona.setMunicipality(persona2.getMunicipality());
            persona.setDepartment(persona2.getDepartment());
            persona.setLeaderType(persona2.getLeaderType());

        }

        return persona;
    }

}
