package Services;

public class DrivingTestService extends GovernmentService {

    public DrivingTestService() {
        super("Driving Test", 5000);
    }

    @Override
    public double calculateFee() {
        return serviceFee;
    }
}
