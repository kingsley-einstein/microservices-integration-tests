const test = require("ava").default;
const request = require("supertest");

const app = require("..");

const __PREFIX__ = "/api/v1";
// let id = "";

test("App is initialized", t => {
  t.not(app, null);
});

test("Users can register", async t => {
  const res = await request(app)
    .post(__PREFIX__ + "/create")
    .send({
      name: "Jeremias Silas",
      age: 28,
      role: "C.T.O",
      programming_languages: ["Java", "JavaScript", "Typescript", "Rust"]
    });
  // id = res.body.id;
  t.is(res.statusCode, 201);
});

test("App can deserialize user from token", async t => {
  const res1 = await request(app)
    .post(__PREFIX__ + "/create")
    .send({
      name: "Ladislao Brent",
      age: 28,
      role: "C.T.O",
      programming_languages: ["Java", "JavaScript", "Typescript", "Rust"]
    });
  const res2 = await request(app)
    .get(__PREFIX__ + "/deserialize")
    .set("Authorization", `Bearer ${res1.body.token}`);
  console.log(res2.body);
  t.is(res2.statusCode, 200);
});
