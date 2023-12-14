package tests;

import main.dominio.Teclado;

//import junit.*;
import static org.junit.Assert.*;
import org.junit.*;
/*
import org.hamcrest.*;
import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import org.mockito.*;
import org.objenesis.*;*/

//no puedo compilarla porque no tengo el make en windows
//tampoco las librerias incluidas porque por algun motivo no me las acaba de reconocer xdddd

class TecladoTest {

    @Test
    public void testConstructorAndGetters() {
        Teclado teclado = new Teclado("tec", "alfa", 16, "GEN");

        assertEquals("tec", teclado.getNombre());
        assertEquals("alfa", teclado.getAlfabeto());
        assertNull(teclado.getLayout()); // Inicialmente es null
        assertNotNull(teclado.getPlayout()); // Asegurarse de que el playout no sea nulo
        assertEquals("GEN", teclado.getAlgoritmo());
    }

    @Test
    public void testSetters() {
        Teclado teclado = new Teclado("tec", "alfa", 16, "QAP");

        teclado.setNombre("patata");
        assertEquals("patata", teclado.getNombre());

        teclado.setAlgoritmo("qapno");
        assertEquals("qapno", teclado.getAlgoritmo());

        teclado.setAlfabeto("beta");
        assertEquals("beta", teclado.getAlfabeto());

        char[] nuevoLayout = {'a', 'b', 'c'};
        teclado.setLayout(nuevoLayout);
        assertArrayEquals(nuevoLayout, teclado.getLayout());
    }
}

//Mariona