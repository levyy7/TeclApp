package tests;
import java.util.*;
import main.dominio.ListaPalabras;

//import junit.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.*;
/*
import org.hamcrest.*;
import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import org.mockito.*;
import org.objenesis.*;*/


public class ListaPalabrasTest {
    @Test
    public void testCreadoraAndGetters(){
        ListaPalabras lp = new ListaPalabras("calculadora",new HashMap<>());

        assertEquals("calculadora", lp.getNombre());
        assertNotNull(lp.getListaFreq());
    }

    @Test
    public void testAddPalabra(){
        ListaPalabras lp = new ListaPalabras("calculadora",new HashMap<>());
        lp.addPalabra("carpeta", 2);
        lp.addPalabra("paraula", 6);

        assertEquals(2, lp.getSize());

        lp.addPalabra("carpeta", 1);
        assertEquals(2, lp.getSize());
    }

    @Test
    public void testGetFrequencia(){
        ListaPalabras lp = new ListaPalabras("calculadora",new HashMap<>());
        lp.addPalabra("carpeta", 2);
        lp.addPalabra("paraula", 6);

        int f = lp.getFreq("paraula");
        assertEquals(6, f);

        assertNull(lp.getFreq("aigua"));
    }

    @Test
    public void testSetListaFreq(){
        ListaPalabras lp = new ListaPalabras("calculadora",new HashMap<>());
        lp.addPalabra("carpeta", 2);
        lp.addPalabra("paraula", 6);

        assertEquals(2, lp.getSize());

        Map<String, Integer> n = new HashMap<>();
        n.put("aigua", 0);
        n.put("peix", 7);
        n.put("formatge", 3);

        lp.setListaFreq(n);

        assertEquals(3, lp.getSize());
        int f = lp.getFreq("peix");
        assertEquals(7, f);
        
        assertNull(lp.getFreq("paraula"));
    }

    /*
    @Test
    public void testImportInputValidType(){
        ListaPalabras lp = new ListaPalabras("calculadora",new HashMap<>());
        lp.importInput("hola", "aixo no arriba", "Lista");

        assertEquals("hola", lp.getNombre());
    }

    @Test
    public void testImportInputInvalidType(){
        ListaPalabras lp = new ListaPalabras("calculadora",new HashMap<>());
        lp.importInput("hola", "aixo no arriba", "notatype");

        assertEquals("calculadora", lp.getNombre());
    }
    */
}

//Mariona