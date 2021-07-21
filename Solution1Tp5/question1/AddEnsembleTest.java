package question1;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TestAdd.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AddEnsembleTest extends junit.framework.TestCase {

    public void addtest()
    {
        question1.Ensemble<Integer> ensemble1 = new question1.Ensemble<Integer>();
        assertEquals(true, ensemble1.add(10));
        assertEquals(false, ensemble1.add(10));
        assertEquals(true, ensemble1.add(100));
        assertEquals(true, ensemble1.add(30));
        assertEquals(true, ensemble1.add(70));
        assertEquals(false, ensemble1.add(70));
        question1.Ensemble<Double> ensemble2 = new question1.Ensemble<Double>();
        assertEquals(true, ensemble2.add(50.2));
        assertEquals(true, ensemble2.add(0.5));
        assertEquals(true, ensemble2.add(650.235));
        assertEquals(true, ensemble2.add(620.2));
        assertEquals(false, ensemble2.add(620.2));
        question1.Ensemble<String> ensemble3 = new question1.Ensemble<String>();
        assertEquals(true, ensemble3.add("abc"));
        assertEquals(true, ensemble3.add("cde"));
        assertEquals(false, ensemble3.add("cde"));
    }
    
      public void testUnion() {
        question1.Ensemble<String> e1, e2;
        e1 = new question1.Ensemble<String>();
        assertEquals(true, e1.add("a"));
        assertEquals(true, e1.add("b"));

        e2 = new question1.Ensemble<String>();
        assertEquals(true, e2.add("b"));
        assertEquals(true, e2.add("d"));

        try
        {
            question1.Ensemble<String> union = e1.union(e2);
            assertEquals(3, union.size());
            assertTrue(union.contains("a"));
            assertTrue(union.contains("b"));
            assertTrue(union.contains("d"));
        }
        catch (question1.EmptyVectorException eve)
        {
            eve.printStackTrace();
        }
    }
    
      public void testInter() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));
        assertEquals(true, e1.add(4));
        assertEquals(true, e1.add(5));
        
        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));
        assertEquals(true, e2.add(6));
        assertEquals(true, e2.add(8));
        
        try
        {
            question1.Ensemble<Integer> inter = e1.inter(e2);
            assertEquals(2, inter.size());
            assertTrue(inter.contains(3));
            assertTrue(inter.contains(4));   
        }
        catch (question1.EmptyVectorException eve)
        {
            eve.printStackTrace();
        }
    
    }
    
    public void testDiff() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));
        assertEquals(true, e1.add(4));
        assertEquals(true, e1.add(5));
        
        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));
        assertEquals(true, e2.add(6));
        assertEquals(true, e2.add(8));
        
        try
        {
            question1.Ensemble<Integer> diff = e1.diff(e2);
            assertEquals(2, diff.size());
            assertTrue(diff.contains(2));
            assertTrue(diff.contains(5));   
        }
        catch (question1.EmptyVectorException eve)
        {
            eve.printStackTrace();
        }
    
    }
    
    public void testDiffSym() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));
        assertEquals(true, e1.add(4));
        assertEquals(true, e1.add(5));
        
        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));
        assertEquals(true, e2.add(6));
        assertEquals(true, e2.add(8));
        
        try
        {
            question1.Ensemble<Integer> diffs = e1.diffSym(e2);
            assertEquals(4, diffs.size());
            assertTrue(diffs.contains(2));
            assertTrue(diffs.contains(5));
            assertTrue(diffs.contains(6));
            assertTrue(diffs.contains(8)); 
        }
        catch (question1.EmptyVectorException eve)
        {
            eve.printStackTrace();
        }
      }
      
      public void TestEnsembleVide() throws EmptyVectorException{
       question1.Ensemble<Integer> e1, e2;
       e1 = new question1.Ensemble<Integer>();
       e2 = new question1.Ensemble<Integer>();
       try
       {
           question1.Ensemble<Integer> union = e1.union(e2);
           assertEquals(0, union.size());
           
           question1.Ensemble<Integer> inter = e1.inter(e2);
           question1.Ensemble<Integer> diff = e1.diff(e2);
           
           question1.Ensemble<Integer> diffs = e1.diffSym(e2);
          
           assertEquals(true, e1.add(2));
           assertEquals(true, e1.add(3));
           question1.Ensemble<Integer> union1 = e1.union(e2);
           assertEquals(2, union1.size());
           assertTrue(union1.contains("2"));
           assertTrue(union1.contains("3"));
           question1.Ensemble<Integer> inter1 = e1.inter(e2);
           assertEquals(0, inter1.size());
           question1.Ensemble<Integer> diff1 = e1.diff(e2);
           assertEquals(2, diff1.size());
           assertTrue(diff1.contains("2"));
           assertTrue(diff1.contains("3"));
           question1.Ensemble<Integer> diffs1 = e1.diffSym(e2);
           assertEquals(2, diffs1.size());
           assertTrue(diffs1.contains("2"));
           assertTrue(diffs1.contains("3"));
       }
       catch (question1.EmptyVectorException eve)
       {
           eve.printStackTrace();
       }
       
    }
}
