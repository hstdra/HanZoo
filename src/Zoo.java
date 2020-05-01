import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Zoo {
    private List<Animal> animals;

    public Zoo() {
        readFromFile();
    }

    public static int getAnimalsTotalCost(List<Animal> animals) {
        return animals.stream().map(Animal::getCost).reduce(0, Integer::sum);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Animal> getAnimalsByClass(Class<?> c) {
        return animals.stream().filter(animal -> animal.getClass().equals(c)).collect(Collectors.toList());
    }

    public List<Animal> filterAnimals(Class<?> c, String name, int[] ageRange, int[] costRange) {
        return animals.stream()
                .filter(animal -> animal.getClass().equals(c) || c.equals(Animal.class))
                .filter(animal -> animal.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(animal -> animal.getAge() > ageRange[0] && animal.getAge() < ageRange[1])
                .filter(animal -> animal.getCost() > costRange[0] && animal.getCost() < costRange[1])
                .collect(Collectors.toList());
    }

    public Animal findAnimalById(String id) {
        Optional<Animal> animal = animals.stream().filter(a -> a.id.equalsIgnoreCase(id)).findAny();

        if (animal.isPresent()) {
            return animal.get();
        } else {
            System.out.println("Animal not found!");
            return null;
        }
    }

    public void addAnimal(Animal animal) {
        while (true) {
            animal.setId(Helper.generateRandomString());

            if (animals.stream().noneMatch(a -> a.getId().equals(animal.getId()))) {
                break;
            }
        }

        animals.add(animal);
    }

    public void sortAnimals() {
        Comparator<Animal> typeComparator = Comparator.comparing(animal -> animal.getClass().getSimpleName());
        Comparator<Animal> nameComparator = Comparator.comparing(Animal::getName);
        Comparator<Animal> ageComparator = Comparator.comparing(Animal::getAge);
        Comparator<Animal> costComparator = Comparator.comparing(Animal::getCost);

        Comparator<Animal> finalComparator = typeComparator
                .thenComparing(nameComparator)
                .thenComparing(ageComparator)
                .thenComparing(costComparator);

        animals.sort(finalComparator);
    }

    public void writeToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./Zoo.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(animals);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./Zoo.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            animals = (List<Animal>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
