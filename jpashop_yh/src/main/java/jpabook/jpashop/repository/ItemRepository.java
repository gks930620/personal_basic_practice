package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor  //final
public class ItemRepository  {

     private final EntityManager em;


     public void save(Item item){
         if(item.getId()== null) {
             em.persist(item);
         }else{
             em.merge(item); // update 비슷한,  merge는 어렵다.  최대한 변경감지로 하나하나 쓰는게 훨 씬 낫다.
         }
     }


     public Item findOne (Long id){
         return em.find(Item.class,id);
     }

     public List<Item> findAll(){
         return em.createQuery("select i from Item i" , Item.class)
                 .getResultList();
     }

}
