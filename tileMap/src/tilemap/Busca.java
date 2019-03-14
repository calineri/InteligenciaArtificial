/*Alunos : Wellington Maciel
 *         Celso Alineri
 *         
 *         Superior em Jogos digitais - 3o Semestre.
 * 
 * */
package tilemap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Busca {
    private List<Node> fila;
    private int tamanhoDoMapa;    
    
    public Busca(){
        this.fila = new ArrayList();
        this.tamanhoDoMapa = 0;
    }
    
    public List<Node> buscaEmLargura(Node noInicial, Node noDestino, Map mapa){
        
        Node noAtual = noInicial;
        this.tamanhoDoMapa = mapa.getMap().size();
        noAtual.setVisited(true);
        this.fila.add(noAtual);
        
        while(!this.fila.isEmpty()){
            
            if(noAtual.equals(noDestino)){
                return this.montaCaminho(noInicial, noDestino);
            }
            
            noAtual = this.fila.remove(0);
            
            for(Node no: noAtual.getNodes()){
                if(!no.isVisited() && !no.isBlocked()){
                    no.setFather(noAtual);
                    no.setVisited(true);
                    this.fila.add(no);
                }
            }
        }

        System.out.println("Nao achou caminho");
        return null;
    }
    
    public List<Node> buscaEmProfundidade(Node noInicial, Node noDestino, Map mapa){
        
        Node noAtual = noInicial;
        this.tamanhoDoMapa = mapa.getMap().size();
        noAtual.setVisited(true);
        this.fila.add(noAtual);
        
        while(!this.fila.isEmpty()){
            
            if(noAtual.equals(noDestino)){
                return this.montaCaminho(noInicial, noDestino);
            }
            
            noAtual = this.fila.remove(this.fila.size()-1);
            
            for(Node no: noAtual.getNodes()){
                if(!no.isVisited() && !no.isBlocked()){
                    no.setFather(noAtual);
                    no.setVisited(true);
                    this.fila.add(no);
                }
            }
        }

        System.out.println("Nao achou caminho");
        return null;
    }
    
    private List<Node> montaCaminho(Node noInicial, Node noDestino){

        List<Node> listaAuxiliar = new ArrayList();
        Node noAtual = noDestino;
        int contador = 0;
        
        while(!listaAuxiliar.contains(noInicial) || contador > this.tamanhoDoMapa){
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
