package doctor;

import doctor.repository.PacientRepository;
import doctor.repository.ProgramareRepository;
import doctor.service.Service;
import doctor.ui.UI;

public class app {

    public static void main(String[] args) {

        PacientRepository pacientRepositoryRepo=new PacientRepository();
        ProgramareRepository programareRepository=new ProgramareRepository();
        Service service=new Service(pacientRepositoryRepo,programareRepository);
        UI ui=new UI(service);
        ui.run();
    }


}

