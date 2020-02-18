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
}
