/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package meu.jogo;
import Componentes.Carta;
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
            jogadorAtual.jogadas.clear();
            jogadorAtual.jogadas.addAll(jogadorAtual.realizarAcao(oponente, resposta));
            if (jogadorAtual.rendeu) { 
                 finalizarJogo(oponente, jogadorAtual);
                 break;
            }
            System.out.println("\n--- TURNO DE " + oponente.nome.toUpperCase() + " ---");
            exibirEstado(j1, j2, j1.jogadas, j2.jogadas);

            oponente.jogadas.addAll( oponente.realizarAcao(jogadorAtual, resposta));

            if (oponente.rendeu) {
                finalizarJogo(jogadorAtual,oponente);
                break;
            }
            // 3. Cálculo de Dano, Vida e Energia
            calculaConfronto(jogadorAtual, oponente, jogadorAtual.jogadas,oponente.jogadas);
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
     
     private void finalizarJogo(Jogador oponente, Jogador jogadorAtual) {
        
    }

    private void calculaConfronto(Jogador jogadorAtual, Jogador oponente, List<Carta> jogadas, List<Carta> jogadas0) {
        
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