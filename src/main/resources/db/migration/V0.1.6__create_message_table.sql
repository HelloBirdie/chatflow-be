CREATE TABLE "message" (
    "id" BIGSERIAL PRIMARY KEY,
    "mindmap_id" BIGINT NOT NULL,
    CONSTRAINT fk_mindmap_message
        FOREIGN KEY(mindmap_id)
        REFERENCES "mindmap"(id),
    "is_ai_message" BOOLEAN NOT NULL,
    "sender_id" BIGINT NOT NULL,
    "text" TEXT NOT NULL,
    "is_deleted" BOOLEAN DEFAULT FALSE NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);