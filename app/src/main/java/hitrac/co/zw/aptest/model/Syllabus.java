package hitrac.co.zw.aptest.model;

public class Syllabus {
    private String id;
    private String name;

    public Syllabus(String id, String name) {
        this.id = id;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Syllabus{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
