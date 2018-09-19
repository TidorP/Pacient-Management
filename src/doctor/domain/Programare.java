package doctor.domain;

import java.util.Comparator;

public class Programare {

    public Programare(int id, int data, int pacientID, String diagnostic, possibleStatus status, boolean urgenta) {
        this.id = id;
        this.data = data;
        this.pacientID = pacientID;
        this.diagnostic = diagnostic;
        this.status = status;
        this.urgenta = urgenta;
    }

    private int id;

    private int data;

    private int pacientID;

    private String diagnostic;

    public enum possibleStatus{urmeaza,anulata,terminata}
    private possibleStatus status;


    private boolean urgenta;

    public int getID() {
        return id;
    }


    public int getDat() {
        return data;
    }

    public int getPacientID() {
        return pacientID;
    }


    public String getDiagnostic() {
        return diagnostic;
    }


    public possibleStatus getStatus() {
        return status;
    }


    public boolean isUrgenta() {
        return urgenta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setPacientID(int pacientID) {
        this.pacientID = pacientID;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setStatus(possibleStatus status) {
        this.status = status;
    }

    public void setUrgenta(boolean urgenta) {
        this.urgenta = urgenta;
    }

    public boolean comp(Programare ot)
    {
        Comparator<Programare> comparator = (p1, p2) -> {

            if(p1.getID()==p2.getID()&& p1.getDat()==p2.getDat()&& p1.getPacientID()==p2.getPacientID()&& p1.getDiagnostic().compareTo(p2.getDiagnostic())==0 && p1.getStatus()==p2.getStatus()&& p1.isUrgenta()==p2.isUrgenta())
                return 1;
            else return 0;

        };
        int in=comparator.compare(this,ot);
        return in > 0;
    }

    public void egaleaza(Programare ot)
    {
        this.id = ot.getID();
        this.data = ot.getDat();
        this.pacientID = ot.getPacientID();
        this.diagnostic = ot.getDiagnostic();
        this.status = ot.getStatus();
        this.urgenta = ot.isUrgenta();
    }


}
