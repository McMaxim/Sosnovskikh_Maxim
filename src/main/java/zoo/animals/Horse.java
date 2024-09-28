package animals;

import creation.Animal;
import interfaces.Herbivore;
import interfaces.Terrestrial;


public class Horse extends Animal implements Terrestrial, Herbivore {
  public Horse(String name) {
    super(name);
  }

  @Override
  public void walk() {
    System.out.println(name + " идет");
  }

  @Override
  public void eatGrass() {
    System.out.println(name + " ест траву");
  }
}