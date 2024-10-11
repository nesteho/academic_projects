package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
// J'ai retiré 1ere ligne de known_problems.csv (num,difficulty,luck,code,validatorNos) CAR -> NumberFormatException.java
public class Problems {
    List<Problem> problems;
    public Problems() throws TuringGameException {
        problems = new ArrayList<>();
        initialzeProblems();
    }
    void initialzeProblems() throws  TuringGameException {
        String name = "/known_problems.csv";
        InputStream problemsFile =  Problems.class.getResourceAsStream(name);

        try ( var reader =  new BufferedReader( new InputStreamReader(problemsFile )    ) )
            {
                //  new BufferedReader() : lire ligne par ligne
                //   new InputStreamReader(problemsFile ) tt seul :lire  byte par byte
                var test =  reader.readLine();
                while (test != null){
                    var dataProblem = test.split(",");
                    int validatorsNb = dataProblem.length-4; // num,difficulty,luck,code, -> 4 nb : le reste de la ligne  = les validateurs
                    var validatorsId =  new int [validatorsNb];

                    for (int i = 0; i < validatorsNb; i++) {
                        validatorsId[i] = stringToInt(dataProblem[i+4]);
                    }
                    Problem problem = new Problem(  stringToInt(dataProblem[0] ),
                            stringToInt(dataProblem[1] ),
                            stringToInt(dataProblem[2] ),
                            stringToInt(dataProblem[3] ),
                            validatorsId);
                    problems.add(problem);
                    test = reader.readLine();
                }
            }
            catch (IOException ioException ) {
                throw new TuringGameException(ioException.getMessage());
            }
    }
    @Override
    public String toString() {
        StringBuilder problems = new StringBuilder();
        problems.append("The Problems{ " + "\n");
        for (Problem p : this.problems) {
            problems.append(" , " + p.toString() + "\n");
        }
        problems.append("}\n");
        return String.valueOf(problems);
    }
    public String displayProblem(int i) throws TuringGameException {
        if(i <= 0 || i > problems.size()){
            throw new TuringGameException("index "+i+" does not exist in the problem list");
        }
        return  problems.get(i-1).toString();
    }
    private  int stringToInt (String s){
        return Integer.parseInt(s);
    }
    public int nbProblems (){
        return problems.size();
    }

    public Problem getProblem(int index) throws TuringGameException {
        if(index <= 0 || index > problems.size()){
            throw new TuringGameException("index "+index+" does not exist in the problem list");
        }
        return problems.get(index-1); // car les pb ont indices entre 1 et 16 (or liste commence indice 0 )
    }
}
