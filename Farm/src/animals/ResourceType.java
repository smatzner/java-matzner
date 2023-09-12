package animals;

public enum ResourceType {
    WOOL("Wolle"),
    MILK("Milch"),
    EGGS("Eier"),
    BACON("Speck");

    private String resourceType;

    ResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }
}
