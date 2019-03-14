/*Alunos : Wellington Maciel
 *         Celso Alineri
 *         
 *         Superior em Jogos digitais - 3o Semestre.
 * 
 * */
package tilemap;

public class TileMap {

    public static void main(String[] args) {
        
        Map mapa = new Map(7,6);
        
        /*
        System.out.println(mapa.getMap().get(0));
        System.out.println(mapa.getMap().get(0).getID());
        */

        for(Node node: mapa.getMap().get(8).getNodes()){
            System.out.println(node.getID());
        }
        
        BuscaEmLargura.buscaEmLargura(mapa.getMap().get(0), mapa.getMap().get(39), mapa);
        
    }
    
}
