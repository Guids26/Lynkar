CREATE TABLE LINK_VISIT (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	visited_at TIMESTAMP NOT NULL DEFAULT NOW(),
	ip_address VARCHAR(45),
	referer TEXT, 
	user_agent TEXT,	
    id_link UUID NOT NULL REFERENCES LINK(ID)
);