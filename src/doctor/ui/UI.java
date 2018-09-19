package doctor.ui;


import doctor.customException;
import doctor.domain.Pacient;
import doctor.domain.Programare;
import doctor.service.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class UI {

    private Service serv;

    public UI(Service serv) {
        this.serv = serv;
    }

    private void printAll()
    {
        List<Pacient> pacienti=serv.getPacientRepo().getAll();
        System.out.println("Aceasta este lista pacientilor:");
        printPac(pacienti);
        List<Programare> programari=serv.getProgramareRepo().getAll();
        System.out.println("Aceasta este lista programarilor asociate pacientilor:");
        printProg(programari);

    }
    private void adaugPacient()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Dati nume:");
        String nume =  input.next();
        System.out.println("Dati varsta(int only):");
        int varsta=0;
        try{
            varsta = input.nextInt();
            serv.addPacient(nume,varsta);

        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid input, varsta nu e nr intreg");
            input.nextLine();
        } catch (customException e) {
            System.err.print(e);
        }

    }
    private void adaugProgramare()
    {
        Scanner input = new Scanner(System.in);
        int pacientID,data;
        String motiv ,status;
        String urgenta;
        System.out.println("Dati Id ul pacientului pt care facem programarea:");
        try
        {
            pacientID=input.nextInt();
            System.out.println("Data de azi:");
            data=input.nextInt();
            System.out.println("Motiv/diagnostic:");
            motiv=input.next();
            System.out.println("Status:");
            status=input.next();
            System.out.println("Urgenta: da sau nu?");
            boolean valid=true;
            while(valid) {
                urgenta = input.next();
                if (urgenta.compareTo("da") == 0) {
                    serv.addProgramare(pacientID, data, motiv, status, true);
                    valid=false;
                }
                if (urgenta.compareTo("nu") == 0) {
                    serv.addProgramare(pacientID, data, motiv, status, false);
                    valid=false;
                }
                if (urgenta.compareTo("da") != 0 && urgenta.compareTo("nu") != 0) {
                    System.out.print("Invalid input, trebuia da sau nu, mai incearca");
                }
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid input, id/data nu e nr intreg");
            input.nextLine();
        } catch (customException e) {
            System.err.print(e);
        }


    }
    private void stergePacient()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Dati Id ul pacientului pt care vreti sa il stergeti:");
        int id;
        try {
            id = input.nextInt();
            serv.removePacient(id);
        }
        catch(InputMismatchException e) {
            System.out.println("Invalid input, nu ati introdus numar");
            input.nextLine();
        } catch (customException e) {
            System.err.print(e);
        }

    }
    private void stergeProgramare()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Dati Id ul programarii pt care vreti sa o stergeti:");
        int id;
        try {
            id = input.nextInt();
            serv.removeProgramare(id);
        }
        catch(InputMismatchException e) {
            System.out.println("Invalid input, nu ati introdus numar");
            input.nextLine();
        } catch (customException e) {
            System.err.print(e);
        }

    }
    private void listareZiStatus()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Dati ziua pentru care vreti programarile:");
        int zi;
        try
        {

            while(true)
            {
                zi=input.nextInt();
                if(zi<1||zi>31)
                {
                    System.out.println("Nu este o data valida!!!");
                }
                else
                {
                    break;
                }
            }
            System.out.println("Dati statusul programarii pentru care sa filtram:");
            String status=input.next();
            List<Programare> programari=serv.filtZiStatus(zi,status);
            if(programari.size()==0)
            {
                System.out.println("Nu sunt inregistrate programari pe acesti parametrii");
            }
            else
            {
                System.out.println("Lista programarilor din  acea zi si acel status sunt:");
                printProg(programari);
            }

        }
        catch(InputMismatchException e)
        {
            System.out.println("Ziua nu este un numar!!!");
            input.nextLine();
        } catch (customException e) {
            System.err.print(e);
        }

    }
    private void filtrareUrgente()
    {
        List<Programare> f=serv.filtUrgenta();
        System.out.println("Listeaza programărilor de tip urgență:");
        printProg(f);
    }
    private void sortAlphabetic()
    {
        System.out.println("Se sorteaza pacientii in ordine alfabetica:");
        List<Pacient> s=serv.sortAlph();
        printPac(s);
    }
    private void sortNrDeProg()
    {
        System.out.println("Se sorteaza pacientii in functie de nr de programari:");
        List<Pacient> s=serv.sortNrProg();
        printPac(s);
    }
    private void getProgramariDupaPacient()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Dati Id ul pacientului:");
        try {
            int id = input.nextInt();
            List<Programare> f = serv.filtProgDupaPacient(id);
            if (f.size() == 0) {
                System.out.println("Pacientul nu are inregistrate programari");
            } else {
                printProg(f);
            }
        }
        catch(InputMismatchException e) {
            System.out.println("Id ul nu este un numar!!!");
            input.nextLine();
        }

    }
    private void getProgramariDupaDiag()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Dati diagnosticul ul pacientului:");
        String diag = input.next();
        List<Programare> f = serv.filtProgDupaDiag(diag);
        if (f.size() == 0) {
            System.out.println("Nu sunt inregistrate programari pe acest diagnostic");
        } else {
            printProg(f);
        }

    }
    private void intervalVarsta()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Se vor filtra pacientii cuprinsi intr un interval de varsta, dati capetele:");
        int st,dr=0;
        try
        {
            while(true)
            {
                System.out.println("Dati capat 1:");
                st=input.nextInt();
                System.out.println("Dati capat 2:");
                dr=input.nextInt();
                if(st<dr)
                {
                    break;
                }
                else
                {
                    System.out.println("Stanga nu e mai mica ca dreapta, mai incearca");
                }
            }
            List<Pacient> l=serv.filtInterval(st,dr);
            System.out.println("Pacientii sunt:");
            printPac(l);
        }
        catch(InputMismatchException e) {
            System.out.println("Id ul nu este un numar!!!");
            input.nextLine();
        }

    }
    private void popularDiag()
    {
        List<Map.Entry<String,Integer>> best=serv.sortPopularityDiag();
        System.out.printf("De cele mai multe ori apare diagnosticul %s de %d ori",best.get(best.size()-1).getKey(),best.get(best.size()-1).getValue());
    }
    private void average()
    {
        double av=serv.medieVarsta();
        System.out.printf("Medie de varsta a pacientilor este: %f \n",av);
    }

    private void printPac(List<Pacient> s) {
        for(Pacient pacient:s)
        {
            System.out.print(pacient.getID()+" ");
            System.out.print(pacient.getNume()+" ");
            System.out.print(pacient.getVarsta());
            System.out.println();

        }
    }


    private void printProg(List<Programare> programari) {
        for(Programare pro:programari)
        {
            System.out.print(pro.getID()+" ");
            System.out.print(pro.getDat()+" ");
            System.out.print(pro.getPacientID()+" ");
            System.out.print(pro.getDiagnostic()+" ");
            System.out.print(pro.getStatus()+" ");
            System.out.print(pro.isUrgenta());
            System.out.println();
        }
    }


    private void showComands()
    {
        System.out.println("0: Print pacienti si programari");
        System.out.println("1: Adaugare pacient");
        System.out.println("2: Adaugare programare pentru un pacient");
        System.out.println("3: Sterge un pacient din baza de date");
        System.out.println("4: Sterge o programare din baza de date");
        System.out.println("5: Listarea programărilor dintr-o zi/lună cu un anumit status");
        System.out.println("6: Listarea programărilor de tip urgență");
        System.out.println("7: Listarea pacienților în ordine alfabetică");
        System.out.println("8: Listarea pacienților in functie de numărul de programări");
        System.out.println("9: Filtrarea programărilor după pacient");
        System.out.println("10: Filtrarea programărilor după diagnostic");
        System.out.println("11:Filtrarea pacienților după un interval de vârstă");
        System.out.println("12:Găsirea celui mai des întâlnit diagnostic");
        System.out.println("13:Găsirea mediei de vârstă a pacienților");
        System.out.println("q:Iesire din aplicatie");

    }

    public void run()
    {
        String cmd = "";
        boolean isRunning = true;

        while (isRunning) {
            showComands();
            Scanner input = new Scanner(System.in);
            System.out.println("Enter command");
            cmd =  input.next();
            System.out.println();

            switch (cmd) {
                case "0":
                    printAll();
                    break;
                case "1":
                    adaugPacient();
                    break;
                case "2":
                    adaugProgramare();
                    break;
                case "3":
                    stergePacient();
                    break;
                case "4":
                    stergeProgramare();
                    break;
                case "5":
                    listareZiStatus();
                    break;
                case "6":
                    filtrareUrgente();
                    break;
                case "7":
                    sortAlphabetic();
                    break;
                case "8":
                    sortNrDeProg();
                    break;
                case "9":
                    getProgramariDupaPacient();
                    break;
                case "10":
                    getProgramariDupaDiag();
                    break;
                case "11":
                    intervalVarsta();
                    break;
                case "12":
                    popularDiag();
                    break;
                case "13":
                    average();
                    break;
                case "q":
                    System.out.println("Bye");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid command!");
                    break;
            }
            System.out.println("\n--------------");

            if (cmd.equals("q") || isRunning == false)
                break;
        }
    }

}
