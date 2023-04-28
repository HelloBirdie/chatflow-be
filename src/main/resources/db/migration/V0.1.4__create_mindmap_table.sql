CREATE TABLE "mindmap" (
    "id" BIGSERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "owner_id" BIGINT NOT NULL,
    CONSTRAINT fk_user_mindmap
        FOREIGN KEY(owner_id)
        REFERENCES "user"(id),
    "ai_id" BIGINT NOT NULL,
    CONSTRAINT fk_ai_mindmap
        FOREIGN KEY(ai_id)
        REFERENCES "ai"(id),
    "update_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "create_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "is_deleted" BOOLEAN DEFAULT FALSE NOT NULL
);