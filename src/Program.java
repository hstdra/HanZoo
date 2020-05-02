import java.util.List;

public class Program {
    private static final Zoo zoo = new Zoo();

    public static void main(String[] args) {
        while (true) {
            System.out.println("======= ZOO Program =======");
            System.out.println("1.\tView zoo information");
            System.out.println("2.\tView all animals");
            System.out.println("3.\tSort all animals");
            System.out.println("4.\tFilter animals");
            System.out.println("5.\tAdd an animal");
            System.out.println("6.\tView an animal");
            System.out.println("7.\tEdit an animal");
            System.out.println("8.\tDelete an animal");
            System.out.println("9.\tExit");
            System.out.println("==========================");

            switch (Helper.getInt("Enter your choice: ", 1, 9)) {
                case 1:
                    viewZooInformation();
                    break;
                case 2:
                    viewAnimals(zoo.getAnimals());
                    break;
                case 3:
                    sortAnimals();
                    break;
                case 4:
                    filterAnimals();
                    break;
                case 5:
                    addAnimal();
                    break;
                case 6:
                    viewAnAnimal();
                    break;
                case 7:
                    editAnimal();
                    break;
                case 8:
                    deleteAnimal();
                    break;
                case 9:
                    zoo.writeToFile();
                    return;
            }
            Helper.waitEnter();
        }
    }

    private static void printAnimalInfo(Animal animal) {
        System.out.printf("%4s %12s %10s %8s %10s\n", animal.getId(), animal.getClass().getSimpleName(), animal.getName(), animal.getAge(), "$" + animal.getCost());
    }

    private static void viewAnimals(List<Animal> animals) {
        System.out.println("================================================");
        System.out.printf("%4s %12s %10s %8s %10s\n", "Id", "Type", "Name", "Age", "Cost");
        System.out.println("================================================");
        animals.forEach(Program::printAnimalInfo);
        System.out.println("================================================");
    }

    private static void viewZooInformation() {
        List<Animal> foxs = zoo.getAnimalsByClass(Fox.class);
        List<Animal> lions = zoo.getAnimalsByClass(Lion.class);
        List<Animal> elephants = zoo.getAnimalsByClass(Elephant.class);

        System.out.println("======================================");
        System.out.printf("%10s %12s %12s\n", "Animal", "Number", "Cost");
        System.out.println("======================================");

        System.out.printf("%10s %12s %12s\n", "Lion", lions.size(), "$" + Zoo.getAnimalsTotalCost(lions));
        System.out.printf("%10s %12s %12s\n", "Fox", foxs.size(), "$" + Zoo.getAnimalsTotalCost(foxs));
        System.out.printf("%10s %12s %12s\n", "Elephant", elephants.size(), "$" + Zoo.getAnimalsTotalCost(elephants));

        System.out.println("======================================");
        System.out.printf("%10s %12s %12s\n", "Total", zoo.getAnimals().size(), "$" + Zoo.getAnimalsTotalCost(zoo.getAnimals()));
        System.out.println("======================================");
    }

    public static void sortAnimals() {
        System.out.println("Sort successfully!");
        zoo.sortAnimals();
        viewAnimals(zoo.getAnimals());
    }

    public static void filterAnimals() {
        Class<?> c = Animal.getAnimalClass();
        String name = Helper.getStringAcceptNull("Input name: ");
        int[] ageRange = Helper.getRange("Input age range: ");
        int[] costRange = Helper.getRange("Input cost range: ");

        viewAnimals(zoo.filterAnimals(c, name, ageRange, costRange));
    }

    private static void addAnimal() {
        while (true) {
            switch (Animal.getAnimalClass().getSimpleName()) {
                case "Lion":
                    zoo.addAnimal(new Lion());
                    return;

                case "Elephant":
                    zoo.addAnimal(new Elephant());
                    return;

                case "Fox":
                    zoo.addAnimal(new Fox());
                    return;
            }
        }
    }

    private static void viewAnAnimal() {
        String id = Helper.getString("Input id: ");
        Animal animal = zoo.findAnimalById(id);

        if (animal != null) {
            System.out.println(animal);
        }
    }

    private static void editAnimal() {
        String id = Helper.getString("Input id: ");
        Animal animal = zoo.findAnimalById(id);

        if (animal != null) {
            animal.update();
            System.out.println("Edit animal successfully");
        }
    }

    private static void deleteAnimal() {
        String id = Helper.getString("Input id: ");
        Animal animal = zoo.findAnimalById(id);

        if (animal != null) {
            zoo.getAnimals().remove(animal);
            System.out.println("Remove animal successfully");
        }
    }
}
