/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilemap;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;        

/**
 *
 * @author celso.alineri
 */
public class AEstrela {
    public static List<Node> listaFechada = new ArrayList();
    public static List<Node> listaAberta = new ArrayList();
    public static List<Node> listaCaminho = new ArrayList();
    public static int colunasDoMapa = 0;
    public static int linhasDoMapa = 0;
    public static int tamanhoDoMapa = 0;
    
    public static List<Node> aEstrela(Node noInicial, Node noDestino, Map mapa){
        colunasDoMapa = mapa.getColumns();
        linhasDoMapa = mapa.getLines();
        tamanhoDoMapa = mapa.getSize();
        
        listaFechada.clear();
        listaAberta.clear();
        
        boolean achouCaminho = false;
        
        Node noAtual = noInicial;
        listaAberta.add(noInicial);
        
        while(!achouCaminho){
            noAtual = procurarMenorF();
            listaAberta.remove(noAtual);
            listaFechada.add(noAtual);
            achouCaminho = noAtual.equals(noDestino);
            
            for(Node no: noAtual.getNodes()){
                if(no.isBlocked() || listaFechada.contains(no)){
                    continue;
                }else if(!listaAberta.contains(no)){
                    listaAberta.add(no);
                    no.setFather(noAtual);
                    no.setH(calcularH(no, noDestino));
                    no.setG(calcularG(no, noAtual));
                    no.setF(calcularF(no));
                }else if(no.getG() < noAtual.getG()){
                    no.setFather(noAtual);
                    no.setG(calcularG(noAtual, no));
                    no.setF(calcularF(no));
                }
            }
            //parei aqui
        }
        
        return null;
    }
    
    private static Node procurarMenorF(){
        Collections.sort(listaAberta, Comparator.comparing(Node::getF));
        return listaAberta.get(0);
    }
    
    private static float calcularH(Node noAtual, Node noDestino){
        int posicaoDestinoX = (noDestino.getID() % colunasDoMapa) + 1;
        int posicaoNoAtualX = (noAtual.getID() % colunasDoMapa) + 1;
        
        int distanciaX = posicaoDestinoX > posicaoNoAtualX ? posicaoDestinoX - posicaoNoAtualX : posicaoNoAtualX - posicaoDestinoX;
        
        int posicaoDestinoY = (noDestino.getID() / linhasDoMapa) + 1;
        int posicaoNoAtualY = (noAtual.getID() / linhasDoMapa) + 1;
        
        int distanciaY = posicaoDestinoY > posicaoNoAtualY ? posicaoDestinoY - posicaoNoAtualY : posicaoNoAtualY - posicaoDestinoY;
        
        return (float)Math.sqrt(Math.pow(distanciaX,2) + Math.pow(distanciaY,2))*10;
    }
    
    private static float calcularG(Node noAtual, Node noVizinho){
        if(noVizinho.getID() % colunasDoMapa == noAtual.getID() % colunasDoMapa || 
           noVizinho.getID() + 1 == noAtual.getID() || 
           noVizinho.getID() - 1 == noAtual.getID()){
           
           return noVizinho.getG()+10;
        }
        return noVizinho.getG()+14;
    }
    
    private static float calcularF(Node no){
        return no.getG() + no.getH();
    }
}
