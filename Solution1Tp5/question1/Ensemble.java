package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();
    
    public int size() {
        return table.size();
    }

    public Iterator<T> iterator() {
        return table.iterator();
    }

    public boolean add(T t) {   
        if (t==null)  return false; // interdiction d'ajouter un element nul
        if (this.table.contains(t))//interdiction d'ajouter un element 
                                   //qui est d�j� pr�sent dans  l'ensemble
        return false;
        this.table.add(t);
        return true;          
        //comme r�ponse sur la question1-1, La m�thode addAll 
        //de la super-classe d�clenche l'ajout de tous les �l�ments 
        //de la collection pass�e en param�tre.
    }

    public Ensemble<T> union(Ensemble<? extends T> e) throws EmptyVectorException{ 
       Ensemble union = new Ensemble();
       
       if (table.isEmpty() && !e.isEmpty()) { 
            union.addAll(e);
        }
       
       if (!table.isEmpty() && e.isEmpty()){
           union.addAll(this);
        }
        
        if(!table.isEmpty() && !e.isEmpty()){
       union.addAll(this);
       union.addAll(e); 
       }
       return union;
    }
    

    public Ensemble<T> inter(Ensemble<? extends T> e)throws EmptyVectorException {
        
        Ensemble inter = new Ensemble();
        //if (table.isEmpty() && e.isEmpty()) 
                    //  throw new EmptyVectorException();
                      
        inter.addAll(this);
        inter.retainAll(e);
        return inter;
    }

      public Ensemble<T> diff(Ensemble<? extends T> e) throws EmptyVectorException {
        Ensemble diff = new Ensemble();
       if (table.isEmpty() && e.isEmpty()) 
                      throw new EmptyVectorException();
    
       if (table.isEmpty() && !e.isEmpty())  
                      throw new EmptyVectorException();
       
       if (!table.isEmpty() && e.isEmpty()){
           diff.addAll(this);
        }
        
        if(!table.isEmpty() && !e.isEmpty()){
        diff.addAll(this);
        diff.removeAll(this.inter(e)); 
       } 
        return diff; 
    }

    Ensemble<T> diffSym(Ensemble<? extends T> e) throws EmptyVectorException{
        Ensemble diffSym = new Ensemble();
       if (table.isEmpty() && e.isEmpty()) 
                      throw new EmptyVectorException();
                      
       if(table.isEmpty() || e.isEmpty())
                      diffSym.addAll(this.union(e));
         
        if(!table.isEmpty() && !e.isEmpty()){
        diffSym.addAll(this.union(e));
        diffSym.removeAll(this.inter(e));
       }   
        return diffSym; 
    }
    
}
