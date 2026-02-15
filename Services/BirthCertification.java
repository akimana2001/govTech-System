package Services;

public class BirthCertification extends GovernmentService {

    public BirthCertification() {
        super("Birth Certificate", 500);
    }

    @Override
    public double calculateFee() {
        return serviceFee;
    }
}
