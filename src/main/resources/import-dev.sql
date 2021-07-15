INSERT INTO institutions (id,name,country,city)
VALUES
('86664d56-71c6-4de6-9771-cb8e707c8674','Stanford','USA','California'),
('e21be850-20f7-4943-bd37-c226cbdc8c83','AUTH','Greece','Thessaloniki'),
('daad559f-4755-4d8a-9d3c-5e039e2ceb2f','UOM','Greece','Thessaloniki'),
('0ba2fe42-b199-4584-baf8-0059199eaaa1','LSE','England','London');

INSERT INTO courses (id,title,start_date,end_date,institution_id,instructor_id)
VALUES
('e5ca80f3-2e37-4bf6-bcc8-b506d3e62b00','Programming methodology','2021-05-27T15:13:22Z','2021-05-27T15:13:22Z','86664d56-71c6-4de6-9771-cb8e707c8674','278553ff-c001-4ac3-a5ea-71141e855704'),
('2c3b2709-73ba-47f2-b4e2-3f0979ea0600','Electrical engines','2021-05-27T15:13:22Z','2021-05-27T15:13:22Z','e21be850-20f7-4943-bd37-c226cbdc8c83','7ce6be58-4eb1-4ff1-b470-a34c2fc54687'),
('d946bc18-e92a-407b-980c-301c2bf3b44b','Marketing','2021-05-27T15:13:22Z','2021-05-27T15:13:22Z','daad559f-4755-4d8a-9d3c-5e039e2ceb2f','c65abe61-fa7b-4fb8-aa17-f8103c12957a'),
('165f03a3-a4a3-48ca-8c8d-78ea591194cb','Statistics','2021-05-27T15:13:22Z','2021-05-27T15:13:22Z','0ba2fe42-b199-4584-baf8-0059199eaaa1','7c30c894-cd0c-4c57-9d11-e6fd2913514b'),
('24c74444-fadb-4e91-8604-f299ad6189ed','SAE 1','2021-05-27T15:13:22Z','2021-05-27T15:13:22Z','e21be850-20f7-4943-bd37-c226cbdc8c83','7ce6be58-4eb1-4ff1-b470-a34c2fc54687');

INSERT INTO instructors(id,first_name,last_name)
VALUES
('278553ff-c001-4ac3-a5ea-71141e855704','Mehran','Sahami'),
('7ce6be58-4eb1-4ff1-b470-a34c2fc54687','Nikolaos','Jabbour'),
('c65abe61-fa7b-4fb8-aa17-f8103c12957a','Rodoula','Tsiotsiou'),
('7c30c894-cd0c-4c57-9d11-e6fd2913514b','Alex','Taylor');

INSERT INTO institution_instructor(institution_id,instructor_id)
VALUES
('86664d56-71c6-4de6-9771-cb8e707c8674','278553ff-c001-4ac3-a5ea-71141e855704'),
('e21be850-20f7-4943-bd37-c226cbdc8c83','7ce6be58-4eb1-4ff1-b470-a34c2fc54687'),
('daad559f-4755-4d8a-9d3c-5e039e2ceb2f','c65abe61-fa7b-4fb8-aa17-f8103c12957a'),
('0ba2fe42-b199-4584-baf8-0059199eaaa1','7c30c894-cd0c-4c57-9d11-e6fd2913514b');

INSERT INTO lectures(id,title,start_time,end_time,course_id)
VALUES
('222c6686-d6dd-4b29-83c7-36abca11f146','Lecture 1','2021-07-15 09:51:26.077768+00','2021-07-15 19:51:26.077768+00','e5ca80f3-2e37-4bf6-bcc8-b506d3e62b00'),
('caeb27ae-e1c1-4104-aad7-ada1e44210ad','Lecture 2','2021-07-16T15:13:22Z','2021-07-16T16:13:22Z','e5ca80f3-2e37-4bf6-bcc8-b506d3e62b00'),
('8ca2ae61-e894-4e49-bd9c-6118bb4d3c24','Lecture 1','2021-07-15 09:51:26.077768+00','2021-07-15 19:51:26.077768+00','2c3b2709-73ba-47f2-b4e2-3f0979ea0600'),
('acff9ee1-abc7-4cda-923a-4659222e1cad','Lecture 2','2021-07-16T15:13:22Z','2021-07-16T16:13:22Z','2c3b2709-73ba-47f2-b4e2-3f0979ea0600'),
('9a04d66e-407c-463d-a41a-01bf2270d794','Lecture 1','2021-07-15 09:51:26.077768+00','2021-07-15 19:51:26.077768+00','d946bc18-e92a-407b-980c-301c2bf3b44b'),
('1e8a2658-4f2b-4e8a-923a-492a0e5f96f7','Lecture 2','2021-06-04T15:13:22Z','2021-06-04T16:13:22Z','d946bc18-e92a-407b-980c-301c2bf3b44b'),
('a030dc4e-6448-4d0f-b550-077bca64f533','Lecture 1','2021-07-15 09:51:26.077768+00','2021-05-27T16:13:22Z','165f03a3-a4a3-48ca-8c8d-78ea591194cb'),
('6e7e34e0-56a5-45f6-a628-54f9d275ae24','Lecture 2','2021-06-04T15:13:22Z','2021-06-04T16:13:22Z','165f03a3-a4a3-48ca-8c8d-78ea591194cb');

INSERT INTO tags(name)
VALUES
('Software'),
('Engineering'),
('Economics'),
('Mathematics'),
('Physics'),
('Statistics');

INSERT INTO course_tag(course_id,tag)
VALUES
('e5ca80f3-2e37-4bf6-bcc8-b506d3e62b00','Software'),
('2c3b2709-73ba-47f2-b4e2-3f0979ea0600','Engineering'),
('d946bc18-e92a-407b-980c-301c2bf3b44b','Economics'),
('165f03a3-a4a3-48ca-8c8d-78ea591194cb','Economics'),
('24c74444-fadb-4e91-8604-f299ad6189ed','Engineering'),
('165f03a3-a4a3-48ca-8c8d-78ea591194cb','Mathematics'),
('e5ca80f3-2e37-4bf6-bcc8-b506d3e62b00','Engineering');




