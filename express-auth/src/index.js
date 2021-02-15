const express = require("express");
const logger = require("morgan");
const api = require("./api");

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(logger("dev"));
app.use("/api/v1/", api);

const PORT = parseInt(process.env.PORT || "7880");

app.listen(PORT, () =>
  console.log(`Express server is running on port: ${PORT}`)
);

module.exports = app;
