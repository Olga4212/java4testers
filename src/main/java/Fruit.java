public class Fruit {
    String name;
    double weight;

    public Fruit (String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public double getWeight()
    {
        return this.weight;
    }

    public void printInfo() {
        System.out.println("Фрукт: " + name + " Вес: " + this.weight);
    }
}