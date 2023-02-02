package lk.ijse.riceMil.to;

public class Cat extends Animal {
    private String Name;

    String getName() {
        return Name;
    }

    protected void setName(String name) {
        Name = name;
    }
}

class Animal{
    public void Run(){
        System.out.println("1");
    }
}

class Dog{
    public static void main(String[] args) {
        Cat cat=new Cat();
        cat.getName();

        Animal animal=new Cat();

        cat.Run();
    }
}