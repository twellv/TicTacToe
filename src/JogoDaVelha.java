import java.util.Scanner;

public class JogoDaVelha extends Validacao {
    Scanner teclado = new Scanner(System.in);
    String jogador1;
    String jogador2;
    int contadorGeral=0;
    boolean condVitoria = false;


    private String[][] matriz = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};

    public void mostrar() {

        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {

                System.out.print("|");
                System.out.print(matriz[linha][coluna]);

            }
            System.out.print("|");
            System.out.println("\n" + "-------");
        }
    }

    public void escolhaJogador() {
        String pegarEscolha;
        System.out.println("Jogador 1, vai jogar com [ X ] ou [ O ] ? ");
        pegarEscolha = teclado.nextLine();
        if (pegarEscolha.equalsIgnoreCase("X")) {
            jogador1 = "X";
            jogador2 = "O";
        } else if (pegarEscolha.equals("O") || pegarEscolha.equals("o")) {
            jogador1 = "O";
            jogador2 = "X";
        } else {
            System.out.println("ESCOLHA INVALIDA!");
            escolhaJogador();
        }
    }

    public void start() {
        mostrar();
        for (int i = 1; i < 3; i++) {

            while (!verificarVencedor()) {

                if (!verificarVencedor()) {
                    jogador01FazJogada();
                    verificarVencedor();
                    mostrar();
                    if(verificarVencedor()){
                        System.out.println("PARABENS, ["+jogador1+"] VOCE VENCEU!");
                        return;
                    }

                    verificarEmpate();
                    if(verificarEmpate()){
                        System.out.println("DEU EMPATE NESSA XIBATA!!");
                        return;
                    }
                }

                if (!verificarVencedor()) {
                    jogador02FazJogada();
                    verificarVencedor();
                    mostrar();
                    if(verificarVencedor()){
                        System.out.println("PARABENS, ["+jogador2+"] VOCE VENCEU!");
                        return;
                    }

                    verificarEmpate();
                    if(verificarEmpate()){
                        System.out.println("DEU EMPATE NESSA XIBATA!!");
                        return;
                    }
                }
            }
        }
    }

    public void jogador01FazJogada() {

        System.out.println("JOGADOR 01, Jogue [" + jogador1 + "], Na posição : ");
        String jogada = teclado.nextLine();
        boolean valorLogicoTeste = false;

        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                if (jogada.equals(matriz[li][co])) {
                    matriz[li][co] = jogador1;
                    valorLogicoTeste = true;
                    contadorGeral += 1;
                }
            }
        }
        if (!valorLogicoTeste) {
            System.out.println("Sua jogada não pode ser processada! Tente Novamente! ");
            jogador01FazJogada();
        } else {
            System.out.println(" ( JOGADOR 01 Jogou: [" + jogador1 + "], na casa numero: " + " " + jogada + " )");
        }
    }

    public void jogador02FazJogada() {

        System.out.println("JOGADOR 02, Jogue [" + jogador2 + "], Na posição : ");
        String jogada = teclado.nextLine();
        boolean valorLogicoTeste = false;

        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                if (jogada.equals(matriz[li][co])) {
                    matriz[li][co] = jogador2;
                    valorLogicoTeste = true;
                    contadorGeral += 1;
                }
            }
        }
        if (!valorLogicoTeste) {
            System.out.println("Sua jogada não pode ser processada! Tente Novamente! ");
            jogador02FazJogada();
        } else {
            System.out.println(" ( JOGADOR 02 Jogou: [" + jogador2 + "], na casa numero: " + " " + jogada + " )");
        }
    }

    public boolean verificarVencedor() {
        if (matriz[0][0].equals(matriz[0][1]) && matriz[0][1].equals(matriz[0][2])) {
            condVitoria = true;
        } else if (matriz[1][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[1][2])) {
            condVitoria = true;
        } else if (matriz[2][0].equals(matriz[2][1]) && matriz[2][1].equals(matriz[2][2])) {
            condVitoria = true;

        } else if (matriz[0][0].equals(matriz[1][0]) && matriz[1][0].equals(matriz[2][0])) {
            condVitoria = true;
        } else if (matriz[0][1].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][1])) {
            condVitoria = true;
        } else if (matriz[0][2].equals(matriz[1][2]) && matriz[1][2].equals(matriz[2][2])) {
            condVitoria = true;

        } else if (matriz[0][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][2])) {
            condVitoria = true;
        } else if (matriz[2][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[0][2])) {
            condVitoria = true;
        }
        return condVitoria;
    }

    public boolean verificarEmpate(){
        if(contadorGeral > 6){
            condVitoria = true;
        }return condVitoria;
    }

}
