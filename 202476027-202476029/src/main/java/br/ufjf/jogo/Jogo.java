/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufjf.jogo;
import br.ufjf.componentes.Carta;
import br.ufjf.componentes.LeArquivo;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author mathe
 */
public class Jogo { 
    int rodada=1;
    
    public static int validaEntrada(Scanner resposta)
    {
        int numero =0;
        boolean entradaValida = false;
        while(!entradaValida)
        {
        try {
            numero = resposta.nextInt();
            entradaValida = true; 
            
        } catch (java.util.InputMismatchException e) {
            
            System.out.println("Erro! Por favor, digite um número.");
            resposta.next(); 
        }
        }
        return numero;
    }
    public static void exibirEstado(Jogador jogador1,Jogador jogador2)
    {
        System.out.println("\n" + jogador1.nome.toUpperCase() + "\nID:"+ jogador1.id + "\nVida: " + jogador1.getVida() + "\nEnergia: "+jogador1.getEnergia());
        LeArquivo.escreveReplay("\n" + jogador1.nome.toUpperCase() + "\nID:"+ jogador1.id + "\nVida: " + jogador1.getVida() + "\nEnergia: "+jogador1.getEnergia());
        
        System.out.println("Cartas na mesa:");
        for(Carta carta : jogador1.jogadas)
        {
            System.out.println(carta.exibeCarta());
        }
         LeArquivo.escreveReplay("Cartas na mesa:");
        for(Carta carta : jogador1.jogadas)
        {
            LeArquivo.escreveReplay(carta.exibeCarta());
        }
        System.out.println("\n" + jogador2.nome.toUpperCase() + "\nID:"+ jogador2.id + "\nVida: " + jogador2.getVida() + "\nEnergia: "+jogador2.getEnergia() + "\n---------------------");
         LeArquivo.escreveReplay("\n" + jogador2.nome.toUpperCase() + "\nID:"+ jogador2.id + "\nVida: " + jogador2.getVida() + "\nEnergia: "+jogador2.getEnergia() + "\n---------------------");
    }
    void iniciarJogo(Jogador jogador1, Jogador jogador2) {
       
        Random rand = new Random();
        boolean ehTurno = rand.nextBoolean(); // escolhe aleatoriamente quem começa
        boolean jogoAtivo = true;
        Scanner resposta = new Scanner(System.in);
        while (jogoAtivo) {
            Jogador jogadorAtual = ehTurno ? jogador2 : jogador1;
            Jogador oponente = ehTurno ? jogador1 : jogador2;
            // Exibição do estado do jogo 
            System.out.println("---------"+rodada+"º RODADA------------");
            LeArquivo.escreveReplay("---------"+rodada+"º RODADA------------");
            System.out.println("\n--- TURNO DE " + jogadorAtual.nome.toUpperCase()+ " ---");
            LeArquivo.escreveReplay("\n--- TURNO DE " + jogadorAtual.nome.toUpperCase()+ " ---");
            exibirEstado(jogadorAtual,oponente ); 
            
           // Lógica de Ação
            jogadorAtual.realizarAcao(jogadorAtual, resposta);
            if (jogadorAtual.rendeu) { 
                 finalizarJogo(oponente, jogadorAtual);
                 break;
            }
            System.out.println("\n--- TURNO DE " + oponente.nome.toUpperCase() + " ---");
             LeArquivo.escreveReplay("\n--- TURNO DE " + oponente.nome.toUpperCase()+ " ---");
            exibirEstado(jogadorAtual,oponente ); 

           oponente.realizarAcao(oponente, resposta);

            if (oponente.rendeu) {
                finalizarJogo(jogadorAtual,oponente);
                break;
            }
            // Cálculo de Dano, Vida e Energia
            calculaConfronto(jogadorAtual, oponente);     
           
            
            // Verificação de Fim de Jogo 
            if (jogadorAtual.getVida()<= 0 || oponente.getVida() <= 0) {
                jogoAtivo = false;
                finalizarJogo(jogadorAtual.getVida() <= 0 ? oponente : jogadorAtual, jogadorAtual.getVida() <= 0 ? jogadorAtual : oponente);
            }
            
            // Alterna o turno
            ehTurno = !ehTurno;
            rodada+=1;
        }
    }
     
    void finalizarJogo(Jogador vencedor, Jogador perdedor) {
         LeArquivo.escreveReplay("O jogador "+vencedor.nome + " ganhou de " + perdedor.nome + " com a vida restante de " + vencedor.getVida() + " pontos!");
         System.out.println("O jogador "+vencedor.nome + " ganhou de " + perdedor.nome + " com a vida restante de " + vencedor.getVida() + " pontos!");
         System.out.println("O replay da partida pode ser encontrado em 'Replay.txt'. Atenção, o arquivo é sobreescrito ao iniciar um novo jogo, se for do interesse,"
         + " copie para outro arquivo de sua preferência!");
         LeArquivo.escreveReplay("Jogo finalizado!");
    }

    public void calculaConfronto(Jogador jogadorAtual, Jogador oponente) 
    {
        
        jogadorAtual.calculaCartas(jogadorAtual);
        oponente.calculaCartas(oponente);
        System.out.println("---------------------------------------------------------------");
        LeArquivo.escreveReplay("---------------------------------------------------------------");
        jogadorAtual.calculaSuportes(jogadorAtual, oponente);
        
        int danoAoOponente = Math.max(0, jogadorAtual.ataque - oponente.defesa);
        int danoAoJogador = Math.max(0, oponente.ataque - jogadorAtual.defesa);

        System.out.println("---------------------------------------------------------------\nResultado da " + rodada + "º Rodada!\n" + 
                jogadorAtual.nome.toUpperCase() + " Possuia "+ jogadorAtual.ataque + " de Ataque e " + jogadorAtual.defesa + " de Defesa!\n" + 
                oponente.nome.toUpperCase() + " Possuia " +  oponente.ataque + " de Ataque e " +oponente.defesa + " de Defesa!" + 
                "\nResultando em " + jogadorAtual.nome.toUpperCase() + " receber " + danoAoJogador + " de dano enquanto " + 
                oponente.nome.toUpperCase() + " recebeu " +   danoAoOponente + " de dano!" 
                + "\n---------------------------------------------------------------" );
        
        LeArquivo.escreveReplay("---------------------------------------------------------------\nResultado da " + rodada + "º Rodada!\n"
                + jogadorAtual.nome.toUpperCase() +" Possuia " +  jogadorAtual.ataque + " de Ataque e " + jogadorAtual.defesa + " de Defesa!\n"+
                oponente.nome.toUpperCase() + " Possuia " +  oponente.ataque + " de Ataque e " + oponente.defesa + " de Defesa!\n" + 
                "Resultando em "+jogadorAtual.nome.toUpperCase()+" receber "+danoAoJogador+" de dano enquanto "+
                oponente.nome.toUpperCase()+" recebeu "+danoAoOponente+" de dano!"
                + "\n---------------------------------------------------------------");
        
        oponente.setVida((int) (Math.round((oponente.getVida() - danoAoOponente)/ 10.0) * 10 ));
        jogadorAtual.setVida((int) (Math.round((jogadorAtual.getVida() - danoAoJogador)/ 10.0 ) * 10));
        if(jogadorAtual.getVida() > 100)
            {
                jogadorAtual.setVida(100);
            }
        if(oponente.getVida() > 100)
            {
                oponente.setVida(100);
            }
        if(jogadorAtual.getEnergia() <10)
            {
                jogadorAtual.alteraEnergia(1);
            }
        if(oponente.getEnergia() <10)
            {
                oponente.alteraEnergia(1);
            }  
        oponente.defesa =0 ;
        oponente.ataque = 0;
        jogadorAtual.ataque = 0;
        jogadorAtual.defesa=0;
        jogadorAtual.jogadas.clear();
        oponente.jogadas.clear();
    }

    
    public static void main(String[] args) throws IOException 
    {
        LeArquivo.comecaReplay("Jogo iniciado!");
        Jogo um = new Jogo(); 
        int numeroJogadores =1;
        int Aleatoriedade = 1;
        Scanner resposta = new Scanner(System.in);           
        System.out.println("""
                           Bem vindo ao Cyber Duel: Guerra de Hackers!
                           Em um futuro pr\u00f3ximo, as guerras deixaram os campos f\u00edsicos e passaram a ser
                           travadas no ciberespa\u00e7o. Megacorpora\u00e7\u00f5es disputam poder por meio de ataques
                           silenciosos, enquanto os hackers mais habilidosos do planeta se enfrentam em
                           duelos estrat\u00e9gicos, usando cartas digitais baseadas em NFTs como armas de
                           combate.
                           Cada carta representa uma ferramenta \u00fanica \u2014 ataques liberam v\u00edrus e exploits
                           poderosos, defesas erguem firewalls e contramedidas, e cartas de suporte
                           garantem vantagens t\u00e1ticas cruciais. Nesse universo, cada jogador assume o
                           papel de um hacker com estilo e estrat\u00e9gias pr\u00f3prias, lutando para dominar
                           sistemas e proteger seus servidores contra as investidas do inimigo.
                           
                           Agora escolha quantos jogadores vão participar do duelo!
                           Responda com: 
                           0 - para ver dois Bots se enfrentando.
                           1 - para enfrentar um Bot.
                           2 - para enfrentar outro jogador""");
         boolean indiceValido=false;
        while(!indiceValido)
        {
           numeroJogadores = Jogo.validaEntrada (resposta);
           if(numeroJogadores >= 0 && numeroJogadores <=2)
           {
               indiceValido=true;
           }else{
               System.out.println("Insira somente de 0 à 2");
           }
        }
        switch (numeroJogadores) {
            case 0:
                {
                    Bot robo = new Bot(0);
                    robo.escolheSelecao(1);
                    Bot robo2 = new Bot(2);
                    robo2.escolheSelecao(1);
                    um.iniciarJogo(robo,robo2);
                    break;
                }
            case 1:
                {
                    resposta.nextLine();
                    System.out.println("Insira seu nome:");
                    String nome = resposta.nextLine();
                    System.out.println("Agora insira seu id:");
                    int id = Jogo.validaEntrada(resposta);
                    Jogador jogador1 = new Jogador( nome,id);
                    System.out.println("Deseja utilizar a seleção de carta aleatória?\nResponda 1 para 'sim' ou 0 para 'não'");
                    indiceValido=false;
                    while(!indiceValido)
                    {
                        Aleatoriedade = Jogo.validaEntrada(resposta);
                        if(Aleatoriedade == 1 || Aleatoriedade ==0)
                        {
                            indiceValido=true;
                        }else{
                            System.out.println("Insira somente 1 ou 0");
                        }
                    }       jogador1.escolheSelecao(Aleatoriedade);
                    Bot robo = new Bot(0);
                    robo.escolheSelecao(1);
                    um.iniciarJogo(jogador1,robo);
                    break;
                }
            default:
                {
                    resposta.nextLine();
                    System.out.println("Insira seu nome:");
                    String nome = resposta.nextLine();
                    System.out.println("Agora insira seu id:");
                    int id = Jogo.validaEntrada(resposta);
                    Jogador jogador1 = new Jogador( nome,id);
                    System.out.println("Deseja utilizar a seleção de carta aleatória?\nResponda 1 para 'sim' ou 0 para 'não'");
                    indiceValido=false;
                    while(!indiceValido)
                    {
                        Aleatoriedade = Jogo.validaEntrada(resposta);
                        if(Aleatoriedade == 1 || Aleatoriedade ==0)
                        {
                            indiceValido=true;
                        }else{
                            System.out.println("Insira somente 1 ou 0");
                        }
                    }       jogador1.escolheSelecao(Aleatoriedade);
                    resposta.nextLine();
                    System.out.println("Insira seu nome:");
                    nome = resposta.nextLine();
                    System.out.println("Agora insira seu id:");
                    id = Jogo.validaEntrada(resposta);
                    Jogador jogador2 = new Jogador(nome,id);
                    System.out.println("Deseja utilizar a seleção de carta aleatória?\nResponda 1 para 'sim' ou 0 para 'não'");
                    indiceValido=false;
                    while(!indiceValido)
                    {
                        Aleatoriedade = Jogo.validaEntrada(resposta);
                        if(Aleatoriedade == 1 || Aleatoriedade ==0)
                        {
                            indiceValido=true;
                        }else{
                            System.out.println("Insira somente 1 ou 0");
                        } 
                    }       jogador2.escolheSelecao(Aleatoriedade);
                    um.iniciarJogo(jogador1,jogador2);
                    break;
                }
        }
    }      
}

