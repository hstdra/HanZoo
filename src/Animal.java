import java.io.Serializable;

public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String name;
    protected int age;
    protected int cost;

    public Animal() {
        initData();
    }

    public static Class<?> getAnimalClass() {
        while (true) {
            String type = Helper.getString("Input type(Animal/Lion/Elephant/Fox): ");

            switch (type.toLowerCase()) {
                case "animal":
                    return Animal.class;

                case "lion":
                    return Lion.class;

                case "elephant":
                    return Elephant.class;

                case "fox":
                    return Fox.class;
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCost() {
        return cost;
    }

    public void update() {
        initData();
    }

    private void initData() {
        name = Helper.getString("Input name: ");
        age = Helper.getInt("Input age: ");
        cost = Helper.getInt("Input cost: ");
    }

    @Override
    public String toString() {
        return String.format("%s[Id=”%s” Name=”%s”, Age=%d, Cost=$%d]", getClass().getSimpleName(), id, name, age, cost);
    }
}
