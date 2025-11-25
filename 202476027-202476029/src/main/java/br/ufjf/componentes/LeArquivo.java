/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.componentes;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
    public static List<Carta> lerCartas(String path) throws IOException {
        Path arquivo = Paths.get(path);
        List<String> infoCartas = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        List<Carta> deck = new ArrayList<>();
        
            for (int i = 1; i < infoCartas.size(); i++) 
            {
            String linha = infoCartas.get(i);
            String[] divide = linha.split(",");
            Carta novaCarta = null;
            switch (divide.length) {
                case 6 -> {
                        
                        String nome = divide[0];
                        String tipo = divide[1]; 
                        float poder = parseFloat(divide[2]);
                        int custo = parseInt(divide[3]);
                        String efeito = divide[4]; 
                        String descricao = divide[5];
                        novaCarta = new CartaSup(nome, tipo, poder, custo, efeito, descricao);
                    }
                case 5 ->{
                        String nome = divide[0];
                        String tipo = divide[1];
                        float poder = parseFloat(divide[2]);
                        int custo = parseInt(divide[3]);
                        String descricao = divide[4];
                        if (tipo.equalsIgnoreCase("DEFESA")) { 
                            novaCarta = new CartaDef(nome, tipo, poder, custo, descricao);
                        } else{ 
                            novaCarta = new CartaAtk(nome, tipo, poder, custo, descricao);
                        }
                    }
                default -> {
                    System.err.println("ERRO LINHA: "+i);
                    continue;
                }
            }
                if (novaCarta != null) {
                    deck.add(novaCarta);
                }
          }

         return deck;
}
    public static void comecaReplay(String conteudo)
    {
      try(BufferedWriter buffWrite = new BufferedWriter(new FileWriter("replay.txt")))
      {
          String linha = conteudo;
          buffWrite.write(linha + "\n");
      }catch(IOException e){
          System.out.println("Nao deu pra escrever");
      }
    }
    public static void escreveReplay(String conteudo)
    {
      try(BufferedWriter buffWrite = new BufferedWriter(new FileWriter("replay.txt",true)))
      {
          String linha = conteudo;
          buffWrite.append(linha + "\n");
      }catch(IOException e){
          System.out.println("Nao deu pra escrever");
      }
    }
}
