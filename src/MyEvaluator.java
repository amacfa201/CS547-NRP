import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;
import org.opt4j.core.start.Constant;
import com.google.inject.Inject;

import java.util.List;
import java.util.ArrayList;


public class MyEvaluator implements Evaluator<String> {

    private FileReader reader;
    private List<Requirement> reqList;
    private double costRatio;

    @Inject
    public MyEvaluator(@Constant(value = "fileName") String file,
                        @Constant(value = "costRatio") double costRatio) {

        reader = new FileReader("res/" + file);
        System.out.println(file + " Loaded");

        this.costRatio = costRatio;
    }

    @Override
    public Objectives evaluate(String phenotype) {
        Objectives objectives = new Objectives();

        int maxRequirementSize = reader.getRequirements().size();
        if (phenotype.length() > maxRequirementSize) {
            phenotype = phenotype.substring(0, maxRequirementSize);
        }

        reqList = new ArrayList<Requirement>();
        List<Requirement> requirements = reader.getRequirements();

        for (int i = 0; i < phenotype.length() - 1; i++) {
            if (phenotype.charAt(i) == '1') {
                reqList.add(requirements.get(i));
            }
        }


        //Multi obj
        //objectives.add("Cost", Sign.MIN, cost(phenotype));
        //objectives.add("Score", Sign.MAX, score(phenotype));


        //Cost Single
		objectives.add("Cost", Sign.MIN, cost(phenotype));
		objectives.add("Score", null, score(phenotype));

        //Score single
		//objectives.add("Cost", null, cost(phenotype));
		//objectives.add("Score", Sign.MAX, score(phenotype));


        return objectives;
    }

    private double cost(String phenotype) {
        double cost = 0;

        for (Customer c : reader.getCustomers()) {
            for (Requirement r : reqList) {
                for (int i : c.getRequirements()) {
                    if (r.getId() == i)
                        cost += r.getCost();
                }
            }
        }

        return cost * (reader.getBudget() * costRatio);
    }

    public double score(String phenotype) {
        double score = 0;

        for (int i = 0; i < phenotype.length(); i++) {
            if (phenotype.charAt(i) == '1') {
                score += generateScore(i);

            }
        }

        return score;
    }

    public double generateScore(int counter) {
        double score = 0;

        for (Customer customer : reader.getCustomers()) {
            if (customer.getRequirements().contains(counter)) {
                score += customer.getWeight();
            }
        }

        return score;
    }
}
