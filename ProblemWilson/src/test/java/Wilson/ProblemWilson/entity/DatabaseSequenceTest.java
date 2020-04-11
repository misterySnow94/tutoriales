/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wilson.ProblemWilson.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class DatabaseSequenceTest {
    
    public DatabaseSequenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class DatabaseSequence.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        DatabaseSequence instance = new DatabaseSequence();
        instance.setId("1");
        String result = instance.getId();
        assertEquals("1", result);
    }

    /**
     * Test of getSeq method, of class DatabaseSequence.
     */
    @Test
    public void testGetSeq() {
        System.out.println("getSeq");
        DatabaseSequence instance = new DatabaseSequence();
        long expResult = 0L;
        long result = instance.getSeq();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class DatabaseSequence.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        DatabaseSequence instance = new DatabaseSequence();
        instance.setId(id);
    }

    /**
     * Test of setSeq method, of class DatabaseSequence.
     */
    @Test
    public void testSetSeq() {
        System.out.println("setSeq");
        long seq = 0L;
        DatabaseSequence instance = new DatabaseSequence();
        instance.setSeq(seq);
    }

    /**
     * Test of equals method, of class DatabaseSequence.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        DatabaseSequence instance = new DatabaseSequence();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of canEqual method, of class DatabaseSequence.
     */
    @Test
    public void testCanEqual() {
        System.out.println("canEqual");
        Object other = null;
        DatabaseSequence instance = new DatabaseSequence();
        boolean expResult = false;
        boolean result = instance.canEqual(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class DatabaseSequence.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DatabaseSequence instance = new DatabaseSequence();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(6018, result);
    }

    /**
     * Test of toString method, of class DatabaseSequence.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DatabaseSequence instance = new DatabaseSequence();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, instance.toString());
    }
    
}
