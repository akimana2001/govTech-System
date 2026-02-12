public class BirthCertification extends GovernmentService {

    public BirthCertification(){
        super("Birthday Certificate",  500);
    }

    @Override

    public double calculateFee(){
        return serviceFee;
    }
}
