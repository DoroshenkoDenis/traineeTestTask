--select (select count(order_id) as count_ordersWithPromo
--        from orders
--        where (promocode_id IS NOT NULL)) / (select count(order_id) as count_orders
--                                             from orders)
--           as ratio;

SELECT COUNT(orders.promocode_id) / COUNT(orders.promocode_id is not null) result from orders;