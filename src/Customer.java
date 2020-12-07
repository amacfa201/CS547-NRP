import java.util.List;

public class Customer {

        private double weight;
        private List<Integer> requirements;

        public Customer(double weight, List<Integer> requirements) {
                this.weight = weight;
                this.requirements = requirements;
        }



        //Getters
        public double getWeight() {
                return weight;
        }
        public List<Integer> getRequirements() {
                return requirements;
        }

        //Setters
        public void setWeight(double weight) {
                this.weight = weight;
        }

}
