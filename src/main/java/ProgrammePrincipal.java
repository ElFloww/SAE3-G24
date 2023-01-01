import JDBC.ConnexionBase;
import java.util.Scanner;

public class ProgrammePrincipal {

    public static void main(String[] args) {
        {
            //Déclaration d'un scanner pour la saisie de l'utilisateur
            Scanner input = new Scanner(System.in);
            //Instanciation d'une variable termine à faux
            boolean termine = false;
            System.out.println("Bienvenue sur le gestionnaire d'emploi du temps du groupe n°24.");
            //Tant que l'utilisateur ne quitte pas le logiciel
            while(!termine)
            {
                //On affiche des disponibilités de l'utilisateur
                System.out.println("Menu Principal\nTapper :\n\t(1) Disponibilités des professeurs\n\t(2) Disponibilités des salle\n\t(3) Emploi du temps par groupe\n\t(4) Emploi du temps par type de cours\n\t(5) Cours à placer\n\t(-1) Quitter");
                //On gère le cas ou l'utilisateur saisit un autre caractère qu'un nombre
                try
                {
                    //On récupère le nombre
                    int saisie = input.nextInt();
                    switch (saisie) {
                        case 1 -> {
                            System.out.println("Vous aller consulter les disponibilités des professeurs");
                            InterfaceUtilisateur.affichageDisponibiliteProfesseur();
                        }
                        case 2 -> {
                            System.out.println("Vous aller consulter les disponibilités pour les salles");
                            InterfaceUtilisateur.affichageDisponibiliteSalle();
                        }
                        case 3 -> {
                            System.out.println("Vous aller consulter les emploi du temps");
                            InterfaceUtilisateur.affichageEDT();
                        }
                        case 4 -> {
                            System.out.println("Vous consulter l'annulaire des cours pour le type donnée");
                            InterfaceUtilisateur.affichageTypeCours();
                        }
                        case 5 -> {
                            System.out.println("Vous consulter les cours qu'il reste à donner");
                            InterfaceUtilisateur.afficheCoursAPlacer();
                        }
                        case -1 -> {
                            System.out.println("Vous venez de quitter le logiciel");
                            termine = true;
                        }
                        default -> System.out.println("Saisie incorrecte !");
                    }
                }
                catch (java.util.InputMismatchException e)
                {
                    System.out.println("Saisie incorrecte ! Merci de saisir un chiffre !");
                    input.nextLine();
                }
            }
        }
        ConnexionBase.DeconnexionBase();
    }
}

