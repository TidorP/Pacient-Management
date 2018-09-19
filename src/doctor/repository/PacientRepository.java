package doctor.repository;

import doctor.domain.Pacient;

import java.util.ArrayList;
import java.util.List;

interface PacientFactory<P extends Pacient> {
    P create(Integer id,String nume,Integer varsta);
}

public class PacientRepository {

    private List<Pacient> pacienti=new ArrayList<Pacient>();


    public PacientRepository() {
        PacientFactory<Pacient> pacientFactory = Pacient::new;
        Pacient pacient1 = pacientFactory.create(1, "Parker1",63);
        Pacient pacient2 = pacientFactory.create(2, "Parker2",55);
        Pacient pacient3 = pacientFactory.create(3, "Parker3",33);
        Pacient pacient4 = pacientFactory.create(4, "Parker4",36);
        Pacient pacient5 = pacientFactory.create(5, "Parker5",83);
        Pacient pacient6 = pacientFactory.create(6, "Parker6",90);
        pacienti.add(pacient1);
        pacienti.add(pacient2);
        pacienti.add(pacient3);
        pacienti.add(pacient4);
        pacienti.add(pacient5);
        pacienti.add(pacient6);
    }

    public void add(Pacient p)
    {
        pacienti.add(p);
    }

    public void sterge(Pacient p)
    {
        int index=pacienti.indexOf(p);
        if(index>-1)
        {
            pacienti.remove(index);
        }
    }
    public boolean find(int id)
    {
        for(Pacient p:pacienti)
        {
            if(p.getID()==id)
                return true;
        }
        return false;
    }
    public List<Pacient> getAll()
    {
        return pacienti;
    }

}
