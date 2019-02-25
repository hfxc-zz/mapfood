package com.codenation.mapfood.repository;

import com.codenation.mapfood.model.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hfxc on 24/02/19.
 */
public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {
}
