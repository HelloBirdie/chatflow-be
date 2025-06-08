-- Insert default AI model
INSERT INTO "ai" ("id", "model") 
VALUES (1, 'DeepSeek-V3-0324')
ON CONFLICT (id) DO NOTHING;