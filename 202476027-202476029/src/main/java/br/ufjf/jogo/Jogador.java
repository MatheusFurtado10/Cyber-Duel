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
    float ataque=0;
    float defesa=0;
    int nAtk = 0;
    int nDef =0;
    int nSup =0;
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
    
    
    public static int selecionarCarta(List<Carta> listaDisponivel, Scanner le) 
    {
        System.out.println("\nEscolha a carta pelo seu numero (1 a " + listaDisponivel.size() + "):");
        boolean entradaValida = false;
        int indice = 0;
        while(!entradaValida)
        {
        try {
            indice = le.nextInt() - 1; 
            entradaValida = true; 
            
        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro! Por favor, digite um número correspondente.");
            le.next(); 
        }
        }
        if (indice < 0 || indice >= listaDisponivel.size()) {
            System.out.println("Erro! Escolha entre o numero correto de cartas!");
            return -1;
        }

        Carta cartaEscolhida = listaDisponivel.get(indice);
        System.out.println("\nCarta escolhida:");
        System.out.println(cartaEscolhida.exibeCarta());
        System.out.println("Tem certeza da sua escolha? Responda com 1 para confirmar.");
        int escolha = Jogo.validaEntrada(le);
        if (1==escolha){
             return 1;
        } 
        System.out.println("Seleção Cancelada");
        return -1; 
       
    }
    public void escolheSelecao(int t) throws IOException
    {
        if(t==1)
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
        while (nAtk < 4) 
        {
            Carta.imprimirLista(cartas,"ataque",4); 
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
            Carta.imprimirLista(cartas,"defesa",4); 
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
            Carta.imprimirLista(cartas,"suporte",2); 

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
                System.out.println(a.exibeCarta());
            }
               maoReserva = mao;

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

    public void jogaCarta(Jogador j1) {
       boolean podeJogar = true;
        while(podeJogar){
            j1.reiniciaMao();
            Scanner cartaSelecionada = new Scanner(System.in);
  
            if(j1.jogadas.isEmpty())
            {
                   j1.reiniciaMao();
                    System.out.println("------------------------------");
                     for(Carta a : j1.mao)
                    {
                        System.out.println(j1.mao.indexOf(a)+1 + " - " + a.exibeCartaSimples());
                        
                    }
                     
                    System.out.println("Escolha a carta pelo número: ");
                     boolean indiceValido=false;
                      int resposta =0;
                    while(!indiceValido)
                    {
                            resposta = Jogo.validaEntrada(cartaSelecionada)-1;
                            if(resposta < j1.mao.size() && resposta>=0)
                            {
                                indiceValido=true;
                            }else{
                                System.out.println("Insira um número dentre as cartas");
                            }
                    }
                    if(j1.mao.get(resposta).custo <= j1.energia){
                        j1.jogadas.add(j1.mao.get(resposta));
                         j1.alteraEnergia(-j1.mao.get(resposta).custo);
                        System.out.println("Carta jogada");
                        LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + j1.mao.get(resposta).exibeCarta());
                        j1.mao.remove(resposta);
                    }else{
                    System.out.println("Energia insuficiente para jogar esta carta! Escolha outra");
                    }
            }else{
                          
            System.out.println("--- Jogando Cartas ---");
            System.out.println("1. Continuar");
            System.out.println("2. Parar de jogar");
            System.out.println("Energia Restante: " + j1.energia);
            System.out.print("Escolha uma opção: ");
            int resposta = Jogo.validaEntrada(cartaSelecionada);
            switch(resposta){
                case 1:
                    j1.reiniciaMao();
                    System.out.println("------------------------------");
                     for(Carta a : j1.mao)
                    {
                        System.out.println(j1.mao.indexOf(a)+1 + " - " + a.exibeCartaSimples());
                        
                    }
                    System.out.println("Escolha a carta pelo número: ");
                    boolean indiceValido=false;
                    while(!indiceValido)
                        {
                            resposta = Jogo.validaEntrada(cartaSelecionada)-1;
                            if(resposta < j1.mao.size() && resposta>=0)
                            {
                                indiceValido=true;
                            }else{
                                System.out.println("Insira um número dentre as cartas");
                            }
                        }
                    if(j1.mao.get(resposta).custo <= j1.getEnergia()){
                        j1.jogadas.add(j1.mao.get(resposta));
                        j1.alteraEnergia(-j1.mao.get(resposta).custo);
                        System.out.println("Carta jogada");
                        LeArquivo.escreveReplay("Jogador " + nome + " jogou a carta: " + j1.mao.get(resposta).exibeCarta());
                        j1.mao.remove(resposta);
                        break;
                    }
                    System.out.println("Energia insuficiente para jogar esta carta! Escolha outra");
                    break;
                    
                case 2:
                    podeJogar = false;
                    break;
            }
          }
        }
    }
    public CartaAtk maiorAtaque()
    {
        CartaAtk maior= new CartaAtk(" "," ",0,0," ");
        for(Carta a : jogadas)
        {
            if(a.poder > maior.poder && a.tipo.equals("ATAQUE"))
            {
                maior= (CartaAtk)a;
            }
        }
        return maior;
    }
    
    public void calculaCartas(Jogador j1)
    {
         for(int i=0;i<j1.jogadas.size();i++)
        {
            switch(j1.jogadas.get(i).tipo)
            {
                case "ATAQUE" -> {
                    j1.ataque+=j1.jogadas.get(i).poder;
                    break;
                }
                case "DEFESA" -> {
                    j1.defesa+=j1.jogadas.get(i).poder;
                    break;
                }
            }
        }  
    }
    public void calculaSuportes(Jogador j1, Jogador j2)
    {
        
        for(int i=0;i<j1.jogadas.size();i++)
            {
                if(j1.jogadas.get(i).tipo.equals("SUPORTE"))
                        {
                            CartaSup mesa = (CartaSup) j1.jogadas.get(i);
                            switch(mesa.efeito)
                                {
                                    case "AUMENTA_ATAQUE" -> 
                                    {
                                        j1.maiorAtaque().poder *= (1 + j1.jogadas.get(i).poder);
                                        j1.ataque = 0;
                                        j1.defesa = 0;
                                        j1.calculaCartas(j1);
                                        System.out.println("A carta " + j1.maiorAtaque().nome + " do jogador " + j1.nome + " teve seu ataque aumentado em " +
                                        j1.jogadas.get(i).poder + " resultando em " + j1.maiorAtaque().poder);
                                        LeArquivo.escreveReplay("A carta " + j1.maiorAtaque().nome + " do jogador " + j1.nome + " teve seu ataque aumentado em " +
                                        j1.jogadas.get(i).poder + " resultando em " + j1.maiorAtaque().poder);
                                    }
                                    case "DIMINUI_ATAQUE" -> 
                                    {
                                        j2.ataque -= j2.ataque * j1.jogadas.get(i).poder ;
                                        System.out.println("O jogador " + j2.nome + " teve seu ataque reduzido em " +
                                        j1.jogadas.get(i).poder + " resultando em " + j2.ataque);
                                        LeArquivo.escreveReplay("O jogador " + j2.nome + " teve seu ataque reduzido em " +
                                        j1.jogadas.get(i).poder + " resultando em " + j2.ataque);
                                    }
                                    case "AUMENTA_VIDA" -> 
                                    {   
                                        j1.setVida(j1.getVida() +  (int) j1.jogadas.get(i).poder ) ;
                                         
                                        System.out.println("O jogador " + j1.nome + " teve sua vida resturada em " +
                                                j1.jogadas.get(i).poder + " resultando em " + j1.getVida());
                                        LeArquivo.escreveReplay("O jogador " + j1.nome + " teve sua vida resturada em " +
                                                j1.jogadas.get(i).poder + " resultando em " + j1.getVida());
                                     }
                                }
                        }
            }
        for(int i=0;i<j2.jogadas.size();i++)
            {
                if(j2.jogadas.get(i).tipo.equals("SUPORTE"))
                        {
                            CartaSup mesa = (CartaSup) j2.jogadas.get(i);
                            switch(mesa.efeito)
                                {
                                    case "AUMENTA_ATAQUE":  j2.maiorAtaque().poder *= (1 + j2.jogadas.get(i).poder);
                                                            j2.ataque = 0;
                                                            j2.defesa = 0;
                                                            j2.calculaCartas(j2);
                                                            System.out.println("A carta " + j2.maiorAtaque().nome + " do jogador " + j2.nome + " teve seu ataque aumentado em " +
                                                            j2.jogadas.get(i).poder + " resultando em " + j2.maiorAtaque().poder);
                                                            LeArquivo.escreveReplay("A carta " + j2.maiorAtaque().nome + " do jogador " + j2.nome + " teve seu ataque aumentado em " +
                                                            j2.jogadas.get(i).poder + " resultando em " + j2.maiorAtaque().poder);
                                                            break;
                                    case "DIMINUI_ATAQUE": j1.ataque -= j1.ataque * j2.jogadas.get(i).poder ;   
                                                            System.out.println("O jogador " + j1.nome + " teve seu ataque reduzido em " +
                                                            j2.jogadas.get(i).poder + " resultando em " + j1.ataque);
                                                            LeArquivo.escreveReplay("O jogador " + j1.nome + " teve seu ataque reduzido em " +
                                                            j2.jogadas.get(i).poder + " resultando em " + j1.ataque);
                                                            break;
                                    case "AUMENTA_VIDA": 
                                                        j2.vida +=  j2.jogadas.get(i).poder; 
                                                        System.out.println("O jogador " + j2.nome + " teve sua vida resturada em " +
                                                        j2.jogadas.get(i).poder + " resultando em " + j2.vida);
                                                        LeArquivo.escreveReplay("O jogador " + j2.nome + " teve sua vida resturada em " +
                                                        j2.jogadas.get(i).poder + " resultando em " + j2.vida);
                                                        break;
                                }
                        }
            }
    }
}
