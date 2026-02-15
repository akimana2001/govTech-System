package Services;

public abstract class GovernmentService {

    protected String serviceName;
    protected double serviceFee;

    public GovernmentService(String serviceName, double serviceFee) {
        this.serviceName = serviceName;
        this.serviceFee = serviceFee;
    }

    public abstract double calculateFee();

    public String getServiceName() {
        return serviceName;
    }

    public double getServiceFee() {
        return serviceFee;
    }
}
