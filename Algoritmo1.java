

import java.util.*;

public class Algoritmo1 implements EstrategiaCreacionLayout {
    
    
    public Vector<Integer> crearLayout(
        Vector<Vector<Double>> distLoc,
        Vector<Vector<Double>> traficoInst) {

        branchandbound();
        
    }

    private vector<int> branchandbound() {
        pair<double, vector<int>> bestSol = greedySolution();
        
        PriorityQueue<pair<double, vector<int>>> q; //Seguramente priority_queue
        q.add({0,[]});

        while (q.size() != 0) {
            pair<double, vector<int>> partialSol = q.poll(); 

            //Solution
            if (partialSol.value.size() == n and partialSol.key < bestSol.key) bestSol = partialSol;
            //Branch&Bound
            else {
                vector<vector<double>> bounds = generateGLBounds();

                //Produce new n - partialSol.value.size() possible solutions
                for (int i = 0; i < z; ++i) {
                    pair<double, vector<int>> possibleSol = //
                    if (bounds[i] < bestSol.key) q.add(possibleSol); 
                }
            }
        }
    }
}