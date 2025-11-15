/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package meu.jogo;

import Componentes.Carta;
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
    public void realizarAcao(Jogador jogador, Scanner resposta){
        boolean podeJogar = true;
        while(jogador.energia>2 && podeJogar)
        {
            jogador.reiniciaMao();
            if(jogador.vida > 75)
            {
                for(Carta a : jogador.mao)
                {
                    if("ATAQUE".equals(a.tipo))
                    {
                        if(a.custo <= jogador.energia)
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
                        if(a.custo <= jogador.energia)
                        {
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            jogador.energia -= a.custo;
                            break;
                         }
                    }
                }
                 for(Carta a : jogador.mao)
                {
                    if("DEFESA".equals(a.tipo))
                    {
                       if(a.custo <= jogador.energia)
                        {
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            jogador.energia -= a.custo;
                            break;
                         }
                        
                    }
                    podeJogar = false;
                }
            }
            else
            {
                for(Carta a : jogador.mao)
                {
                    if("DEFESA".equals(a.tipo))
                    {
                        if(a.custo <= jogador.energia){
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            jogador.energia -= a.custo;
                            break;
                         }
                    }
                    podeJogar = false;
                }
            }
        }
        jogador.energia += 1;
    }
}