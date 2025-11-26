/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.jogo;

import br.ufjf.componentes.Carta;
import br.ufjf.componentes.CartaAtk;
import br.ufjf.componentes.CartaSup;
import br.ufjf.componentes.LeArquivo;
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
    private int vida =100;
    private int energia = 10;
    protected float ataque=0;
    protected float defesa=0;
    protected int numAtk = 0;
    protected int numDef =0;
    protected int numSup =0;
    boolean rendeu = false;
    public List<Carta> mao = new ArrayList<>();
    public List<Carta> jogadas =  new ArrayList<>(); 
    public List<Carta> maoReserva = new ArrayList<>();
   
    public Jogador(String nomeEscolhido, int idEscolhido)
    {   
        nome= nomeEscolhido;
        id= idEscolhido;
    }
    
    public void setVida(int vidaNova)
    {
        this.vida = vidaNova;
    }
     public int getVida()
    {
      return this.vida;
    }
     public void alteraEnergia(int energiaNova)
    {
        this.energia += energiaNova;
    }
     public int getEnergia()
    {
      return this.energia;
    }
    
    
    public static int selecionarCarta(List<Carta> listaDisponivel, Scanner leitura) 
    {
        System.out.println("\nEscolha a carta pelo seu numero (1 a " + listaDisponivel.size() + "):");
        boolean entradaValida = false;
        int indice = 0;
        while(!entradaValida)
        {
           indice = Jogo.validaEntrada(leitura);
           if (indice > 0 || indice < listaDisponivel.size()) {
               entradaValida = true;
           }else
           {
               System.out.println("Erro! Escolha entre o numero correto de cartas!");
               return -1;
           }
        }
        Carta cartaEscolhida = listaDisponivel.get(indice);
        System.out.println("\nCarta escolhida:");
        System.out.println(cartaEscolhida.exibeCarta());
        System.out.println("Tem certeza da sua escolha? Responda com 1 para confirmar.");
        int escolha = Jogo.validaEntrada(leitura);
        if (1==escolha){
             return 1;
        } 
        System.out.println("Seleção Cancelada");
        return -1; 
       
    }
    public void escolheSelecao(int resposta) throws IOException
    {
        if(resposta==1)
        {
            this.defineAleatorio();
        }else{
            this.defineManual();
        }
        LeArquivo.escreveReplay("Jogador " + nome + " escolheu suas cartas!");
    }
    public void defineManual() throws IOException
    {
        List<Carta> cartas = LeArquivo.lerCartas("ataque.csv");
        Scanner le = new Scanner(System.in);
        while (numAtk < 4) 
        {
            Carta.imprimirLista(cartas,"ataque",4); 
            int indiceEscolhido = selecionarCarta(cartas, le); 

            if (indiceEscolhido != -1) { 
                mao.add(cartas.get(indiceEscolhido));
                cartas.remove(indiceEscolhido);
                numAtk++;
            } 
        }
        System.out.println();
        cartas = LeArquivo.lerCartas("defesa.csv");
        while (numDef < 4) 
        {
            Carta.imprimirLista(cartas,"defesa",4); 
            int indiceEscolhido = selecionarCarta(cartas, le); 

            if (indiceEscolhido != -1) { 
                mao.add(cartas.get(indiceEscolhido));
                cartas.remove(indiceEscolhido);
                numDef++;
            } 
            
        }
        System.out.println();
        cartas = LeArquivo.lerCartas("suporte.csv");
        while (numSup < 2) 
        {
            Carta.imprimirLista(cartas,"suporte",2); 

            int indiceEscolhido = selecionarCarta(cartas, le); 

            if (indiceEscolhido != -1) { 
                mao.add(cartas.get(indiceEscolhido));
                cartas.remove(indiceEscolhido);
                numSup++;
            } 
            
        }
        System.out.println();
    }
    public void defineAleatorio() throws IOException
    {
        List<Carta> cartas = LeArquivo.lerCartas("ataque.csv");
        while (numAtk < 4) 
        {
            int indiceEscolhido = ((int) Math.floor(Math.random()*(cartas.size())));
            mao.add(cartas.get(indiceEscolhido));
            cartas.remove(indiceEscolhido);
            numAtk++;
        }
        cartas = LeArquivo.lerCartas("defesa.csv");
        while (numDef < 4) 
        {
            int indiceEscolhido = (int) Math.floor(Math.random()*(cartas.size())) ;             
            mao.add(cartas.get(indiceEscolhido));
            cartas.remove(indiceEscolhido);
            numDef++;
        } 
            
        
        cartas = LeArquivo.lerCartas("suporte.csv");
        while (numSup < 2) 
        {

            int indiceEscolhido = (int) Math.floor(Math.random()*(cartas.size()));
            mao.add(cartas.get(indiceEscolhido));
            cartas.remove(indiceEscolhido);
            numSup++;
         }
                 maoReserva.addAll(mao);

    }
    public void reiniciaMao()
    {
        if(mao.isEmpty())
        {
            LeArquivo.escreveReplay("Jogador " + nome + " teve sua mão reiniciada!");
            mao.addAll(maoReserva);
        }
       
    }

    public void realizarAcao(Jogador jogador, Scanner resposta) {
        System.out.println("------------------\nSuas cartas sao:");
        for(Carta a : jogador.mao)
            {
                System.out.print(jogador.mao.indexOf(a)+1 + " - ");
                System.out.println(a.exibeCarta());
            }
        System.out.println("Escolha a sua ação nesse turno");
        int opcao=0;
        boolean respondeu = false;
        while (!respondeu) {
          // Exibe as opções do menu
          System.out.println("\n--- Menu ---");
          System.out.println("1. Jogar Cartas");
          System.out.println("2. Passar a vez");
          System.out.println("3. Entregar o Sistema");
          System.out.print("Escolha uma opção: \n");

          
          opcao = Jogo.validaEntrada(resposta);

          
          switch (opcao) {
              case 1:
                  respondeu = true;
                  jogador.jogaCarta(jogador);
                   if(jogador.energia <10)
                  {
                  jogador.energia+=1;
                  }
                 LeArquivo.escreveReplay("Jogador " + nome + " encerrou seu turno de cartas!");
                  break; // Sai do switch

              case 2:
                  respondeu=true;
                  if(jogador.energia <10)
                  {
                  jogador.energia+=1;
                  }                  
                  LeArquivo.escreveReplay("Jogador " + nome + " passou a vez");
                  break;
              case 3:
                  respondeu=true;
                  jogador.rendeu =true;
                  LeArquivo.escreveReplay("Jogador " + nome + " rendeu o sistema!");
                  break; 

              default:
                  System.out.println("Opção inválida. Tente novamente.");
          }
        }
    }

    public void jogaCarta(Jogador jogador) {
       boolean podeJogar = true;
        while(podeJogar){
            jogador.reiniciaMao();
            Scanner cartaSelecionada = new Scanner(System.in);
  
            if(jogador.jogadas.isEmpty())
            {
                   jogador.reiniciaMao();
                    System.out.println("------------------------------");
                     for(Carta carta : jogador.mao)
                    {
                        System.out.println(jogador.mao.indexOf(carta)+1 + " - " + carta.exibeCartaSimples());
                        
                    }
                     
                    System.out.println("Escolha a carta pelo número: ");
                     boolean indiceValido=false;
                      int resposta =0;
                    while(!indiceValido)
                    {
                            resposta = Jogo.validaEntrada(cartaSelecionada)-1;
                            if(resposta < jogador.mao.size() && resposta>=0)
                            {
                                indiceValido=true;
                            }else{
                                System.out.println("Insira um número dentre as cartas");
                            }
                    }
                    if(jogador.mao.get(resposta).custo <= jogador.energia){
                        jogador.jogadas.add(jogador.mao.get(resposta));
                         jogador.alteraEnergia(-jogador.mao.get(resposta).custo);
                        System.out.println("Carta jogada");
                        LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogador.mao.get(resposta).exibeCarta());
                        jogador.mao.remove(resposta);
                    }else{
                    System.out.println("Energia insuficiente para jogar esta carta! Escolha outra");
                    }
            }else{
            if(jogador.getEnergia() > 0)
            {  
                System.out.println("--- Jogando Cartas ---");
                System.out.println("1. Continuar");
                System.out.println("2. Parar de jogar");
                System.out.println("Energia Restante: " + jogador.getEnergia());
                System.out.print("Escolha uma opção: ");
                int resposta = Jogo.validaEntrada(cartaSelecionada);
                switch(resposta){
                    case 1:
                        jogador.reiniciaMao();
                        System.out.println("------------------------------");
                         for(Carta carta: jogador.mao)
                        {
                            System.out.println(jogador.mao.indexOf(carta)+1 + " - " + carta.exibeCartaSimples());

                        }
                        System.out.println("Escolha a carta pelo número: ");
                        boolean indiceValido=false;
                        while(!indiceValido)
                            {
                                resposta = Jogo.validaEntrada(cartaSelecionada)-1;
                                if(resposta < jogador.mao.size() && resposta>=0)
                                {
                                    indiceValido=true;
                                }else{
                                    System.out.println("Insira um número dentre as cartas");
                                }
                            }
                        if(jogador.mao.get(resposta).custo <= jogador.getEnergia()){
                            jogador.jogadas.add(jogador.mao.get(resposta));
                            jogador.alteraEnergia(-jogador.mao.get(resposta).custo);
                            System.out.println("Carta jogada");
                            LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + jogador.mao.get(resposta).exibeCarta());
                            jogador.mao.remove(resposta);
                            break;
                        }
                        System.out.println("Energia insuficiente para jogar esta carta! Escolha outra");
                        break;

                    case 2:
                        podeJogar = false;
                        break;
                }
            }else
            {
            System.out.println("--- Jogando Cartas ---");
            System.out.println("1. Parar de jogar");
            System.out.println("Energia Restante: " + jogador.getEnergia());
            System.out.print("Escolha uma opção: ");
            int resposta = Jogo.validaEntrada(cartaSelecionada);
            switch(resposta){
                case 1:
                     podeJogar = false;
                    break;
                default: System.out.println("Escolha certo!");
                    
            }
          }
        }
        }
    }
    public CartaAtk maiorAtaque()
    {
        CartaAtk maior= new CartaAtk(" "," ",0,0," ");
        for(Carta cartaJogada : jogadas)
        {
            if(cartaJogada.poder > maior.poder && cartaJogada.tipo.equals("ATAQUE"))
            {
                maior= (CartaAtk)cartaJogada;
            }
        }
        return maior;
    }
    
    public void calculaCartas(Jogador jogador1)
    {
         for(int i=0;i<jogador1.jogadas.size();i++)
        {
            switch(jogador1.jogadas.get(i).tipo)
            {
                case "ATAQUE" -> {
                    jogador1.ataque+=jogador1.jogadas.get(i).poder;
                    break;
                }
                case "DEFESA" -> {
                    jogador1.defesa+=jogador1.jogadas.get(i).poder;
                    break;
                }
            }
        }  
    }
    public void calculaSuportes(Jogador jogador1, Jogador jogador2)
    {
        
        for(int i=0;i<jogador1.jogadas.size();i++)
            {
                if(jogador1.jogadas.get(i).tipo.equals("SUPORTE"))
                        {
                            CartaSup mesa = (CartaSup) jogador1.jogadas.get(i);
                            switch(mesa.efeito)
                                {
                                    case "AUMENTA_ATAQUE" -> 
                                    {
                                        boolean teveAtaqueJogado = false;
                                        for(Carta cartaJogada : jogador1.jogadas)
                                        {
                                            if("ATAQUE".equals(cartaJogada.tipo)) teveAtaqueJogado = true;
                                        }
                                        if(teveAtaqueJogado)
                                        {
                                        jogador1.maiorAtaque().poder *= (1 + jogador1.jogadas.get(i).poder);
                                        jogador1.ataque = 0;
                                        jogador1.defesa = 0;
                                        jogador1.calculaCartas(jogador1);
                                        System.out.println("A carta " + jogador1.maiorAtaque().nome + " do jogador " + jogador1.nome + " teve seu ataque aumentado em " +
                                        jogador1.jogadas.get(i).poder + " resultando em " + jogador1.maiorAtaque().poder);
                                        LeArquivo.escreveReplay("A carta " + jogador1.maiorAtaque().nome + " do jogador " + jogador1.nome + " teve seu ataque aumentado em " +
                                        jogador1.jogadas.get(i).poder + " resultando em " + jogador1.maiorAtaque().poder);
                                        }
                                        break;
                                    }
                                    case "DIMINUI_ATAQUE" -> 
                                    {
                                        jogador2.ataque -= jogador2.ataque * jogador1.jogadas.get(i).poder ;
                                        System.out.println("O jogador " + jogador2.nome + " teve seu ataque reduzido em " +
                                        jogador1.jogadas.get(i).poder + " resultando em " + jogador2.ataque);
                                        LeArquivo.escreveReplay("O jogador " + jogador2.nome + " teve seu ataque reduzido em " +
                                        jogador1.jogadas.get(i).poder + " resultando em " + jogador2.ataque);
                                         break;
                                    }
                                    case "AUMENTA_VIDA" -> 
                                    {   
                                        jogador1.setVida(jogador1.getVida() +  (int) jogador1.jogadas.get(i).poder ) ;
                                         
                                        System.out.println("O jogador " + jogador1.nome + " teve sua vida resturada em " +
                                                jogador1.jogadas.get(i).poder + " resultando em " + jogador1.getVida());
                                        LeArquivo.escreveReplay("O jogador " + jogador1.nome + " teve sua vida resturada em " +
                                                jogador1.jogadas.get(i).poder + " resultando em " + jogador1.getVida());
                                         break;
                                    }
                                }
                        }
            }
        for(int i=0;i<jogador2.jogadas.size();i++)
            {
                if(jogador2.jogadas.get(i).tipo.equals("SUPORTE"))
                        {
                            CartaSup mesa = (CartaSup) jogador2.jogadas.get(i);
                            switch(mesa.efeito)
                                {
                                    case "AUMENTA_ATAQUE":  jogador2.maiorAtaque().poder *= (1 + jogador2.jogadas.get(i).poder);
                                                            jogador2.ataque = 0;
                                                            jogador2.defesa = 0;
                                                            jogador2.calculaCartas(jogador2);
                                                            System.out.println("A carta " + jogador2.maiorAtaque().nome + " do jogador " + jogador2.nome + " teve seu ataque aumentado em " +
                                                            jogador2.jogadas.get(i).poder + " resultando em " + jogador2.maiorAtaque().poder);
                                                            LeArquivo.escreveReplay("A carta " + jogador2.maiorAtaque().nome + " do jogador " + jogador2.nome + " teve seu ataque aumentado em " +
                                                            jogador2.jogadas.get(i).poder + " resultando em " + jogador2.maiorAtaque().poder);
                                                            break;
                                    case "DIMINUI_ATAQUE": jogador1.ataque -= jogador1.ataque * jogador2.jogadas.get(i).poder ;   
                                                            System.out.println("O jogador " + jogador1.nome + " teve seu ataque reduzido em " +
                                                            jogador2.jogadas.get(i).poder + " resultando em " + jogador1.ataque);
                                                            LeArquivo.escreveReplay("O jogador " + jogador1.nome + " teve seu ataque reduzido em " +
                                                            jogador2.jogadas.get(i).poder + " resultando em " + jogador1.ataque);
                                                            break;
                                    case "AUMENTA_VIDA": 
                                                        jogador2.vida +=  jogador2.jogadas.get(i).poder; 
                                                        System.out.println("O jogador " + jogador2.nome + " teve sua vida resturada em " +
                                                        jogador2.jogadas.get(i).poder + " resultando em " + jogador2.vida);
                                                        LeArquivo.escreveReplay("O jogador " + jogador2.nome + " teve sua vida resturada em " +
                                                        jogador2.jogadas.get(i).poder + " resultando em " + jogador2.vida);
                                                        break;
                                }
                        }
            }
    }
}
