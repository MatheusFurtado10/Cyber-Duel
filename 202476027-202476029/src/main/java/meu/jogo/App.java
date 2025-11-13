/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package meu.jogo;
import Componentes.Baralho;
import java.io.IOException;

/**
 *
 * @author mathe
 */
public class App {

    public static void main(String[] args) throws IOException {
        Baralho deck = new Baralho();
        deck.criaBaralhoAtaque();
        deck.criaBaralhoDefesa();
        deck.criaBaralhoSuporte();
        
    }
}
