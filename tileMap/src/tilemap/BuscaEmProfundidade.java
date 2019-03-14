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

public class BuscaEmProfundidade {
    public static List<Node> fila = new ArrayList();
    public static List<Node> caminho = new ArrayList();
    public static int tamanhoDoMapa;    
    
    public static List<Node> buscaEmProfundidade(Node noInicial, Node noDestino, Map mapa){
        
        Node noAtual = noInicial;
        tamanhoDoMapa = mapa.getMap().size();
        noAtual.setVisited(true);
        fila.add(noAtual);
        
        while(!fila.isEmpty()){
            
            if(noAtual.equals(noDestino)){
                return montaCaminho(noInicial, noDestino);
            }
            
            noAtual = fila.remove(fila.size()-1);
            
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
