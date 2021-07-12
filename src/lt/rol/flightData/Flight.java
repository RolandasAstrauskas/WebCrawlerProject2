package lt.rol.flightData;

public class Flight {

    private String departureAndArrivalTime;
    private String flightFrom;
    private String flightTo;
    private String flightLastAirport;
    private String price;

    public Flight(String departureAndArrivalTime, String flightFrom, String flightTo, String flightLastAirport, String price) {
        this.departureAndArrivalTime = departureAndArrivalTime;
        this.flightFrom = flightFrom;
        this.flightTo = flightTo;
        this.flightLastAirport = flightLastAirport;
        this.price = price;
    }

    public String getDepartureAndArrivalTime() {
        return departureAndArrivalTime;
    }

    public void setDepartureAndArrivalTime(String departureAndArrivalTime) {
        this.departureAndArrivalTime = departureAndArrivalTime;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public String getFlightLastAirport() {
        return flightLastAirport;
    }

    public void setFlightLastAirport(String flightLastAirport) {
        this.flightLastAirport = flightLastAirport;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
