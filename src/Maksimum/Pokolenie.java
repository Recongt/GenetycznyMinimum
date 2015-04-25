package Maksimum;

/**
 * Created by Adam on 2015-04-22.
 */

import java.util.ArrayList;
import java.util.Random;


public class Pokolenie {

    private ArrayList<String> Lista_osobnikow = new ArrayList<String>(); // zapis 011101001
    private ArrayList<Double> Lista_przystosowania = new ArrayList<Double>(); //0.01458474 szansa
    private ArrayList<Double> Lista_wartosci = new ArrayList<Double>();  //f(x) = ...
    private double suma_populacji;


    public void PrintPokolenie() {
        int lenght = this.Lista_osobnikow.size();

        for (int i = 0; i < lenght; i++) {
            System.out.println("Chromosom: " + (i + 1) + " Kod: " + this.Lista_osobnikow.get(i) + " Wartosc: " + (Integer.parseUnsignedInt(this.Lista_osobnikow.get(i), 2)));

        }

    }

    public void DodajOsobnika(String osobnik){
        this.Lista_osobnikow.add(osobnik);
    }


    public void LosujPokolenie(int wielkosc) {
        Random generator = new Random();
        for (int i = 0; i < wielkosc; i++) {
            int temp = 127 - generator.nextInt(127);
            this.Lista_osobnikow.add(dopelnienie(Integer.toString(temp, 2)));

        }

    }

    public void LiczPrzystosowanie(String funkcja) {
        // przyjmuje funkcje w postaci (x^2 +1) zabawa ma polegac na podmianie x
        MathEval wyrazenie = new MathEval();
        int lenght = this.Lista_osobnikow.size();
        double suma_wszystkich = 0;

        for (int i = 0; i < lenght; i++) {
            //String osobnik to funkcja stworzona na podstawie wartosci z Listy_pokolenia i string Funkcji
            String osobnik = funkcja.replaceAll("x", Integer.toString(Integer.parseUnsignedInt(this.Lista_osobnikow.get(i), 2)));
            Lista_wartosci.add(i, wyrazenie.evaluate(osobnik));
            System.out.println(osobnik);
        }


    }

    public void LiczFunkcjePrzetrwania() {
        int lenght = this.Lista_wartosci.size();
        suma_populacji = 0;
        for (int i = 0; i < lenght; i++) {
            suma_populacji = suma_populacji + Lista_wartosci.get(i);
        }
        for (int i = 0; i < lenght; i++) {
            Lista_przystosowania.add(Lista_wartosci.get(i) / suma_populacji);
        }

    }


    public String dopelnienie(String in) {
        int lenght = in.length();
        int dlugosc = 8 - lenght;
        String temp = "";
        for (int i = 0; i < dlugosc; i++) {
            temp += "0";
        }
        return temp + in;


    }


    public void PrintPrzystosowanie() {
        int lenght = this.Lista_wartosci.size();

        for (int i = 0; i < lenght; i++) {
            System.out.println("Wartosci: " + Lista_wartosci.get(i));
        }
    }

    public void PrintSzansaPrzetrwania() {
        int lenght = this.Lista_przystosowania.size();
        for (int i = 0; i < lenght; i++) {
            System.out.println("Szansa przetrwania: " + Lista_przystosowania.get(i));
        }


    }

    //
    public Pokolenie Krzyzowanie() {
        int lenth = this.Lista_osobnikow.size();
        int ilosc_potomkow = 0;
        ArrayList<Integer> OsobnikiDoKrzyzowania = new ArrayList<Integer>();
        // liczenie ilosci mozliwych potomkow ma byc ich 3/4 najlepszych aby mogly sie krzyzowac
        //zaokraglane jest w dol

        if ((3 * (lenth / 4) % 2) == 1) {

            ilosc_potomkow = (int) (3 * (lenth / 4)) - 1;
        } else {
            ilosc_potomkow = (int) (3 * (lenth / 4));
        }
        System.out.println("Ilosc osobnikow do tworzenia potomkow: " + ilosc_potomkow);

        for (int i = 0; i < ilosc_potomkow; i++) {
            Random generator = new Random(); // obiekt losujacy
            Double wylosowana = generator.nextDouble(); // losowanie wartosci <0;1>
            double najlepszy = 1;   // szukamy wartosci jaknajblizszej 0 wiec 1 jest dobra na start
            boolean istnieje_juz = false;

            int wybrany_osobnik = 0;
            // Przeszukiwanie przestrzeni osobnikow
            for (int licznik_losowaniiu = 0; licznik_losowaniiu < lenth; licznik_losowaniiu++) {
                //sprawdzam jak blisko jast wartosc przystosowania wylosowanej wartosci
                double roznica = java.lang.Math.abs(Lista_przystosowania.get(licznik_losowaniiu) - wylosowana);

                if (roznica < najlepszy) {
                    najlepszy = roznica;
                    wybrany_osobnik = licznik_losowaniiu;
                }

            }
            for (int licznik_czy_juz_istnieje = 0; licznik_czy_juz_istnieje < OsobnikiDoKrzyzowania.size(); licznik_czy_juz_istnieje++) {
                if (OsobnikiDoKrzyzowania.get(licznik_czy_juz_istnieje) == wybrany_osobnik) {
                    istnieje_juz = true;

                }
            }
            if (istnieje_juz) {
                i--;
                istnieje_juz = false;
            } else {
                OsobnikiDoKrzyzowania.add(wybrany_osobnik);
            }

            // po przejsciu calej listy i wybraniu najbliszego liczby wyloswanej nalezy go dodac na liste oczekujacych do krzyzowania i losowac nastepne


            System.out.println("wylosowana wartosc:" + wylosowana);
        }

        for (int dupa = 0; dupa < OsobnikiDoKrzyzowania.size(); dupa++) {

            System.out.println("Osobniki wygrane: " + OsobnikiDoKrzyzowania.get(dupa));
        }
        Pokolenie nowe = RekombinacjaPopulacji(OsobnikiDoKrzyzowania, lenth);
        System.out.println("Nowe pokolenie");
        nowe.PrintPokolenie();
        System.out.println("Stop Nowe pokolenie");

        return nowe;

    }

    public void RekokombinacjaOsobnikow(int pierwszy, int drugi) {

        String tempPierwszy = this.Lista_osobnikow.get(pierwszy);
        String tempDrugi = this.Lista_osobnikow.get(drugi);
        this.Lista_osobnikow.set(pierwszy, this.Lista_osobnikow.get(pierwszy).substring(0, 4) + tempDrugi.substring(4));
        this.Lista_osobnikow.set(drugi, this.Lista_osobnikow.get(drugi).substring(0, 4) + tempPierwszy.substring(4));


    }

    public Pokolenie RekombinacjaPopulacji(ArrayList<Integer> OsobnikiDoKrzyzowania, int WielkoscPopulacji) {
        int lenght = OsobnikiDoKrzyzowania.size();
        Pokolenie nowe = new Pokolenie();
        for (int i = 0; (i < lenght); i= i+2) {
            nowe.DodajOsobnika(Lista_osobnikow.get(OsobnikiDoKrzyzowania.get(i)));
            nowe.DodajOsobnika(Lista_osobnikow.get(OsobnikiDoKrzyzowania.get(i+1)));
            nowe.RekokombinacjaOsobnikow(i,(i+1));

        }
        nowe.LosujPokolenie(WielkoscPopulacji - nowe.Lista_osobnikow.size());



        return nowe;
    }

    public void Najlepszy(){
        this.LiczPrzystosowanie("(x^2 +1)");


        int lenght = this.Lista_osobnikow.size();
        System.out.println(this.Lista_wartosci.get(1));
        double najwyszaWartoscOsobnika = 0;
        int indexNajlepszego=0;
        System.out.println("Lenght:"+lenght);
        System.out.println("Wartosc przykaldowa: "+this.Lista_wartosci.get(7));

        for(int i = 0; i < lenght; i++){
            if(this.Lista_wartosci.get(i) > najwyszaWartoscOsobnika){
                najwyszaWartoscOsobnika = this.Lista_wartosci.get(i);
                indexNajlepszego = i;
            }
        }
        System.out.println("Indeks najlepszego: "+indexNajlepszego);
        System.out.println("Chromosom: " + (indexNajlepszego+1) + " Kod: " + this.Lista_osobnikow.get(indexNajlepszego) + " Wartosc: " + (Integer.parseUnsignedInt(this.Lista_osobnikow.get(indexNajlepszego), 2)));
    }


}
