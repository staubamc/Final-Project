public class Question {
  private String question;
  private String opt1;
  private String opt2;
  private String opt3;
  private String opt4;
  private int answer;
  private int points;
  private String category;

  public Question (String qQuestion, String qOpt1, String qOpt2, String qOpt3, String qOpt4, int qAnswer, int qPoints, String qCategory) {
    question = qQuestion;
    opt1 = qOpt1;
    opt2 = qOpt2;
    opt3 = qOpt3;
    opt4 = qOpt4;
    answer = qAnswer;
    points = qPoints;
    category = qCategory;
  }

  String getQuestion() {
    return question;
  }

  String getOption1() {
    return opt1;
  }

  String getOption2() {
    return opt2;
  }

  String getOption3() {
    return opt3;
  }

  String getOption4() {
    return opt4;
  }

  int getAnswer() {
    return answer;
  }

  int getPoints() {
    return points;
  }

  String getCategory(){
    return category;
  }
}