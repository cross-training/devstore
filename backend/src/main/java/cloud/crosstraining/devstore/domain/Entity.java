package cloud.crosstraining.devstore.domain;

public interface Entity<ID>  {
    void setId(ID id);
}

//public abstract class Entity<ID> implements Serializable {
//    protected ID id;
//    public void setId(ID id){
//        this.id = id;
//    }
//    public ID getId() {
//        return this.id;
//    }
//}



