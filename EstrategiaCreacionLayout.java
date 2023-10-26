

import java.util.*;
import javafx.util.Pair;

public interface EstrategiaCreacionLayout {
    
    
    public Vector<Integer> crearLayout(
        Vector<Vector<Double>> distLoc,
        Vector<Vector<Double>> traficoInst);
}