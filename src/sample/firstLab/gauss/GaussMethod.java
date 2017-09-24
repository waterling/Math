package sample.firstLab.gauss;

import java.util.ArrayList;
import java.util.List;

public class GaussMethod<N extends Number, T extends Gauss<N, T>> {
    private LinearSystem<N, T> list = null;
    private List<N> coefficients = new ArrayList<>();

    public GaussMethod(LinearSystem<N, T> system) {
        list = system;
    }

    public List<N> getCoefficients() {
        return coefficients;
    }

    public void calculate() throws NullPointerException, ArithmeticException {
//        if (list == null) {
//            throw new NullPointerException("LinearSystem<N, T> instance equal null");
//        }
//        if (!checkSystem(list)) {
//            throw new ArithmeticException("Incorrect system for Gauss Method");
//        }
        coefficients = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                N k = list.get(j).findCoefficient(list.get(j).at(i), list.get(i).at(i));
                coefficients.add(k);
                list.get(j).mul(k);
                list.get(j).addEquation(list.get(i));
            }
        }
    }

    private boolean checkSystem(LinearSystem<N, T> system) {
        if (system.size() < 2) return false;
        for (int i = 0; i < system.size(); i++) {
            if (system.get(i).size() != (system.size() + 1)) {
                return false;
            }
        }
        return true;
    }
}