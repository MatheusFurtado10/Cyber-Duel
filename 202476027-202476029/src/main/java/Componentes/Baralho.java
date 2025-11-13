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
    List<Carta> ataque = new ArrayList<>();
    List<Carta> defesa = new ArrayList<>();
    List<Carta> suporte = new ArrayList<>();
    
    public void criaBaralhoAtaque() throws IOException
    {
        ataque = LeArquivo.leCarta("ataque.csv");
        
        for(Carta a : ataque)
        {
            a.exibeCarta();
        }
    }
    
    public void criaBaralhoDefesa() throws IOException
    {
        defesa = LeArquivo.leCarta("defesa.csv");
        
        for(Carta a : defesa)
        {
            a.exibeCarta();
        }
    }
    
    public void criaBaralhoSuporte() throws IOException
    {
        suporte = LeArquivo.leCarta("suporte.csv");
        
         for(Carta a : suporte)
        {
            a.exibeCarta();
        }
    }
}
