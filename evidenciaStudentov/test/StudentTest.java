/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author janik
 */
public class StudentTest {
    @Test
    public void studentBezSkupiny() {
        Student s = new Student("123", "Ferko", "Mrkvicka");
        Assert.assertNull(s.getCisloSkupiny());
    }
    
    @Test
    public void studentVSkupine() {
        Student s = new Student("123", "Ferko", "Mrkvicka");
        Skupina skup = new Skupina("5");
        skup.pridajStudenta(s);
        Assert.assertEquals("5", s.getCisloSkupiny());
    }
    
    @Test
    public void ziskanieNeexistujucehoStudenta() {
        Skupina skup = new Skupina("5");
        Student ziskany = skup.getStudent("123");
        
        Assert.assertNull(ziskany);
    }
    
    @Test
    public void ziskanieStudenta() {
        Student s = new Student("123", "Ferko", "Mrkvicka");
        Skupina skup = new Skupina("5");
        skup.pridajStudenta(s);
        Student ziskany = skup.getStudent("123");
        
        Assert.assertSame(ziskany, s);
    }
    
    @Test
    public void presunStudentaDoSkupiny() {
        Student s = new Student("123", "Ferko", "Mrkvicka");
        Skupina skup = new Skupina("5");
        skup.pridajStudenta(s);
        
        Assert.assertEquals("5", s.getCisloSkupiny());
        
        Skupina skup10 = new Skupina("10");
        skup10.pridajStudenta(s);
        
        Assert.assertEquals("10", s.getCisloSkupiny());
        Assert.assertSame(s, skup10.getStudent("123"));
        Assert.assertNull(skup.getStudent("123"));
    }
}
