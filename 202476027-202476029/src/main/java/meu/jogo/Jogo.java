/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package meu.jogo;
import Componentes.Carta;
import Componentes.CartaSup;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mathe
 */
public class Jogo { 

    public static void exibirEstado(Jogador j1,Jogador j2, List<Carta> j1jogadas,List<Carta> j2jogadas)
    {
        System.out.println("\n" + j1.nome + "\nVida: " + j1.vida + "\nEnergia: "+j1.energia);
        System.out.println("Cartas na mesa:");
        for(Carta a : j1jogadas)
        {
            a.exibeCarta();
        }
        System.out.println("\n" + j2.nome + "\n Vida: " + j2.vida + "\n Energia: "+j2.energia);
        System.out.println("Cartas na mesa:");
        for(Carta a : j2jogadas)
        {
            a.exibeCarta();
        }
    }
     public void iniciarJogo(Jogador j1, Jogador j2) {
        boolean ehTurno = true; 
        boolean jogoAtivo = true;
        Scanner resposta = new Scanner(System.in);

        while (jogoAtivo) {
            Jogador jogadorAtual = ehTurno ? j1 : j2;
            Jogador oponente = ehTurno ? j2 : j1;
            // 1. Exibição do estado do jogo 
            System.out.println("\n--- TURNO DE " + jogadorAtual.nome.toUpperCase()+ " ---");
            exibirEstado(j1, j2, j1.jogadas,j2.jogadas); 
            
           // 2. Lógica de Ação
            jogadorAtual.realizarAcao(jogadorAtual, resposta);
            if (jogadorAtual.rendeu) { 
                 finalizarJogo(oponente, jogadorAtual);
                 break;
            }
            System.out.println("\n--- TURNO DE " + oponente.nome.toUpperCase() + " ---");
            exibirEstado(j1, j2, j1.jogadas, j2.jogadas);

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
        }
    }
     
     private void finalizarJogo(Jogador vencedor, Jogador perdedor) {
         System.out.println("O jogador "+vencedor.nome + " ganhou de " + perdedor.nome + " com a vida restante de " + vencedor.vida + " pontos!");
    }

    public void calculaConfronto(Jogador jogadorAtual, Jogador oponente) {
        boolean jogadorAtual_soAtaque = true; // Começa como true, se for jogado algo diferente, vira false
        boolean oponente_soAtaque = true;
        for(int i=0;i<jogadorAtual.jogadas.size();i++)
        {
            switch(jogadorAtual.jogadas.get(i).tipo)
            {
                case "ATAQUE" -> {
                    jogadorAtual.ataque+=jogadorAtual.jogadas.get(i).poder;
                }
                case "DEFESA" -> {
                    jogadorAtual.defesa+=jogadorAtual.jogadas.get(i).poder;
                    jogadorAtual_soAtaque = false;
                }
                case "SUPORTE" -> {
                    jogadorAtual_soAtaque = false;
                    CartaSup mesa = (CartaSup) jogadorAtual.jogadas.get(i);
                    switch(mesa.efeito)
                    {
                        case "AUMENTA_ATAQUE":  jogadorAtual.ataque *= jogadorAtual.jogadas.get(i).poder ;
                        case "DIMINUI_ATAQUE": oponente.ataque -= oponente.ataque * jogadorAtual.jogadas.get(i).poder ;
                        case "AUMENTA_VIDA": jogadorAtual.vida +=  jogadorAtual.jogadas.get(i).poder ;
                        default: break;
                    }
                    }  
                }
        }
         for(int i=0;i<oponente.jogadas.size();i++)
        {
            switch(oponente.jogadas.get(i).tipo)
            {
                case "ATAQUE" -> {
                    oponente.ataque+=oponente.jogadas.get(i).poder;
                    
                }
                case "DEFESA" -> {
                    oponente.defesa+=oponente.jogadas.get(i).poder;
                    oponente_soAtaque = false;
                }
                case "SUPORTE" -> {
                    oponente_soAtaque = false;
                    CartaSup mesa = (CartaSup) oponente.jogadas.get(i);
                    switch(mesa.efeito)
                    {
                        case "AUMENTA_ATAQUE":  oponente.ataque *= 1 + oponente.jogadas.get(i).poder ;
                        case "DIMINUI_ATAQUE": jogadorAtual.defesa -= jogadorAtual.ataque * oponente.jogadas.get(i).poder ;
                        case "AUMENTA_VIDA": oponente.vida +=  oponente.jogadas.get(i).poder ;
                        default: break;
                    }
                    }  
                }
        }
        int danoAoOponente = 0;
        int danoAoJogador = 0;

        // Verificação da Condição Especial "Ataque vs Ataque"
        if (jogadorAtual_soAtaque && oponente_soAtaque) {
            danoAoOponente = (int) oponente.ataque; 
            danoAoJogador = (int) jogadorAtual.ataque; 

        } else {

            danoAoOponente = (int) Math.max(0, jogadorAtual.ataque - oponente.defesa);


            danoAoJogador = (int) Math.max(0, oponente.ataque - jogadorAtual.defesa);


        }

        // Aplica o Dano à Vida
            oponente.vida -= danoAoOponente;
            jogadorAtual.vida -= danoAoJogador;

            }

    
    public static void main(String[] args) throws IOException 
    {
        Jogo um = new Jogo(); 
        Scanner resposta = new Scanner(System.in);           
        System.out.println("Serao quantos jogadores?\n Responda com 1 ou 2");
        int nj = resposta.nextInt();
        Scanner nomer = new Scanner(System.in);
        System.out.println("Insira seu nome:");
        String nome = nomer.nextLine();
        System.out.println("Agora insira seu id:");
        int id = nomer.nextInt();
        Jogador j1 = new Jogador(nome,id);
        System.out.println("Seleção Aleatória?\n Responda 1 para 'sim' ou 0 para 'nao'");
        int aleatorio = resposta.nextInt();
        j1.escolheCarta(aleatorio);
        if(nj == 1){
           Bot robo = new Bot();
           robo.defineAleatorio();
           um.iniciarJogo(j1,robo);
        }
        else {
            nomer = new Scanner(System.in);
            System.out.println("Insira seu nome:");
            nome = nomer.nextLine();
            System.out.println("Agora insira seu id:");
            id = nomer.nextInt();
            Jogador j2 = new Jogador(nome,id);
            System.out.println("Seleção Aleatória?\n Responda 1 para 'sim' ou 0 para 'nao'");
            aleatorio = resposta.nextInt();
            j2.escolheCarta(aleatorio);
            um.iniciarJogo(j1,j2);
        } 
    }  
}