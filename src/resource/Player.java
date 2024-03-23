package resource;

import java.util.HashMap;

public class Player {
    private String name;
    private int victoryPoints;
    private int roadsRemaining;
    private int settlementsRemaining;
    private int citiesRemaining;
    private HashMap<ResourceType, Integer> resources;
    public Player(String name) {
        this.name = name;
        this.victoryPoints = 0;
        this.roadsRemaining = 0;
        this.settlementsRemaining = 0;
        this.citiesRemaining = 0;
        this.resources = new HashMap<ResourceType, Integer>();
        // Initialize resources to zero
        for (ResourceType resource: ResourceType.values()) this.resources.put(resource, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getRoadsRemaining() {
        return roadsRemaining;
    }

    public void setRoadsRemaining(int roadsRemaining) {
        this.roadsRemaining = roadsRemaining;
    }

    public int getSettlementsRemaining() {
        return settlementsRemaining;
    }

    public void setSettlementsRemaining(int settlementsRemaining) {
        this.settlementsRemaining = settlementsRemaining;
    }

    public int getCitiesRemaining() {
        return citiesRemaining;
    }

    public void setCitiesRemaining(int citiesRemaining) {
        this.citiesRemaining = citiesRemaining;
    }

    public HashMap<ResourceType, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<ResourceType, Integer> resources) {
        this.resources = resources;
    }

    // Methods for resource management

    public int getResource(ResourceType resourceType) {
        return this.resources.get(resourceType);
    }

    public void addResource(ResourceType resourceType, int amount) {
        resources.put(resourceType, this.resources.get(resourceType) + amount);
    }

    public void removeResource(ResourceType resourceType, int amount) {
        int currentAmount = this.resources.get(resourceType);

        if (currentAmount >= amount) this.resources.put(resourceType, currentAmount - amount);
        else System.out.println("The amount of " + resourceType.toString() + " is not enough.");
    }

    public boolean hasRemainingBuilding() {
        return roadsRemaining > 0 || settlementsRemaining > 0 || citiesRemaining > 0;
    }

}
