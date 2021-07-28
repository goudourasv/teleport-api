CREATE TABLE favourites(
id UUID PRIMARY KEY,
course_id UUID  REFERENCES courses(id),
user_id UUID REFERENCES users(id)
);