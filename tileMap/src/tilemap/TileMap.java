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

        /* Testa Busca em Largura / Busca em Profundidade
        for(Node node: mapa.getMap().get(8).getNodes()){
            System.out.println(node.getID());
        }
        
        // Utilizando metodo estático
        BuscaEmLargura.buscaEmLargura(mapa.getMap().get(0), mapa.getMap().get(39), mapa);
        BuscaEmProfundidade.buscaEmProfundidade(mapa.getMap().get(0), mapa.getMap().get(39), mapa);
        
        // Utilizando objetos
        Busca buscaL = new Busca();
        Busca buscaP = new Busca();
        
        buscaL.buscaEmLargura(mapa.getMap().get(0), mapa.getMap().get(39), mapa);
        buscaP.buscaEmProfundidade(mapa.getMap().get(0), mapa.getMap().get(39), mapa);
        */
        
        /* Testa Busca A* */
        mapa.getMap().get(12).setBlocked(true);
        mapa.getMap().get(13).setBlocked(true);
        mapa.getMap().get(14).setBlocked(true);
        mapa.getMap().get(15).setBlocked(true);
        mapa.getMap().get(16).setBlocked(true);
        
        System.out.println("--------------------------------");
       
        AEstrela.aEstrela(mapa.getMap().get(0), mapa.getMap().get(32), mapa);
        
    }
    
}
