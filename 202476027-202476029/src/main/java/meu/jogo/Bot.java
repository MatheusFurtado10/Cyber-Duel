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
import static meu.jogo.Jogador.imprimirLista;
import static meu.jogo.Jogador.selecionarCarta;

/**
 *
 * @author Lucas
 */
class Bot {
    String nome;
    int id;
    int nAtk=0;
    int nDef=0;
    int nSup=0;
    List<Carta> mao = new ArrayList<>();
    public Bot()
    {
        nome= "Bot";
        id=202565001;
    }
    public void defineAleatorio() throws IOException
    {
        List<Carta> cartas = LeArquivo.lerCartas("ataque.csv");
        while (nAtk < 4) 
        {
            int indiceEscolhido = (int) Math.floor(Math.random()*(cartas.size())) ; 

             
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

            int indiceEscolhido = (int) Math.floor(Math.random()*(cartas.size())) ; 
             
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
}
