/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meu.jogo;

import Componentes.Carta;
import Componentes.CartaAtk;
import Componentes.CartaDef;
import Componentes.CartaSup;
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
   int nAtk = 0;
   int nDef =0;
   int nSup =0;
   List<Carta> mao = new ArrayList<>();
   
    public Jogador()
    {
        Scanner nomer = new Scanner(System.in);
        System.out.println("Insira seu nome:");
        nome = nomer.nextLine();
        System.out.println("Agora insira seu id:");
        id = nomer.nextInt();
    }
    
    public static void imprimirLista(List<Carta> cartas, String tipo) 
    {
          System.out.println("Escolha 4 cartas de "+tipo+" para sua mao de jogo:");
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
    
    public void defineCarta() throws IOException
    {
        List<Carta> cartas = LeArquivo.lerCartas("ataque.csv");
        Scanner le = new Scanner(System.in);
        while (nAtk < 4) 
        {
            imprimirLista(cartas,"ataque"); 
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
            imprimirLista(cartas,"defesa"); 
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
            imprimirLista(cartas,"suporte"); 

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
    
}
