
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author User
 */
@Entity
public class Journal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    @OneToOne  
    private Client client;
    @Temporal(TemporalType.TIMESTAMP)
    private Date takeOnDate;    
    
    public Journal(){}
    
    public Journal(Product p, Client c){ // прописали, что должен делать конструктор при обращении к нему в Deal
        this.client = c;  
        this.product = p;
        this.takeOnDate = new Date();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTakeOnDate() {
        return takeOnDate;
    }

    public void setTakeOnDate(Date takeOnDate) {
        this.takeOnDate = takeOnDate;
    }
   
    @Override // метод get, который мы унаследовали, переопределяем v toString
    public String toString(){
        return "Journal{"
                + "product=" + product.getName()
                + ", client=" + client.getFirstname()+" "+client.getLastname()
                + ", takeOnDate=" + takeOnDate
                +"}";
    }
      @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.product);
        hash = 31 * hash + Objects.hashCode(this.client);
        hash = 31 * hash + Objects.hashCode(this.takeOnDate);
        return hash;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Journal other = (Journal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.takeOnDate, other.takeOnDate)) {
            return false;
        }
        return true;
    }
      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
