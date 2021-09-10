CREATE TABLE rating(
id UUID PRIMARY KEY,
course_id UUID REFERENCES courses(id),
user_id UUID REFERENCES users(id),
user_rating INTEGER CHECK(user_rating > 1 AND user_rating <= 5),
message VARCHAR(1000)
);