const router = require("express").Router();
const C = require("../controllers");
const { authenticate } = require("../middlewares");

const controller = new C();

router.post("/create", controller.create);
router.get("/get/:id", controller.getById);
// router.get("/signIn", controller.signIn);
router.get("/deserialize", authenticate, controller.getDeserializedUser);

module.exports = router;
