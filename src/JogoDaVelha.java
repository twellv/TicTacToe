import java.util.Scanner;

public class JogoDaVelha {
    Scanner teclado = new Scanner(System.in);
    String jogador1;
    String jogador2;

    private String[][] m = { {"1","2","3"}, {"4","5","6"},{"7","8","9"} };

        public String mostrar(){

            for(int linha = 0; linha < 3; linha++){
                for(int coluna = 0; coluna < 3; coluna++){

                    System.out.printf("|");
                    System.out.printf(m[linha][coluna]);

                } System.out.printf("|");
                System.out.println("\n" + "-------");
            }
            return null;
        }
    //----------------------------------------------------------
    public void escolhaJogador(){
     String pegarEscolha;
     System.out.println("Jogador 1, vai jogar com [ X ] ou [ O ] ? ");
     pegarEscolha = teclado.nextLine();
      if(pegarEscolha.equals("X")){
        jogador1 = "X";
        jogador2 = "O";
      } else if(pegarEscolha.equals("O")){
          jogador1 = "O";
          jogador2 = "X";
      } else{
          System.out.println("JOGADA INVALIDA!");
          escolhaJogador();
      }
    }
    //----------------------------------------------------------

        public void start(){
            mostrar();
            for(int i = 1; i < 3; i++){

                do{
                    jogador01FazJogada();
                    validar();
                    mostrar();


                    jogador02FazJogada();
                    validar();
                    mostrar();

                }while(vale != true || vale != true);
            }
        }
//----------------------------------------------------------

public void jogador01FazJogada(){

    System.out.println("JOGADOR 01 Faça sua jogada: ");
    String jogada = teclado.nextLine();
    System.out.println("JOGADOR 01 Jogou na casa numero: " + " " + jogada);

    for (int co = 0; co < 3 ; co++){
        for (int li = 0; li < 3; li++){
            if (jogada.equals(m[li][co])){
                m[li][co] = jogador1;
            }
        }
    }
}
//----------------------------------------------------------
public void jogador02FazJogada(){

    System.out.println("JOGADOR 02 Faça sua jogada: ");
    String jogada = teclado.nextLine();
    System.out.println("JOGADOR 02 Jogou na casa numero: " + " " + jogada);

    for (int co = 0; co < 3 ; co++){
        for (int li = 0; li < 3; li++){
            if (jogada.equals(m[li][co])){
                m[li][co] = jogador2;
            }
        }
    }
}
//----------------------------------------------------------

        boolean vale = false;
        public boolean validar(){

            if ( vencedor01() == true || vencedor02() == true){
                vale = true;
            }
            return vale;
        }
        //---------------------------------------------------------------
        boolean condVitoria01 = false;
        public boolean vencedor01(){


            if(m[0][0] == "X" && m[0][1] == "X" && m[0][2] == "X"){
                condVitoria01 = true;
            } else if(m[1][0] == "X" && m[1][1] == "X" && m[1][2] == "X"){
                condVitoria01 = true;
            } else if(m[2][0] == "X" && m[2][1] == "X" && m[2][2] == "X"){
                condVitoria01 = true;


            } else if(m[0][0] == "X" && m[1][0] == "X" && m[2][0] == "X"){
                condVitoria01 = true;
            } else if(m[0][1] == "X" && m[1][1] == "X" && m[2][1] == "X"){
                condVitoria01 = true;
            } else if(m[0][2] == "X" && m[1][2] == "X" && m[2][2] == "X"){
                condVitoria01 = true;


            } else if(m[0][0] == "X" && m[1][1] == "X" && m[2][2] == "X"){
                condVitoria01 = true;
            } else if(m[2][0] == "X" && m[1][1] == "X" && m[0][2] == "X"){
                condVitoria01 = true;
            }
            return condVitoria01;
        }

        //----------------------------------------------------------------

        boolean condVitoria02 = false;
        public boolean vencedor02(){


            if(m[0][0] == "O" && m[0][1] == "O" && m[0][2] == "O"){
                condVitoria02 = true;
            } else if(m[1][0] == "O" && m[1][1] == "O" && m[1][2] == "O"){
                condVitoria02 = true;
            } else if(m[2][0] == "O" && m[2][1] == "O" && m[2][2] == "O"){
                condVitoria02 = true;

            } else if(m[0][0] == "O" && m[1][0] == "O" && m[2][0] == "O"){
                condVitoria02 = true;
            } else if(m[0][1] == "O" && m[1][1] == "O" && m[2][1] == "O"){
                condVitoria02 = true;
            } else if(m[0][2] == "O" && m[1][2] == "O" && m[2][2] == "O"){
                condVitoria02 = true;

            } else if(m[0][0] == "O" && m[1][1] == "O" && m[2][2] == "O"){
                condVitoria02 = true;
            } else if(m[2][0] == "O" && m[1][1] == "O" && m[0][2] == "O"){
                condVitoria02 = true;
            }
            return condVitoria02;
        }




}
