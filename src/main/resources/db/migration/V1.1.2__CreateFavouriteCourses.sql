CREATE TABLE favourite_course(
course_id UUID  REFERENCES courses(id),
user_id UUID REFERENCES users(id)
);