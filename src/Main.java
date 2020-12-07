import org.opt4j.core.genotype.BooleanGenotype;

public class Main {

    public static void main(String[] args) throws Exception {
        MyCreator creator = new MyCreator(1000);
        MyDecoder decoder = new MyDecoder();
        MyEvaluator evaluator = new MyEvaluator("nrp1.txt", 0.4);

        BooleanGenotype genotype = creator.create();
        String phenotype = decoder.decode(genotype);
        evaluator.evaluate(phenotype);
    }
}
