/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.nio.charset.StandardCharsets;
import java.io.IOException;
import static java.lang.Float.parseFloat;
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
    
     public static List<CartaDef> leCartaDef(String path) throws IOException{
        Path arquivo = Paths.get(path);
        List<String> infoCartas = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        List<CartaDef> deck = new ArrayList<>();
            for(int i=1;i<infoCartas.size();i++)
        {
            String linha = infoCartas.get(i);
            String[] divide = linha.split(","); 
            
            String nome = divide[0];
            String tipo = divide[1];
            float poder = parseFloat(divide[2]);
            float custo = parseFloat(divide[3]);
            String descricao = divide[4];

            deck.add(new CartaDef(nome,tipo,poder,custo,descricao));
            
        }
            return deck;
    }
        public static List<CartaAtk> leCartaAtk(String path) throws IOException{
        Path arquivo = Paths.get(path);
        List<String> infoCartas = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        List<CartaAtk> deck = new ArrayList<>();
            for(int i=1;i<infoCartas.size();i++)
        {
            String linha = infoCartas.get(i);
            String[] divide = linha.split(","); 
            
            String nome = divide[0];
            String tipo = divide[1];
            float poder = parseFloat(divide[2]);
            float custo = parseFloat(divide[3]);
            String descricao = divide[4];

            deck.add(new CartaAtk(nome,tipo,poder,custo,descricao));
            
        }
            return deck;
    }
    
    public static List<CartaSup> leCartaSup(String path) throws IOException{
        Path arquivo = Paths.get(path);
        List<String> infoCartas = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        List<CartaSup> deck = new ArrayList<>();
            for(int i=1;i<infoCartas.size();i++)
        {
            String linha = infoCartas.get(i);
            String[] divide = linha.split(","); 
            
            String nome = divide[0];
            String tipo = divide[1];
            float poder = parseFloat(divide[2]);
            float custo = parseFloat(divide[3]);
            String efeito =divide[4];
            String descricao = divide[5];

            deck.add(new CartaSup(nome,tipo,poder,custo,efeito,descricao));
            
        }
            return deck;
    }
}
