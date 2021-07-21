package question3;

import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

public class Tests extends junit.framework.TestCase {

    public void test1(question3.Factory<Set>f) throws Exception {
        Set<Integer> set = f.create();
        for (int i = 20; i > 0; i--)
            set.add(i);
    }

    public void testCreation() {
        try {
            test1(new TreeSetFactory<Integer>());
            test1(new HashSetFactory<Integer>());
        } catch (NoSuchMethodError e) {
            fail("NoSuchMethodError : " + e.getMessage());
        } catch (Exception e) {
            fail(" exception inattendue : " + e.getMessage());
        }
        
    }
    
    public void test2() {
       
        TreeSetFactory ft1 =new TreeSetFactory<Integer>();
        HashSetFactory fh1 = new HashSetFactory<Integer>();
        Set<Integer> tsf= ft1.create();
        Set<Integer> hsf= fh1.create();
        
        tsf.add(1);tsf.add(2);tsf.add(3);
        hsf.add(1);hsf.add(2);hsf.add(3);
        
        assertFalse(tsf.isEmpty());
        assertEquals(3, tsf.size());
        assertEquals(3, hsf.size());
        
        assertTrue(tsf.contains(1) && hsf.contains(2));
        
        
    }
     
}
