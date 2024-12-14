SELECT COUNT(*)
FROM profile p
WHERE NOT EXISTS (
    SELECT 1
    FROM post
    WHERE post.profile_id = p.profile_id
);
