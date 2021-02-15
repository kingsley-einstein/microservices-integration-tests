const { v4: uuid } = require("uuid");
const jwt = require("jsonwebtoken");
const db = require("../db");

module.exports = class Controller {
  async create(req, res) {
    try {
      const data = await db._collections.add(
        Object.assign({}, req.body, { id: uuid() })
      );
      const token = jwt.sign(
        data,
        "this is a hardcoded secret. Don't try this at home"
      );
      return res.status(201).json(Object.assign({}, data, { token }));
    } catch (error) {
      return res.status(500).json({
        message: error.message
      });
    }
  }

  // async signIn(req, res) {
  //   try {
  //     const data = await db._collections.getById(req.query.id);
  //     const token = jwt.sign(
  //       data,
  //       "this is a hardcoded secret. Do not try this at home"
  //     );
  //     return res.status(200).json(Object.assign({}, data, { token }));
  //   } catch (error) {
  //     return res.status(500).json({
  //       message: error.message
  //     });
  //   }
  // }

  async getById(req, res) {
    try {
      const data = await db._collections.getById(req.params["id"]);

      if (!data) throw new Error("User with specified id not found");

      return res.status(200).json(data);
    } catch (error) {
      return res.status(500).json({
        message: error.message
      });
    }
  }

  async getDeserializedUser(req, res) {
    try {
      const { user } = req;
      return res.status(200).json(user);
    } catch (error) {
      return res.status(500).json({
        message: error.message
      });
    }
  }
};
