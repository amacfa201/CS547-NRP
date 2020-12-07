import java.util.Random;
import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

public class MyCreator implements Creator<BooleanGenotype> {
    Random random = new Random();
    int population;

    @Inject
    public MyCreator(@Constant(value = "population") int population) {
        this.population = population;
    }

    public BooleanGenotype create() {
        BooleanGenotype genotype = new BooleanGenotype();
        genotype.init(random, population);
        return genotype;
    }
}
