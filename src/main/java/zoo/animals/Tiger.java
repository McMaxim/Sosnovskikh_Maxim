package animals;

import creation.Animal;
import interfaces.Terrestrial;
import interfaces.Carnivore;

public class Tiger extends Animal implements Terrestrial, Carnivore {
  public Tiger(String name) {
    super(name);
  }

  @Override
  public void walk() {
    System.out.println(name + " идет");
  }

  @Override
  public void eatMeat(String meat) {
    if (meat.equals("говядина")) {
      System.out.println(name + " ест говядину");
    } else {
      System.out.println(name + " не ест " + meat);
    }
  }
}