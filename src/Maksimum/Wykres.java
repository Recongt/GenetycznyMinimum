package Maksimum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Adam on 2015-04-22.
 */
public class Wykres extends JFrame {

private ArrayList<String> lista_chromosomow;
    private JPanel panel1;


    public Wykres(){
        setTitle("Wykres");
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 330);
        setSize(640, 330);
        this.add(panel1);
        getContentPane().add(new MyComponent());
        setSize(300, 200);
        setVisible(true);



// po dodaniu mozna operowac


    };

    public Wykres( ArrayList<String> lista_wejscie ){

    int size = lista_wejscie.size();


}
    public void  SetWykres(ArrayList<String> wejsciowe){
        int lenght = wejsciowe.size();

        for(int i=0; i < lenght; i++){
            this.lista_chromosomow.add(wejsciowe.get(i));
        }



    };

}




class Slice {
    double value;
    Color color;
    String nazwa;

    public Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
    public Slice(double value, Color color, String nazwa) {
        this.value = value;
        this.color = color;
        this.nazwa = nazwa;
    }
}

class MyComponent extends JComponent {
    Slice[] slices = { new Slice(5, Color.black,"lol"), new Slice(33, Color.green),
            new Slice(20, Color.yellow), new Slice(15, Color.red) };

    MyComponent() {

    }
    MyComponent(ArrayList<String> lista){

    }
    public void paint(Graphics g) {
        drawPie((Graphics2D) g, getBounds(), slices);
    }

    void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
        double total = 0.0D;
        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }

        double curValue = 0.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].value * 360 / total);

            g.setColor(slices[i].color);
            g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
            curValue += slices[i].value;
        }
    }
}
/*
class PieChart {
    public static void main(String[] argv) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MyComponent());
        frame.setSize(300, 200);
        frame.setVisible(true);

    }
}*/