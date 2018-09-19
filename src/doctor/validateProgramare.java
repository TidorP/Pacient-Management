package doctor;

import doctor.domain.Programare;
import doctor.repository.PacientRepository;

public class validateProgramare {

    private Programare programare;
    PacientRepository pRepo=new PacientRepository();

    public validateProgramare(Programare programare, PacientRepository pRepo) {
        this.programare = programare;
        this.pRepo = pRepo;
    }

    public void validate() throws customException {
        if(programare.getDat()<0 || programare.getDat()>31)
            throw new customException("Data trebuie >0 si <32 !!!");
        if(programare.getPacientID()>pRepo.getAll().size())
            throw new customException("Pacientul nu exista in baza de data a pacientilor!!!");

    }
}
