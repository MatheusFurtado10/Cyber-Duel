/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class Carta {
    String nome;
    String tipo;
    int poder;
    int custo;
    String descricao;
    
    public Carta(String n,String t,int p,int c, String desc)
    {
        nome=n;
        tipo=t;
        poder=p;
        custo = c;
        descricao=desc;
    }
    public void exibeCarta()
    {
        System.out.println(this.nome + " " + this.tipo + " " + this.poder + " " + this.custo + " " + this.descricao);
    }
    public static void CriaBaralhoAtaque() throws IOException
    {
        List<Carta> deck = LeArquivo.leCarta("ataque.csv");
        
        for(Carta a : deck)
        {
            a.exibeCarta();
        }
    }
    
}
