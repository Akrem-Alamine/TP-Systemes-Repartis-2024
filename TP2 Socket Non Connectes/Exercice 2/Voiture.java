import java.io.*;
class Voiture implements Serializable {
    private int carburant;
    private String model;
    private String type;
    private static int capacite = 300;
    Voiture(String type, String model) {
        this.type = type;
        this.model = model;
        carburant = 0;
    }
    public void setCarburant(int c) {
        int maxi = capacite - carburant;
        if (c < maxi) {
            carburant += c;
            System.out.println("Le remplissage a été effectué sans problème.");
        } else {
            carburant = capacite;
            System.out.println((c - maxi) + " litre(s) de carburant ont débordé.");
        }
    }
    public int getCarburant() {
        return carburant;
    }
    public int getCapacite() {
        return capacite;
    }

    public String getModel() {
        return model;
    }
}
