package tests;
import main.dominio.Texto;

//import junit.*;
import static org.junit.Assert.*;
import org.junit.*;
/*
import org.hamcrest.*;
import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import org.mockito.*;
import org.objenesis.*;*/

public class TextoTest {
    @Test
    public void testCreadoraAndGetters(){
        Texto t = new Texto("brocoli", "El boli es vermell");
        
        assertEquals("brocoli", t.getNombre());
        assertEquals("El boli es vermell", t.getTexto());
        assertEquals("Texto", t.getType());
        assertEquals(18, t.getSize());
    }

    @Test
    public void testImportInputValidType(){
        Texto t = new Texto("brocoli", "El boli es vermell");
        t.importInput("mongeta", "Blau es el boli", "texto");

        assertEquals("mongeta", t.getNombre());
        assertEquals("Blau es el boli", t.getTexto());

    }

    @Test
    public void testImportInputInvalidType(){
        Texto t = new Texto("brocoli", "El boli es vermell");
        t.importInput("mongeta", "Blau es el boli", "altre");

        assertEquals("brocoli", t.getNombre());
        assertEquals("El boli es vermell", t.getTexto());

    }

    @Test
    public void testSetTexto(){
        Texto t = new Texto("brocoli", "El boli es vermell");
        t.setTexto("no m'agrada el brocoli");

        assertEquals("no m'agrada el brocoli", t.getTexto());
    }

    @Test
    public void testSetNombre(){
        Texto t = new Texto("brocoli", "El boli es vermell");
        t.setNombre("mongeta");

        assertEquals("mongeta", t.getNombre());
    }
}

//Mariona


