class Collar {
}

public class Dog {
    Collar c;
    String name;

    public static void main(String[] args) {

        Dog d;                              //local variable
        d = new Dog();
        d.go(d);
    }

    void go(Dog dog) {                      //local variable
        c = new Collar();
        dog.setName("Aiko");
    }

    void setName(String dogName) {         //local variable
        name = dogName;
        //do stuff
    }
}

