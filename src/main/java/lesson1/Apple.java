package lesson1;

public class Apple extends Fruit {

    public Apple(String name, double weight) {
        super(name, weight);
    }

    public static class Main {
        public static void main(String[] args) {
            BoxWithFruits<Orange> box1 = new BoxWithFruits<Orange>(
                    new Orange("Апельсин 1", 1.1f),
                    new Orange("Апельсин 2", 1.6f),
                    new Orange("Апельсин 3", 2.1f)
            );

            System.out.println("Вес коробки 1 с апельсинами " + box1.getWeight());
            box1.printInfo();

            BoxWithFruits<Apple> box2 = new BoxWithFruits<Apple>(
                    new Apple("Яблоко 1", 2.1f),
                    new Apple("Яблоко 2", 2.6f),
                    new Apple("Яблоко 3", 2.1f)
            );

            System.out.println("Вес коробки 2 с яблоками " + box2.getWeight());
            box2.printInfo();

            System.out.println("Равны ли веса коробок? " + box1.compare(box2));


            BoxWithFruits<Apple> box3 = new BoxWithFruits<Apple>(
                    new Apple("Яблоко 4", 2.6f),
                    new Apple("Яблоко 5", 2.7f)
            );
            System.out.println("Вес коробки 3 с яблоками " + box3.getWeight());
            box3.printInfo();

            System.out.println("Пересыпали яблоки из коробки 2 в коробку 3");
            box2.putInto(box3);

            System.out.println("Вес коробки 2 с яблоками " + box2.getWeight());
            box2.printInfo();
            System.out.println("Вес коробки 3 с яблоками " + box3.getWeight());
            box3.printInfo();
        }
    }
}