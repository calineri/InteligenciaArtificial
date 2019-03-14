/*Alunos : Wellington Maciel
 *         Celso Alineri
 *         
 *         Superior em Jogos digitais - 3o Semestre.
 * 
 * */
package tilemap;

import java.util.ArrayList;
import java.util.List;

public class Node {
    
    private int ID;
    private Node father;
    private boolean visited;
    private boolean blocked;
    private List<Node> neighbors;
    
    public Node(int ID){
        this.ID = ID;
        this.visited = false;
        this.blocked = false;
        this.neighbors = new ArrayList();
    }
    
    public Node(Node node){
        this.ID = node.getID();
        this.visited = node.isVisited();
        this.blocked = node.isBlocked();
        this.neighbors = node.getNodes();
    }
    
    /* nao quero que altere o ID de ninguem
    public void setID(int ID){
        this.ID = ID;
    }
    */
    
    public int getID(){
        return this.ID;
    }
    
    public Node getFather(){
        return this.father;
    }
    
    public void setFather(Node father){
        this.father = father;
    }
    
    public void setVisited(boolean v){
        this.visited = v;
    }
    
    public boolean isVisited(){
        return this.visited;
    }
    
    public void setBlocked(boolean b){
        this.blocked = b;
    }
    
    public boolean isBlocked(){
        return this.blocked;
    }
    
    public void setNodes(Node node){
        this.neighbors.add(node);
    }
    
    public List<Node> getNodes(){
        return this.neighbors;
    }
    
    
}
