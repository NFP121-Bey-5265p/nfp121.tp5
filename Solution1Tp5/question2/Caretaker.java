package question2;
import java.util.ArrayList;

class Caretaker {
     ArrayList<Memento>savedStates=new ArrayList<Memento>();
     
     public void addMemento(Memento m) {
        savedStates.add(m);
     }
      public Memento getMemento(int index){
        return savedStates.get(index);
     }
    
 }
