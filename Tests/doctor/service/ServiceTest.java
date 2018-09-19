package doctor.service;

import doctor.customException;
import doctor.repository.PacientRepository;
import doctor.repository.ProgramareRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void addPacient() {
        PacientRepository pacientRepositoryRepo=new PacientRepository();
        ProgramareRepository programareRepository=new ProgramareRepository();
        Service service=new Service(pacientRepositoryRepo,programareRepository);
        try{
            service.addPacient("Parker7",60);
            assertEquals(pacientRepositoryRepo.getAll().get(6).getVarsta(),60);
        } catch (customException e) {
            fail("This will fail!");
        }
        try{
            service.addPacient("Parker7",0);
            fail("This will fail!");
        } catch (customException e) {
            assertTrue("HAHAHAH",true);
        }

    }

    @Test
    public void addProgramare() {
    }

    @Test
    public void removePacient() {
    }

    @Test
    public void removeProgramare() {
    }

    @Test
    public void filtZiStatus() {
    }

    @Test
    public void filtUrgenta() {
    }

    @Test
    public void sortAlph() {
    }
}