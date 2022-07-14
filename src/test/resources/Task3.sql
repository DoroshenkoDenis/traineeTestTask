--select (select count(order_id) as count_ordersWithPromo
--        from orders
--        where (promocode_id IS NOT NULL)) / (select count(order_id) as count_orders
--                                             from orders)
--           as ratio;

SELECT COUNT(orders.promocode_id) / COUNT(orders.order_id) result from orders;