package jpabook.jpashop.repository;


import jpabook.jpashop.api.OrderSimpleApiController;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }


    public List<Order> findAllByString(OrderSearch orderSearch) {

        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(" select o from Order o "
        + " join fetch o.member m "
                + " join fetch o.delivery d ", Order.class)
                .getResultList();  //Lazy 신경안쓰고 그냥 join함   이걸 fetchJoin이라 함
    }

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(" select new jpabook.jpashop.repository.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                " from Order o "
        + " join o.member m "
        +" join o.delivery d" , OrderSimpleQueryDto.class).getResultList();

    }

    public List<Order> findAllWithItem() {
        //쿼리 결과 Order가  X OrderItem개수  만큼 나오는데
        //이 Order 객체(Entity)가 각각 oneToMany를 하니까
        // 똑같은 Entity가   OrderItem개수만큼  중복되겠군
        // JPA는 같은객체로 인식,저장하는데  List에  같은객체를 여러 개 담는 상황이 발생
        //이를 방지하는게 distinct  ,  이는 DB의 distcnt랑 다름.
        // JPA distinct는 Entity가 같은거 중복제거,   DB는 쿼리 결과가 완전히 같아야 중복제거.
        return em.createQuery(" select distinct o from Order o "
        + " join fetch o.member m "
        + " join fetch o.delivery d "
        + " join fetch o.orderItems oi "
        + " join fetch oi.item i" , Order.class).getResultList() ;
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
        return em.createQuery(" select o from Order o "
                + " join fetch o.member m "
                + " join fetch o.delivery d ", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }


    //public List<Order> findAllByCriteria(OrderSearch orderSearch){} 는 복잡해서 안 씀
    //QueryDsl로 쓰자


}
