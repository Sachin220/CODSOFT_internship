import java.util.Random;
import java.util.Scanner;

public class GuesstheNumber {
    

    static void Randomnumber(int n,Scanner input, int number){
        int i, guess;
        for(i=0; i<n; i++)
        {
            System.out.println("\nEnter you guess here (Any number between 0 to 100): ");
            guess = input.nextInt();
            if(guess-number == 0)
            {
                System.out.println("\n\nCongratulation!!! You have guessed the number correctly...");
                break;
            }

            if(guess-number < 0)
            {
                System.out.println("\nToo Low. Guess a higher number.");
            }

            if(guess-number > 0)
            {
                System.out.println("\nToo High. Guess a lower number.");
            }
        }

        if(i==n)
        {
            System.out.println("\nYou have used all your attempts!!!");
        }

    }

    public static void main(String[] args) {

        Random random = new Random();
        
        try (Scanner input = new Scanner(System.in)) {
            int number, choice, cont;
            cont = 1;
            
            while(cont==1)
            {
                number = 1 + random.nextInt(100);
                System.out.print("\nChoose the Difficulty you want to play on: \n1.Easy (10 Attempts) \n2.Normal (5 Attempts) \n3.Hard (3 Attempts)");
                System.out.print("\nEnter your choice here: ");
                choice = input.nextInt();
                switch(choice)
                {
                    case 1: 

                        Randomnumber(10,input, number);
                    
                    break;

                    case 2:
                        Randomnumber(5, input, number);
                    break;
                    
                    case 3:
                        Randomnumber(3, input, number);
                        
                    break;

                    default:
                        System.out.println("\n\nYou have entered an incorrect choice. Please enter a valid one.");
                    continue;
                }

                System.out.print("\nDo you want to try the game again? If yes then enter 1 or else enter 0: ");
                cont = input.nextInt();
            }
            
            
        }
        
    }
    
}
