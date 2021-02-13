import java.lang.Math;

class Die{
  private String diceType;
  private int numSides;
  private int sideUp;

  public static void main(String[] args) {

  }
  //Constructors
  //-------------

  //Default Constructor / Zero Argumented Constructor
  Die(){
    setDiceType("d6");
    setNumSides(6);
    setSideUp(roll(numSides));//randomValue
  }

  //1 Argumented Constructor
  Die(int numSides){
    setDiceType("d"+String.valueOf(numSides));
    setNumSides(numSides);
    setSideUp(roll(numSides));
  }

  //2 Argumented Constructor
  Die(int numSides, String diceType){
    setNumSides(numSides);
    setDiceType(diceType);
    setSideUp(roll(numSides));
  }


  //Setter Methods
  //---------------

  //Setter for diceType
  public void setDiceType(String diceType){
    this.diceType=diceType;
  }

  //Setter for number of sides
  public void setNumSides(int numSides){
    this.numSides=numSides;
  }

  //Setter for side up
  public void setSideUp(int sideUp){
    if (sideUp>numSides){
      System.out.println("\nSorry, that number is not available, let me roll the dice for another number!");
      this.sideUp=roll(numSides);
    }
    else{
      this.sideUp=sideUp;
    }

  }


  //Getter Methods
  //---------------

  //Getter for diceType
  public String getDiceType(){
    return diceType;
  }

  //Getter for number of sides
  public int getNumSides(){
    return numSides;
  }

  //Getter for side up
  public int getSideUp(){
    return sideUp;
  }

  //Roll Method for rolling the dice
  public int roll(int numSides){
    sideUp= 1+((int)(Math.random()*100) % numSides);
    //System.out.println("The random value of the "+numSides+" side dice is "+sideUp);
    return sideUp;
  }



}
