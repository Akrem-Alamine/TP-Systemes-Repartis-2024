import java.io.Serializable;

public class Personne implements Serializable {
    private int age;
    private String name;
    public Personne(int age , String name){
        this.age = age;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
}
