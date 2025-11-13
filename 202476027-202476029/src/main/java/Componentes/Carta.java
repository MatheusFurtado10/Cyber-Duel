/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;


/**
 *
 * @author mathe
 */
  public abstract class Carta  {
    protected String nome;
    protected String tipo; // Você pode querer usar um Enum para 'tipo' no futuro
    protected float poder;
    protected int custo;
    protected String descricao;
    
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
            return(this.nome + " Poder: " + this.poder + " Custo:" + this.custo);
        }
    
    public void exibeCarta()
        {
            System.out.println(this.nome + " Tipo: " + this.tipo + " Poder: " + this.poder + " Custo: " + this.custo + " Descricao: " + this.descricao);
        }
    
   
}
