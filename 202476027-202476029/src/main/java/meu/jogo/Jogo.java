/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package meu.jogo;
import Componentes.Carta;
import Componentes.CartaSup;
import FrJOGO.IfGui;
import FrJOGO.IfJogador;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class Jogo { 
    int rodada=1;
    public static int validaEntradaUI(int numero, IfJogador a)
    { int n=1;
        boolean entradaValida = false;
        while(!entradaValida)
        {
        try {
             n = numero;
            entradaValida = true; 
            
        } catch (java.util.InputMismatchException e) {
            JOptionPane.showMessageDialog(a,"Digita certo");
        }
        }
       
        return n;
    }
    public static int validaEntrada(Scanner resposta)
    {
        int numero =0;
        boolean entradaValida = false;
        while(!entradaValida)
        {
        try {
            numero = resposta.nextInt();
            entradaValida = true; 
            
        } catch (java.util.InputMismatchException e) {
            
            System.out.println("Erro! Por favor, digite um número.");
            resposta.next(); 
        }
        }
        return numero;
    }
    public static void exibirEstado(Jogador j1,Jogador j2)
    {
        System.out.println("\n" + j1.nome.toUpperCase() + "\nID:"+ j1.id + "\nVida: " + j1.vida + "\nEnergia: "+j1.energia);
        System.out.println("Cartas na mesa:");
        for(Carta a : j1.jogadas)
        {
            a.exibeCarta();
        }
        System.out.println("\n" + j2.nome.toUpperCase() + "\nID:"+ j2.id + "\nVida: " + j2.vida + "\nEnergia: "+j2.energia);
        System.out.println("---------------------");
    }
     public void iniciarJogo(Jogador j1, Jogador j2) {
       Random rand = new Random();
        boolean ehTurno = rand.nextBoolean(); // escolhe aleatoriamente quem começa
        boolean jogoAtivo = true;
        Scanner resposta = new Scanner(System.in);
        while (jogoAtivo) {
            Jogador jogadorAtual = ehTurno ? j2 : j1;
            Jogador oponente = ehTurno ? j1 : j2;
            // 1. Exibição do estado do jogo 
            System.out.println("---------"+rodada+"º RODADA------------");
            System.out.println("\n--- TURNO DE " + jogadorAtual.nome.toUpperCase()+ " ---");
            exibirEstado(jogadorAtual,oponente ); 
            
           // 2. Lógica de Ação
            jogadorAtual.realizarAcao(jogadorAtual, resposta);
            if (jogadorAtual.rendeu) { 
                 finalizarJogo(oponente, jogadorAtual);
                 break;
            }
            System.out.println("\n--- TURNO DE " + oponente.nome.toUpperCase() + " ---");
            exibirEstado(jogadorAtual,oponente ); 

           oponente.realizarAcao(oponente, resposta);

            if (oponente.rendeu) {
                finalizarJogo(jogadorAtual,oponente);
                break;
            }
            // 3. Cálculo de Dano, Vida e Energia
            calculaConfronto(jogadorAtual, oponente);
            jogadorAtual.jogadas.clear();
            oponente.jogadas.clear();
            
            // 4. Verificação de Fim de Jogo 
            if (j1.vida<= 0 || j2.vida <= 0) {
                jogoAtivo = false;
                finalizarJogo(j1.vida <= 0 ? j2 : j1, j1.vida <= 0 ? j1 : j2);
            }
            
            // 5. Alterna o turno
            ehTurno = !ehTurno;
            rodada+=1;
        }
    }
     
     private void finalizarJogo(Jogador vencedor, Jogador perdedor) {
         System.out.println("O jogador "+vencedor.nome + " ganhou de " + perdedor.nome + " com a vida restante de " + vencedor.vida + " pontos!");
    }

    public void calculaConfronto(Jogador jogadorAtual, Jogador oponente) 
    {
        jogadorAtual.calculaCartas(jogadorAtual);
        oponente.calculaCartas(oponente);
        jogadorAtual.calculaSuportes(jogadorAtual, oponente);
        
        int danoAoOponente = (int) Math.max(0, jogadorAtual.ataque - oponente.defesa);
        int danoAoJogador = (int) Math.max(0, oponente.ataque - jogadorAtual.defesa);

        System.out.println("---------------------------------------------------------------\nResultado da " + rodada + "º Rodada!");
        System.out.println(jogadorAtual.nome.toUpperCase() + " Possuia " + jogadorAtual.ataque + " de Ataque e " + jogadorAtual.defesa + " de Defesa!");
        System.out.println(oponente.nome.toUpperCase() + " Possuia " + oponente.ataque + " de Ataque e " + oponente.defesa + " de Defesa!");
        System.out.println("Resultando em " + jogadorAtual.nome.toUpperCase() + " receber " + danoAoJogador + " de dano enquanto " + oponente.nome.toUpperCase() + 
        " recebeu " + danoAoOponente + " de dano!");
        System.out.println("---------------------------------------------------------------");
        
        // Aplica o Dano à Vida
        oponente.vida = (int) (Math.round((oponente.vida - danoAoOponente)/ 10.0) * 10 );
        jogadorAtual.vida = (int) (Math.round((jogadorAtual.vida - danoAoJogador)/ 10.0 ) * 10);
        if(jogadorAtual.vida > 100)
        {
            jogadorAtual.vida = 100;
        }
        if(oponente.vida > 100)
        {
            oponente.vida = 100;
        }
        oponente.defesa =0 ;
        oponente.ataque = 0;
        jogadorAtual.ataque = 0;
        jogadorAtual.defesa=0;

    }

    
    public static void main(String[] args) throws IOException 
    {
        
        Jogo um = new Jogo(); 
        int numeroJogadores =1;
        int Aleatoriedade = 1;
        Scanner resposta = new Scanner(System.in);           
        System.out.println("""
                           Bem vindo ao Cyber Duel: Guerra de Hackers!
                           Em um futuro pr\u00f3ximo, as guerras deixaram os campos f\u00edsicos e passaram a ser
                           travadas no ciberespa\u00e7o. Megacorpora\u00e7\u00f5es disputam poder por meio de ataques
                           silenciosos, enquanto os hackers mais habilidosos do planeta se enfrentam em
                           duelos estrat\u00e9gicos, usando cartas digitais baseadas em NFTs como armas de
                           combate.
                           Cada carta representa uma ferramenta \u00fanica \u2014 ataques liberam v\u00edrus e exploits
                           poderosos, defesas erguem firewalls e contramedidas, e cartas de suporte
                           garantem vantagens t\u00e1ticas cruciais. Nesse universo, cada jogador assume o
                           papel de um hacker com estilo e estrat\u00e9gias pr\u00f3prias, lutando para dominar
                           sistemas e proteger seus servidores contra as investidas do inimigo.
                           
                           Agora escolha quantos jogadores vão participar do duelo!
                           Responda com 1 ou 2""");
        numeroJogadores = Jogo.validaEntrada (resposta);
        resposta.nextLine();
        System.out.println("Insira seu nome:");
        String nome = resposta.nextLine();
        System.out.println("Agora insira seu id:");
        int id = Jogo.validaEntrada(resposta);
        Jogador j1 = new Jogador( nome,id);
        System.out.println("Deseja utilizar a seleção de carta aleatória?\n Responda 1 para 'sim' ou 0 para 'não'");
         Aleatoriedade = Jogo.validaEntrada(resposta);
        j1.escolheCarta(Aleatoriedade);
        if(numeroJogadores == 1){
           Bot robo = new Bot();
           robo.defineAleatorio();
           um.iniciarJogo(j1,robo);
        }
        else {
            resposta.nextLine();
            System.out.println("Insira seu nome:");
            nome = resposta.nextLine();
            System.out.println("Agora insira seu id:");
            id = Jogo.validaEntrada(resposta);
            Jogador j2 = new Jogador(nome,id);
            System.out.println("Deseja utilizar a seleção de carta aleatória?\n Responda 1 para 'sim' ou 0 para 'não'");
            Aleatoriedade = resposta.nextInt();
            j2.escolheCarta(Aleatoriedade);
            um.iniciarJogo(j1,j2);
        } 
     
        /*IfGui tela = new IfGui();
        tela.setVisible(true);*/
    }     
}

