/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package meu.jogo;
import Componentes.Carta;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mathe
 */
public class Jogo {
    
    
    public static void main(String[] args) throws IOException {
        Scanner resposta = new Scanner(System.in);           
           System.out.println("Serao quantos jogadores?\n Responda com 1 ou 2");
           int nj = resposta.nextInt();
           Jogador j1 = new Jogador();
           System.out.println("Seleção Aleatória?\n Responda 1 para 'sim' ou 0 para 'nao'");
           int aleatorio = resposta.nextInt();
           
           j1.escolheCarta(aleatorio);
           
           if(nj == 1){
              Bot robo = new Bot();
              robo.defineAleatorio();
           }
           else {
               Jogador j2 = new Jogador();
               System.out.println("Seleção Aleatória?\n Responda 1 para 'sim' ou 0 para 'nao'");
               aleatorio = resposta.nextInt();
               j2.escolheCarta(aleatorio);
           }
           
}
}