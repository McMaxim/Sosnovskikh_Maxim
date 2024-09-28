package animals;
import creation.Animal;
import interfaces.Aquatic;
import interfaces.Carnivore;

public class Dolphin extends Animal implements Aquatic, Carnivore {
  public Dolphin(String name) {
    super(name);
  }

  @Override
  public void swim() {
    System.out.println(name + " плывет");
  }

  @Override
  public void eatMeat(String meat) {
    if (meat.equals("рыба")) {
      System.out.println(name + " ест рыбу");
    } else {
      System.out.println(name + " не ест " + meat);
    }
  }
}