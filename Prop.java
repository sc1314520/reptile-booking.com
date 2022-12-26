package Jsoup.practice;

public class Prop {
    private String name;
    private String cost;

    public Prop(){

    }
    public Prop(String name, String cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Prop{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
