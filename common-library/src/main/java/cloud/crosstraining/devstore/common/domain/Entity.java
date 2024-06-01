package cloud.crosstraining.devstore.common.domain;

public interface Entity<ID>  {
    void setId(ID id);
    ID getId();
}



