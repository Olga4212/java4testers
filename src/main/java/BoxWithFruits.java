import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxWithFruits <Obj extends Fruit> {
    private List<Obj> objects;

    public BoxWithFruits(Obj...newObjects) {
        this.objects = new ArrayList<Obj>();
        this.objects.addAll(Arrays.asList(newObjects));
    }

    public void add(Obj obj) {
        this.objects.add(obj);
    }

    public double getWeight(){
        double result = 0.0;
        for (Obj obj : objects){
            result += obj.getWeight();
        }
        return result;
    }

    public boolean compare (BoxWithFruits<?> other){
        return this.getWeight() == other.getWeight();
    }

    public void printInfo() {
        System.out.println("Коробка. Вес " + this.getWeight());
        for (Obj obj : this.objects) {
            obj.printInfo();
        }
        System.out.println();
    }

    public void putInto(BoxWithFruits<Obj> otherBox) {
        for (Obj obj : objects) {
            otherBox.add(obj);
        }
        objects.clear();
    }
}
