CREATE TABLE "node" (
    "id" BIGSERIAL PRIMARY KEY,
    "mindmap_id" BIGINT NOT NULL,
    CONSTRAINT fk_mindmap_node
        FOREIGN KEY(mindmap_id)
        REFERENCES "mindmap"(id),
    "parent_id" BIGINT,
    CONSTRAINT fk_node_node
        FOREIGN KEY(parent_id)
        REFERENCES "node"(id),
    "conversation_pair_id" BIGINT NOT NULL,
    CONSTRAINT fk_conversation_pair_node
        FOREIGN KEY(conversation_pair_id)
        REFERENCES "conversation_pair"(id),
    "user_message" TEXT NOT NULL,
    "ai_message" TEXT NOT NULL,
    "note" TEXT,
    "color" VARCHAR(30),
    "update_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "create_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "is_deleted" BOOLEAN DEFAULT FALSE NOT NULL
);
