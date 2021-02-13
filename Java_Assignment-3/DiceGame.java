import java.util.Scanner;

class DiceGame{

  public static void main(String[] args) {

      int sides=0, cSide=0;
      String name="";
      try{
        //First Dice
        Die dice1 = new Die();
        System.out.println("\n\nDICE GAME !!");
        System.out.println("--------------------------------------------------");
        System.out.println("\nDefault Dice");
        System.out.println("\nRolling the default dice with 6 sides..");
        System.out.println("\nDice: "+dice1.getDiceType());
        System.out.println("Number of Sides:"+dice1.getNumSides());
        System.out.println("Side Up: "+dice1.getSideUp()+"\n");

        //Second Dice
        System.out.println("\n--------------------------------------------------");
        System.out.println("\nCustom Dice");
        System.out.println("\nLet's create your custom dice !!");
        System.out.print("Enter the number of sides you would like: ");

        Scanner keyb = new Scanner(System.in);
        sides = keyb.nextInt();
        System.out.println("Enter your dice name: ");
        Scanner keyb2 = new Scanner(System.in);
        name=keyb2.nextLine();


        Die dice2 = new Die(sides);
        dice2.setDiceType(name);
        dice2.roll(6);
        System.out.println("\nRolling your dice "+dice2.getDiceType()+" with "+dice2.getNumSides()+" sides...");
        System.out.println("Side Up: "+dice2.getSideUp()+"\n");


        //Third Dice
        System.out.println("\n--------------------------------------------------");
        System.out.println("\nCreating another dice with 20 sides !!");
        Die dice3 = new Die(20,"d20");
        System.out.println("Which side up do you want your dice to be ? ");
        Scanner keyb3 = new Scanner(System.in);
        cSide = keyb3.nextInt();
        System.out.println("\nSetting the side to be "+cSide+"...");
        dice3.setSideUp(cSide);
        System.out.println("Side Up: "+dice3.getSideUp()+"\n");


        //Yahtzee
        System.out.println("\n--------------------------------------------------");
        System.out.println("\nYahtzee Time !!");
        System.out.println("\nLet's see how many rolls it takes..");
        //creating 5 objects for five dices
        Die dice4 = new Die();
        Die dice5 = new Die();
        Die dice6 = new Die();
        Die dice7 = new Die();
        Die dice8 = new Die();

        int counter = 0, roll1=0, roll2=0,roll3=0,roll4=0,roll5=0;
        int high = 6;
        boolean yFlag = false;

        //rolling dices until we get Yahtzee
        while (yFlag==false){
          roll1 = dice4.roll(6);
          roll2 = dice5.roll(6);
          roll3 = dice6.roll(6);
          roll4 = dice7.roll(6);
          roll5 = dice8.roll(6);
          //if all dices are the same value
          if((roll1==roll2 && roll2==roll3 && roll3==roll4 && roll4==roll5 && roll5==roll1))
          {
            yFlag=true;
            System.out.println("Yahtzee: "+roll1+", "+roll2+", "+roll3+", "+roll4+", "+roll5);
            break;
          }
          //else roll again
          else{
            counter++;
          }
        }

        System.out.println("\nIt took "+counter+" rolls to get Yahtzee !");
        System.out.println("\n--------------------------------------------------\n");

    }
    catch (Exception e) {
      System.out.println("Invalid input, please try again.");
    }
  }

}
