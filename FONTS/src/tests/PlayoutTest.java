package tests;

import main.dominio.Playout;
import java.awt.geom.Point2D;

//import junit.*;
import static org.junit.Assert.*;
import org.junit.*;
/*
import org.hamcrest.*;
import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import org.mockito.*;
import org.objenesis.*;*/

public class PlayoutTest {
    @Test
    public void testPlayoutWithPerfectSquare() {
        Playout playout = new Playout(16); 

        Point2D[] teclas = playout.getTeclas();
        assertEquals(16, teclas.length);

        //comprobem algunes tecles amb les coordenades amb +-(0.0001) d'error

        assertEquals(0.0, teclas[0].getX(), 0.0001);
        assertEquals(0.0, teclas[0].getY(), 0.0001);

        assertEquals(2.0, teclas[9].getX(), 0.0001);
        assertEquals(1.0, teclas[9].getY(), 0.0001);

        assertEquals(3.0, teclas[15].getX(), 0.0001);
        assertEquals(3.0, teclas[15].getY(), 0.0001);
    }

    @Test
    public void testPlayoutWithNonPerfectSquare() {
        Playout playout = new Playout(15);

        Point2D[] teclas = playout.getTeclas();
        assertEquals(15, teclas.length);

        assertEquals(0.0, teclas[0].getX(), 0.0001);
        assertEquals(1.0, teclas[0].getY(), 0.0001);

        assertEquals(2.0, teclas[9].getX(), 0.0001);
        assertEquals(1.0, teclas[9].getY(), 0.0001);        

        assertEquals(3.0, teclas[12].getX(), 0.0001);
        assertEquals(0.0, teclas[12].getY(), 0.0001);
    }
}

//Mariona