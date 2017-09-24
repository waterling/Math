package sample.firstLab;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.firstLab.gauss.Equation;
import sample.firstLab.gauss.GaussMethod;
import sample.firstLab.gauss.LinearSystem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Matrix {
    private GridPane matrix = new GridPane();
    private LinearSystem<Float, Equation> system = new LinearSystem<>();
    private int size;

    public GridPane getMatrix() {
        return matrix;
    }
    public void clear(){
        system = new LinearSystem<>();
//        matrix = new GridPane();
    }
    public void setMatrix(GridPane matrix) {
        this.matrix = matrix;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        matrix = new GridPane();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                TextField textField = new TextField();
                textField.setPrefSize(45, -1);
                matrix.add(textField, j, i);
            }
        }
        this.size = size;
    }
    public void fillTextField () {
        for(Node textField : matrix.getChildren()){
            ((TextField) textField).setText(String.valueOf(system.itemAt(GridPane.getRowIndex(textField),GridPane.getColumnIndex(textField))));
        }
    }

    public void setList() {
        system = new LinearSystem<>();
        for (int i = 0; i < size; i++) {
            Equation equation = new Equation();
            for (int j = 0; j < size + 1; j++) {
                for (Node textField : matrix.getChildren()) {
                    if (GridPane.getColumnIndex(textField) == j && GridPane.getRowIndex(textField) == i) {
                        System.out.print(Float.parseFloat(((TextField) textField).getText())+"  ");
                        equation.getEquation().add(Float.parseFloat(((TextField) textField).getText()));
                        break;
                    }
                }
            }
            System.out.print("\n");
            system.push(equation);
        }
        system.setDefaultList();
    }
    public void setList (LinearSystem<Float, Equation> system){
        this.system = new LinearSystem<>();
        this.system=system;
        system.setDefaultList();
    }
    public void setList (String filename){
        List<String> lines = new ArrayList<>();
        try {
            Files.lines(Paths.get(filename), StandardCharsets.UTF_8).forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator listIterator = lines.listIterator();
        system = new LinearSystem<>();
        while (listIterator.hasNext()){
            String string = (String) listIterator.next();
            String [] arrayOfFloat =  string.split(" ");
            size = arrayOfFloat.length - 1;
            Equation equation = new Equation();
            for (int i = 0; i < size+1; i++) {
                System.out.print(Float.parseFloat(arrayOfFloat[i])+"  ");
                equation.getEquation().add(Float.parseFloat(arrayOfFloat[i]));
            }
            System.out.print("\n");
            system.push(equation);
        }
        system.setDefaultList();


    }
    Float[] x = new Float[size];
    float determinant=1;
    public void calculate() {
//        system = new LinearSystem<>();
//        setList();
//        system=generateSystem();
//        printSystem(system);
        GaussMethod<Float, Equation> gaussMethod = new GaussMethod<>(system);
        gaussMethod.calculate();
        x = new Float[size];
        determinant=1;
        int i, j;
        for (i = system.size() - 1; i >= 0; i--) {
            Float sum = 0.0f;
            determinant*=system.itemAt(i,i);
            for (j = system.size() - 1; j > i; j--) {
                sum += system.itemAt(i, j) * x[j];
            }
            x[i] = (system.itemAt(i, system.size()) - sum) / system.itemAt(i, j);
        }
        for (Float k:gaussMethod.getCoefficients()){
            determinant/=k;
        }
    }

    public float getDeterminant() {
        return determinant;
    }

    public String printSystem() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < system.size(); i++) {
            Equation temp = system.get(i);
            for (int j = 0; j < temp.size(); j++) {
                s.append(String.format("%f %s", system.itemAt(i, j), "\t"));
            }
            /*Iterator iterator = system.getList().listIterator();
            while (iterator.hasNext()){
                System.out.println(((Equation) iterator.next()).getEquation().toString());
            }*/
            s.append("\n");
        }
        return s.toString();
    }

    public String printResult() {
        String s = "";
        for (int i = 0; i < x.length; i++) {
            s += String.format("x%d = %f ", i+1, x[i]);
        }
        return s;
    }
    public LinearSystem<Float, Equation> generateSystem(){
        LinearSystem<Float, Equation> list = new LinearSystem<>();
        int i;
        for (i = 0; i < size; i++){
            Equation eq = new Equation();
            eq.generate(size + 1);
            list.push(eq);
        }
        return list;
    }
    public String printResiduals(){
        float residual;
        float sum=0;
        StringBuilder stringBuilder = new StringBuilder();
        List<Equation> equationList = system.getDefaultList();
        Iterator equationListIterator = equationList.listIterator();
        while (equationListIterator.hasNext()){
            Equation eq = (Equation) equationListIterator.next();
            sum=0;
            for (int i = 0; i < size; i++) {
                sum+=eq.at(i)*x[i];
            }
            residual=sum-eq.at(size);
            stringBuilder.append(residual).append("    ");
        }

        return stringBuilder.toString();
    }

}
