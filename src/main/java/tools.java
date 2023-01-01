import java.util.Scanner;

public class tools {
    //Fonction permettant de faire une saisie de semaine
    public static int choixSemaine()
    {
        //Initialisation de la semaine par défaut à 1.
        int semaine = 1;
        Scanner input = new Scanner(System.in);
        boolean termine = false;
        //Tant que la semaine n'est pas correcte
        while(!termine)
        {
            System.out.print("Saisissez un numéro de la semaine valide (1 à 53) : ");
            try
            {
                int choix = input.nextInt();
                //On vérifie que le choix de semaine est correcte
                //S'il est correct, on valide son choix et la boucle se termine
                if(choix > 0 && choix <=53)
                {
                    semaine = choix;
                    termine = true;
                }
                else
                {
                    System.out.println("Numéro de semaine incorrect");
                }
            } catch (java.util.InputMismatchException e)
            {
                System.out.println("Saisie incorrecte ! Merci de saisir un chiffre !");
                input.nextLine();
            }
        }
        //On retourne la semaine saisie par l'utilisateur
        return semaine;
    }
}
