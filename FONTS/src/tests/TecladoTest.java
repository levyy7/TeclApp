package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import main.dominio.Teclado;
import java.awt.geom.Point2D;
import java.beans.Transient;
import java.awt.Point;

import main.dominio.Playout;

//no puedo compilarla porque no tengo el make en windows
//tampoco las librerias incluidas porque por algun motivo no me las acaba de reconocer xdddd

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
    void testGetLayout() {
         Assert.assertNull(teclado.getAlgoritmo()); //Assumiendo que el layout inicialmente es null
    }

    @Test 
    void testGetSizeLayout(){
        char[] lay = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        teclado.setLayout(lay);
        Assert.assertEquals(lay, teclado.getLayout());
    }

    @Test 
    void testGetSizeLayout(){
        char[] lay = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        teclado.setLayout(lay);
        Assert.assertEquals(10, teclado.getSizeLayout());
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
}
