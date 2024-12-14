SELECT *
FROM post p
WHERE EXISTS (
    SELECT 1
    FROM comment c
    WHERE c.post_id = p.post_id
    GROUP BY c.post_id
    HAVING COUNT(*) < 2
)
ORDER BY p.post_id
LIMIT 10;
