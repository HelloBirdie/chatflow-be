-- Update ai model column to varchar(64)
ALTER TABLE "ai"
    ALTER COLUMN "model" TYPE varchar(64);

-- Set model default to 'DeepSeek-V3-0324'
ALTER TABLE "ai"
    ALTER COLUMN "model" SET DEFAULT 'DeepSeek-V3-0324';