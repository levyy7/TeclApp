package tests;
import main.persistencia.GestorCSV;
import java.util.*;

//import junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.hamcrest.*;
import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.objenesis.*;


/**
 * Clase GestorCSVTest
 * Clase encargada de testear los métodos de GestorCSV mediante JUnit
 * @author Eneko Sabaté
*/
@RunWith(value=MockitoJUnitRunner.class)
public class GestorCSVTest {
    
    /** Path relativa del archivo "Teclados.csv" */
	private static final String PATHTec = "../informacion/Teclados.csv";
	/** Path relativa del archivo "Alfabetos.csv" */
	private static final String PATHAlf = "../informacion/Alfabetos.csv";
	/** Path relativa del archivo "Textos.csv" */
	private static final String PATHTex = "../informacion/Textos.csv";
	/** Path relativa del archivo "Listas.csv" */
	private static final String PATHLis = "../informacion/Listas.csv";

    @Mock
    GestorCSV gCSVMock;

    @Test
    public void testGuardarInfoOW_v1() {
        String[] s1 = {"tec1", "QAP", "abcd", "cadb"};
        String[][] input = new String[][] {s1};


        GestorCSV gCSV = new GestorCSV();

        gCSV.guardarInfoOW(input, PATHTec);

        String output[][] = gCSV.cargarInfo(PATHTec);

        assertEquals(1, output.length);
        assertArrayEquals(input[0], output[0]);
    }

    @Test
    public void testGuardarInfoOW_v2() {
        String[] s1 = {"tec1", "QAP", "abcd", "cadb"};
        String[] s2 = {"tec2", "QAP", "abcde", "ecadb"};
        String[] s3 = {"tec3", "QAP", "abcdef", "cadbef"};
        String[] s4 = {"tec4", "QAP", "abcdefg", "cadefgb"};
        String[] s5 = {"tec5", "QAP", "abcdefgh", "cefghadb"};
        String[][] input = new String[][] {s1, s2, s3, s4, s5};


        GestorCSV gCSV = new GestorCSV();

        gCSV.guardarInfoOW(input, PATHTec);

        String output[][] = gCSV.cargarInfo(PATHTec);

        assertEquals(5, output.length);
        assertArrayEquals(input[0], output[0]);
        assertArrayEquals(input[1], output[1]);
        assertArrayEquals(input[2], output[2]);
        assertArrayEquals(input[3], output[3]);
        assertArrayEquals(input[4], output[4]);
    }

    @Test
    public void testGuardarInfo_v1() {
        String[] c = {"Data", "Before", "Store"};
        String[][] before = new String[][] {c};

        String[] s1 = {"tec1", "QAP", "abcd", "cadb"};
        String[][] input = new String[][] {s1};


        GestorCSV gCSV = new GestorCSV();
        gCSV.guardarInfoOW(before, PATHTec);
        String outputBefore[][] = gCSV.cargarInfo(PATHTec);
        assertEquals(1, outputBefore.length);
        assertArrayEquals(before[0], outputBefore[0]);


        gCSV.guardarInfo(input, PATHTec);
        String output[][] = gCSV.cargarInfo(PATHTec);
        assertArrayEquals(before[0], output[0]);
        assertArrayEquals(input[0], output[1]);
    }

    @Test
    public void testBorrarInfo_v1() {
        String[] primaryKeysToDelete = {"tec2", "tec4"};

        String[] s1 = {"tec1", "QAP", "abcd", "cadb"};
        String[] s2 = {"tec2", "QAP", "abcde", "ecadb"};
        String[] s3 = {"tec3", "QAP", "abcdef", "cadbef"};
        String[] s4 = {"tec4", "QAP", "abcdefg", "cadefgb"};
        String[] s5 = {"tec5", "QAP", "abcdefgh", "cefghadb"};
        String[][] input = new String[][] {s1, s2, s3, s4, s5};


        GestorCSV gCSV = new GestorCSV();
        gCSV.guardarInfoOW(input, PATHTec);
        String outputBefore[][] = gCSV.cargarInfo(PATHTec);
        assertEquals(5, outputBefore.length);
        assertArrayEquals(input[0], outputBefore[0]);
        assertArrayEquals(input[1], outputBefore[1]);
        assertArrayEquals(input[2], outputBefore[2]);
        assertArrayEquals(input[3], outputBefore[3]);
        assertArrayEquals(input[4], outputBefore[4]);


        gCSV.borrarInfo(primaryKeysToDelete, PATHTec);
        String output[][] = gCSV.cargarInfo(PATHTec);
        assertEquals(3, output.length);
        assertArrayEquals(s1, output[0]);
        assertArrayEquals(s3, output[1]);
        assertArrayEquals(s5, output[2]);
    }
    
    @Test
    public void testBorrarInfo_PrimaryKeysDontExist() {
        String[] primaryKeysToDelete = {"IDONTEXIST1", "IDONTEXIST2"};

        String[] s1 = {"tec1", "QAP", "abcd", "cadb"};
        String[] s2 = {"tec2", "QAP", "abcde", "ecadb"};
        String[] s3 = {"tec3", "QAP", "abcdef", "cadbef"};
        String[] s4 = {"tec4", "QAP", "abcdefg", "cadefgb"};
        String[] s5 = {"tec5", "QAP", "abcdefgh", "cefghadb"};
        String[][] input = new String[][] {s1, s2, s3, s4, s5};


        GestorCSV gCSV = new GestorCSV();
        gCSV.guardarInfoOW(input, PATHTec);
        String outputBefore[][] = gCSV.cargarInfo(PATHTec);
        assertEquals(5, outputBefore.length);
        assertArrayEquals(input[0], outputBefore[0]);
        assertArrayEquals(input[1], outputBefore[1]);
        assertArrayEquals(input[2], outputBefore[2]);
        assertArrayEquals(input[3], outputBefore[3]);
        assertArrayEquals(input[4], outputBefore[4]);


        gCSV.borrarInfo(primaryKeysToDelete, PATHTec);
        String output[][] = gCSV.cargarInfo(PATHTec);
        assertEquals(5, output.length);
        assertArrayEquals(s1, output[0]);
        assertArrayEquals(s2, output[1]);
        assertArrayEquals(s3, output[2]);
        assertArrayEquals(s4, output[3]);
        assertArrayEquals(s5, output[4]);
    }
}
