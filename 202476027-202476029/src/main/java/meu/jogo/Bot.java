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
public class Bot extends Jogador {
    int ataqueJogado = 0;
    int defesaJogado =0;
    public Bot()
    {
        super("Bot",202567001);
    }
    @Override
    public void realizarAcao(Jogador jogador, Scanner resposta)
    {
        boolean podeJogar = true;
        while(jogador.energia>3 && podeJogar)
        {
            jogador.reiniciaMao();
            if(jogador.vida > 70)
            {
                for(Carta a : jogador.mao)
                {
                    if("ATAQUE".equals(a.tipo) && ataqueJogado<2)
                    {
                        if(a.custo <= jogador.energia - ataqueJogado)
                        {
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            ataqueJogado+=1;
                            jogador.energia -= a.custo;
                            break;
                        }
                        podeJogar = false;
                    }
                }
                for(Carta a : jogador.mao)
                {
                    if("DEFESA".equals(a.tipo) && defesaJogado < 1 )
                    {
                       if(a.custo <= jogador.energia-2)
                        {
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            jogador.energia -= a.custo;
                            break;
                         }
                        podeJogar = false;
                    }
                }
                for(Carta a : jogador.mao)
                {
                    if("SUPORTE".equals(a.tipo) && ataqueJogado == 3)
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
            else if(jogador.vida > 30 && jogador.vida < 70)
            {
                for(Carta a : jogador.mao)
                {
                    if("ATAQUE".equals(a.tipo) && ataqueJogado < 1)
                    {
                        if(a.custo <= jogador.energia)
                        {
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            ataqueJogado+=1;
                            jogador.energia -= a.custo;
                            break;
                         }
                        podeJogar = false;
                    }
                }
                 for(Carta a : jogador.mao)
                {
                    if("DEFESA".equals(a.tipo) && defesaJogado < 2)
                    {
                       if(a.custo <= jogador.energia)
                        {
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            defesaJogado+=1;
                            jogador.energia -= a.custo;
                            break;
                         }
                        podeJogar = false;
                    }
                }
                 for(Carta a : jogador.mao)
                {
                    if("SUPORTE".equals(a.tipo))
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
            else if(jogador.energia > 5 )
            {
                for(Carta a : jogador.mao)
                {
                    if("DEFESA".equals(a.tipo) && defesaJogado<1)
                    {
                        if(a.custo <= jogador.energia){
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            defesaJogado+=1;
                            jogador.energia -= a.custo;
                            break;
                         }
                        podeJogar = false;
                    }
                }
                for(Carta a : jogador.mao)
                {   
                    if("ATAQUE".equals(a.tipo) && ataqueJogado<1)
                    {
                        if(a.custo <= jogador.energia){
                           jogador.jogadas.add(a);
                           jogador.mao.remove(a);
                           ataqueJogado+=1;
                           jogador.energia -= a.custo;
                           break;
                        }
                        podeJogar = false;
                    }
                }
                 for(Carta a : jogador.mao)
                {   
                    if("SUPORTE".equals(a.tipo))
                    {
                        if(a.custo <= jogador.energia){
                            jogador.jogadas.add(a);
                            jogador.mao.remove(a);
                            jogador.energia -= a.custo;
                            break;
                         }
                        podeJogar = false;
                    }
                }
            }else{
                podeJogar = false;
            }
        }
        if(jogador.energia<10)
            {
                jogador.energia += 1;
            }
        ataqueJogado = 0;
        defesaJogado =0;
    }    
}