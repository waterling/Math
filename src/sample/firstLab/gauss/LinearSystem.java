package sample.firstLab.gauss;

import java.util.ArrayList;
import java.util.List;

public class LinearSystem<N extends Number, T extends Gauss<N, T>> {
    private List<T> defaultList = new ArrayList<>();
    private List<T> list = new ArrayList<>();

    public T get(int index) {
        return list.get(index);
    }

    public void push(T elem) {
        list.add(elem);
    }

    public int size() {
        return list.size();
    }

    public N itemAt(int i, int j) {
        return list.get(i).at(j);
    }

    public List<T> getDefaultList() {
        return defaultList;
    }

    public void setDefaultList() {
        this.defaultList =this.list;
    }
}