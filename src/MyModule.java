import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;




public class MyModule extends ProblemModule {

    @Constant(value = "population")
    private int population = 1000;
    @Constant(value = "fileName")
    private String fileName = "nrp1.txt";
    @Constant(value = "costRatio")
    private double costRatio = 0.4;


    protected void config() {
        bindProblem(MyCreator.class, MyDecoder.class, MyEvaluator.class);
    }





    //Needed to change values in OPT4J interface
    //Getters
    public int getPopulation() {
        return population;
    }
    public String getFileName() {
        return fileName;
    }
    public double getCostRatio() {
        return costRatio;
    }

    //Setters
    public void setPopulation(int population) {
        this.population = population;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setCostRatio(double costRatio) {
        this.costRatio = costRatio;
    }

}
