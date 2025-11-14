/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meu.jogo;

import Componentes.Carta;
import Componentes.LeArquivo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class Jogador {
   String nome;
   int id;
   int vida =100;
   int energia = 10;
   float ataque=0;
   float defesa=0;
   int nAtk = 0;
   int nDef =0;
   int nSup =0;
   boolean rendeu = false;
   List<Carta> mao = new ArrayList<>();
   List<Carta> jogadas =  new ArrayList<>(); 
   
    public Jogador(String n,int i)
    {
        nome = n;
        id = i;
    }
    
    public static void imprimirLista(List<Carta> cartas, String tipo, int n) 
    {
        System.out.println("Escolha "+n+" cartas de "+tipo+" para sua mao de jogo:");
        System.out.println("--------------------------------------------------------------------------------------------");
        int totalCartas = cartas.size();
        if (totalCartas == 0) return; 
        int meio = (int) Math.ceil(totalCartas / 2.0);
        String formato = "%-61s"; 
        for (int i = 0; i < meio; i++) {
            Carta cartaEsquerda = cartas.get(i);
            System.out.print(String.format(formato, (i + 1) + " - " + cartaEsquerda.exibeCartaSimples()));
            int indiceDireita = i + meio;
            if (indiceDireita < totalCartas) {
                Carta cartaDireita = cartas.get(indiceDireita);
                System.out.println((indiceDireita + 1) + " - " + cartaDireita.exibeCartaSimples());
            } else {
                System.out.println();
            }
        }
    }
    
    public static int selecionarCarta(List<Carta> listaDisponivel, Scanner le) 
    {
        System.out.println("\nEscolha a carta pelo seu numero (1 a " + listaDisponivel.size() + "):");
        
        int indice = le.nextInt() - 1;

        if (indice < 0 || indice >= listaDisponivel.size()) {
            System.out.println("❌ Escolha entre o numero correto de cartas!");
            return -1;
        }

        Carta cartaEscolhida = listaDisponivel.get(indice);
        System.out.println("\nCarta escolhida:");
        cartaEscolhida.exibeCarta(); 
        System.out.println("Tem certeza da sua escolha? Responda com 'sim' ou 'nao'.");
        String escolha = le.next();
        if (!escolha.equals("sim")){
            return -1; 
        }
        return 1;
    }
    public void escolheCarta(int t) throws IOException
    {
        if(t==1)
        {
            this.defineAleatorio();
        }else{
            this.defineCarta();
        }
    }
    public void defineCarta() throws IOException
    {
        List<Carta> cartas = LeArquivo.lerCartas("ataque.csv");
        Scanner le = new Scanner(System.in);
        while (nAtk < 4) 
        {
            imprimirLista(cartas,"ataque",4); 
            int indiceEscolhido = selecionarCarta(cartas, le); 

            if (indiceEscolhido != -1) { 
                mao.add(cartas.get(indiceEscolhido));
                cartas.remove(indiceEscolhido);
                nAtk++;
            } 
        }
        System.out.println();
        cartas = LeArquivo.lerCartas("defesa.csv");
        while (nDef < 4) 
        {
            imprimirLista(cartas,"defesa",4); 
            int indiceEscolhido = selecionarCarta(cartas, le); 

            if (indiceEscolhido != -1) { 
                mao.add(cartas.get(indiceEscolhido));
                cartas.remove(indiceEscolhido);
                nDef++;
            } 
            
        }
        System.out.println();
        cartas = LeArquivo.lerCartas("suporte.csv");
        while (nSup < 2) 
        {
            imprimirLista(cartas,"suporte",2); 

            int indiceEscolhido = selecionarCarta(cartas, le); 

            if (indiceEscolhido != -1) { 
                mao.add(cartas.get(indiceEscolhido));
                cartas.remove(indiceEscolhido);
                nSup++;
            } 
            
        }
        System.out.println();
        System.out.println("As cartas escolhidas foram:");
        for(Carta a : mao)
            {
                a.exibeCarta();
            }
         
    }
    public void defineAleatorio() throws IOException
    {
        List<Carta> cartas = LeArquivo.lerCartas("ataque.csv");
        while (nAtk < 4) 
        {
            int indiceEscolhido = ((int) Math.floor(Math.random()*(cartas.size())));
            mao.add(cartas.get(indiceEscolhido));
            cartas.remove(indiceEscolhido);
            nAtk++;
        }
        cartas = LeArquivo.lerCartas("defesa.csv");
        while (nDef < 4) 
        {
            int indiceEscolhido = (int) Math.floor(Math.random()*(cartas.size())) ;             
            mao.add(cartas.get(indiceEscolhido));
            cartas.remove(indiceEscolhido);
            nDef++;
        } 
            
        
        cartas = LeArquivo.lerCartas("suporte.csv");
        while (nSup < 2) 
        {

            int indiceEscolhido = (int) Math.floor(Math.random()*(cartas.size()));
            mao.add(cartas.get(indiceEscolhido));
            cartas.remove(indiceEscolhido);
            nSup++;
         }
         System.out.println("As cartas escolhidas foram:");
        for(Carta a : mao)
            {
                a.exibeCarta();
            }
           
    }

    public void realizarAcao(Jogador jogador, Scanner resposta) {
        System.out.println("Suas cartas sao:");
        for(Carta a : jogador.mao)
            {
                System.out.print(jogador.mao.indexOf(a)+1 + " - ");
                a.exibeCarta();
            }
        System.out.println("Escolha a sua ação nesse turno");
        int opcao=0;
        boolean respondeu = false;
        while (!respondeu) {
          // Exibe as opções do menu
          System.out.println("\n--- Açoes ---");
          System.out.println("1. Jogar Cartas");
          System.out.println("2. Passar a vez");
          System.out.println("3. Entregar o Sistema");
          System.out.print("Escolha uma opção: ");

          
          opcao = resposta.nextInt();

          
          switch (opcao) {
              case 1:
                  respondeu = true;
                  jogador.jogaCarta();
                   if(jogador.energia <10)
                  {
                  jogador.energia+=1;
                  }
                  break; // Sai do switch

              case 2:
                  respondeu=true;
                  if(jogador.energia <10)
                  {
                  jogador.energia+=1;
                  }
                  System.out.println("Você escolheu a Opção 2.");
                  
                  break;
              case 3:
                  respondeu=true;
                  jogador.vida =0;
                  break; 

              default:
                  System.out.println("Opção inválida. Tente novamente.");
          }
        }
    }

    public void jogaCarta() {
       
    }
}
