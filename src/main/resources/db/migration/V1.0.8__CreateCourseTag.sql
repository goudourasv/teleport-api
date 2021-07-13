CREATE TABLE course_tag (
course_id UUID REFERENCES courses(id),
tag VARCHAR(200) REFERENCES tags(name)
)