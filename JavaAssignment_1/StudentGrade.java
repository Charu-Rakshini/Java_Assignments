//importing Java utility packages which includes Scanner
import java.util.*;

class StudentGrade{
  public static void main(String[] args){

    String studentName = "";
    Double studentGrade=0.00, difference=0.0;
    char letterGrade='A', nextGrade='A';

    System.out.println("\n STUDENT GRADE APPLICATION");
    System.out.println("----------------------------\n");

    //Retrieving details from user
    System.out.print("Enter your name: ");
    Scanner keyb = new Scanner(System.in);
    studentName=keyb.nextLine();            //Student Name
    System.out.print("Enter your grade: ");
    Scanner keyb2=new Scanner(System.in);
    studentGrade = keyb2.nextDouble();      //Student Grade


    //Checking if it is an invalid input
    if(studentGrade<0 || studentGrade>100){
      System.out.println("\nInvalid grade. Please try again with a valid grade between 0-100.\n");
    }

    //Identifying grade if it is a valid input.
    else{

      //F Grade
      if(studentGrade<50){
        letterGrade='F';
        nextGrade='D';
        difference=50-studentGrade;
      }
      //D Grade
      else if(studentGrade>=50 && studentGrade<60){
        letterGrade='D';
        nextGrade='C';
        difference=60-studentGrade;
      }

      //C Grade
      else if(studentGrade>=60 && studentGrade<70){
        letterGrade='C';
        nextGrade='B';
        difference=70-studentGrade;
      }

      //B Grade
      else if(studentGrade>=70 && studentGrade<80){
        letterGrade='B';
        nextGrade='A';
        difference=80-studentGrade;
      }

      //A Grade
      else if(studentGrade>=80 && studentGrade<=100){
        letterGrade='A';
      }


      //Printing Letter Grade
      System.out.println("\nHi "+studentName+", your grade is "+String.valueOf(letterGrade)+"\n");

      //Printing additional information
      if(letterGrade=='A'){
        System.out.println("Excellent!");
      }
      else{
        System.out.println("You can achieve "+String.valueOf(nextGrade)+" Grade by scoring "+String.valueOf(difference)+" more marks.");
      }
    }
    System.out.println("\n----------------------------------");

  }

}
