package sk.uniza.fri.worldOfFri.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sk.uniza.fri.worldOfFri.mapa.Miestnost;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import sk.uniza.fri.worldOfFri.vynimky.NeexistujuciVychodException;

/**
 *
 * @author janik
 */
public class MiestnostTest {

    private Miestnost nemaVychody;
    private Miestnost zapad;
    private Miestnost vychod;
    private Miestnost sever;
    private Miestnost juh;

    @Before
    public void setUp() {
        this.nemaVychody = new Miestnost("nema vychody");
        this.zapad = new Miestnost("zapad");
        this.vychod = new Miestnost("vychod");
        this.sever = new Miestnost("sever");
        this.juh = new Miestnost("juh");
        this.zapad.nastavVychod("sever", this.sever);
        this.zapad.nastavVychod("vychod", this.vychod);
        this.zapad.nastavVychod("juh", this.juh);
        this.vychod.nastavVychod("sever", this.sever);
        this.vychod.nastavVychod("juh", this.juh);
        this.vychod.nastavVychod("zapad", this.zapad);
        this.sever.nastavVychod("vychod", this.vychod);
        this.sever.nastavVychod("juh", this.juh);
        this.sever.nastavVychod("zapad", this.zapad);
        this.juh.nastavVychod("sever", this.sever);
        this.juh.nastavVychod("vychod", this.vychod);
        this.juh.nastavVychod("zapad", this.zapad);
    }

    @Test
    public void nemaVychody() {
        Assert.assertEquals("nema vychody", this.nemaVychody.getPopis());
        try {
            this.nemaVychody.getDvere("vychod");
            Assert.fail("Vychod na vychod nema existovat");
        } catch (NeexistujuciVychodException e) {
        }
        try {
            this.nemaVychody.getDvere("zapad");
            Assert.fail("Vychod na zapad nema existovat");
        } catch (NeexistujuciVychodException e) {
        }
        try {
            this.nemaVychody.getDvere("sever");
            Assert.fail("Vychod na sever nema existovat");
        } catch (NeexistujuciVychodException e) {
        }
        try {
            this.nemaVychody.getDvere("juh");
            Assert.fail("Vychod na juh nema existovat");
        } catch (NeexistujuciVychodException e) {
        }
    }

    @Test
    public void sever() throws NeexistujuciVychodException {
        Assert.assertEquals("sever", this.sever.getPopis());
        Assert.assertSame(this.vychod, this.sever.getDvere("vychod").getVychod());
        Assert.assertSame(this.zapad, this.sever.getDvere("zapad").getVychod());
        Assert.assertSame(this.juh, this.sever.getDvere("juh").getVychod());
    }

    @Test(expected = NeexistujuciVychodException.class)
    public void severNeznamy() throws NeexistujuciVychodException {
        this.sever.getDvere("sever");
    }

    @Test
    public void juh() throws NeexistujuciVychodException {
        Assert.assertEquals("juh", this.juh.getPopis());
        Assert.assertSame(this.vychod, this.juh.getDvere("vychod").getVychod());
        Assert.assertSame(this.zapad, this.juh.getDvere("zapad").getVychod());
        Assert.assertSame(this.sever, this.juh.getDvere("sever").getVychod());
    }

    @Test(expected = NeexistujuciVychodException.class)
    public void juhNeznamy() throws NeexistujuciVychodException {
        this.juh.getDvere("juh");
    }
}
