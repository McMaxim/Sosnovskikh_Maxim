package animals;

import creation.Animal;
import interfaces.Carnivore;
import interfaces.Flying;

public class Eagle extends Animal implements Flying, Carnivore {
  public Eagle(String name) {
    super(name);
  }

  @Override
  public void fly() {
    System.out.println(name + " летит");
  }

  @Override
  public void eatMeat(String meat) {
    System.out.println(name + " ест " + meat);
  }
}