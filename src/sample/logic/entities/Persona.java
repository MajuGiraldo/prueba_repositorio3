package sample.logic.entities;

import sample.PersonaException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;

public class Persona implements Serializable {

    private String name;
    private String lastName;
    private LocalDate deathDate;
    private String municipality;
    private String department;
    private Enum leaderType;
    //private boolean isVictim;

    public Persona(String name, String lastName, String deathDate, String municipality, String department, String leaderType) throws PersonaException {
        this.name = name;
        this.lastName = lastName;
        this.setDeathDateToString(deathDate);
        this.municipality = municipality;
        this.department = department;
        this.setLeaderTypeToString(leaderType);
        //this.isVictim = isVictim;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDeathDateToString(String deathDateInput){

        this.deathDate = LocalDate.parse(deathDateInput);
    }

    public LocalDate getDeathDate(){
        return this.deathDate;
    }

    public String getMunicipality(){
        return municipality;
    }

    public String getDepartment(){
        return department;
    }

    public void setLeaderTypeToString(String leaderTypeInput) {
        if(leaderTypeInput.equalsIgnoreCase("Civico")){
            this.leaderType = LeaderTypeEnum.CIVICO;
        }
        if(leaderTypeInput.equalsIgnoreCase("Afrodescendiente")){
            this.leaderType = LeaderTypeEnum.AFRODESCENDIENTE;
        }
    }

    public Enum getLeaderType(){
        return this.leaderType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setLeaderType(Enum leaderType) {
        this.leaderType = leaderType;
    }

    //public boolean isVictim(){return isVictim;}

    @Override
    public String toString(){

        return String.format("Nombre=%s, Apellido=%s, Fecha de Fallecimiento=%s, Municipio=%s, Departamento=%s, Tipo de Lider=%s", this.name, this.lastName, this.deathDate, this.municipality, this.department, this.leaderType);
    }
}
