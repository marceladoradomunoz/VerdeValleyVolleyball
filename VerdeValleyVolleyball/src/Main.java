import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
        int selectOption = scanner.nextInt();
        Process process = new Process();
        while (selectOption != 5){
            if (selectOption == 1){
                process.readListPlayers();
            } else if (selectOption == 2){
                process.showTheBestAttackPlayers();
            } else if (selectOption == 3){
                process.showTheBestDefensePlayers();
            } else if (selectOption == 4){
                process.createAndShowPracticeTeams();
            }
            showMainMenu();
            selectOption = scanner.nextInt();
        }
    }

    private static void showMainMenu(){
        System.out.println("1) Abrir lista de jugadores\n" +
                "2) Mostrar los 3 mejores atacantes\n" +
                "3) Mostrar los 3 mejores bloqueadores\n" +
                "4) Crear y mostrar equipos de práctica\n" +
                "5) Salir");
    }
}