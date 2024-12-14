SELECT post_id
FROM post p
WHERE EXISTS (
    SELECT 1
    FROM comment c
    WHERE c.post_id = p.post_id
    GROUP BY c.post_id
    HAVING COUNT(*) = 2
)
AND CHAR_LENGTH(p.content) > 20
AND p.title ~ '^[0-9]'
ORDER BY p.post_id;