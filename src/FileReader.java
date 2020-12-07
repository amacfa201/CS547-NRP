import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader {
    private String filePath;
    private int lineNum;
    private List<String> lines = new ArrayList<String>();
    private double budget;
    private List<Customer> customers;
    private List<Requirement> requirements;
    private double totalWeight;

    @Inject
    public FileReader(String file) {
        this.filePath = file;

        requirements = new ArrayList<Requirement>();
        customers = new ArrayList<Customer>();

        String line = "";


        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(file);
        if (stream == null) {
            System.out.println("Error");
            return;
        }

        try {
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            streamReader.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


        int requirementLevel = Integer.parseInt(lines.get(0));
        lineNum = 2;
        int counter = 0;

        for (int i = 0; i < requirementLevel; i++) {
            counter = calcReq(counter);
        }

        int dependencies = Integer.parseInt(lines.get(lineNum - 1));
        lineNum += dependencies;


        totalWeight = 0;
        int numCustomers = Integer.parseInt(lines.get(lineNum));
        for (int i = 0; i < numCustomers; i++) {
            lineNum++;
            generateCust(i + 1);
        }

        for (Customer c : customers) {
            double custWeight = c.getWeight() / totalWeight;
            c.setWeight(custWeight);
        }

    }

    private int calcReq(int counter) {
        String[] costList = lines.get(lineNum).split(" ");
        for (String cost : costList) {
            int temp = Integer.parseInt(cost);
            budget += temp;
            counter++;
            requirements.add(new Requirement(counter, temp));
        }
        lineNum += 2;
        return counter;
    }

    private void generateCust(int num) {
        String[] singleLines = lines.get(lineNum).split(" ");

        int weight = Integer.parseInt(singleLines[0]);
        int numOfReq = Integer.parseInt(singleLines[1]);

        List<Integer> reqList = new ArrayList<Integer>();
        int counter = 2;

        for (int i = 0; i < numOfReq; i++) {
            int id = Integer.parseInt(singleLines[counter++]);
            if (requirements.size() > id) {
                reqList.add(id);
            }
        }

        customers.add(new Customer(weight, reqList));
        totalWeight += weight;

    }

    //Getters
    public List<Requirement> getRequirements() {
        return requirements;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public double getBudget() {
        return budget;
    }




}
