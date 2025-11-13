/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;


/**
 *
 * @author Lucas
 */
public class Carta {
    String nome;
    String tipo;
    float poder;
    float custo;
    String efeito;
    String descricao;
    
    public Carta(String n,String t,float p,float c,String f, String desc)
    {
        nome=n;
        tipo=t;
        poder=p;
        custo = c;
        if("SUPORTE".equals(t))
        {
            efeito = f;
        }
        descricao=desc;
    }
    public void exibeCarta()
    {
        System.out.println(this.nome + " " + this.tipo + " " + this.poder + " " + this.custo + " "+this.efeito + " " + this.descricao);
    }
    
    
}
