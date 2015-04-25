package Maksimum;

/**
 * Created by Adam on 2015-04-22.
 */
import java.util.ArrayList;
import java.util.Random;

public class GenMax {

    public static void main(String[] args) {
        //To DO
        // Sprawdzenie co sie dzieje zaleznie od szansy na krzyzowanie sie gdy jest wieksza lub mniejsza
        // Sprawdzenie zaleznie od wielkosci populacji(ilosci osobnikow)

        // p1 = f(x1)/Suma
        // suma zaleznie od ruletki

        // f(x) = (x^2 +1)
        // zakres <1; 127>


        int IloscPrzejsc = 50;
        int OcenaPrzystosowania[];
        MathEval wyrazenie = new MathEval();

        ArrayList<String> Lista_pokolenia = new ArrayList<String>();

        Random generator = new Random();
	/*
		int a = 1;
		String z = Integer.toString(a, 2);
		System.out.println(z.length());

		System.out.println(z);
		System.out.println(dopelnienie(z));


		*/

        for(int i = 0; i< IloscPrzejsc; i++){

            int z = 127 - generator.nextInt(127);
            System.out.println(z);
            int temp = 127 - generator.nextInt(127);

            Lista_pokolenia.add(Integer.toString(temp, 2));
            System.out.println(Lista_pokolenia.get(i));


        }






    }


    public static String dopelnienie(String in){
        int lenght = in.length();
        int dlugosc = 8 - lenght;
        String temp ="";
        for(int i =0; i < dlugosc; i++){
            temp +="0";
        }
        return temp + in;


    }


}
