package question2;

import java.util.Stack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.TreeSet;
import java.util.Set;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayList;

public class JPanelListe3Memento extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("d�croissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;

    

    private Originator originator;
    private Caretaker caretaker ;
    int saveFiles= 0;
    int currentArticle=0;
    int elementRetire=0;
    public JPanelListe3Memento(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        //Nouvelle pile
        
        originator = new Originator();
        caretaker = new Caretaker();
        
        
        
        cmd.setLayout(new GridLayout(3, 1));
        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);

        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incompl�te");
        }
        
        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);

        saisie.addActionListener(this);//pour la touche ENTER
        boutonAnnuler.addActionListener(this);//undo
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("r�sultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
                
            } else if (ae.getSource() == boutonRetirer) {
                //List<String> listeBis = new ArrayList<String>(this.liste);
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
               // if(res) {stockerEtat(listeBis);}
                afficheur.setText("r�sultat du retrait de tous les �l�ments commen�ant par -->  "
                    + saisie.getText() + " : " + res + " �l�ments retir�s "+elementRetire );
                
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null ){
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                     
                }else{
                    afficheur.setText(" -->  ??? ");
                     
                }
            }else if(ae.getSource() == boutonAnnuler){
                //Bouton annuler 
                try{
                    if(currentArticle>=1){
                        currentArticle--;
                        liste = originator.restoreFromMemento(caretaker.getMemento(currentArticle));
                        occurrences = Chapitre2CoreJava2.occurrencesDesMots(this.liste); 
                    }else{ boutonAnnuler.setEnabled(false);}
                } catch (Exception e){}
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        List<String> liste2 = new ArrayList<String>(this.liste);
       try {  
        boolean res = false;
        if (ie.getSource() == ordreCroissant){
            res = true;
            if(res) {stockerEtat(liste2);}
            Collections.sort(this.liste);  
        }else if (ie.getSource() == ordreDecroissant){
            res = true;
            if(res) {stockerEtat(liste2);}
            Collections.sort(this.liste, Collections.reverseOrder());
        }
        texte.setText(liste.toString());
        }catch (Exception e) {
            afficheur.setText(e.toString());
       }
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
     boolean resultat = false;
        List<String> liste2 = new ArrayList<String>(this.liste);
        List<String> temp = this.liste;
        Iterator<String> it = temp.iterator();
        
        try{
        while(it.hasNext()) {
            String s = it.next();
            if (s.startsWith(prefixe)) {
                stockerEtat(liste2);
                it.remove();
                resultat = true; 
                elementRetire ++;//to count the number of the first deleted element 
                                 //elementRetire+1= the number of times we can repeat undo 
                                 //it keeps piling the number of deleted elements so it doen't
                                 //have big importance after the first "retirer"
                this.occurrences.put(s, 0);
            }
        }
        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
        texte.setText(liste.toString());
        return resultat;
    }

      private void stockerEtat(List<String> liste2){
        liste2 = new ArrayList<String>(this.liste);
        originator.set(liste2);//memento// 
        caretaker.addMemento(originator.saveToMemento());//memento
        boutonAnnuler.setEnabled(true);
        saveFiles ++;
        currentArticle ++; 
    }
}  
