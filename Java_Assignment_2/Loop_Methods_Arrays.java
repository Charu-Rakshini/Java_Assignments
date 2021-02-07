import java.util.Scanner;
import java.lang.Math;
class Loop_Methods_Arrays{

  public static void main(String[] args){

    Scanner keyb = new Scanner(System.in);
    Loop_Methods_Arrays lma = new Loop_Methods_Arrays();

    String text="", eText="";
    int code;

    //1. Encryption Method Calling
    //-----------------------------
    try{//Exception handling - try block
      System.out.println("1. Encryption");
      System.out.print("\nPlease enter the text you want to encrypt: ");
      text=keyb.nextLine();
      System.out.print("\nEnter the secret code for encrypting: ");
      code=keyb.nextInt();
      lma.encrypt(text,code);

      //2. Decryption Method Calling
      //-----------------------------
      Scanner keyb2 = new Scanner(System.in);
      System.out.println("2. Decryption");
      System.out.print("\nPlease enter the text you want to decrypt:");
      eText=keyb2.nextLine();
      System.out.print("\nEnter the secret code for decrypting: ");
      code=keyb2.nextInt();
      lma.decrypt(eText,code);

      //3. Sum of Array - Method Calling
      //---------------------------------
      int inputArray[] = new int[10];
      System.out.println("3. Array Sum");
      inputArray=lma.generateRandomArray(10);
      lma.printArray(inputArray);
      int sumRes;
      //Method Calling - Array Sum
      sumRes=lma.arraySum(inputArray);
      System.out.println("\n The sum of array is "+sumRes);
      System.out.println("\n---------------------------------------------------------------");

      //4. Search value in an array - Method Calling
      Scanner keyb3 = new Scanner(System.in);
      System.out.println("4. Search value in array");
      System.out.println("Enter the value to search in this array: ");
      lma.printArray(inputArray);
      int searchVal =keyb3.nextInt();
      System.out.println("The array "+lma.arrayContainsElement(inputArray,searchVal)+ " the value "+searchVal);
      System.out.println("\n------------------------------------------------------------------------");

    }

    catch (Exception e) { //Catch block for handling exceptions
      System.out.println("\nInvalid input, please try again.\n");
    }


  }

  String rawValue="";
  String enValue;
  int eCode;
  String encryptRes="";


  //Encryption Method Definition
  public String encrypt(String rawValue, int eCode){
    this.eCode=eCode;
    for(int i=0;i<rawValue.length();i++){
      if ((int)(rawValue.charAt(i))+eCode >= 123) { //Checking if the value is above lowercase Z
        encryptRes += String.valueOf((char)((rawValue.charAt(i)+eCode)-26));
      }
      else if ((int)(rawValue.charAt(i))+eCode >= 91 && (int)(rawValue.charAt(i))+eCode <= 96) { //Checking if the value is above uppercase Z
        encryptRes += String.valueOf((char)((rawValue.charAt(i)+eCode)-26));
      }
      // Any value within alphabet range
      else{
        encryptRes += String.valueOf((char)(rawValue.charAt(i)+eCode));
      }

    }
    System.out.println("\nThe encrypted text for "+rawValue+" is..");
    System.out.println(encryptRes+"\n");
    System.out.println("---------------------------------------------------------------");

    return encryptRes;
  }


  //Decryption Method Definition
  public String decrypt(String enValue, int eCode){
    for(int i=0;i<enValue.length();i++){
      //System.out.println(String.valueOf((char)((enValue.charAt(i)-eCode))));
      if ((int)(enValue.charAt(i))-eCode <= 64){
        rawValue += String.valueOf((char)((enValue.charAt(i)-eCode+26)));
      }
      else if ((int)(enValue.charAt(i))-eCode >= 91 && (int)(enValue.charAt(i))-eCode <= 96){
        rawValue += String.valueOf((char)((enValue.charAt(i)-eCode+26)));
      }
      else{
        rawValue += String.valueOf(((char)(enValue.charAt(i)-eCode)));
      }

      // System.out.println(enValue.charAt(i));
      // System.out.println((char)(enValue.charAt(i)-eCode));
    }
    System.out.println("\nUsing "+enValue+" to decrypt with "+eCode+": ");
    System.out.println(rawValue+"\n");
    System.out.println("---------------------------------------------------------------");

    return rawValue;
  }


  //Sum of array - Method Definition
  public int arraySum(int[] sArray){
    int sum=0;
    for (int i=0; i<sArray.length;i++){
      sum+=sArray[i];
    }
    return sum;

  }


  //Generating random 10 integers for the array - Method Definition
  public int[] generateRandomArray(int length){
    System.out.print("\nGenerating random 10 integers in array..\n");
    int intArray[] = new int[length];
    for(int i=0; i<length;i++){
      intArray[i]=(int)(Math.random()*100);
    }
    return intArray;
  }


  //Concatenating array elements into a String - Method Definition
  public void printArray(int[] inArray){
    String arrayString ="[";
    for(int i=0; i<inArray.length;i++){
      if(i==(inArray.length)-1){//excluding , for the last element in the array
        arrayString=arrayString+inArray[i];
      }
      else
      {
        arrayString=arrayString+inArray[i]+", ";
      }
    }
    arrayString=arrayString+"]";
    System.out.println(arrayString+"\n"); //Printing Array elements
  }

  //Searching a value in a given array - Method Definition
  public String arrayContainsElement(int[] inArray,int searchVal){
    String valFound = "does not contain";
    for(int i=0;i<inArray.length;i++){
      if(inArray[i]==searchVal){
        valFound="contains";
        break;
      }
    }
    return valFound;
  }

}
