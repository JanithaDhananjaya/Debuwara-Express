package sample.model;

public class TableModel {
    private String name;
   private  Double time;

    public TableModel() {
    }

    public TableModel(String name, Double time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
