/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.componentes;

/**
 *
 * @author mathe
 */
public class CartaSup extends Carta {
    public String efeito;
    
    public CartaSup(String n, String t, float p, int c, String f, String desc) {
        super(n, t, p, c, desc);
        this.efeito = f;
    }
    @Override
   public String exibeCartaSimples()
   {
       return(this.nome + " Tipo: " + this.tipo +" Poder: " + this.poder + " Custo:" + this.custo + " Efeito: " + this.efeito);
   }
    @Override
    public void exibeCarta()
    {
        System.out.println(this.nome + " Tipo: " + this.tipo + " Poder:" + this.poder + " Custo:" + this.custo + " Efeito: "+this.efeito + " Descrição:" + this.descricao);
    }
}
