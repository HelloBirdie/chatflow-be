CREATE TABLE "node_tag"(
    "node_id" BIGINT,
    CONSTRAINT fk_node_node_tag
        FOREIGN KEY(node_id)
        REFERENCES "node"(id),
    "tag_id" BIGINT,
    CONSTRAINT fk_tag_node_tag
        FOREIGN KEY(tag_id)
        REFERENCES "tag"(id),
    PRIMARY KEY (node_id, tag_id)
);