package doctor.repository;

import doctor.domain.Programare;

import java.util.ArrayList;
import java.util.List;

interface ProgFactory<P extends Programare> {
    P create(int id, int data, int pacientID, String diagnostic, Programare.possibleStatus status, boolean urgenta);
}
public class ProgramareRepository {
    private List<Programare> programari=new ArrayList<Programare>();


    public ProgramareRepository() {
        ProgFactory<Programare> progFactory = Programare::new;
        Programare prog1 = progFactory.create(1, 30,5,"cardio-vasulara",Programare.possibleStatus.terminata,true);
        Programare prog2 = progFactory.create(2, 30,5,"cardio",Programare.possibleStatus.anulata,false);
        Programare prog3 = progFactory.create(3, 22,2,"diabetes",Programare.possibleStatus.urmeaza,true);
        programari.add(prog1);
        programari.add(prog2);
        programari.add(prog3);
    }

    public void add(Programare p)
    {
        programari.add(p);
    }

    public void sterge(Programare p)
    {
        int index=programari.indexOf(p);
        if(index>-1)
        {
            programari.remove(index);
        }
    }
    public boolean find(int id)
    {
        for(Programare p:programari)
        {
            if(p.getID()==id)
                return true;
        }
        return false;
    }
    public List<Programare> getAll()
    {
        return programari;
    }
}
