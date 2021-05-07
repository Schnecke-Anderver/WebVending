package entity;

import entity.Client;
import entity.Product;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-07T10:26:16")
@StaticMetamodel(Journal.class)
public class Journal_ { 

    public static volatile SingularAttribute<Journal, Date> takeOnDate;
    public static volatile SingularAttribute<Journal, Product> product;
    public static volatile SingularAttribute<Journal, Client> client;
    public static volatile SingularAttribute<Journal, Long> id;

}