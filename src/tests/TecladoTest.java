package tests;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import main.dominio.Teclado;
import java.awt.geom.Point2D;
import java.awt.Point;

import main.dominio.Playout;

class TecladoTest {

    private Teclado teclado;

    @Before
    void setUp() {
        teclado = new Teclado("Prova", "alfabeto", 8);
    }

    @Test
    void testGetNombre() {
        Assert.assertEquals("Prova", teclado.getNombre());
    }   

    @Test
    void testGetAlgoritmo() {
        Assert.assertNull(teclado.getAlgoritmo()); // Asumiendo que el algoritmo es inicializado como null
    }

    @Test
    void testGetAlfabeto() {
        Assert.assertEquals("alfabeto", teclado.getAlfabeto());
    }

    @Test
    void testSetNombre() {
        teclado.setNombre("newname");
        Assert.assertEquals("newname", teclado.getNombre());
    }

    @Test
    void testSetAlgoritmo() {
        teclado.setAlgoritmo("newalgo");
        Assert.assertEquals("newalgo", teclado.getAlgoritmo());
    }

    @Test
    void testSetAlfabeto() {
        teclado.setAlfabeto("newalph");
        Assert.assertEquals("newalph", teclado.getAlfabeto());
    }

    @Test
    void testGetPlayout() {
        Point2D[] mockPlayout = new Point2D[]{new Point2D.Double(1.0, 2.0), new Point2D.Double(3.0, 4.0)};
        Playout mockPlayoutObj = mock(Playout.class);
        
        when(mockPlayoutObj.getTeclas()).thenReturn(mockPlayout);
        
        teclado = new Teclado("Prova", "alfabeto", 10);
        teclado.setPlayout(mockPlayoutObj);
        
        assertArrayEquals(mockPlayout, teclado.getPlayout());
        verify(mockPlayoutObj, times(1)).getTeclas(); // Verifica que se llamó al método getTeclas
    }

    @Test
    void testSetLayoutAndModify() {
        char[] nuevoLayout = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};

        teclado.setLayout(nuevoLayout);
        Assert.assertArrayEquals(nuevoLayout, teclado.getLayout());

        char[] otroLayout = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        teclado.modify(otroLayout);
        Assert.assertArrayEquals(otroLayout, teclado.getLayout());
    }
}
