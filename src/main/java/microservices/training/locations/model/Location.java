package microservices.training.locations.model;

public class Location {

    private Long id;
    private String name;
    private double lat;
    private double lon;

    public Location(Long id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public Location() {
    }

    @Override
    public String toString() {
        return "Location {" +
                " name=" + name +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
