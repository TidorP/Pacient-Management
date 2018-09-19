package doctor;

import doctor.domain.Pacient;

public class validatePacient {
    private Pacient pacient;

    public validatePacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public void validate() throws customException {
        if(pacient.getVarsta()<1)
            throw new customException("Varsta trebuie sa fie >0 !!!");
    }


}
