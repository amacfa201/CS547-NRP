import org.opt4j.core.problem.Decoder;
import org.opt4j.core.genotype.BooleanGenotype;

public class MyDecoder implements Decoder<BooleanGenotype, String>{
    public String decode(BooleanGenotype genotype) {
        String phenotype = "";
        for (int i = 0; i < genotype.size(); i++) {
            if(genotype.get(i) == true)
                phenotype += "1";
            else
                phenotype += "0";
        }
        return phenotype;
    }
}
