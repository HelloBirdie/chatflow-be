CREATE TABLE "edge" (
                        "edge_id" BIGINT PRIMARY KEY,
                        "source_id" BIGINT NOT NULL,
                        "target_id" BIGINT NOT NULL,
                        "edge_string" VARCHAR(255) NOT NULL
);
