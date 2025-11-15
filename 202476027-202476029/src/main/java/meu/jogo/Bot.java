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
    @Override
    public void realizarAcao(Jogador jogador, Scanner resposta){
        boolean podeJogar = true;
        while(jogador.energia>2 && podeJogar)
        {
            if(jogador.vida > 75)
            {
                for(Carta a : jogador.mao)
                {
                    if("ATAQUE".equals(a.tipo))
                    {
                        if(a.custo < jogador.energia)
                        {
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            jogador.energia -= a.custo;
                            break;
                        }
                        podeJogar = false;
                    }
                }
                    
            }
            else if(jogador.vida > 25)
            {
                for(Carta a : jogador.mao)
                {
                    if("ATAQUE".equals(a.tipo))
                    {
                        jogador.jogadas.add(a);
                        jogador.mao.remove(a);
                        jogador.energia -= a.custo;
                        break;
                    }
                }
                 for(Carta a : jogador.mao)
                {
                    if("DEFESA".equals(a.tipo) && a.custo < jogador.energia)
                    {
                        jogador.jogadas.add(a);
                        jogador.mao.remove(a);
                        jogador.energia -= a.custo;
                        break;
                    }
                    podeJogar = false;
                }
            }
            else
            {
                for(Carta a : jogador.mao)
                {
                    if("DEFESA".equals(a.tipo) && a.custo < jogador.energia)
                    {
                        jogador.jogadas.add(a);
                        jogador.mao.remove(a);
                        jogador.energia -= a.custo;
                        break;
                    }
                    podeJogar = false;
                }
            }
        }
        jogador.energia += 1;
    }
}