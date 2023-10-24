

import java.util.*;

public interface EstrategiaCreacionLayout {
    
    
    public vector< pair< pair<double,double>, char>> crearLayout(
        vector<pair<double, double>> coorLoc, 
        vector<vector<double>> distLoc,
        vector<char> charInst,
        vector<vector<double>> traficoInst);
}