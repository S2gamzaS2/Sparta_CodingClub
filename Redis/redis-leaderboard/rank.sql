SELECT i.id, SUM(o.count)
FROM item i
    INNER JOIN orders o
        ON i.id = o.item_id
GROUP BY i.id
ORDER BY SUM(o.count) DESC
LIMIT 10;
