package doctor.domain;

import java.util.Comparator;

public class Pacient {
    private int id;
    private String nume;
    private int varsta;

    public Pacient(int id, String nume, int varsta)
    {
        this.id=id;
        this.nume=nume;
        this.varsta=varsta;
    }

    public int getID() {
        return this.id;
    }

    public String getNume() {
        return this.nume;
    }

    public int getVarsta() {
        return this.varsta;
    }

    public void setID(int i)
    {
        this.id=i;
    }
    public void setNume(String n)
    {
        this.nume=n;
    }
    public void setVarsta(int v)
    {
        this.varsta=v;
    }
    public boolean comp(Pacient ot)
    {
        Comparator<Pacient> comparator = (p1, p2) -> {

            if(p1.getID()==p2.getID()&& p1.getNume().compareTo(p2.getNume())==0 && p1.getVarsta()==p2.getVarsta())
                return 1;
            else return 0;

        };
        int in=comparator.compare(this,ot);
        return in==1;
    }
    public void egaleaza(Pacient ot)
    {
        this.id=ot.getID();
        this.nume=ot.getNume();
        this.varsta=ot.getVarsta();
    }


}
