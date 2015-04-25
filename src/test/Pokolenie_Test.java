package test;

import Maksimum.Pokolenie;

/**
 * Created by Adam on 2015-04-23.
 */
public class Pokolenie_Test {
    public static void main(String[] args) {

       // Pokolenie test = new Pokolenie();
        /*
        test.LosujPokolenie(10);
        test.PrintPokolenie();
        test.LiczPrzystosowanie("(x^2+1)");
        test.LiczFunkcjePrzetrwania();
        test.PrintPrzystosowanie();
        test.PrintSzansaPrzetrwania();
        test.Krzyzowanie();
        test.PrintPokolenie();

        System.out.println("Po rekombinacji");
        test.PrintPokolenie();
*/
/*
        test.LosujPokolenie(20);


        for (int i = 0; i < 80; i++) {
            test.LiczPrzystosowanie("(x^2+1)");
            test.LiczFunkcjePrzetrwania();
           test = test.Krzyzowanie();
        }*/



    Szukaj(160, "(x^2+1)", 20, 0.01);




    }

    public static void Szukaj(int ilosc_pokolen, String wzor, int wielkoscPokolenia, double SzansaNaMutacje){
        Pokolenie nowe = new Pokolenie();

        nowe.LosujPokolenie(wielkoscPokolenia);
        for (int i = 0; i < ilosc_pokolen; i++) {
            nowe.LiczPrzystosowanie(wzor);
            nowe.LiczFunkcjePrzetrwania();
            nowe = nowe.Krzyzowanie();
        }
        nowe.Najlepszy();
        nowe.Mutacja(SzansaNaMutacje);

    }


}
