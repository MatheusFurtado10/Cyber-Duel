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

/**
 *
 * @author Lucas
 */
class Bot extends Jogador {
    
    public Bot()
    {
        super("Bot",202567001);
    }

    @Override
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
    }
}
