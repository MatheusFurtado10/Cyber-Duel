/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class Baralho {
    List<CartaAtk> ataque = new ArrayList<>();
    List<CartaDef> defesa = new ArrayList<>();
    List<CartaSup> suporte = new ArrayList<>();
    
    public void criaBaralhoAtaque() throws IOException
    {
        ataque = LeArquivo.leCartaAtk("ataque.csv");
        
        for(CartaAtk a : ataque)
        {
            a.exibeCarta();
        }
    }
    
    public void criaBaralhoDefesa() throws IOException
    {
        defesa = LeArquivo.leCartaDef("defesa.csv");
        
        for(CartaDef a : defesa)
        {
            a.exibeCarta();
        }
    }
    
    public void criaBaralhoSuporte() throws IOException
    {
        suporte = LeArquivo.leCartaSup("suporte.csv");
        
         for(CartaSup a : suporte)
        {
            a.exibeCarta();
        }
    }
}
