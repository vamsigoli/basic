import java.util.List;
import java.util.function.Function;

public class AnotherCurryUsage {
    //one example usage of curry is in the PersonBuilder example in the main sources
    //here we are using code as one of the parameters. in PersonBuilder all inputs are data.

    //lets say we have a list of words and we calculate the score of each word. we want a list that has score greater than 1
    //for now lets assume the score being the length of the word
    private final Function<String, Integer> scoreAWord = String::length;

    //if we want the threshold to be a parameter, the function will be like below

    public List<String> highScores(Function<String, Integer> score, List<String> inputStringList, Integer threshold) {
        return inputStringList.stream().filter(one -> score.apply(one) > threshold).toList();
    }

    //and we will use the highScore function as below
    public void testHighScores() {

        List<String> sampleStringList = List.of("java", "scala", "haskell", "c#");

        List<String> highscore1 = highScores(scoreAWord, sampleStringList, 1);
        List<String> highscore5 = highScores(scoreAWord, sampleStringList, 5);
        List<String> highscore2 = highScores(scoreAWord, sampleStringList, 2);

    }

        //but the problem with this code is we are passing the same scoring function in all three invocations
        //only thing that is changing here is the threshold. rest all is constant and repeating three times.
        // how about a function that just takes the threshold and rest all is coded only once

        //a new function should be written that returns a function that takes an Integer later and returns a List<String>
        //so the return type of the function would be Function<Integer,List<String>> and absorbs the other two inputs

        private Function<Integer,List<String>> absorbTwoInputs(Function<String,Integer> scoreFunction,List<String> inputStringList) {
            return threshold -> inputStringList.stream().filter(one -> scoreFunction.apply(one) > threshold).toList();
        }
        //and it can be used as below

    public void testMoreElegant() {
        List<String> sampleStringList = List.of("java", "scala", "haskell", "c#");

        var wordsWithScoreHigherThan = absorbTwoInputs(scoreAWord, sampleStringList);
        List<String> highscores1 = wordsWithScoreHigherThan.apply(1);
        List<String> highscores4 = wordsWithScoreHigherThan.apply(4);
    }

    //but this has another problem. if we have two lists, we have to use two functions like below

    public void notSoFast() {
        List<String> sampleStringList = List.of("java", "scala", "haskell", "c#");
        List<String> sampleStringList2 = List.of("pascal", "fortron");

        var wordsWithScoreHigherThan = absorbTwoInputs(scoreAWord, sampleStringList);
        List<String> highscores1 = wordsWithScoreHigherThan.apply(1);
        List<String> highscores4 = wordsWithScoreHigherThan.apply(4);

        var wordsWithScoreHigherThan2 = absorbTwoInputs(scoreAWord, sampleStringList2);
        wordsWithScoreHigherThan2.apply(3);
        wordsWithScoreHigherThan2.apply(2);

        // true currying that takes only one parameter per function eliminates this problem
        // once done, this will become same as CurryExample or PersonBuilder example
    }



    //Start with Function< first param, add Function< second param, add Function< Third Param, output and complete the function end tags
    //each param will have one arrow in the return line at 71 below
    Function<List<String>,Function<Function<String,Integer>,Function<Integer,List<String>>>> allParamsAsFunctions() {
        return inputList -> scoreFunction -> threshold -> inputList.stream().filter(one -> scoreFunction.apply(one) > threshold).toList();
    }

    //only two as input. first is scoreFunction and second is threshold. apply will also be written in this order
    Function<Function<String,Integer>,Function<Integer,List<String>>> wordsWithScoreHigher (List<String> inputList) {
        return scoreFunction -> threshold -> inputList.stream().filter(one -> scoreFunction.apply(one) > threshold).toList();
    }

    //threshold first and then scoreFunction later
    Function<Integer, Function<Function<String,Integer>, List<String>>> twoParamsAsFunctions(List<String> input) {
        return threshold ->  scoreFunction -> input.stream().filter(one -> scoreFunction.apply(one) > threshold).toList();
    }

    public void finallyClean() {

        List<String> sampleStringList = List.of("java", "scala", "haskell", "c#");
        List<String> sampleStringList2 = List.of("pascal", "fortron");

        List<String> highestscore1 = allParamsAsFunctions().apply(sampleStringList).apply(scoreAWord).apply(1);
        List<String> highestscore3 = wordsWithScoreHigher(sampleStringList).apply(scoreAWord).apply(3);
        List<String> highestscore4 = wordsWithScoreHigher(sampleStringList2).apply(scoreAWord).apply(4);

        System.out.println(highestscore1);
        System.out.println(highestscore3);
        System.out.println(highestscore4);
    }

    public static void main(String[] args) {
        AnotherCurryUsage c = new AnotherCurryUsage();
        c.finallyClean();

    }





}
