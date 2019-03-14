/*Alunos : Wellington Maciel
 *         Celso Alineri
 *         
 *         Superior em Jogos digitais - 3o Semestre.
 * 
 * */
package tilemap;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Node> map;
    private int lines;
    private int columns;
    
    public Map(int lines, int columns){
        this.map = new ArrayList();
        this.lines = lines;
        this.columns = columns;
        mapCreate();
        neighboorsCreate();
    }
    
    public List<Node> getMap(){
        return map;
    }
    
    public int getSize(){
        return this.lines * this.columns;
    }
    
    private void mapCreate(){
        int id=0;
        
        for(int i=0; i<this.lines; i++){
            for(int j=0; j<this.columns; j++){
                Node node = new Node(id);
                this.map.add(node);
                id++;
            }
        }
    }
    
    public List<Node> findDiagonal(Node node){
        
        int id = node.getID();
        List<Node> auxList = new ArrayList();
        
        //calcular linha
        int lineNode = (node.getID()/this.lines)+1;
        //calcular coluna
        int columnNode = (node.getID()%this.columns)+1;
        
        //pega canto superior esquerdo
        if(lineNode >1 && columnNode>1){
            auxList.add(map.get((id - this.columns)-1));
        }
        
        //pega canto superior direito
        if(lineNode >1 && columnNode<this.columns){
            auxList.add(map.get((id - this.columns)+1));
        }
        
        //pegar canto infoerior esquerdo
        if (lineNode < map.size() / this.lines && columnNode > 1) {
            auxList.add(map.get((id + this.columns) - 1));
        }
        
        //pegar canto inferior direito
        if (lineNode < map.size() / lines && columnNode < this.columns) {
            auxList.add(map.get((id + this.columns) + 1));
        }
        
        return auxList;
        
    }
    
    public List<Node> findOrthogonal(Node node){
        
        int id = node.getID();
        List<Node> auxList = new ArrayList();
        
        //calcular linha
        int lineNode = (node.getID()/this.lines)+1;
        //calcular coluna
        int columnNode = (node.getID()%this.columns)+1;
        
        //pegar nó superior
        if (lineNode > 1){
            auxList.add(map.get((id - this.columns)));
        }
        
        //pegar nó inferior
        if (lineNode < this.lines-1){
            auxList.add(map.get((id + this.columns)));
        }
        
        //pegar nó esquerda
        if (columnNode > 1){
            auxList.add(map.get((id - 1)));
        }
        
        //pegar nó direita
        if (columnNode < this.columns){
            auxList.add(map.get((id + 1)));
        }
        
        return auxList;
    }
    
    
    private void neighboorsCreate(){
        
        for(Node node: map){
            node.getNodes().addAll(this.findDiagonal(node));
            node.getNodes().addAll(this.findOrthogonal(node));
        }
    }
    
}
