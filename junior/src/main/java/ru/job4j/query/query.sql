1) select * from product as p
   left join type as t
   on t.id = p.type_id
   where t.name = 'СЫР';

2) select * from product
   where name like '%мороженое%';

3) select * from products
   where month(expired_date) - month(now()) = 1;

4) select * from product as p
   where p.price = (select max(price) from product);

5) select t_p.type_name, count(t_p.product_id) from
        (select p.id as product_id, p.name as product_name,
                t.id as type_id, t.name as type_name from product as p
         right join type as t on t.id = p.type_id) as t_p
   group by t_p.type_id;

6) select * from product as p
   left join type as t
   on t.id = p.type_id
   where t.name = 'СЫР' or t.name = 'МОЛОКО';

 7) select * from t2
        (select t_p.type_name, count(t_p.product_id) as count_product from
               (select p.id as product_id, p.name as product_name,
                       t.id as type_id, t.name as type_name from product as p
                right join type as t on t.id = p.type_id) as t_p
         group by t_p.type_id) as t2
    where count_product < 10;

 8) select p.name, t.name from product
    left join type as t
    on p.type_id = t.id;