/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.componentes;

import java.util.List;


/**
 *
 * @author mathe
 */
  public abstract class Carta  {
    public String nome;
    public String tipo;
    public float poder;
    public int custo;
    public String descricao;
    
    // Construtor base para inicializar os atributos comuns
    public Carta(String n, String t,float p, int c, String desc) {
        this.nome = n;
        this.tipo = t; 
        this.poder = p;
        this.custo = c;
        this.descricao = desc;
    }
    
    public String exibeCartaSimples()
        {
          
            return(this.nome + " Tipo: " + this.tipo +" Poder: " + this.poder + " Custo:" + this.custo);
        }
    
    public String exibeCarta()
        {
           
            return(this.nome + " Tipo: " + this.tipo + " Poder: " + this.poder + " Custo: " + this.custo + " Descricao: " + this.descricao);
        } 
    
    public static void imprimirLista(List<Carta> cartas, String tipo, int n) 
    {
        System.out.println("Escolha suas "+n+" cartas de "+tipo+" para sua mao de jogo:");
        System.out.println("--------------------------------------------------------------------------------------------");
        int totalCartas = cartas.size();
        if (totalCartas == 0) return; 
        int meio = (int) Math.ceil(totalCartas / 2.0);
        String formato = "%-90s"; 
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
}
