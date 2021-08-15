package main;

import java.util.Scanner;

public class JogoDaVelha{
    Scanner teclado = new Scanner(System.in);
    String jogador1;
    String jogador2;
    int contadorRodadas = 0;
    boolean condVitoria = false;

    public static String[][] matriz ={  {"1", "2", "3"},
                                        {"4", "5", "6"},
                                        {"7", "8", "9"}};

    public void mostrar(){
        for (int linha = 0; linha < 3; linha++){
            for (int coluna = 0; coluna < 3; coluna++){

                System.out.print("|");
                System.out.print(matriz[linha][coluna]);

            }
            System.out.print("|");
            System.out.println("\n" + "-------");
        }
    }

    public void escolhaJogador(){
        String pegarEscolha;
        System.out.println("Jogador 1, vai jogar com [ X ] ou [ O ] ? ");
        pegarEscolha = teclado.nextLine();
        if (pegarEscolha.equalsIgnoreCase("X")){
            jogador1 = "X";
            jogador2 = "O";
        } else if (pegarEscolha.equalsIgnoreCase("O")){
            jogador1 = "O";
            jogador2 = "X";
        } else {
            System.out.println("ESCOLHA INVALIDA!");
            escolhaJogador();
        }
    }

    public void start() {
        while(contadorRodadas != 9){
            jogador01FazJogada();
            jogador02FazJogada();
        }
    }

    public void jogador01FazJogada(){
        System.out.println("JOGADOR 01, Jogue [" + jogador1 + "], Na posição : ");
        String jogada = teclado.nextLine();
        boolean valorLogico = false;
        
        for (int li = 0; li < 3; li++){
            for (int co = 0; co < 3; co++){
                if (jogada.equals(matriz[li][co])){
                    matriz[li][co] = jogador1;
                    valorLogico = true;
                }
            }
        }
        mostrar();
        if(valorLogico){
            System.out.println(jogador1+" na posição "+jogada);
            contadorRodadas +=1;
            System.out.println(contadorRodadas);
        } else{
            System.out.println("jogada invalida! tente novamente! ");
            jogador01FazJogada();
        }

        if(verificarVencedor()){
            System.out.println("voce venceu!!! ");
            System.exit(1);
        }
        verificarEmpate();
    }

    public void jogador02FazJogada(){
        System.out.println("JOGADOR 02, Jogue [" + jogador2 + "], Na posição : ");
        String jogada = teclado.nextLine();
        boolean valorLogico = false;

        for (int li = 0; li < 3; li++){
            for (int co = 0; co < 3; co++){
                if (jogada.equals(matriz[li][co])){
                    matriz[li][co] = jogador2;
                    valorLogico = true;
                }
            }
        }
        mostrar();
        if(valorLogico){
            System.out.println(jogador2+" na posição "+jogada);
            contadorRodadas +=1;
            System.out.println(contadorRodadas);
        } else{
            System.out.println("jogada invalida! tente novamente! ");
            jogador02FazJogada();
        }

        if(verificarVencedor()){
            System.out.println("voce venceu!!! ");
            System.exit(1);
        }
        verificarEmpate();
    }

    public boolean verificarVencedor(){
        if (matriz[0][0].equals(matriz[0][1]) && matriz[0][1].equals(matriz[0][2])){
            condVitoria = true;
        } else if (matriz[1][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[1][2])){
            condVitoria = true;
        } else if (matriz[2][0].equals(matriz[2][1]) && matriz[2][1].equals(matriz[2][2])){
            condVitoria = true;

        } else if (matriz[0][0].equals(matriz[1][0]) && matriz[1][0].equals(matriz[2][0])){
            condVitoria = true;
        } else if (matriz[0][1].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][1])){
            condVitoria = true;
        } else if (matriz[0][2].equals(matriz[1][2]) && matriz[1][2].equals(matriz[2][2])){
            condVitoria = true;

        } else if (matriz[0][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][2])){
            condVitoria = true;
        } else if (matriz[2][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[0][2])){
            condVitoria = true;
        }
        return condVitoria;
    }

    public boolean verificarEmpate(){
        if (contadorRodadas >= 9){
            condVitoria = true;
            System.out.println(" < E M P A T E >  (-.-')");
            System.exit(1);
        }
        return condVitoria;
    }
}
