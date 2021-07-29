CREATE TABLE courses_users(
course_id UUID  REFERENCES courses(id),
user_id UUID REFERENCES users(id)
);