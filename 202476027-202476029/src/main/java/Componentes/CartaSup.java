/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

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
    public void exibeCarta()
    {
        System.out.println(this.nome + " " + this.tipo + " " + this.poder + " " + this.custo + " "+this.efeito + " " + this.descricao);
    }
}
