package doctor.service;

import doctor.customException;
import doctor.domain.Pacient;
import doctor.domain.Programare;
import doctor.repository.PacientRepository;
import doctor.repository.ProgramareRepository;
import doctor.validatePacient;
import doctor.validateProgramare;

import java.util.*;
import java.util.stream.Stream;

public class Service {

    private PacientRepository pacientRepo;

    private ProgramareRepository programareRepo;


    public Service(PacientRepository pacientRepo, ProgramareRepository programareRepo) {
        this.pacientRepo = pacientRepo;
        this.programareRepo = programareRepo;
    }

    public PacientRepository getPacientRepo() {
        return pacientRepo;
    }

    public ProgramareRepository getProgramareRepo() {
        return programareRepo;
    }

    public void addPacient(String nume, int varsta) throws customException {
        Pacient p=new Pacient(pacientRepo.getAll().size()+1,nume,varsta);
        validatePacient vp=new validatePacient(p);
        vp.validate();
        pacientRepo.add(p);

    }
    public void addProgramare(int pacientID, int data, String motiv, String status,boolean urgenta) throws customException
    {

        //if(pacientRepo.find(pacientID))
        //{
            if(status.compareTo("terminata")==0 || status.compareTo("urmeaza")==0 || status.compareTo("anulata")==0)
            {
                Programare p=new Programare(programareRepo.getAll().size()+1,data,pacientID,motiv,Programare.possibleStatus.valueOf(status),urgenta);
                validateProgramare vp=new validateProgramare(p,pacientRepo);
                vp.validate();
                programareRepo.add(p);

            }
            else
                throw new customException("Statusul nu corestpunde celor 3 stari !!!");

        //}
    }
    public void removePacient(int id) throws customException {
        if(pacientRepo.find(id))
        {
            List<Pacient> l=pacientRepo.getAll();
            int indexSters=0;
            for(Pacient element: l)
            {
                if(element.getID()==id) {
                    indexSters=element.getID();
                    pacientRepo.sterge(element);
                    break;
                }

            }
            for(int i=indexSters-1;i<pacientRepo.getAll().size();i++)
            {
                pacientRepo.getAll().get(i).setID(pacientRepo.getAll().get(i).getID()-1);
            }

        }
        else
            throw new customException("Pacient inexistent !!!");
    }
    public void removeProgramare(int id) throws customException
    {
        if(programareRepo.find(id))
        {
            List<Programare> l=programareRepo.getAll();
            int indexSters=0;
            for(Programare el: l)
            {
                if(el.getID()==id)
                {
                    indexSters=el.getID();
                    programareRepo.sterge(el);
                    break;
                }
            }
            for(int i=indexSters-1;i<programareRepo.getAll().size();i++)
            {
                programareRepo.getAll().get(i).setId(programareRepo.getAll().get(i).getID()-1);
            }
        }
        else
            throw new customException("Programare inexistenta !!!");
    }
    public List<Programare> filtZiStatus(int zi, String status) throws customException {
        List<Programare> programari=programareRepo.getAll();

        if(status.compareTo("terminata")==0 || status.compareTo("urmeaza")==0 || status.compareTo("anulata")==0)
        {
            Stream<Programare> filtrat=programari.stream().filter((s)->{
                if(s.getDat()==zi && s.getStatus()==Programare.possibleStatus.valueOf(status)) return true;
                else return false;
            });
            List<Programare> fil=new ArrayList<>();
            filtrat.forEach(fil::add);
            return fil;

        }
        else
            throw new customException("Statusul nu este din cele 3 stari !!!");

    }
    public List<Programare> filtUrgenta()
    {
        List<Programare> programari=programareRepo.getAll();
        Stream<Programare> filtrat=programari.stream().filter((s)->{
            if(s.isUrgenta()) return true;
            else return false;
        });
        List<Programare> fil=new ArrayList<>();
        filtrat.forEach(fil::add);
        return fil;

    }
    public List<Pacient> sortAlph()
    {
        List<Pacient> pacienti=pacientRepo.getAll();
        Comparator<Pacient> comp=(s1, s2)->{if(s1.getNume().compareTo(s2.getNume())<0)return -1; else return 0;};

        //Sortare generica
        Collections.sort(pacienti,comp);
        return pacienti;

    }
    public int nrDeProgramari(int idPacient)
    {
        int contor=0;
        List<Programare> programri=programareRepo.getAll();
        for(Programare prog:programri)
        {
            if(prog.getPacientID()==idPacient)
            {
                contor++;
            }
        }
        return contor;
    }

    public List<Pacient> sortNrProg()
    {
        List<Pacient> pacienti=pacientRepo.getAll();
        Comparator<Pacient> comp=(s1, s2)->{if(nrDeProgramari(s1.getID())>nrDeProgramari(s2.getID()))return -1; else return 0;};
        pacienti.sort(comp);
        return pacienti;

    }
    public List<Programare> filtProgDupaPacient(int idPacient)
    {
        List<Programare> programari=programareRepo.getAll();
        Stream<Programare> filtrat=programari.stream().filter((s)->{
            return s.getPacientID() == idPacient;
        });
        List<Programare> fil=new ArrayList<>();
        filtrat.forEach(fil::add);
        return fil;
    }
    public List<Programare> filtProgDupaDiag(String diag)
    {
        List<Programare> programari=programareRepo.getAll();
        Stream<Programare> filtrat=programari.stream().filter((s)->{
            return s.getDiagnostic().compareTo(diag) == 0;
        });
        List<Programare> fil=new ArrayList<>();
        filtrat.forEach(fil::add);
        return fil;
    }
    public List<Pacient> filtInterval(int st,int dr)
    {
        List<Pacient> pacienti=pacientRepo.getAll();
        Stream<Pacient>  filtrat=pacienti.stream().filter((s)->{
            return s.getVarsta() >= st && s.getVarsta() <= dr;
        });
        List<Pacient> fil=new ArrayList<>();
        filtrat.forEach(fil::add);
        return fil;

    }
    public List<Map.Entry<String,Integer>> sortPopularityDiag()
    {
        List<Programare> programari=programareRepo.getAll();
        List<String> diagnostice=new ArrayList<String>();
        for(Programare prog:programari)
        {
            diagnostice.add(prog.getDiagnostic());
        }
        HashMap<String,Integer> hh = new HashMap<String,Integer>();
        int val=0;
        for (String key : diagnostice) {
            if (hh.containsKey(key)) {
                val = hh.get(key);
                hh.put(key, val + 1);
            } else {
                hh.put(key, 1);
            }
        }
        Stream<Map.Entry<String,Integer>> sorted = hh.entrySet().stream().sorted(Map.Entry.comparingByValue());
        List<Map.Entry<String,Integer>> best=new ArrayList<Map.Entry<String,Integer>>();
        sorted.forEach(best::add);
        return best;

    }
    public double medieVarsta()
    {
        List<Pacient> pacienti=pacientRepo.getAll();
        double med=0;
        for(Pacient p:pacienti)
        {
            med+=p.getVarsta();
        }
        med=med/pacienti.size();
        return med;
    }
}
