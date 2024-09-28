package org.example;

import animals.*;

public class Main {
  public static void main(String[] args) {
    Horse horse = new Horse("Лошадь");
    Tiger tiger = new Tiger("Тигр");
    Dolphin dolphin = new Dolphin("Дельфин");
    Eagle eagle = new Eagle("Орел");
    Camel camel = new Camel("Верблюд");

    horse.walk();
    horse.eatGrass();

    tiger.walk();
    tiger.eatMeat("говядина");
    tiger.eatMeat("рыба");

    dolphin.swim();
    dolphin.eatMeat("рыба");

    eagle.fly();
    eagle.eatMeat("говядина");

    camel.walk();
    camel.eatGrass();
  }
}
