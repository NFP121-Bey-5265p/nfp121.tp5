package question2;

import javax.swing.*;
import java.util.*;

public class IHMListe2 extends JFrame {
    public IHMListe2() {
        List<String> liste = Chapitre2CoreJava2.listeDesMots();
        try
        {
            Map<String, Integer> table = Chapitre2CoreJava2.occurrencesDesMots(liste);
            JPanelListe2 jPanelListe = new JPanelListe2(liste, table);
            add(jPanelListe);
            setLocation(120,150);
            pack();
            setVisible(true);
        }
        catch (question2.ListeVideException lve)
        {
            lve.printStackTrace();
        }
       
    }

    public static void main(String[] args){
        new IHMListe2();
    }

}
