/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.nio.charset.StandardCharsets;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lucas
 */
public class LeArquivo {
    
    public static List<Carta> leCarta(String path) throws IOException  {
        Path arquivo = Paths.get(path);
        List<String> infoCartas = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        List<Carta> deck = new ArrayList<>();
        
        for(int i=1;i<infoCartas.size();i++)
        {
            String linha = infoCartas.get(i);
            String[] divide = linha.split(","); 
            
            String nome = divide[0];
            String tipo = divide[1];
            int poder = parseInt(divide[2]);
            int custo = parseInt(divide[3]);
            String descricao = divide[4];
            
            deck.add(new Carta(nome,tipo,poder,custo,descricao));
            
        }
        return deck;
    }
}
