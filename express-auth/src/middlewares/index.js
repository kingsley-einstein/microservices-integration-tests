const jwt = require("jsonwebtoken");

module.exports.authenticate = (req, res, next) => {
  try {
    const { authorization } = req.headers;

    if (!authorization) throw new Error("Authorization header is required");

    const token = authorization.split(" ")[1];

    // console.log(authorization);

    if (!token) throw new Error("Token is not detected in auth header");

    req.user = jwt.decode(token);
    next();
  } catch (error) {
    return res.status(500).json({
      message: error.message
    });
  }
};
