package main;

import ru.nsu.MyMock;

public class Main {
    public static void main(String[] args){
        Cat obj = MyMock.mock(new Cat(Cat.Trait.kind), Cat.class);
        String response = obj.PetTheCat();
        System.out.println(response); //null

        MyMock.setReturn(obj.PetTheCat(), "meow");
        response = obj.PetTheCat();
        System.out.println(response); //meow

        MyMock.setReturn(obj.PetTheCat(), "rrr");
        response = obj.PetTheCat();
        System.out.println(response);


        response = Cat.GiveBox(Cat.Box.big);
        System.out.println(response);
        MyMock.setReturn(Cat.GiveBox(Cat.Box.big), "meow");
        response = Cat.GiveBox(Cat.Box.big);
        System.out.println(response);


    }
}
