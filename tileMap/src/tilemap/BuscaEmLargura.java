/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilemap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author celso.alineri
 */
public class BuscaEmLargura {
    public static List<Node> fila = new ArrayList();
    public static List<Node> caminho = new ArrayList();
    public static int tamanhoDoMapa;    
    
    public static List<Node> buscaEmLargura(Node noInicial, Node noDestino, Map mapa){
        
        Node noAtual = noInicial;
        tamanhoDoMapa = mapa.getMap().size();
        noAtual.setVisited(true);
        fila.add(noAtual);
        
        while(!fila.isEmpty()){
            
            if(noAtual.equals(noDestino)){
                return montaCaminho(noInicial, noDestino);
            }
            
            noAtual = fila.remove(0);
            
            for(Node no: noAtual.getNodes()){
                if(!no.isVisited() && !no.isBlocked()){
                    no.setFather(noAtual);
                    no.setVisited(true);
                    fila.add(no);
                }
            }
        }

        System.out.println("Nao achou caminho");
        return null;
    }
    
    private static List<Node> montaCaminho(Node noInicial, Node noDestino){

        List<Node> listaAuxiliar = new ArrayList();
        Node noAtual = noDestino;
        int contador = 0;
        
        while(!listaAuxiliar.contains(noInicial) || contador > tamanhoDoMapa){
            listaAuxiliar.add(noAtual);
            noAtual = noAtual.getFather();
            contador++;
        }
        
        Collections.reverse(listaAuxiliar);
        
        System.out.println("Caminho: ");
        for(Node no: listaAuxiliar){
            System.out.print(" -> " + no.getID());
        }
        System.out.println("");
        System.out.println("Fim! ");
        
        return listaAuxiliar;
        
    }
    
}
