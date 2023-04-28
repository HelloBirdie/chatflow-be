CREATE TABLE IF NOT EXISTS "conversation_pair" (
   "id" BIGSERIAL PRIMARY KEY,
   "mindmap_id" BIGINT NOT NULL,
   CONSTRAINT fk_mindmap_conversation_pair
        FOREIGN KEY(mindmap_id)
        REFERENCES "mindmap"(id),
   "user_messsage_id" BIGSERIAL NOT NULL,
   CONSTRAINT fk_user_message_conversation_pair
        FOREIGN KEY(user_messsage_id)
        REFERENCES "message"(id),
   "ai_message_id" BIGINT NOT NULL,
   CONSTRAINT fk_ai_message_conversation_pair
        FOREIGN KEY(ai_message_id)
        REFERENCES "message"(id),
   "is_deleted" BOOLEAN DEFAULT FALSE NOT NULL,
   "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
   "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
