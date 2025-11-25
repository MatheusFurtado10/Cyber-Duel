/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.jogo;

import br.ufjf.componentes.Carta;
import br.ufjf.componentes.LeArquivo;
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
        while(jogador.getEnergia()>3 && podeJogar)
        {
            jogador.reiniciaMao();
            if(jogador.getVida() > 70)
            {
                for(Carta jogada : jogador.mao)
                {
                    if("ATAQUE".equals(jogada.tipo) && ataqueJogado<2)
                    {
                          if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           ataqueJogado+=1;
                          jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
                for(Carta jogada : jogador.mao)
                {
                    if("DEFESA".equals(jogada.tipo) && defesaJogado < 1 )
                    {
                       if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           defesaJogado+=1;
                          jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
                for(Carta jogada : jogador.mao)
                {
                    if("SUPORTE".equals(jogada.tipo) && ataqueJogado >= 2)
                    {
                        if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
                    
            }
            else if(jogador.getVida() > 30 && jogador.getVida() < 70)
            {
                for(Carta jogada : jogador.mao)
                {
                    if("ATAQUE".equals(jogada.tipo) && ataqueJogado < 1)
                    {
                          if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           ataqueJogado+=1;
                           jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
                 for(Carta jogada : jogador.mao)
                {
                    if("DEFESA".equals(jogada.tipo) && defesaJogado < 2)
                    {
                         if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           defesaJogado+=1;
                           jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
                 for(Carta jogada : jogador.mao)
                {
                    if("SUPORTE".equals(jogada.tipo))
                    {
                        if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                          jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
            }
            else
            {
                for(Carta jogada : jogador.mao)
                {
                    if("DEFESA".equals(jogada.tipo) && defesaJogado<1)
                    {
                          if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           defesaJogado+=1;
                           jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
                for(Carta jogada : jogador.mao)
                {   
                    if("ATAQUE".equals(jogada.tipo) && ataqueJogado<1)
                    {
                        if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           ataqueJogado+=1;
                          jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
                 for(Carta jogada : jogador.mao)
                {   
                    if("SUPORTE".equals(jogada.tipo))
                    {
                       if(jogada.custo <= jogador.getEnergia()){
                           jogador.jogadas.add(jogada);
                           jogador.mao.remove(jogada);
                           jogador.alteraEnergia(-jogada.custo);
                           LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogada.exibeCarta());
                           break;
                        }
                        podeJogar = false;
                    }
                }
            }
        }
        if(jogador.getEnergia()<10)
            {
                jogador.alteraEnergia(1);
            }
        ataqueJogado = 0;
        defesaJogado =0;
        LeArquivo.escreveReplay("Jogador " + nome + " terminou seu turno!");
    }    
  
}