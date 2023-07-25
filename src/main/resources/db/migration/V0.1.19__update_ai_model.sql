-- Update ai model column to varchar(64)
ALTER TABLE "ai"
    ALTER COLUMN "model" TYPE varchar(64);

-- Set model default to 'chatgpt-3'
ALTER TABLE "ai"
    ALTER COLUMN "model" SET DEFAULT 'chatgpt-3';