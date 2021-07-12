package PRIM;

public class Par <T>{
	
    private T origen;
    private T destino;
    
    public Par(T o, T d) {
        origen = o;
        destino = d;
    }
    
    public T getOrigen(){
        return this.origen;
    }
    
    public void setOrigen(T n){
        this.origen = n;
    }
    public T getDestino(){
        return this.destino;
    }
    
    public void setDestino(T n){
        this.destino = n;
    }
}