import java.util.Scanner;

public class JogoDaVelha extends Validacao{
    Scanner teclado = new Scanner(System.in);
    String jogador1;
    String jogador2;
    String jogadaRecebida;

    private String[][] matriz = { {"1","2","3"}, {"4","5","6"},{"7","8","9"} };

        public String mostrar(){

            for(int linha = 0; linha < 3; linha++){
                for(int coluna = 0; coluna < 3; coluna++){

                    System.out.printf("|");
                    System.out.printf(matriz[linha][coluna]);

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
          System.out.println("ESCOLHA INVALIDA!");
          escolhaJogador();
      }
    }
    //----------------------------------------------------------

        public void start(){
            mostrar();
            for(int i = 1; i < 3; i++){

                do{
                    jogador02FazJogada();
                    validar();
                    mostrar();



                    jogador01FazJogada();
                    validar();
                    mostrar();

                }while(vale != true || vale != true);
            }
        }
//----------------------------------------------------------

public Boolean permitirJogada(){

    int jogadaInteira = Integer.parseInt(jogadaRecebida);

    if(jogadaInteira > 0 && jogadaInteira < 10){
        return true;
    } else return false;

}

//----------------------------------------------------------
public void jogador01FazJogada(){

    System.out.println("JOGADOR 01 Faça sua jogada: ");
    jogadaRecebida = teclado.nextLine();
    permitirJogada();
    System.out.println("O valor do outro metodo é" + permitirJogada() + " esse valor ai ó");

    if(permitirJogada()){

        System.out.println("O valor do outro metodo é" + permitirJogada() + " esse valor ai ó");

        for (int co = 0; co < 3 ; co++){
            for (int li = 0; li < 3; li++){
                System.out.println(li);

                if (jogadaRecebida.equals(matriz[li][co])){
                    System.out.println(co +"COLUNA " +li+ " LINHA");
                    matriz[li][co] = jogador1;
                    System.out.println(co +"COLUNA " +li+ " LINHA");
                }else{
                    System.out.println("O valor do outro metodo é" + permitirJogada() + " esse valor ai ó");
                    System.out.println("Ainda não deu certo, tenta de novo!!");
                    //jogador01FazJogada();
                }


            }
        }



    } else{
        System.out.println("Ta errado, joga isso direito ¬¬ ");
        jogador01FazJogada();
    }
System.out.println("JOGADOR 01 Jogou na casa numero: " + " " + "46 A ");
}
//----------------------------------------------------------
public void jogador02FazJogada(){

    System.out.println("JOGADOR 02 Faça sua jogada: ");
    String jogada = teclado.nextLine();

    for (int co = 0; co < 3 ; co++){
        for (int li = 0; li < 3; li++){

            if (jogada.equals(matriz[li][co])){
                matriz[li][co] = jogador2;
            } else{
                System.out.println("Deu ruim.");
            }
        }
    }
    System.out.println("JOGADOR 02 Jogou na casa numero: " + " " + jogada);
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


            if(matriz[0][0] == "X" && matriz[0][1] == "X" && matriz[0][2] == "X"){
                condVitoria01 = true;
            } else if(matriz[1][0] == "X" && matriz[1][1] == "X" && matriz[1][2] == "X"){
                condVitoria01 = true;
            } else if(matriz[2][0] == "X" && matriz[2][1] == "X" && matriz[2][2] == "X"){
                condVitoria01 = true;


            } else if(matriz[0][0] == "X" && matriz[1][0] == "X" && matriz[2][0] == "X"){
                condVitoria01 = true;
            } else if(matriz[0][1] == "X" && matriz[1][1] == "X" && matriz[2][1] == "X"){
                condVitoria01 = true;
            } else if(matriz[0][2] == "X" && matriz[1][2] == "X" && matriz[2][2] == "X"){
                condVitoria01 = true;


            } else if(matriz[0][0] == "X" && matriz[1][1] == "X" && matriz[2][2] == "X"){
                condVitoria01 = true;
            } else if(matriz[2][0] == "X" && matriz[1][1] == "X" && matriz[0][2] == "X"){
                condVitoria01 = true;
            }
            return condVitoria01;
        }

        //----------------------------------------------------------------

        boolean condVitoria02 = false;
        public boolean vencedor02(){


            if(matriz[0][0] == "O" && matriz[0][1] == "O" && matriz[0][2] == "O"){
                condVitoria02 = true;
            } else if(matriz[1][0] == "O" && matriz[1][1] == "O" && matriz[1][2] == "O"){
                condVitoria02 = true;
            } else if(matriz[2][0] == "O" && matriz[2][1] == "O" && matriz[2][2] == "O"){
                condVitoria02 = true;

            } else if(matriz[0][0] == "O" && matriz[1][0] == "O" && matriz[2][0] == "O"){
                condVitoria02 = true;
            } else if(matriz[0][1] == "O" && matriz[1][1] == "O" && matriz[2][1] == "O"){
                condVitoria02 = true;
            } else if(matriz[0][2] == "O" && matriz[1][2] == "O" && matriz[2][2] == "O"){
                condVitoria02 = true;

            } else if(matriz[0][0] == "O" && matriz[1][1] == "O" && matriz[2][2] == "O"){
                condVitoria02 = true;
            } else if(matriz[2][0] == "O" && matriz[1][1] == "O" && matriz[0][2] == "O"){
                condVitoria02 = true;
            }
            return condVitoria02;
        }




}
