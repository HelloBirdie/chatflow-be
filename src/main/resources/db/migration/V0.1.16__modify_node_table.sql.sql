-- 删除 parent_id 列
ALTER TABLE "node" DROP COLUMN "parent_id";

-- 添加 x 和 y 字段
ALTER TABLE "node"
    ADD COLUMN "x" BIGINT DEFAULT 0 NOT NULL,
ADD COLUMN "y" BIGINT DEFAULT 0 NOT NULL;