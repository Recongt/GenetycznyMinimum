import Maksimum.Wykres;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Adam on 2015-04-22.
 */
public class Wykres_test {

    public static void main(String[] args) {

        final ArrayList<String> test = new ArrayList<String>();
        test.add("1100101");
        test.add("1100101");
        test.add("1100101");
        test.add("1100101");


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Wykres frame;
                    frame = new Wykres();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });






    }
    }

