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


