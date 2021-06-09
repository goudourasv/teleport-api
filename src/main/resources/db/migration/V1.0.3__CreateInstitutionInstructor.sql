CREATE TABLE institution_instructor (
institution_id UUID REFERENCES institutions(id),
instructor_id UUID REFERENCES instructors(id)
)