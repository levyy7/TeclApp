package tests;
import main.dominio.Alfabeto;

//import junit.*;
import static org.junit.Assert.*;
import org.junit.*;
/*
import org.hamcrest.*;
import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import org.mockito.*;
import org.objenesis.*;*/

public class AlfabetoTest {

    @Test
    public void testCreadoraAndGetters(){
        Alfabeto a = new Alfabeto("beta", "sdiurl");
        
        assertEquals("sdiurl", a.getLetras());
        assertEquals("beta", a.getNombre());
        assertEquals(6, a.getSize());
    }

    /*
    @Test
    public void testImportInputValidType(){
        Alfabeto a = new Alfabeto("beta", "sdiurl");
        a.importInput("gamma", "junit", "Alfabeto");

        assertEquals("gamma", a.getNombre());
        assertEquals("junit", a.getAlfabeto());

    }

    @Test
    public void testImportInputInvalidType(){
        Alfabeto a = new Alfabeto("beta", "sdiurl");
        a.importInput("gamma", "junit", "Texto");

        assertEquals("beta", a.getNombre());
        assertEquals("sdiurl", a.getAlfabeto());

    }
    */

    @Test
    public void testSetNombre(){
        Alfabeto a = new Alfabeto("beta", "sdiurl");
        a.setNombre("omega");
        assertEquals("omega", a.getNombre());
    }

    @Test
    public void testSetLetras(){
        Alfabeto a = new Alfabeto("beta", "sdiurl");
        a.setLetras("help");
        assertEquals("help", a.getLetras());
    }
}

//Mariona
